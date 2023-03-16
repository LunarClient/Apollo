package com.moonsworth.apollo.impl.bukkit;

import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.ApolloPlatform;
import com.moonsworth.apollo.module.type.Cooldowns;
import com.moonsworth.apollo.module.type.LegacyCombat;
import com.moonsworth.apollo.ApolloManager;
import com.moonsworth.apollo.module.ApolloModuleManagerImpl;
import com.moonsworth.apollo.module.type.CooldownsImpl;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

/**
 * Implementation of ApolloPlatform for Bukkit-based servers.
 */
public class ApolloBukkitPlatform extends JavaPlugin implements ApolloPlatform, Listener {

    @Getter private static ApolloBukkitPlatform instance;

    @Override
    public void onEnable() {
        ApolloBukkitPlatform.instance = this;

        ApolloManager.bootstrap(this);

        this.loadModules();

        this.registerPluginChannel();
    }

    @Override
    public Kind getKind() {
        return Kind.SERVER;
    }

    private void loadModules() {
        ((ApolloModuleManagerImpl) Apollo.getModuleManager())
                .addModule(Cooldowns.class, new CooldownsImpl())
                .addModule(LegacyCombat.class);

        this.loadConfiguration();
    }

    private void loadConfiguration() {
//        ((ApolloModuleManagerImpl) Apollo.getModuleManager()).loadConfiguration(this.loadConfig());
//        this.saveConfig(((ApolloModuleManagerImpl) Apollo.getModuleManager()).saveConfiguration());
    }

    private void registerPluginChannel() {
        Messenger messenger = getServer().getMessenger();

        messenger.registerOutgoingPluginChannel(this, ApolloManager.PLUGIN_MESSAGE_CHANNEL);
//        messenger.registerIncomingPluginChannel(this, ApolloManager.PLUGIN_MESSAGE_CHANNEL, (channel, player, bytes) ->
//                Apollo.getApolloPacketManager().handleIncomingPacket(player, bytes));
    }

//    @Override
//    public void onEnable() {
//        instance = this;
//        Apollo.setPlatform(this);
//        Apollo.getApolloModuleManager().register(NametagModule.class);
//        Apollo.getApolloModuleManager().register(NotificationModule.class);
//        Apollo.getApolloModuleManager().register(HeartTextureModule.class);
//        Apollo.getApolloModuleManager().register(ColoredFireModule.class);
//        handleConfiguration();
//
//        registerPluginChannel();
//        getServer().getPluginManager().registerEvents(this, this);
//        Apollo.getApolloModuleManager().registerModuleListener(LegacyCombatModule.class, combatModule -> {
//            getCommand("setkb").setExecutor(new KnockbackCommand());
//            getServer().getPluginManager()
//                    .registerEvents(new DisableProjectileRandomnessListener(this, combatModule), this);
//            getServer().getPluginManager().registerEvents(new AttackSpeedListener(combatModule), this);
//            getServer().getPluginManager().registerEvents(new KnockbackListener(combatModule), this);
//            getServer().getPluginManager().registerEvents(new ArmorDurabilityListener(this, combatModule), this);
//            getServer().getPluginManager().registerEvents(new RegenListener(this, combatModule), this);
//            getServer().getPluginManager().registerEvents(new AttackFrequencyListener(combatModule), this);
//        });
//
//        Apollo.getApolloModuleManager().registerModuleListener(EVNTModule.class, combatModule -> {
//            getCommand("vignette").setExecutor(new VignetteCommand());
//            Bukkit.broadcastMessage("sdf");
//        });
//    }
//
//    private void handleConfiguration() {
//        saveDefaultConfig();
//        ConfigurationSection modules = getConfig().getConfigurationSection("modules");
//        if (modules == null) {
//            return;
//        }
//        Map<String, Object> values = modules.getValues(true);
//        Apollo.getApolloModuleManager().loadConfiguration(values);
//    }
//
//    private void registerPluginChannel() {
//        Messenger messenger = getServer().getMessenger();
//
//        messenger.registerOutgoingPluginChannel(this, Apollo.PLUGIN_MESSAGE_CHANNEL);
//        messenger.registerIncomingPluginChannel(this, Apollo.PLUGIN_MESSAGE_CHANNEL, (channel, player, bytes) ->
//                Apollo.getApolloPacketManager().handleIncomingPacket(player, bytes));
//    }
//
//    @Override
//    public @Nullable ApolloPlayer tryWrapPlayer(Object o) {
//        if (o instanceof Player player) {
//            return Apollo.getApolloPlayerManager().getApolloPlayer(player.getUniqueId())
//                    .orElse(new BukkitPlayer(player));
//        }
//        return null;
//    }
//
//
//    @EventHandler
//    public void onRegister(PlayerRegisterChannelEvent event) {
//        if (!event.getChannel().equalsIgnoreCase(Apollo.PLUGIN_MESSAGE_CHANNEL)) {
//            return;
//        }
//        Player player = event.getPlayer();
//
//        Apollo.getApolloPlayerManager().registerPlayer(player);
//    }
//
//    @EventHandler
//    public void onUnregister(PlayerUnregisterChannelEvent event) {
//        if (event.getChannel().equalsIgnoreCase(Apollo.PLUGIN_MESSAGE_CHANNEL)) {
//            Apollo.getApolloPlayerManager().unRegisterPlayer(event.getPlayer().getUniqueId());
//
//        }
//    }
//
//    @EventHandler
//    public void onQuit(PlayerQuitEvent event) {
//        Apollo.getApolloPlayerManager().unRegisterPlayer(event.getPlayer().getUniqueId());
//    }

}
