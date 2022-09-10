package com.moonsworth.apollo.impl.bukkit.command;

import com.google.common.primitives.Ints;
import com.google.protobuf.ByteString;
import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.bridge.ApolloBlockPos;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.module.impl.EVNTModule;
import com.moonsworth.apollo.api.module.impl.HeartTextureModule;
import com.moonsworth.apollo.api.module.impl.NotificationModule;
import com.moonsworth.apollo.api.module.impl.ServerRuleModule;
import com.moonsworth.apollo.api.protocol.CooldownMessage;
import com.moonsworth.apollo.api.protocol.RenderableIcon;
import com.moonsworth.apollo.impl.bukkit.ApolloBukkitPlatform;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VignetteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Please specify a vignette.");
            return true;
        }

        ApolloPlayer apolloPlayer = ApolloBukkitPlatform.getInstance().tryWrapPlayer(sender);
        if (apolloPlayer != null) {
            if (args[0].equals("reset")) {
                Apollo.getApolloModuleManager().getModule(EVNTModule.class)
                        .ifPresent(module -> module.displayVignette(apolloPlayer, "", 0.0f));
                sender.sendMessage(ChatColor.GREEN + "Reset vignette!");
                return true;
            }

            if (args.length == 2 && args[1].equals("health")) {
                Integer value = Ints.tryParse(args[1]);
                if (value == null) {
                    sender.sendMessage(ChatColor.RED + "Not a valid number, use -1 for removal!");
                    return true;
                }
                Apollo.getApolloModuleManager().getModule(HeartTextureModule.class).ifPresent(module -> {
                    if (value == -1) {
                        module.clearHeartLocation(apolloPlayer);
                        sender.sendMessage(ChatColor.GREEN + "Clear Custom Health!");
                    } else {
                        module.setHeartXLocationOverride(apolloPlayer, value, false);
                        sender.sendMessage(ChatColor.GREEN + "Set X Loc Custom Health!");
                    }
                });

            }
            if (args.length == 2 && args[0].equals("bright")) {
                Integer value = Ints.tryParse(args[1]);
                if (value == null) {
                    Boolean t = Boolean.parseBoolean(args[1]);
                    Apollo.getApolloModuleManager().getModule(ServerRuleModule.class).ifPresent(rule -> {
                        rule.getAffectBrightness().update(t);
                        sender.sendMessage(
                                ChatColor.YELLOW + "Setting value for brightness to " + rule.getAffectBrightness()
                                        .get());

                    });
                    return true;
                }
                Apollo.getApolloModuleManager().getModule(ServerRuleModule.class).ifPresent(rule -> {
                    rule.getBrightness().update(value);
                    sender.sendMessage(ChatColor.GREEN + "Setting value for brightness " + rule.getBrightness().get());

                });
                return true;
            }
            NamespacedKey textureLocation = NamespacedKey.fromString(args[0]);
            if (textureLocation == null) {
                sender.sendMessage(ChatColor.RED + "Please specify a valid texture location.");
                return true;
            }
            if (args.length == 4 && args[1].equals("notify")) {
                String title = args[2];
                String description = args[3];
                Apollo.getApolloModuleManager().getModule(NotificationModule.class).get()
                        .notifyAll(ChatColor.translateAlternateColorCodes('&', title),
                                ChatColor.translateAlternateColorCodes('&', description), textureLocation.toString());
                sender.sendMessage(ChatColor.GREEN + "Displayed notify!");

                return true;
            }
            if (args.length == 2 && args[1].equals("cooldown")) {
                apolloPlayer.sendPacket(
                        CooldownMessage.newBuilder().setDurationMs(8000).setName(ByteString.copyFromUtf8("Text"))
                                .setIconLocation(RenderableIcon.newBuilder()
                                        .setLocation(ByteString.copyFromUtf8(textureLocation.toString())).setSize(10)
                                        .build()).build());
                sender.sendMessage(ChatColor.GREEN + "Displayed cooldown!");
                return true;
            }

            Apollo.getApolloModuleManager().getModule(EVNTModule.class)
                    .ifPresent(module -> module.displayVignette(apolloPlayer, textureLocation.toString(), 1.0f));

            sender.sendMessage(ChatColor.GREEN + "Displayed vignette!");
        }
        return true;
    }
}
