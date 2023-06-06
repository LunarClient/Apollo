package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.ServerRuleExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ServerRuleCommand implements CommandExecutor {

    private final ServerRuleExample serverRuleExample = ApolloExamplePlugin.getPlugin().getServerRuleExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        if (args.length != 2) {
            player.sendMessage("Usage: /serverrule <antiportaltraps|overridenametagrenderdistance|nametagrenderdistance> <value>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "antiportaltraps" -> {
                boolean value = Boolean.parseBoolean(args[1]);
                this.serverRuleExample.setAntiPortalTraps(value);

                player.sendMessage("Anti portal traps rule has been set to " + value);
            }

            case "overridenametagrenderdistance" -> {
                boolean value = Boolean.parseBoolean(args[1]);
                this.serverRuleExample.setOverrideNametagRenderDistance(value);

                player.sendMessage("Override nametag render distance rule has been set to " + value);
            }

            case "nametagrenderdistance" -> {
                int value;

                try {
                    value = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage("Input must be a number for this rule.");
                    return true;
                }

                this.serverRuleExample.setNametagRenderDistanceExample(value);
                player.sendMessage("Nametag render distance has been set to " + value);
            }

            default -> player.sendMessage("Usage: /serverrule <antiportaltraps|overridenametagrenderdistance|nametagrenderdistance> <value>");
        }

        return true;
    }
}
