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
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Usage: /title <display|broadcast|reset>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "display": {
                this.titleExample.displayTitleExample(player);
                player.sendMessage("Displaying title....");
                break;
            }

            case "broadcast": {
                this.titleExample.broadcastTitleExample();
                player.sendMessage("Broadcasting title....");
                break;
            }

            case "reset": {
                this.titleExample.resetTitlesExample(player);
                player.sendMessage("Resetting titles....");
                break;
            }

            default: player.sendMessage("Usage: /title <display|broadcast|reset>");
        }

        return true;
    }
}
