package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.HologramExample;
import com.lunarclient.apollo.example.modules.LimbExample;
import com.lunarclient.apollo.module.limb.LimbModule;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LimbCommand implements CommandExecutor {

    private final LimbExample limbExample = ApolloExamplePlugin.getPlugin().getLimbExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        if (args.length != 2) {
            player.sendMessage("Usage: /limb <hideArmor|resetArmor|hideBody|resetBody> <player>");
            return true;
        }

        var target = Bukkit.getPlayer(args[1]);

        if(target == null) {
            player.sendMessage("Player '" + args[1] + "' not found!");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "hidearmor" -> {
                this.limbExample.hideArmorExample(player, target);
                player.sendMessage("Hiding armor....");
            }

            case "resetarmor" -> {
                this.limbExample.resetArmorExample(player, target);
                player.sendMessage("Resetting armor....");
            }

            case "hidebody" -> {
                this.limbExample.hideBodyExample(player, target);
                player.sendMessage("Hiding body....");
            }

            case "resetbody" -> {
                this.limbExample.resetBodyExample(player, target);
                player.sendMessage("Resetting body....");
            }

            default -> player.sendMessage("Usage: /limb <hideArmor|resetArmor|hideBody|resetBody>");
        }

        return true;
    }
}
