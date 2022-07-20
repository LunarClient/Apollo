package com.moonsworth.apollo.impl.bukkit;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.network.PacketRegistry;
import com.moonsworth.apollo.impl.bukkit.command.KnockbackCommand;
import com.moonsworth.apollo.impl.bukkit.listener.ArmorDurabilityListener;
import com.moonsworth.apollo.impl.bukkit.listener.AttackSpeedListener;
import com.moonsworth.apollo.impl.bukkit.listener.KnockbackListener;
import com.moonsworth.apollo.impl.bukkit.listener.RegenListener;
import com.moonsworth.apollo.impl.bukkit.wrapper.BukkitPlayer;
import com.moonsworth.apollo.impl.bukkit.network.BukkitPacketHandler;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Implementation of ApolloPlatform for Bukkit-based servers.
 */
public class ApolloBukkitPlatform extends JavaPlugin implements ApolloPlatform, Listener {

    @Getter
    private static ApolloBukkitPlatform instance;
    private final Set<UUID> supportedPlayers = new HashSet<>();
    private final BukkitPacketHandler packetHandler = new BukkitPacketHandler();

    @Override
    public Kind getKind() {
        return Kind.SERVER;
    }

    @Override
    public void onEnable() {
        instance = this;
        Apollo.setPlatform(this);
        getCommand("setkb").setExecutor(new KnockbackCommand());
        registerPluginChannel();
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new AttackSpeedListener(), this);
        getServer().getPluginManager().registerEvents(new KnockbackListener(), this);
        getServer().getPluginManager().registerEvents(new ArmorDurabilityListener(this), this);
        getServer().getPluginManager().registerEvents(new RegenListener(this), this);
    }

    private void registerPluginChannel() {
        Messenger messenger = getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(this, Apollo.PLUGIN_MESSAGE_CHANNEL);
        messenger.registerIncomingPluginChannel(this, Apollo.PLUGIN_MESSAGE_CHANNEL, (channel, player, bytes) -> {
            this.supportedPlayers.add(player.getUniqueId());
            PacketRegistry.parse(bytes).ifPresent(packet -> {
                packet.processServer(packetHandler);
            });
        });
    }
    
    @Override
    public @Nullable ApolloPlayer tryWrapPlayer(Object o) {
        if (o instanceof Player player) {
            if (supportedPlayers.contains(player.getUniqueId())) {
                return new BukkitPlayer(player);
            }
        }
        return null;
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        supportedPlayers.remove(event.getPlayer().getUniqueId());
    }

}
