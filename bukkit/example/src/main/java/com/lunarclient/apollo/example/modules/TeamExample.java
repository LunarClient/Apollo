package com.lunarclient.apollo.example.modules;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.module.team.TeamMember;
import com.lunarclient.apollo.module.team.TeamModule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class TeamExample implements Listener {

    private final TeamModule teamModule = Apollo.getModuleManager().getModule(TeamModule.class);

    private final Map<UUID, Team> teamsByTeamId = Maps.newHashMap();
    private final Map<UUID, Team> teamsByPlayerUuid = Maps.newHashMap();

    public TeamExample() {
        new TeamUpdateTask();

        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getPlugin());
    }

    public Optional<Team> getByPlayerUuid(UUID playerUuid) {
        return Optional.ofNullable(this.teamsByPlayerUuid.get(playerUuid));
    }

    public Optional<Team> getByTeamId(UUID teamId) {
        return Optional.ofNullable(this.teamsByTeamId.get(teamId));
    }

    public Team createTeam() {
        var team = new Team();
        this.teamsByTeamId.put(team.getTeamId(), team);

        return team;
    }

    public void deleteTeam(UUID teamId) {
        var team = this.teamsByTeamId.remove(teamId);

        if (team != null) {
            team.getMembers().forEach(team::removeMember);
        }
    }

    @Getter
    @RequiredArgsConstructor
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

        public void refresh() {
            var teammates = this.members.stream().filter(Objects::nonNull)
                .map(member -> {
                    var location = member.getLocation();

                    return TeamMember.builder()
                        .playerUuid(member.getUniqueId())
                        .markerColor(Color.WHITE)
                        .location(ApolloLocation.builder()
                            .world(location.getWorld().getName())
                            .x(location.getX())
                            .y(location.getY())
                            .z(location.getZ())
                            .build())
                        .build();
                })
                .collect(Collectors.toList());

            this.members.forEach(member -> Apollo.getPlayerManager().getPlayer(member.getUniqueId())
                .ifPresent(apolloPlayer -> teamModule.updateTeamMembers(apolloPlayer, teammates)));
        }
    }

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
