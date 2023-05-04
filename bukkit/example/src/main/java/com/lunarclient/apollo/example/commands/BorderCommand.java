package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.BorderExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BorderCommand implements CommandExecutor {

    private final BorderExample borderExample = ApolloExamplePlugin.getPlugin().getBorderExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /border <display|remove|reset>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "display" -> {
                this.borderExample.displayBorderExample(player);
                player.sendMessage("Displaying border....");
            }

            case "remove" -> {
                this.borderExample.removeBorderExample(player);
                player.sendMessage("Removing border....");
            }
            case "reset" -> {
                this.borderExample.resetBordersExample(player);
                player.sendMessage("Reseting borders...");
            }

            default -> player.sendMessage("Usage: /border <display|remove|reset>");
        }

        return true;
    }
}
