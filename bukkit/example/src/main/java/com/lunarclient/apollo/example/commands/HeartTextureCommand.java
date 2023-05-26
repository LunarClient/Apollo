package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.HeartTextureExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HeartTextureCommand implements CommandExecutor {

    private final HeartTextureExample heartTextureExample = ApolloExamplePlugin.getPlugin().getHeartTextureExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /hearttexture <override|reset>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "override" -> {
                this.heartTextureExample.overrideHeartTextureExample(player);
                player.sendMessage("Overriding heart texture....");
            }

            case "reset" -> {
                this.heartTextureExample.resetHeartTextureExample(player);
                player.sendMessage("Resetting heart texture....");
            }

            default -> player.sendMessage("Usage: /hearttexture <override|reset>");
        }

        return true;
    }
}
