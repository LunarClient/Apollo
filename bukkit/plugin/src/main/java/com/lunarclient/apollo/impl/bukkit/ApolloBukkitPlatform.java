package com.lunarclient.apollo.impl.bukkit;

import com.google.protobuf.Any;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.module.ApolloModuleManagerImpl;
import com.lunarclient.apollo.impl.bukkit.wrapper.BukkitApolloPlayer;
import com.lunarclient.apollo.module.type.Borders;
import com.lunarclient.apollo.module.type.BordersImpl;
import com.lunarclient.apollo.module.type.ColoredFiresImpl;
import com.lunarclient.apollo.module.type.ColoredFires;
import com.lunarclient.apollo.module.type.Cooldowns;
import com.lunarclient.apollo.module.type.CooldownsImpl;
import com.lunarclient.apollo.module.type.HeartTextureImpl;
import com.lunarclient.apollo.module.type.HeartTextures;
import com.lunarclient.apollo.module.type.LegacyCombat;
import com.lunarclient.apollo.module.type.Nametags;
import com.lunarclient.apollo.module.type.NametagsImpl;
import com.lunarclient.apollo.module.type.Notifications;
import com.lunarclient.apollo.module.type.NotificationsImpl;
import com.lunarclient.apollo.module.type.Saturation;
import com.lunarclient.apollo.module.type.SaturationImpl;
import com.lunarclient.apollo.module.type.Stopwatch;
import com.lunarclient.apollo.module.type.StopwatchImpl;
import com.lunarclient.apollo.module.type.Teams;
import com.lunarclient.apollo.module.type.TeamsImpl;
import com.lunarclient.apollo.module.type.Titles;
import com.lunarclient.apollo.module.type.TitlesImpl;
import com.lunarclient.apollo.module.type.TntCountdown;
import com.lunarclient.apollo.module.type.TntCountdownImpl;
import com.lunarclient.apollo.module.type.Waypoints;
import com.lunarclient.apollo.module.type.WaypointsImpl;
import com.lunarclient.apollo.player.ApolloPlayerManagerImpl;
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
public final class ApolloBukkitPlatform extends JavaPlugin implements ApolloPlatform, Listener {

    @Getter private static ApolloBukkitPlatform instance;

    private HoconConfigurationLoader configurationLoader;

    @Override
    public void onEnable() {
        ApolloBukkitPlatform.instance = this;

        this.getServer().getPluginManager().registerEvents(this, this);

        ApolloManager.bootstrap(this);

        ((ApolloModuleManagerImpl) Apollo.getModuleManager())
                .addModule(Borders.class, new BordersImpl())
                .addModule(ColoredFires.class, new ColoredFiresImpl())
                .addModule(Cooldowns.class, new CooldownsImpl())
                .addModule(HeartTextures.class, new HeartTextureImpl())
                .addModule(Nametags.class, new NametagsImpl())
                .addModule(Notifications.class, new NotificationsImpl())
                .addModule(Saturation.class, new SaturationImpl())
                .addModule(Stopwatch.class, new StopwatchImpl())
                .addModule(Teams.class, new TeamsImpl())
                .addModule(Titles.class, new TitlesImpl())
                .addModule(TntCountdown.class, new TntCountdownImpl())
                .addModule(Waypoints.class, new WaypointsImpl())
                .addModule(LegacyCombat.class);

        this.loadConfiguration();

        Messenger messenger = getServer().getMessenger();
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
    public void onRegisterChannel(PlayerRegisterChannelEvent event) {
        if(!event.getChannel().equalsIgnoreCase(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) return;
        Player player = event.getPlayer();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).addPlayer(new BukkitApolloPlayer(player));
    }

    @EventHandler
    public void onUnregisterChannel(PlayerUnregisterChannelEvent event) {
        if(!event.getChannel().equalsIgnoreCase(ApolloManager.PLUGIN_MESSAGE_CHANNEL)) return;
        Player player = event.getPlayer();
        ((ApolloPlayerManagerImpl) Apollo.getPlayerManager()).removePlayer(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
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

            CommentedConfigurationNode root = this.configurationLoader.load();
            CommentedConfigurationNode modules = root.node("modules");

            ((ApolloModuleManagerImpl) Apollo.getModuleManager()).loadConfiguration(modules);
            ((ApolloModuleManagerImpl) Apollo.getModuleManager()).saveConfiguration(modules);

            this.configurationLoader.save(root);
        } catch(Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void handlePacket(Player player, byte[] bytes) {
        Apollo.getPlayerManager().getPlayer(player.getUniqueId()).ifPresent(apolloPlayer -> {
            try {
                ApolloManager.getNetworkManager().receivePacket(apolloPlayer, Any.parseFrom(bytes));
            } catch(Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
    }

}
