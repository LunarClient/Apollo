package com.moonsworth.apollo.impl.bukkit.command;

import com.moonsworth.apollo.impl.bukkit.listener.KnockbackListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class KnockbackCommand implements CommandExecutor {

    private void listDefaultValues(CommandSender sender) {
        sender.sendMessage("knockbackHorizontal: " + KnockbackListener.defaultKnockbackHorizontal);
        sender.sendMessage("knockbackVertical: " + KnockbackListener.defaultKnockbackVertical);
        sender.sendMessage("knockbackVerticalLimit: " + KnockbackListener.defaultKnockbackVerticalLimit);
        sender.sendMessage("knockbackExtraHorizontal: " + KnockbackListener.defaultKnockbackExtraHorizontal);
        sender.sendMessage("knockbackExtraVertical: " + KnockbackListener.defaultKnockbackExtraVertical);
    }

    private void listSetServerValues(CommandSender sender) {
        sender.sendMessage("knockbackHorizontal: " + KnockbackListener.knockbackHorizontal);
        sender.sendMessage("knockbackVertical: " + KnockbackListener.knockbackVertical);
        sender.sendMessage("knockbackVerticalLimit: " + KnockbackListener.knockbackVerticalLimit);
        sender.sendMessage("knockbackExtraHorizontal: " + KnockbackListener.knockbackExtraHorizontal);
        sender.sendMessage("knockbackExtraVertical: " + KnockbackListener.knockbackExtraVertical);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            listSetServerValues(sender);
            return true;
        }

        String key;
        if (args[0].equals("reset")) {
            if (args.length == 1) {
                KnockbackListener.knockbackHorizontal = KnockbackListener.defaultKnockbackHorizontal;
                KnockbackListener.knockbackVertical = KnockbackListener.defaultKnockbackVertical;
                KnockbackListener.knockbackVerticalLimit = KnockbackListener.defaultKnockbackVerticalLimit;
                KnockbackListener.knockbackExtraHorizontal = KnockbackListener.defaultKnockbackExtraHorizontal;
                KnockbackListener.knockbackExtraVertical = KnockbackListener.defaultKnockbackExtraVertical;

                listSetServerValues(sender);
                sender.sendMessage(ChatColor.GREEN + "Reset to defaults.");
                return true;
            } else {
                key = args[1];

                if (key.equalsIgnoreCase("knockbackHorizontal")) {
                    KnockbackListener.knockbackHorizontal = KnockbackListener.defaultKnockbackHorizontal;
                    sender.sendMessage("knockbackHorizontal: " + KnockbackListener.knockbackHorizontal);
                } else if (key.equalsIgnoreCase("knockbackVertical")) {
                    KnockbackListener.knockbackVertical = KnockbackListener.defaultKnockbackVertical;
                    sender.sendMessage("knockbackVertical: " + KnockbackListener.knockbackVertical);
                } else if (key.equalsIgnoreCase("knockbackVerticalLimit")) {
                    KnockbackListener.knockbackVerticalLimit = KnockbackListener.defaultKnockbackVerticalLimit;
                    sender.sendMessage("knockbackVerticalLimit: " + KnockbackListener.knockbackVerticalLimit);
                } else if (key.equalsIgnoreCase("knockbackExtraHorizontal")) {
                    KnockbackListener.knockbackExtraHorizontal = KnockbackListener.defaultKnockbackExtraHorizontal;
                    sender.sendMessage("knockbackExtraHorizontal: " + KnockbackListener.knockbackExtraHorizontal);
                } else if (key.equalsIgnoreCase("knockbackExtraVertical")) {
                    KnockbackListener.knockbackExtraVertical = KnockbackListener.defaultKnockbackExtraVertical;
                    sender.sendMessage("knockbackExtraVertical: " + KnockbackListener.knockbackExtraVertical);
                } else {
                    sender.sendMessage(ChatColor.RED + "Unknown key " + args[0]);
                    return true;
                }
                sender.sendMessage(ChatColor.GREEN + "Value reset.");
                return true;
            }
        } else if (args[0].equalsIgnoreCase("defaults")) {
            listDefaultValues(sender);
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "/" + label + " <key> <value>");
            return false;
        }

        key = args[0];
        double value = Double.parseDouble(args[1]);

        if (key.equalsIgnoreCase("knockbackHorizontal") || key.equalsIgnoreCase("kH")) {
            KnockbackListener.knockbackHorizontal = value;
            sender.sendMessage("knockbackHorizontal: " + KnockbackListener.knockbackHorizontal);
        } else if (key.equalsIgnoreCase("knockbackVertical") || key.equalsIgnoreCase("kV")) {
            KnockbackListener.knockbackVertical = value;
            sender.sendMessage("knockbackVertical: " + KnockbackListener.knockbackVertical);
        } else if (key.equalsIgnoreCase("knockbackVerticalLimit") || key.equalsIgnoreCase("kVL")) {
            KnockbackListener.knockbackVerticalLimit = value;
            sender.sendMessage("knockbackVerticalLimit: " + KnockbackListener.knockbackVerticalLimit);
        } else if (key.equalsIgnoreCase("knockbackExtraHorizontal") || key.equalsIgnoreCase("kEH")) {
            KnockbackListener.knockbackExtraHorizontal = value;
            sender.sendMessage("knockbackExtraHorizontal: " + KnockbackListener.knockbackExtraHorizontal);
        } else if (key.equalsIgnoreCase("knockbackExtraVertical") || key.equalsIgnoreCase("kEV")) {
            KnockbackListener.knockbackExtraVertical = value;
            sender.sendMessage("knockbackExtraVertical: " + KnockbackListener.knockbackExtraVertical);
        } else {
            sender.sendMessage(ChatColor.RED + "Unknown key " + args[0]);
            return true;
        }

        sender.sendMessage(ChatColor.YELLOW + "Value changed.");
        return true;
    }
}
