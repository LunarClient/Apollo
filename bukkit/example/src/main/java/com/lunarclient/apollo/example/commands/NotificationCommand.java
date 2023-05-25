package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.NotificationExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NotificationCommand implements CommandExecutor {

    private final NotificationExample notificationExample = ApolloExamplePlugin.getPlugin().getNotificationExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /notification <display|broadcast>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "display" -> {
                this.notificationExample.displayNotificationExample(player);
                player.sendMessage("Displaying notification....");
            }

            case "broadcast" -> {
                this.notificationExample.broadcastNotificationExample();
                player.sendMessage("Broadcasting notification....");
            }

            default -> player.sendMessage("Usage: /notification <display|broadcast>");
        }

        return true;
    }
}
