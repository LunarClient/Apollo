package com.lunarclient.apollo.example.modules;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.Component;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.module.team.TeamMember;
import com.lunarclient.apollo.module.team.TeamModule;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class TeamExample {

    private final TeamModule teamModule = Apollo.getModuleManager().getModule(TeamModule.class);

    private final Map<UUID, Team> teamsByTeamId = Maps.newHashMap();
    private final Map<UUID, Team> teamsByPlayerUuid = Maps.newHashMap();

    public TeamExample() {
        new TeamUpdateTask();
    }

    public Optional<Team> getByPlayerUuid(UUID playerUuid) {
        return Optional.ofNullable(this.teamsByPlayerUuid.get(playerUuid));
    }

    public Optional<Team> getByTeamId(UUID teamId) {
        return Optional.ofNullable(this.teamsByTeamId.get(teamId));
    }

    public Team createTeam() {
        Team team = new Team();
        this.teamsByTeamId.put(team.getTeamId(), team);

        return team;
    }

    public void deleteTeam(UUID teamId) {
        Team team = this.teamsByTeamId.remove(teamId);

        if (team != null) {
            team.getMembers().forEach(team::removeMember);
        }
    }

    public class Team {

        private final UUID teamId;
        private final Set<Player> members;

        public Team() {
            this.teamId = UUID.randomUUID();
            this.members = Sets.newHashSet();
        }

        public void addMember(Player player) {
            this.members.add(player);
            teamsByPlayerUuid.put(player.getUniqueId(), this);
        }

        public void removeMember(Player player) {
            this.members.remove(player);
            teamsByPlayerUuid.remove(player.getUniqueId());

            Apollo.getPlayerManager().getPlayer(player.getUniqueId())
                .ifPresent(teamModule::resetTeamMembers);
        }

        private TeamMember createTeamMember(Player member) {
            Location location = member.getLocation();

            return TeamMember.builder()
                .playerUuid(member.getUniqueId())
                .displayName(Component.builder()
                    .content(member.getName())
                    .color(Color.WHITE)
                    .build())
                .markerColor(Color.WHITE)
                .location(ApolloLocation.builder()
                    .world(location.getWorld().getName())
                    .x(location.getX())
                    .y(location.getY())
                    .z(location.getZ())
                    .build())
                .build();
        }

        // The refresh method used for updating members locations
        public void refresh() {
            List<TeamMember> teammates = this.members.stream().filter(Player::isOnline)
                .map(this::createTeamMember)
                .collect(Collectors.toList());

            this.members.forEach(member -> Apollo.getPlayerManager().getPlayer(member.getUniqueId())
                .ifPresent(apolloPlayer -> teamModule.updateTeamMembers(apolloPlayer, teammates)));
        }

        public UUID getTeamId() {
            return this.teamId;
        }

        public Set<Player> getMembers() {
            return this.members;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || other.getClass() != this.getClass()) return false;

            Team team = (Team) other;
            return this.teamId.equals(team.getTeamId());
        }

        @Override
        public int hashCode() {
            return this.teamId.hashCode();
        }
    }

    // Updates players location every 2 ticks (100ms)
    public class TeamUpdateTask extends BukkitRunnable {

        public TeamUpdateTask() {
            this.runTaskTimerAsynchronously(ApolloExamplePlugin.getPlugin(), 2L, 2L);
        }

        @Override
        public void run() {
            teamsByTeamId.values().forEach(Team::refresh);
        }
    }
}
