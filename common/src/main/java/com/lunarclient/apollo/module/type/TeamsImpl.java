package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.event.player.ApolloUnregisterPlayerEvent;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayerManager;
import com.lunarclient.apollo.player.ui.Team;
import com.lunarclient.apollo.team.v1.TeamMember;
import com.lunarclient.apollo.team.v1.UpdateTeamMembersMessage;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
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

        List<TeamMember> members = teammates.entrySet().stream()
            .map(entry ->
                TeamMember.newBuilder()
                    .setPlayerUuid(NetworkTypes.toProtobuf(entry.getKey().getUniqueId()))
                    .setLocation(NetworkTypes.toProtobuf(entry.getValue().getLocation()))
                    .setMarkerColor(NetworkTypes.toProtobuf(entry.getValue().getColor()))
                    .build()
            )
            .collect(Collectors.toList());

        UpdateTeamMembersMessage message = UpdateTeamMembersMessage.newBuilder()
            .addAllMembers(members)
            .build();

        teammates.forEach((player, teammate) -> ((AbstractApolloPlayer) player).sendPacket(message));
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

}
