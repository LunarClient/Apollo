package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.ModSettingsExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ModSettingsCommand implements CommandExecutor {

    private final ModSettingsExample modSettingsExample = ApolloExamplePlugin.getPlugin().getModSettingsExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Usage: /modsettings <send|reset|broadcast>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "send": {
                this.modSettingsExample.sendSettingsExample(player);
                player.sendMessage("Sending settings....");
                break;
            }

            case "reset": {
                this.modSettingsExample.resetSettingsExample(player);
                player.sendMessage("Resetting settings....");
                break;
            }

            case "broadcast": {
                this.modSettingsExample.broadcastSettingsExample();
                player.sendMessage("Broadcasting settings....");
                break;
            }

            default: player.sendMessage("Usage: /modsettings <send|reset|broadcast>");
        }

        return true;
    }
}
