package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.google.protobuf.ByteString;
import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.impl.player.EventApolloPlayerUnregister;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.protocol.Teammate;
import com.moonsworth.apollo.api.protocol.TeammateMessage;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.*;

public class TeammatesModule extends ApolloModule {

    private final Map<UUID, ApolloTeam> teams = new HashMap<>();
    // ApolloPlayer -> TeamId
    private final Map<UUID, UUID> playerTeamMap = new HashMap<>();

    public TeammatesModule() {
        super("TeammatesModule");
        handle(EventApolloPlayerUnregister.class, event -> {
            if (!playerTeamMap.containsKey(event.getPlayer().getUniqueId())) {
                return;
            }
            var teamId = playerTeamMap.remove(event.getPlayer().getUniqueId());
            if (!teams.containsKey(teamId)) {
                return;
            }
            ApolloTeam team = teams.get(teamId);
            team.getPlayerTeammateMap().remove(event.getPlayer().getUniqueId());
            if (team.getPlayerTeammateMap().isEmpty()) {
                teams.remove(teamId);
            }
        });
    }

    @Override
    public List<ApolloOption<?>> options() {
        return new ArrayList<>();
    }

    @Override
    public boolean notifyPlayers() {
        return true;
    }

    @Override
    public List<ApolloPlatform.Kind> runsOn() {
        return ImmutableList.of(ApolloPlatform.Kind.SERVER);
    }

    /**
     * Calls to update all teams for all players.
     */
    public void updateAllTeams() {
        for(var value : teams.values()) {
            value.refresh();
        }
    }

    public ApolloTeam createTeam() {
        return this.createTeam(Collections.emptyList());
    }

    /**
     * Creates a team instance for the API to track.
     *
     * @param apolloPlayers The list of players in the team.
     * @return The newly created team object.
     */
    public ApolloTeam createTeam(List<ApolloPlayer> apolloPlayers) {
        var team = new ApolloTeam(UUID.randomUUID());

        for(var apolloPlayer : apolloPlayers) {
            team.addMemberNoRefresh(apolloPlayer, 0xFF00AA80);
        }

        if(!apolloPlayers.isEmpty()) {
            team.refresh();
        }

        this.teams.put(team.getTeamId(), team);
        return team;
    }

    /**
     * Removes all the teams currently tracked.
     */
    public void clearTeams() {
        teams.clear();
    }

    /**
     * Deletes a team and all the players associated with it
     * @param team The team to delete
     */
    public void deleteTeam(ApolloTeam team) {
        deleteTeam(team.getTeamId());
    }

    /**
     * Deletes a team and all the players associated with it
     * @param teamId The team to delete
     */
    public void deleteTeam(UUID teamId) {
        teams.remove(teamId);
        playerTeamMap.values().remove(teamId);
    }

    @Data
    @RequiredArgsConstructor
    public static class ApolloTeam {

        private final UUID teamId;
        private Map<UUID, Teammate> playerTeammateMap = new HashMap<>();

        /**
         * Refresh all the locations of all the players in the team.
         * Remove old entries.
         */
        public void refresh() {
            var toRemove = new HashSet<UUID>();
            var message = TeammateMessage.newBuilder();

            for(var entry : playerTeammateMap.entrySet()) {
                var apolloPlayer = Apollo.getApolloPlayerManager().getApolloPlayer(entry.getKey());

                apolloPlayer.ifPresentOrElse(player -> {
                    var packet = entry.getValue().toBuilder();
                    var location = player.getWorldLocation();

                    packet.setX(location.getX());
                    packet.setY(location.getY());
                    packet.setZ(location.getZ());
                    packet.setWorld(ByteString.copyFromUtf8(location.getWorld()));

                    message.addTeammates(packet);
                }, () -> toRemove.add(entry.getKey()));
            }

            playerTeammateMap.keySet().removeAll(toRemove);
            sendUpdatedInfo(message.build());
        }

        /**
         * Sends updated message to the entire team.
         *
         * @param message The message to send to the team.
         */
        private void sendUpdatedInfo(TeammateMessage message) {
            for (Map.Entry<UUID, Teammate> entry : playerTeammateMap.entrySet()) {
                Apollo.getApolloPlayerManager().getApolloPlayer(entry.getKey())
                        .ifPresent(apolloPlayer -> apolloPlayer.sendPacket(message));
            }
        }

        /**
         * Adds a new member to the team.
         * This will force a refresh to all player locations.
         *
         * @param player The new team member
         * @param color  The color to assoicate with this player.
         *               0xFF00AA80 is the standard color.
         */
        public void addMember(ApolloPlayer player, int color) {
            addMemberNoRefresh(player, color);
            refresh();
        }

        public void removeMember(ApolloPlayer player) {
            playerTeammateMap.remove(player.getUniqueId());

            Apollo.getApolloModuleManager().getModule(TeammatesModule.class)
                    .ifPresent(module -> module.playerTeamMap.remove(player.getUniqueId()));
        }

        /**
         * Updates the player's color
         * This does NOT call a refresh.
         *
         * @param player The player to update
         * @param color  The new color
         */
        public void updatePlayerColor(ApolloPlayer player, int color) {
            var teammate = playerTeammateMap.get(player.getUniqueId());

            if(teammate != null) {
                playerTeammateMap.put(player.getUniqueId(), teammate.toBuilder().setColor(color).build());
            }
        }

        protected void addMemberNoRefresh(ApolloPlayer player, int color) {
            var teammate = Teammate.newBuilder()
                    .setPlayer(ByteString.copyFromUtf8(player.getUniqueId().toString()))
                    .setColor(color)
                    .build();

            playerTeammateMap.put(player.getUniqueId(), teammate);

            Apollo.getApolloModuleManager().getModule(TeammatesModule.class)
                    .ifPresent(module -> module.playerTeamMap.put(player.getUniqueId(), teamId));
        }
    }
}
