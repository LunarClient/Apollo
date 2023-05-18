package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.TntCountdownExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TntCountdownCommand implements CommandExecutor {

    private final TntCountdownExample tntCountdownExample = ApolloExamplePlugin.getPlugin().getTntCountdownExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /tntcountdown <override|set|clear>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "override" -> {
                this.tntCountdownExample.overrideTntCountdownExample(player);
                player.sendMessage("Overriding custom tnt countdown option...");
            }

            case "set" -> {
                this.tntCountdownExample.setTntCountdownExample();
                player.sendMessage("Setting custom tnt countdown option....");
            }

            case "clear" -> {
                this.tntCountdownExample.clearTntCountdownOptionExample();
                player.sendMessage("Removing custom tnt countdown option....");
            }

            default -> player.sendMessage("Usage: /tntcountdown <override|set|clear>");
        }

        return true;
    }

}
