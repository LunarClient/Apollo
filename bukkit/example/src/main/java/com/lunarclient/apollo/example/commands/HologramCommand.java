package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
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
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Usage: /hologram <display|remove|reset>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "display": {
                this.hologramExample.displayHologramExample();
                player.sendMessage("Displaying hologram....");
                break;
            }

            case "remove": {
                this.hologramExample.removeHologramExample();
                player.sendMessage("Removing hologram....");
                break;
            }

            case "reset": {
                this.hologramExample.resetHologramsExample(player);
                player.sendMessage("Resetting holograms...");
                break;
            }

            default: player.sendMessage("Usage: /hologram <display|remove|reset>");
        }

        return true;
    }
}
