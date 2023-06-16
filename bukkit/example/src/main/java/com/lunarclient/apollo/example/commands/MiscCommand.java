package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.MiscExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MiscCommand implements CommandExecutor {

    private final MiscExample miscExample = ApolloExamplePlugin.getPlugin().getMiscExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Usage: /misc <displayVignette|resetVignette|overrideSheep|resetSheep|flipEntities|resetFlippedEntities>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "displayvignette": {
                this.miscExample.displayVignetteExample(player);
                player.sendMessage("Displaying vignette....");
                break;
            }

            case "resetvignette": {
                this.miscExample.resetVignetteExample(player);
                player.sendMessage("Resetting vignette....");
                break;
            }

            case "overridesheep": {
                this.miscExample.overrideRainbowSheepExample(player);
                player.sendMessage("Overriding rainbow sheep....");
                break;
            }

            case "resetsheep": {
                this.miscExample.resetRainbowSheepExample(player);
                player.sendMessage("Resetting rainbow sheep....");
                break;
            }

            case "flipentities": {
                this.miscExample.flipEntityExample(player);
                player.sendMessage("Flipping entities....");
                break;
            }

            case "resetflippedentities": {
                this.miscExample.resetFlippedEntityExample(player);
                player.sendMessage("Resetting flipped entities....");
                break;
            }

            default: player.sendMessage("Usage: /misc <displayVignette|resetVignette|overrideSheep|resetSheep|flipEntities|resetFlippedEntities>");
        }

        return true;
    }
}
