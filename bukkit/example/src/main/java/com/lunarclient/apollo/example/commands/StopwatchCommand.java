package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.StopwatchExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StopwatchCommand implements CommandExecutor {

    private final StopwatchExample stopwatchExample = ApolloExamplePlugin.getPlugin().getStopwatchExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /stopwatch <start|stop|reset>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "start" -> {
                this.stopwatchExample.startStopwatchExample(player);
                player.sendMessage("Starting stopwatch....");
            }

            case "stop" -> {
                this.stopwatchExample.stopStopwatchExample(player);
                player.sendMessage("Stopping stopwatch....");
            }

            case "reset" -> {
                this.stopwatchExample.resetStopwatchExample(player);
                player.sendMessage("Resetting stopwatch....");
            }

            default -> player.sendMessage("Usage: /stopwatch <start|stop|reset>");
        }

        return true;
    }
}
