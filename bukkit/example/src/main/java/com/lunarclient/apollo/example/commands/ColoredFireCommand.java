package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.ColoredFireExample;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ColoredFireCommand implements CommandExecutor {

    private final ColoredFireExample coloredFireExample = ApolloExamplePlugin.getPlugin().getColoredFireExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        var action = args[0].toLowerCase();

        if(args.length == 1 && action.equals("clear")) {
            this.coloredFireExample.resetColoredFiresExample(player);
            player.sendMessage("Reseting colored fires...");
            return true;
        }

        if (args.length != 2) {
            player.sendMessage("Usage: /coloredfire <override|reset|clear> <player>");
            return true;
        }

        var target = Bukkit.getPlayer(args[1]);

        if(target == null) {
            player.sendMessage("Player '" + args[1] + "' not found!");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "override" -> {
                this.coloredFireExample.overrideColoredFireExample(target.getUniqueId());
                player.sendMessage("Displaying colored fire....");
            }

            case "reset" -> {
                this.coloredFireExample.resetColoredFireExample(target.getUniqueId());
                player.sendMessage("Reseting colored fire....");
            }

            default -> player.sendMessage("Usage: /coloredfire <override|reset|clear> <player>");
        }

        return true;
    }
}
