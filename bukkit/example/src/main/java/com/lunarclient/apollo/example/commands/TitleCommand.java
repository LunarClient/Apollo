package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.TitleExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TitleCommand implements CommandExecutor {

    private final TitleExample titleExample = ApolloExamplePlugin.getPlugin().getTitleExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /title <display|broadcast|reset>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "display" -> {
                this.titleExample.displayTitleExample(player);
                player.sendMessage("Displaying title....");
            }

            case "broadcast" -> {
                this.titleExample.broadcastTitleExample();
                player.sendMessage("Broadcasting title....");
            }

            case "reset" -> {
                this.titleExample.resetTitlesExample(player);
                player.sendMessage("Resetting titles....");
            }

            default -> player.sendMessage("Usage: /title <display|broadcast|reset>");
        }

        return true;
    }
}
