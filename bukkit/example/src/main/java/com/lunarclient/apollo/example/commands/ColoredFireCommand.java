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
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1 && args[0].equalsIgnoreCase("clear")) {
            this.coloredFireExample.resetColoredFiresExample(player);
            player.sendMessage("Resetting colored fires...");
            return true;
        }

        if (args.length != 2) {
            player.sendMessage("Usage: /coloredfire <override|reset|clear> <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if(target == null) {
            player.sendMessage("Player '" + args[1] + "' not found!");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "override": {
                this.coloredFireExample.overrideColoredFireExample(target.getUniqueId());
                player.sendMessage("Displaying colored fire....");
                break;
            }

            case "reset": {
                this.coloredFireExample.resetColoredFireExample(target.getUniqueId());
                player.sendMessage("Resetting colored fire....");
                break;
            }

            default: player.sendMessage("Usage: /coloredfire <override|reset|clear> <player>");
        }

        return true;
    }
}
