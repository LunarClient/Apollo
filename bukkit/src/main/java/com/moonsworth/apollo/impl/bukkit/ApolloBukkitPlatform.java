package com.moonsworth.apollo.impl.bukkit;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.module.impl.ColoredFireModule;
import com.moonsworth.apollo.api.module.impl.EVNTModule;
import com.moonsworth.apollo.api.module.impl.LegacyCombatModule;
import com.moonsworth.apollo.api.module.impl.NotificationModule;
import com.moonsworth.apollo.impl.bukkit.command.KnockbackCommand;
import com.moonsworth.apollo.impl.bukkit.command.VignetteCommand;
import com.moonsworth.apollo.impl.bukkit.listener.*;
import com.moonsworth.apollo.impl.bukkit.wrapper.BukkitPlayer;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Implementation of ApolloPlatform for Bukkit-based servers.
 */
public class ApolloBukkitPlatform extends JavaPlugin implements ApolloPlatform, Listener {

    @Getter
    private static ApolloBukkitPlatform instance;

    @Override
    public Kind getKind() {
        return Kind.SERVER;
    }

    @Override
    public void onEnable() {
        instance = this;
        Apollo.setPlatform(this);
        Apollo.getApolloModuleManager().register(NotificationModule.class);
        Apollo.getApolloModuleManager().register(ColoredFireModule.class);
        handleConfiguration();

        registerPluginChannel();
        getServer().getPluginManager().registerEvents(this, this);
        Apollo.getApolloModuleManager().registerModuleListener(LegacyCombatModule.class, combatModule -> {
            getCommand("setkb").setExecutor(new KnockbackCommand());
            getServer().getPluginManager()
                    .registerEvents(new DisableProjectileRandomnessListener(this, combatModule), this);
            getServer().getPluginManager().registerEvents(new AttackSpeedListener(combatModule), this);
            getServer().getPluginManager().registerEvents(new KnockbackListener(combatModule), this);
            getServer().getPluginManager().registerEvents(new ArmorDurabilityListener(this, combatModule), this);
            getServer().getPluginManager().registerEvents(new RegenListener(this, combatModule), this);
            getServer().getPluginManager().registerEvents(new AttackFrequencyListener(combatModule), this);
        });

        Apollo.getApolloModuleManager().registerModuleListener(EVNTModule.class, combatModule -> {
            getCommand("vignette").setExecutor(new VignetteCommand());
            Bukkit.broadcastMessage("sdf");
        });
    }

    private void handleConfiguration() {
        saveDefaultConfig();
        ConfigurationSection modules = getConfig().getConfigurationSection("modules");
        if (modules == null) {
            return;
        }
        Map<String, Object> values = modules.getValues(true);
        Apollo.getApolloModuleManager().loadConfiguration(values);
    }

    private void registerPluginChannel() {
        Messenger messenger = getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(this, Apollo.PLUGIN_MESSAGE_CHANNEL);
        messenger.registerIncomingPluginChannel(this, Apollo.PLUGIN_MESSAGE_CHANNEL, (channel, player, bytes) -> {
            Apollo.handleIncomingPacket(player, bytes);
        });
    }

    @Override
    public @Nullable ApolloPlayer tryWrapPlayer(Object o) {
        if (o instanceof Player player) {
            return Apollo.getApolloPlayerManager().getApolloPlayer(player.getUniqueId())
                    .orElse(new BukkitPlayer(player));
        }
        return null;
    }


    @EventHandler
    public void onRegister(PlayerRegisterChannelEvent event) {
        if (!event.getChannel().equalsIgnoreCase(Apollo.PLUGIN_MESSAGE_CHANNEL)) {
            return;
        }
        Player player = event.getPlayer();

        Apollo.getApolloPlayerManager().registerPlayer(player);
    }

    @EventHandler
    public void onUnregister(PlayerUnregisterChannelEvent event) {
        if (event.getChannel().equalsIgnoreCase(Apollo.PLUGIN_MESSAGE_CHANNEL)) {
            Apollo.getApolloPlayerManager().unRegisterPlayer(event.getPlayer().getUniqueId());

        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Apollo.getApolloPlayerManager().unRegisterPlayer(event.getPlayer().getUniqueId());
    }

}
