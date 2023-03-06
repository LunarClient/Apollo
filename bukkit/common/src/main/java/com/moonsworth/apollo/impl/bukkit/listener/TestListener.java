package com.moonsworth.apollo.impl.bukkit.listener;

import com.google.protobuf.ByteString;
import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.bridge.ApolloItemStack;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.module.impl.SaturationModule;
import com.moonsworth.apollo.api.module.impl.TeammatesModule;
import com.moonsworth.apollo.api.module.impl.WaypointModule;
import com.moonsworth.apollo.api.protocol.AddWaypointMessage;
import com.moonsworth.apollo.api.protocol.RemoveWaypointMessage;
import com.moonsworth.apollo.api.protocol.Waypoint;
import com.moonsworth.apollo.impl.bukkit.ApolloBukkitPlatform;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.util.UUID;

// TODO: remove before release!
public class TestListener implements Listener {

    private TeammatesModule.ApolloTeam teamA;
    private TeammatesModule.ApolloTeam teamB;

    private final ApolloBukkitPlatform plugin;

    public TestListener(ApolloBukkitPlatform plugin) {
        this.plugin = plugin;

        Apollo.using(TeammatesModule.class);
        Apollo.using(WaypointModule.class);
        Apollo.using(SaturationModule.class);

        Apollo.getApolloModuleManager().getModule(TeammatesModule.class).ifPresent(module -> {
            this.teamA = module.createTeam();
            this.teamB = module.createTeam();
        });

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            Apollo.getApolloModuleManager().getModule(TeammatesModule.class).ifPresent(TeammatesModule::updateAllTeams);
        }, 20L, 20L);
    }

    @EventHandler
    private void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        TeammatesModule.ApolloTeam team = message.equalsIgnoreCase("A") ?
            this.teamA : message.equalsIgnoreCase("B") ? this.teamB : null;
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        ApolloPlayer apolloPlayer = Apollo.getPlatform().tryWrapPlayer(player);

        if(apolloPlayer == null) {
            player.sendMessage("Apollo player is null");
            return;
        }


        if(message.equalsIgnoreCase("C")) {
            Apollo.getApolloModuleManager().getModule(TeammatesModule.class).ifPresent(TeammatesModule::updateAllTeams);
            player.sendMessage("Updated all teams");
            return;
        }

        if(message.equalsIgnoreCase("D")) {
            Apollo.getApolloModuleManager().getModule(WaypointModule.class).ifPresent(module -> {
                Waypoint waypoint = Waypoint.newBuilder()
                    .setName(ByteString.copyFromUtf8("Test"))
                    .setWorld(ByteString.copyFromUtf8("World"))
                    .build();

                Location location = player.getLocation();

                AddWaypointMessage addWaypoint = AddWaypointMessage.newBuilder()
                    .setWaypoint(waypoint)
                    .setX(location.getBlockX())
                    .setY(location.getBlockY())
                    .setZ(location.getBlockZ())
                    .setColor(Color.RED.getRGB())
                    .setForced(true)
                    .setVisible(true)
                    .build();

                apolloPlayer.sendPacket(addWaypoint);
                player.sendMessage("Sent add waypoint");
            });

            return;
        }

        if(message.equalsIgnoreCase("E")) {
            Apollo.getApolloModuleManager().getModule(WaypointModule.class).ifPresent(module -> {
                Waypoint waypoint = Waypoint.newBuilder()
                    .setName(ByteString.copyFromUtf8("Test"))
                    .setWorld(ByteString.copyFromUtf8("World"))
                    .build();

                RemoveWaypointMessage removeWaypoint = RemoveWaypointMessage.newBuilder()
                    .setWaypoint(waypoint)
                    .build();

                apolloPlayer.sendPacket(removeWaypoint);
                player.sendMessage("Sent remove waypoint");
            });

            return;
        }

        if(message.equalsIgnoreCase("F")) {
            player.sendMessage(this.teamA.toString());
            player.sendMessage(this.teamB.toString());
            return;
        }

        if(message.equalsIgnoreCase("G")) {
            ItemStack item = player.getInventory().getItemInMainHand();
            ApolloItemStack apolloItemStack = Apollo.getPlatform().getItemStack(item);

            Apollo.getApolloModuleManager().getModule(SaturationModule.class).ifPresent(module -> {
                if(module.hasCustomSaturation(apolloItemStack)) {
                    module.applyCustomSaturation(apolloItemStack, 10);
                    player.sendMessage(ChatColor.GREEN + "Added saturation");
                } else {
                    module.removeCustomSaturation(apolloItemStack);
                    player.sendMessage(ChatColor.RED + "Removed saturation");
                }

                player.getInventory().setItemInMainHand((ItemStack) apolloItemStack.get());
            });

            return;
        }

        if(team == null) return;

        if(team.isMember(uuid)) {
            team.removeMember(apolloPlayer);

            player.sendMessage(ChatColor.RED + "Removed player " + player.getName() + " from " + team.getTeamId());
        } else {
            team.addMember(apolloPlayer , 0xFF00AA80);

            player.sendMessage(ChatColor.GREEN + "Add player " + player.getName() + " to " + team.getTeamId());
        }
    }
}
