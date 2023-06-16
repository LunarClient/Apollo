package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.CooldownExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CooldownCommand implements CommandExecutor {

    private final CooldownExample cooldownExample = ApolloExamplePlugin.getPlugin().getCooldownExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Usage: /cooldown <display|remove|reset>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "display": {
                this.cooldownExample.displayCooldownExample(player);
                player.sendMessage("Displaying cooldown....");
                break;
            }

            case "remove": {
                this.cooldownExample.removeCooldownExample(player);
                player.sendMessage("Removing cooldown....");
                break;
            }

            case "reset": {
                this.cooldownExample.resetCooldownsExample(player);
                player.sendMessage("Resetting cooldowns...");
            }

            default: player.sendMessage("Usage: /cooldown <display|remove|reset>");
        }

        return true;
    }
}
