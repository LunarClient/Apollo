package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
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
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Usage: /waypoint <display|remove|reset>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "display": {
                this.waypointExample.displayWaypointExample(player);
                player.sendMessage("Displaying waypoint....");
                break;
            }

            case "remove": {
                this.waypointExample.removeWaypointExample(player);
                player.sendMessage("Removing waypoint....");
                break;
            }
            case "reset": {
                this.waypointExample.resetWaypointsExample(player);
                player.sendMessage("Resetting waypoints...");
                break;
            }

            default: player.sendMessage("Usage: /waypoint <display|remove|reset>");
        }

        return true;
    }
}
