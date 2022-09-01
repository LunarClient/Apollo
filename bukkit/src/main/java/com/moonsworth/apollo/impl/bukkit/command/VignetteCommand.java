package com.moonsworth.apollo.impl.bukkit.command;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.module.impl.EVNTModule;
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
            if(args[0].equals("reset")) {
                Apollo.getApolloModuleManager().getModule(EVNTModule.class)
                        .ifPresent(module -> module.displayVignette(apolloPlayer, null, 1.0f));
                sender.sendMessage(ChatColor.GREEN + "Reset vignette!");
                return true;
            }
            NamespacedKey textureLocation = NamespacedKey.fromString(args[0]);
            if (textureLocation == null) {
                sender.sendMessage(ChatColor.RED + "Please specify a valid texture location.");
                return true;
            }

            Apollo.getApolloModuleManager().getModule(EVNTModule.class)
                    .ifPresent(module -> module.displayVignette(apolloPlayer, textureLocation.toString(), 1.0f));
            sender.sendMessage(ChatColor.GREEN + "Displayed vignette!");
        }
        return true;
    }
}
