package com.moonsworth.apollo.impl.bukkit;

import com.google.protobuf.Any;
import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.ApolloPlatform;
import com.moonsworth.apollo.impl.bukkit.wrapper.BukkitApolloPlayer;
import com.moonsworth.apollo.module.type.ColoredFire;
import com.moonsworth.apollo.module.type.Cooldowns;
import com.moonsworth.apollo.module.type.LegacyCombat;
import com.moonsworth.apollo.ApolloManager;
import com.moonsworth.apollo.module.ApolloModuleManagerImpl;
import com.moonsworth.apollo.module.type.CooldownsImpl;
import com.moonsworth.apollo.player.ApolloPlayerManagerImpl;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

/**
 * Implementation of ApolloPlatform for Bukkit-based servers.
 */
public class ApolloBukkitPlatform extends JavaPlugin implements ApolloPlatform, Listener {

    @Getter private static ApolloBukkitPlatform instance;

    private HoconConfigurationLoader configurationLoader;

    @Override
    public void onEnable() {
        ApolloBukkitPlatform.instance = this;

        this.getServer().getPluginManager().registerEvents(this, this);

        ApolloManager.bootstrap(this);

        ((ApolloModuleManagerImpl) Apollo.getModuleManager())
                .addModule(ColoredFire.class)
                .addModule(Cooldowns.class, new CooldownsImpl())
                .addModule(LegacyCombat.class);

        this.loadConfiguration();

        final Messenger messenger = getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(this, ApolloManager.PLUGIN_MESSAGE_CHANNEL);
        messenger.registerIncomingPluginChannel(this, ApolloManager.PLUGIN_MESSAGE_CHANNEL,
                (channel, player, bytes) -> this.handlePacket(player, bytes)
        );
    }

    @Override
    public Kind getKind() {
        return Kind.SERVER;
    }

    @EventHandler
    public void onRegisterChannel(final PlayerRegisterChannelEvent event) {
        if(!event.getChannel().equalsIgnoreCase(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) return;
        final Player player = event.getPlayer();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).addPlayer(new BukkitApolloPlayer(player));
    }

    @EventHandler
    public void onUnregisterChannel(final PlayerUnregisterChannelEvent event) {
        if(!event.getChannel().equalsIgnoreCase(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) return;
        final Player player = event.getPlayer();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(player.getUniqueId());
    }

    private void loadConfiguration() {
        try {
            if (this.configurationLoader == null) {
                this.configurationLoader = HoconConfigurationLoader.builder()
                        .path(this.getDataFolder().toPath().resolve("settings.conf"))
                        .build();
            }

            this.configurationLoader.load();

            final CommentedConfigurationNode root = this.configurationLoader.load();
            final CommentedConfigurationNode modules = root.node("modules");

            ((ApolloModuleManagerImpl) Apollo.getModuleManager()).loadConfiguration(modules);
            ((ApolloModuleManagerImpl) Apollo.getModuleManager()).saveConfiguration(modules);

            this.configurationLoader.save(root);
        } catch(final Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void handlePacket(final Player player, final byte[] bytes) {
        Apollo.getPlayerManager().getPlayer(player.getUniqueId()).ifPresent(apolloPlayer -> {
            try {
                ApolloManager.getNetworkManager().receivePacket(apolloPlayer, Any.parseFrom(bytes));
            } catch(final Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
    }

}
