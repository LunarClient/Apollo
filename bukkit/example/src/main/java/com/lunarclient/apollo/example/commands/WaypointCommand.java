package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.HologramExample;
import com.lunarclient.apollo.example.modules.WaypointExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WaypointCommand implements CommandExecutor {

    private final WaypointExample waypointExample = ApolloExamplePlugin.getPlugin().getWaypointExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /waypoint <display|remove|reset>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "display" -> {
                this.waypointExample.displayWaypointExample(player);
                player.sendMessage("Displaying waypoint....");
            }

            case "remove" -> {
                this.waypointExample.removeWaypointExample(player);
                player.sendMessage("Removing waypoint....");
            }
            case "reset" -> {
                this.waypointExample.resetWaypointsExample(player);
                player.sendMessage("Resetting waypoints...");
            }

            default -> player.sendMessage("Usage: /waypoint <display|remove|reset>");
        }

        return true;
    }
}
