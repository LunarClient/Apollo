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
package com.lunarclient.apollo.example.json.module;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.json.util.AdventureUtil;
import com.lunarclient.apollo.example.json.util.JsonPacketUtil;
import com.lunarclient.apollo.example.json.util.JsonUtil;
import com.lunarclient.apollo.example.module.impl.TeamExample;
import java.awt.Color;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamJsonExample extends TeamExample implements Listener {

    private final Map<UUID, Team> teamsByTeamId = Maps.newHashMap();
    private final Map<UUID, Team> teamsByPlayerUuid = Maps.newHashMap();

    public TeamJsonExample() {
        new TeamUpdateTask();

        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getInstance());
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
            TeamJsonExample.this.teamsByPlayerUuid.put(player.getUniqueId(), this);
        }

        public void removeMember(Player player) {
            this.members.remove(player);
            TeamJsonExample.this.teamsByPlayerUuid.remove(player.getUniqueId());

            JsonObject message = new JsonObject();
            message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.team.v1.ResetTeamMembersMessage");

            JsonPacketUtil.sendPacket(player, message);
        }

        private JsonObject createTeamMember(Player member) {
            JsonObject message = new JsonObject();
            message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.team.v1.TeamMember");
            message.add("player_uuid", JsonUtil.createUuidObject(member.getUniqueId()));
            message.addProperty("adventure_json_player_name", AdventureUtil.toJson(
                Component.text()
                    .content(member.getName())
                    .color(NamedTextColor.WHITE)
                    .build()
            ));
            message.add("marker_color", JsonUtil.createColorObject(Color.WHITE));
            message.add("location", JsonUtil.createLocationObject(member.getLocation()));

            return message;
        }

        // The refresh method used for updating members locations
        public void refresh() {
            JsonArray teammates = this.members.stream().filter(Player::isOnline)
                .map(this::createTeamMember)
                .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);

            JsonObject message = new JsonObject();
            message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.team.v1.UpdateTeamMembersMessage");
            message.add("members", teammates);

            this.members.forEach(member -> JsonPacketUtil.sendPacket(member, message));
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
            this.runTaskTimerAsynchronously(ApolloExamplePlugin.getInstance(), 1L, 1L);
        }

        @Override
        public void run() {
            TeamJsonExample.this.teamsByTeamId.values().forEach(Team::refresh);
        }
    }

    @Override
    public void createTeam(Player player) {
        Optional<Team> teamOpt = this.getByPlayerUuid(player.getUniqueId());

        if (teamOpt.isPresent()) {
            player.sendMessage("You already have a team...");
            return;
        }

        Team team = this.createTeam();
        team.addMember(player);

        player.sendMessage("Creating team...");
    }

    @Override
    public void deleteTeam(Player player) {
        Optional<Team> teamOpt = this.getByPlayerUuid(player.getUniqueId());

        if (teamOpt.isPresent()) {
            this.deleteTeam(teamOpt.get().getTeamId());
            player.sendMessage("Deleting team...");
            return;
        }

        player.sendMessage("No team found...");
    }

    @Override
    public void addMember(Player player, Player target) {
        Optional<Team> teamOpt = this.getByPlayerUuid(player.getUniqueId());
        Optional<Team> targetTeamOpt = this.getByPlayerUuid(target.getUniqueId());

        if (targetTeamOpt.isPresent()) {
            player.sendMessage("Player " + target.getName() + " already has a team...");
            return;
        }

        if (teamOpt.isPresent()) {
            teamOpt.get().addMember(target);
            player.sendMessage("Added " + target.getName() + " to your team...");
            return;
        }

        player.sendMessage("No team found...");
    }

    @Override
    public void removeMember(Player player, Player target) {
        Optional<Team> teamOpt = this.getByPlayerUuid(player.getUniqueId());
        Optional<Team> targetTeamOpt = this.getByPlayerUuid(target.getUniqueId());

        if (!targetTeamOpt.isPresent()) {
            player.sendMessage("Player " + target.getName() + " doesn't have a team...");
            return;
        }

        if (teamOpt.isPresent()) {
            teamOpt.get().removeMember(target);
            player.sendMessage("Removed " + target.getName() + " from your team...");
            return;
        }

        player.sendMessage("No team found...");
    }

}
