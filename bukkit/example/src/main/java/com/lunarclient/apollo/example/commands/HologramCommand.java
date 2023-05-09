package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.CooldownExample;
import com.lunarclient.apollo.example.modules.HologramExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HologramCommand implements CommandExecutor {

    private final HologramExample hologramExample = ApolloExamplePlugin.getPlugin().getHologramExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /hologram <display|remove|reset>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "display" -> {
                this.hologramExample.displayHologramExample();
                player.sendMessage("Displaying cooldown....");
            }

            case "remove" -> {
                this.hologramExample.removeHologramExample();
                player.sendMessage("Removing cooldown....");
            }
            case "reset" -> {
                this.hologramExample.resetHologramsExample(player);
                player.sendMessage("Resetting cooldowns...");
            }

            default -> player.sendMessage("Usage: /hologram <display|remove|reset>");
        }

        return true;
    }
}
