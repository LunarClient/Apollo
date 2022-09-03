package com.moonsworth.apollo.impl.bukkit.command;

import com.google.protobuf.ByteString;
import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.module.impl.CooldownModule;
import com.moonsworth.apollo.api.module.impl.EVNTModule;
import com.moonsworth.apollo.api.module.impl.NotificationModule;
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
            NamespacedKey textureLocation = NamespacedKey.fromString(args[0]);
            if (textureLocation == null) {
                sender.sendMessage(ChatColor.RED + "Please specify a valid texture location.");
                return true;
            }
            if (args.length == 4 && args[1].equals("notify")) {
                String title = args[2];
                String description = args[3];
                Apollo.getApolloModuleManager().getModule(NotificationModule.class).get().notifyAll(title, description, textureLocation.toString());
                sender.sendMessage(ChatColor.GREEN + "Displayed notify!");

                return true;
            }
            if (args.length == 2 && args[1].equals("cooldown")) {
                apolloPlayer.sendPacket(CooldownMessage.newBuilder().setDurationMs(8000).setName(ByteString.copyFromUtf8("Text")).setIconLocation(RenderableIcon.newBuilder().setLocation(ByteString.copyFromUtf8(textureLocation.toString())).setSize(10).build()).build());
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
