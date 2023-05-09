package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.ColoredFireExample;
import com.lunarclient.apollo.example.modules.NametagExample;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NametagCommand implements CommandExecutor {

    private final NametagExample nametagExample = ApolloExamplePlugin.getPlugin().getNametagExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /nametag <override|reset|clear>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "override" -> {
                this.nametagExample.overrideNametagExample(player);
                player.sendMessage("Overriding nametag....");
            }

            case "reset" -> {
                this.nametagExample.resetNametagExample(player);
                player.sendMessage("Resetting nametag....");
            }

            case "clear" -> {
                this.nametagExample.resetNametagsExample(player);
                player.sendMessage("Clearing nametags...");
            }

            default -> player.sendMessage("Usage: /nametag <override|reset|clear>");
        }

        return true;
    }
}
