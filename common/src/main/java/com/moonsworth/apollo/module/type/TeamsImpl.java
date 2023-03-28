package com.moonsworth.apollo.module.type;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.event.player.ApolloUnregisterPlayerEvent;
import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.option.Options;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayerManager;
import com.moonsworth.apollo.player.ui.Team;
import com.moonsworth.apollo.protocol.LocationMessage;
import com.moonsworth.apollo.protocol.TeamMessage;
import com.moonsworth.apollo.world.ApolloLocation;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.awt.*;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Provides the teams module.
 *
 * @since 1.0.0
 */
public final class TeamsImpl extends Teams {

    private final Map<UUID, Team> teamsByTeamUuid;
    private final Map<UUID, Team> teamsByPlayerUuid;

    public TeamsImpl() {
        super();

        this.teamsByTeamUuid = Maps.newHashMap();
        this.teamsByPlayerUuid = Maps.newHashMap();

        this.handle(ApolloUnregisterPlayerEvent.class, this::onPlayerUnregister);

        NetworkOptions.register(Team.class, TeamMessage.getDefaultInstance(), new OptionConverter<Team, TeamMessage>() {
            @Override
            public TeamMessage to(final Team object) throws IllegalArgumentException {
                final OptionConverter<ApolloLocation, LocationMessage> locationConverter = NetworkOptions.get(ApolloLocation.class);

                final Map<String, TeamMessage.TeammateMessage> teammates = object.getTeammates().entrySet().stream()
                    .collect(Collectors.toMap(
                        entry -> entry.getKey().toString(),
                        entry -> TeamMessage.TeammateMessage.newBuilder()
                            .setColor(entry.getValue().getColor().getRGB())
                            .setLocation(locationConverter.to(entry.getValue().getLocation()))
                            .build()
                    ));

                return TeamMessage.newBuilder()
                    .setTeamId(object.getTeamId().toString())
                    .putAllMembers(teammates)
                    .build();
            }

            @Override
            public Team from(final TeamMessage message) throws IllegalArgumentException {
                final OptionConverter<ApolloLocation, LocationMessage> locationConverter = NetworkOptions.get(ApolloLocation.class);

                final Map<UUID, Team.Teammate> teammates = message.getMembersMap().entrySet().stream()
                    .collect(Collectors.toMap(
                        entry -> UUID.fromString(entry.getKey()),
                        entry -> Team.Teammate.of(
                            new Color(entry.getValue().getColor()),
                            locationConverter.from(entry.getValue().getLocation())
                        )
                    ));

                return Team.of(
                    UUID.fromString(message.getTeamId()),
                    teammates
                );
            }
        });
    }

    @Override
    public Team createTeam() {
        return this.createTeam(Maps.newHashMap());
    }

    @Override
    public Team createTeam(final Map<UUID, Team.Teammate> teammates) {
        requireNonNull(teammates, "teammates");

        final Team team = Team.of(UUID.randomUUID(), teammates);
        team.getTeammates().keySet().forEach(uuid -> this.teamsByPlayerUuid.put(uuid, team));

        this.teamsByTeamUuid.put(team.getTeamId(), team);

        this.refreshTeam(team);
        return team;
    }

    @Override
    public boolean deleteTeam(final Team team) {
        requireNonNull(team, "team");

        team.getTeammates().keySet().forEach(this.teamsByPlayerUuid::remove);
        return this.teamsByTeamUuid.remove(team.getTeamId()) != null;
    }

    @Override
    public Team getByPlayerUuid(final UUID uuid) {
        requireNonNull(uuid, "uuid");
        return this.teamsByPlayerUuid.get(uuid);
    }

    @Override
    public boolean addMember(final ApolloPlayer player, final Team team, final Team.Teammate teammate) {
        requireNonNull(team, "team");
        requireNonNull(teammate, "teammate");
        requireNonNull(player, "player");

        final UUID uuid = player.getUniqueId();
        final Team playerTeam = this.getByPlayerUuid(uuid);

        if(playerTeam != null) return false;

        team.getTeammates().put(uuid, teammate);

        if(this.getOptions().get(Teams.AUTOMATICALLY_REFRESH_ON_CHANGE)) {
            this.refreshTeam(team);
        }

        return true;
    }

    @Override
    public boolean removeMember(final ApolloPlayer player, final Team team) {
        requireNonNull(team, "team");
        requireNonNull(player, "player");

        final UUID uuid = player.getUniqueId();
        final Team playerTeam = this.getByPlayerUuid(uuid);

        if(!team.equals(playerTeam)) return false;

        team.getTeammates().remove(uuid);

        if(this.getOptions().get(Teams.AUTOMATICALLY_REFRESH_ON_CHANGE)) {
            this.refreshTeam(team);
        }

        return true;
    }

    @Override
    public Team getByTeamId(final UUID uuid) {
        requireNonNull(uuid, "uuid");
        return this.teamsByTeamUuid.get(uuid);
    }

    @Override
    public void refreshTeams() {
        this.teamsByTeamUuid.values().forEach(this::refreshTeam);
    }

    @Override
    public void refreshTeam(final Team team) {
        requireNonNull(team, "team");

        final ApolloPlayerManager playerManager = Apollo.getPlayerManager();
        final Options.Container options = this.getOptions();

        final Map<ApolloPlayer, Team.Teammate> teammates = team.getTeammates().entrySet().stream()
            .map(entry -> new ImmutablePair<>(
                playerManager.getPlayer(entry.getKey()),
                entry.getValue())
            )
            .filter(entry -> entry.getKey().isPresent())
            .collect(Collectors.toMap(
                entry -> entry.getKey().get(),
                Map.Entry::getValue
            ));

        teammates.forEach((player, teammate) -> player.getLocation().ifPresent(teammate::setLocation));
        teammates.forEach((player, teammate) -> options.set(player, null, Lists.newArrayList(team)));
    }

    public void onPlayerUnregister(final ApolloUnregisterPlayerEvent event) {
        final UUID uuid = event.getPlayer().getUniqueId();
        final Team team = this.teamsByPlayerUuid.remove(uuid);

        if(team != null) {
            final Map<UUID, Team.Teammate> teammates = team.getTeammates();
            teammates.remove(uuid);

            if(this.getOptions().get(Teams.DISBAND_TEAMS_IF_EMPTY) && teammates.isEmpty()) {
                this.deleteTeam(team);
            }
        }
    }
}
