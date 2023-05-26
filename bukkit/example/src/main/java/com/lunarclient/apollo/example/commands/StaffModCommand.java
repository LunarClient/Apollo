package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.StaffModExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StaffModCommand implements CommandExecutor {

    private final StaffModExample staffModExample = ApolloExamplePlugin.getPlugin().getStaffModExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /staffmod <enable|disable>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "enable" -> {
                this.staffModExample.enableStaffModsExample(player);
                player.sendMessage("Enabling staff mods....");
            }

            case "disable" -> {
                this.staffModExample.disableStaffModsExample(player);
                player.sendMessage("Disabling staff mods....");
            }

            default -> player.sendMessage("Usage: /staffmod <enable|disable>");
        }

        return true;
    }
}
