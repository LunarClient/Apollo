/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.example.modules;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.module.team.TeamMember;
import com.lunarclient.apollo.module.team.TeamModule;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamExample implements Listener {

    private final TeamModule teamModule = Apollo.getModuleManager().getModule(TeamModule.class);

    private final Map<UUID, Team> teamsByTeamId = Maps.newHashMap();
    private final Map<UUID, Team> teamsByPlayerUuid = Maps.newHashMap();

    public TeamExample() {
        new TeamUpdateTask();

        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getPlugin());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        this.getByPlayerUuid(player.getUniqueId()).ifPresent(team -> {
            if (team.getMembers().size() == 1) {
                this.deleteTeam(team.getTeamId());
            }
        });
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
            TeamExample.this.teamsByPlayerUuid.put(player.getUniqueId(), this);
        }

        public void removeMember(Player player) {
            this.members.remove(player);
            TeamExample.this.teamsByPlayerUuid.remove(player.getUniqueId());

            Apollo.getPlayerManager().getPlayer(player.getUniqueId())
                .ifPresent(TeamExample.this.teamModule::resetTeamMembers);
        }

        private TeamMember createTeamMember(Player member) {
            Location location = member.getLocation();

            return TeamMember.builder()
                .playerUuid(member.getUniqueId())
                .displayName(Component.text()
                    .content(member.getName())
                    .color(NamedTextColor.WHITE)
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
                .ifPresent(apolloPlayer -> TeamExample.this.teamModule.updateTeamMembers(apolloPlayer, teammates)));
        }

        public UUID getTeamId() {
            return this.teamId;
        }

        public Set<Player> getMembers() {
            return this.members;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }

            if (other == null || other.getClass() != this.getClass()) {
                return false;
            }

            Team team = (Team) other;
            return this.teamId.equals(team.getTeamId());
        }

        @Override
        public int hashCode() {
            return this.teamId.hashCode();
        }
    }

    // Updates players location every 1 tick (50ms)
    public class TeamUpdateTask extends BukkitRunnable {

        public TeamUpdateTask() {
            this.runTaskTimerAsynchronously(ApolloExamplePlugin.getPlugin(), 1L, 1L);
        }

        @Override
        public void run() {
            TeamExample.this.teamsByTeamId.values().forEach(Team::refresh);
        }
    }
}
