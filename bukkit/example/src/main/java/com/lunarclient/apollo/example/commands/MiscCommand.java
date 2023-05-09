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
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /misc <displayVignette|resetVignette|overrideSheep|resetSheep|flipEntities|resetFlippedEntities>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "displayvignette" -> {
                this.miscExample.displayVignetteExample(player);
                player.sendMessage("Displaying vignette....");
            }

            case "resetvignette" -> {
                this.miscExample.resetVignetteExample(player);
                player.sendMessage("Resetting vignette....");
            }

            case "overridesheep" -> {
                this.miscExample.overrideRainbowSheepExample(player);
                player.sendMessage("Overriding rainbow sheep....");
            }

            case "resetsheep" -> {
                this.miscExample.resetRainbowSheepExample(player);
                player.sendMessage("Resetting rainbow sheep....");
            }

            case "flipentities" -> {
                this.miscExample.flipEntityExample(player);
                player.sendMessage("Flipping entities....");
            }

            case "resetflippedentities" -> {
                this.miscExample.resetFlippedEntityExample(player);
                player.sendMessage("Resetting flipped entities....");
            }

            default -> player.sendMessage("Usage: /misc <displayVignette|resetVignette|overrideSheep|resetSheep|flipEntities|resetFlippedEntities>");
        }

        return true;
    }
}
