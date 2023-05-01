package com.lunarclient.apollo.impl.bukkit;

import com.google.protobuf.Any;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.impl.bukkit.listener.TntCountdownListener;
import com.lunarclient.apollo.impl.bukkit.wrapper.BukkitApolloPlayer;
import com.lunarclient.apollo.module.ApolloModuleManagerImpl;
import com.lunarclient.apollo.module.beam.BeamModule;
import com.lunarclient.apollo.module.beam.BeamModuleImpl;
import com.lunarclient.apollo.module.border.BorderModule;
import com.lunarclient.apollo.module.border.BorderModuleImpl;
import com.lunarclient.apollo.module.coloredfire.ColoredFireModule;
import com.lunarclient.apollo.module.coloredfire.ColoredFireModuleImpl;
import com.lunarclient.apollo.module.cooldown.CooldownModule;
import com.lunarclient.apollo.module.cooldown.CooldownModuleImpl;
import com.lunarclient.apollo.module.hearttexture.HeartTextureModule;
import com.lunarclient.apollo.module.hearttexture.HeartTextureModuleImpl;
import com.lunarclient.apollo.module.hologram.HologramModule;
import com.lunarclient.apollo.module.hologram.HologramModuleImpl;
import com.lunarclient.apollo.module.limb.LimbModule;
import com.lunarclient.apollo.module.limb.LimbModuleImpl;
import com.lunarclient.apollo.module.misc.MiscModule;
import com.lunarclient.apollo.module.misc.MiscModuleImpl;
import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.lunarclient.apollo.module.modsetting.ModSettingModuleImpl;
import com.lunarclient.apollo.module.nametag.NametagModule;
import com.lunarclient.apollo.module.nametag.NametagModuleImpl;
import com.lunarclient.apollo.module.notification.NotificationModule;
import com.lunarclient.apollo.module.notification.NotificationModuleImpl;
import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.lunarclient.apollo.module.staffmod.StaffModModule;
import com.lunarclient.apollo.module.staffmod.StaffModModuleImpl;
import com.lunarclient.apollo.module.stopwatch.StopwatchModule;
import com.lunarclient.apollo.module.stopwatch.StopwatchModuleImpl;
import com.lunarclient.apollo.module.team.TeamModule;
import com.lunarclient.apollo.module.team.TeamModuleImpl;
import com.lunarclient.apollo.module.title.TitleModule;
import com.lunarclient.apollo.module.title.TitleModuleImpl;
import com.lunarclient.apollo.module.tntcountdown.TntCountdownModule;
import com.lunarclient.apollo.module.tntcountdown.TntCountdownModuleImpl;
import com.lunarclient.apollo.module.transfer.TransferModule;
import com.lunarclient.apollo.module.transfer.TransferModuleImpl;
import com.lunarclient.apollo.module.waypoint.WaypointModule;
import com.lunarclient.apollo.module.waypoint.WaypointModuleImpl;
import com.lunarclient.apollo.player.ApolloPlayerManagerImpl;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;
import org.bukkit.plugin.PluginManager;
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
        ApolloManager.bootstrap(this);

        ((ApolloModuleManagerImpl) Apollo.getModuleManager())
                .addModule(BeamModule.class, new BeamModuleImpl())
                .addModule(BorderModule.class, new BorderModuleImpl())
                .addModule(ColoredFireModule.class, new ColoredFireModuleImpl())
                .addModule(CooldownModule.class, new CooldownModuleImpl())
                .addModule(HeartTextureModule.class, new HeartTextureModuleImpl())
                .addModule(HologramModule.class, new HologramModuleImpl())
                .addModule(LimbModule.class, new LimbModuleImpl())
                .addModule(MiscModule.class, new MiscModuleImpl())
                .addModule(ModSettingModule.class, new ModSettingModuleImpl())
                .addModule(NametagModule.class, new NametagModuleImpl())
                .addModule(NotificationModule.class, new NotificationModuleImpl())
                .addModule(ServerRuleModule.class)
                .addModule(StaffModModule.class, new StaffModModuleImpl())
                .addModule(StopwatchModule.class, new StopwatchModuleImpl())
                .addModule(TeamModule.class, new TeamModuleImpl())
                .addModule(TitleModule.class, new TitleModuleImpl())
                .addModule(TntCountdownModule.class, new TntCountdownModuleImpl())
                .addModule(TransferModule.class, new TransferModuleImpl())
                .addModule(WaypointModule.class, new WaypointModuleImpl());

        this.loadConfiguration();

        Messenger messenger = getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(this, ApolloManager.PLUGIN_MESSAGE_CHANNEL);
        messenger.registerIncomingPluginChannel(this, ApolloManager.PLUGIN_MESSAGE_CHANNEL,
                (channel, player, bytes) -> this.handlePacket(player, bytes)
        );

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(this, this);
        pluginManager.registerEvents(new TntCountdownListener(
            Apollo.getModuleManager().getModule(TntCountdownModule.class),
            Apollo.getPlayerManager()
        ), this);
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
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void handlePacket(Player player, byte[] bytes) {
        Apollo.getPlayerManager().getPlayer(player.getUniqueId()).ifPresent(apolloPlayer -> {
            try {
                ApolloManager.getNetworkManager().receivePacket(apolloPlayer, Any.parseFrom(bytes));
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
    }

}
