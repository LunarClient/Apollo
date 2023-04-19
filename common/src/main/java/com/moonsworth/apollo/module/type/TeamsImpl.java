package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.event.player.ApolloUnregisterPlayerEvent;
import com.moonsworth.apollo.network.NetworkTypes;
import com.moonsworth.apollo.option.Options;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayerManager;
import com.moonsworth.apollo.player.ui.Team;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lunarclient.apollo.modules.TeamMessage;

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

        this.teamsByTeamUuid = new HashMap<>();
        this.teamsByPlayerUuid = new HashMap<>();

        this.handle(ApolloUnregisterPlayerEvent.class, this::onPlayerUnregister);
    }

    @Override
    public Team createTeam() {
        return this.createTeam(new HashMap<>());
    }

    @Override
    public Team createTeam(Map<UUID, Team.Teammate> teammates) {
        requireNonNull(teammates, "teammates");

        Team team = Team.of(UUID.randomUUID(), teammates);
        team.getTeammates().keySet().forEach(uuid -> this.teamsByPlayerUuid.put(uuid, team));

        this.teamsByTeamUuid.put(team.getTeamId(), team);

        this.refreshTeam(team);
        return team;
    }

    @Override
    public boolean deleteTeam(Team team) {
        requireNonNull(team, "team");

        team.getTeammates().keySet().forEach(this.teamsByPlayerUuid::remove);
        return this.teamsByTeamUuid.remove(team.getTeamId()) != null;
    }

    @Override
    public Team fromPlayer(UUID uuid) {
        requireNonNull(uuid, "uuid");
        return this.teamsByPlayerUuid.get(uuid);
    }

    @Override
    public boolean addMember(ApolloPlayer player, Team team, Team.Teammate teammate) {
        requireNonNull(team, "team");
        requireNonNull(teammate, "teammate");
        requireNonNull(player, "player");

        UUID uuid = player.getUniqueId();
        Team playerTeam = this.fromPlayer(uuid);

        if(playerTeam != null) return false;

        team.getTeammates().put(uuid, teammate);

        if(this.getOptions().get(Teams.AUTOMATICALLY_REFRESH_ON_CHANGE)) {
            this.refreshTeam(team);
        }

        return true;
    }

    @Override
    public boolean removeMember(ApolloPlayer player, Team team) {
        requireNonNull(team, "team");
        requireNonNull(player, "player");

        UUID uuid = player.getUniqueId();
        Team playerTeam = this.fromPlayer(uuid);

        if(!team.equals(playerTeam)) return false;

        team.getTeammates().remove(uuid);

        if(this.getOptions().get(Teams.AUTOMATICALLY_REFRESH_ON_CHANGE)) {
            this.refreshTeam(team);
        }

        return true;
    }

    @Override
    public Team fromTeam(UUID uuid) {
        requireNonNull(uuid, "uuid");
        return this.teamsByTeamUuid.get(uuid);
    }

    @Override
    public void refreshTeams() {
        this.teamsByTeamUuid.values().forEach(this::refreshTeam);
    }

    @Override
    public void refreshTeam(Team team) {
        requireNonNull(team, "team");

        ApolloPlayerManager playerManager = Apollo.getPlayerManager();
        Options.Container options = this.getOptions();

        Map<ApolloPlayer, Team.Teammate> teammates = team.getTeammates().entrySet().stream()
            .map(entry -> new AbstractMap.SimpleImmutableEntry<>(
                playerManager.getPlayer(entry.getKey()),
                entry.getValue())
            )
            .filter(entry -> entry.getKey().isPresent())
            .collect(Collectors.toMap(
                entry -> entry.getKey().get(),
                Map.Entry::getValue
            ));

        teammates.forEach((player, teammate) -> player.getLocation().ifPresent(teammate::setLocation));
        teammates.forEach((player, teammate) -> options.set(player, null, Collections.singletonList(team)));
    }

    public void onPlayerUnregister(ApolloUnregisterPlayerEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        Team team = this.teamsByPlayerUuid.remove(uuid);

        if(team != null) {
            Map<UUID, Team.Teammate> teammates = team.getTeammates();
            teammates.remove(uuid);

            if(this.getOptions().get(Teams.DISBAND_TEAMS_IF_EMPTY) && teammates.isEmpty()) {
                this.deleteTeam(team);
            }
        }
    }

    public TeamMessage to(Team object) throws IllegalArgumentException {
        List<TeamMessage.TeammateMessage> teammates = object.getTeammates().entrySet().stream()
            .map(entry ->
                TeamMessage.TeammateMessage.newBuilder()
                    .setPlayerUuid(NetworkTypes.toUuid(entry.getKey()))
                    .setColor(NetworkTypes.toColor(entry.getValue().getColor()))
                    .setLocation(NetworkTypes.toLocation(entry.getValue().getLocation()))
                    .build()
            ).collect(Collectors.toList());

        return TeamMessage.newBuilder()
            .setTeamId(object.getTeamId().toString())
            .addAllMembers(teammates)
            .build();
    }

    public Team from(TeamMessage message) throws IllegalArgumentException {
        Map<UUID, Team.Teammate> teammates = message.getMembersList().stream()
            .collect(Collectors.toMap(
                teammate -> NetworkTypes.fromUuid(teammate.getPlayerUuid()),
                teammate -> Team.Teammate.of(
                    NetworkTypes.fromColor(teammate.getColor()),
                    NetworkTypes.fromLocation(teammate.getLocation())
                )
            ));

        return Team.of(
            UUID.fromString(message.getTeamId()),
            teammates
        );
    }
}
