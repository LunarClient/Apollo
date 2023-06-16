package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.TransferExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TransferCommand implements CommandExecutor {

    private final TransferExample transferExample = ApolloExamplePlugin.getPlugin().getTransferExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Usage: /transfer <transfer|ping>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "transfer": {
                this.transferExample.transferExample(player);
                player.sendMessage("Transferring player...");
                break;
            }

            case "ping": {
                this.transferExample.pingExample(player);
                player.sendMessage("Pinging....");
                break;
            }

            default: player.sendMessage("Usage: /transfer <transfer|ping>");
        }

        return true;
    }

}
