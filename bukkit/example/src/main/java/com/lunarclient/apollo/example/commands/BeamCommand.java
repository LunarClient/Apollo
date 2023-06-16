package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.BeamExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BeamCommand implements CommandExecutor {

    private final BeamExample beamExample = ApolloExamplePlugin.getPlugin().getBeamExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Usage: /beam <display|remove|reset>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "display": {
                this.beamExample.displayBeamExample(player);
                player.sendMessage("Displaying beam....");
                break;
            }

            case "remove": {
                this.beamExample.removeBeamExample(player);
                player.sendMessage("Removing beam....");
                break;
            }

            case "reset": {
                this.beamExample.resetBeamsExample(player);
                player.sendMessage("Resetting beams...");
                break;
            }

            default: player.sendMessage("Usage: /beam <display|remove|reset>");
        }

        return true;
    }
}
