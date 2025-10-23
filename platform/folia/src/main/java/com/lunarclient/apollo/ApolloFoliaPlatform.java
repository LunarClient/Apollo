/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo;

import com.lunarclient.apollo.command.FoliaApolloCommand;
import com.lunarclient.apollo.command.FoliaLunarClientCommand;
import com.lunarclient.apollo.listener.ApolloMetadataListener;
import com.lunarclient.apollo.listener.ApolloPlayerListener;
import com.lunarclient.apollo.listener.ApolloWorldListener;
import com.lunarclient.apollo.metadata.FoliaMetadataManager;
import com.lunarclient.apollo.module.ApolloModuleManagerImpl;
import com.lunarclient.apollo.module.autotexthotkey.AutoTextHotkeyModule;
import com.lunarclient.apollo.module.beam.BeamModule;
import com.lunarclient.apollo.module.beam.BeamModuleImpl;
import com.lunarclient.apollo.module.border.BorderModule;
import com.lunarclient.apollo.module.border.BorderModuleImpl;
import com.lunarclient.apollo.module.chat.ChatModule;
import com.lunarclient.apollo.module.chat.ChatModuleImpl;
import com.lunarclient.apollo.module.coloredfire.ColoredFireModule;
import com.lunarclient.apollo.module.coloredfire.ColoredFireModuleImpl;
import com.lunarclient.apollo.module.combat.CombatModule;
import com.lunarclient.apollo.module.cooldown.CooldownModule;
import com.lunarclient.apollo.module.cooldown.CooldownModuleImpl;
import com.lunarclient.apollo.module.entity.EntityModule;
import com.lunarclient.apollo.module.entity.EntityModuleImpl;
import com.lunarclient.apollo.module.glow.GlowModule;
import com.lunarclient.apollo.module.glow.GlowModuleImpl;
import com.lunarclient.apollo.module.hologram.HologramModule;
import com.lunarclient.apollo.module.hologram.HologramModuleImpl;
import com.lunarclient.apollo.module.limb.LimbModule;
import com.lunarclient.apollo.module.limb.LimbModuleImpl;
import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.lunarclient.apollo.module.modsettings.ModSettingsModuleImpl;
import com.lunarclient.apollo.module.nametag.NametagModule;
import com.lunarclient.apollo.module.nametag.NametagModuleImpl;
import com.lunarclient.apollo.module.nickhider.NickHiderModule;
import com.lunarclient.apollo.module.nickhider.NickHiderModuleImpl;
import com.lunarclient.apollo.module.notification.NotificationModule;
import com.lunarclient.apollo.module.notification.NotificationModuleImpl;
import com.lunarclient.apollo.module.packetenrichment.PacketEnrichmentImpl;
import com.lunarclient.apollo.module.packetenrichment.PacketEnrichmentModule;
import com.lunarclient.apollo.module.richpresence.RichPresenceModule;
import com.lunarclient.apollo.module.richpresence.RichPresenceModuleImpl;
import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.lunarclient.apollo.module.staffmod.StaffModModule;
import com.lunarclient.apollo.module.staffmod.StaffModModuleImpl;
import com.lunarclient.apollo.module.stopwatch.StopwatchModule;
import com.lunarclient.apollo.module.stopwatch.StopwatchModuleImpl;
import com.lunarclient.apollo.module.team.TeamModule;
import com.lunarclient.apollo.module.team.TeamModuleImpl;
import com.lunarclient.apollo.module.tebex.TebexModule;
import com.lunarclient.apollo.module.tebex.TebexModuleImpl;
import com.lunarclient.apollo.module.title.TitleModule;
import com.lunarclient.apollo.module.title.TitleModuleImpl;
import com.lunarclient.apollo.module.tntcountdown.TntCountdownModule;
import com.lunarclient.apollo.module.tntcountdown.TntCountdownModuleImpl;
import com.lunarclient.apollo.module.transfer.TransferModule;
import com.lunarclient.apollo.module.transfer.TransferModuleImpl;
import com.lunarclient.apollo.module.vignette.VignetteModule;
import com.lunarclient.apollo.module.vignette.VignetteModuleImpl;
import com.lunarclient.apollo.module.waypoint.WaypointModule;
import com.lunarclient.apollo.module.waypoint.WaypointModuleImpl;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.option.OptionsImpl;
import com.lunarclient.apollo.stats.ApolloStats;
import com.lunarclient.apollo.wrapper.FoliaApolloStats;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

/**
 * The Folia platform plugin.
 *
 * @since 1.1.8
 */
public final class ApolloFoliaPlatform extends JavaPlugin implements ApolloPlatform {

    @Getter private static ApolloFoliaPlatform instance;

    @Getter private final Options options = new OptionsImpl(null, new ArrayList<>());

    private ApolloStats stats;

    @Override
    public void onEnable() {
        ApolloFoliaPlatform.instance = this;

        this.stats = new FoliaApolloStats();

        ApolloManager.bootstrap(this);
        ApolloManager.setMetadataManager(new FoliaMetadataManager());

        new ApolloMetadataListener(this);
        new ApolloPlayerListener(this);
        new ApolloWorldListener(this);

        ((ApolloModuleManagerImpl) Apollo.getModuleManager())
            .addModule(AutoTextHotkeyModule.class)
            .addModule(BeamModule.class, new BeamModuleImpl())
            .addModule(BorderModule.class, new BorderModuleImpl())
            .addModule(ChatModule.class, new ChatModuleImpl())
            .addModule(ColoredFireModule.class, new ColoredFireModuleImpl())
            .addModule(CombatModule.class)
            .addModule(CooldownModule.class, new CooldownModuleImpl())
            .addModule(EntityModule.class, new EntityModuleImpl())
            .addModule(GlowModule.class, new GlowModuleImpl())
            .addModule(HologramModule.class, new HologramModuleImpl())
            .addModule(LimbModule.class, new LimbModuleImpl())
            .addModule(ModSettingModule.class, new ModSettingsModuleImpl())
            .addModule(NametagModule.class, new NametagModuleImpl())
            .addModule(NickHiderModule.class, new NickHiderModuleImpl())
            .addModule(NotificationModule.class, new NotificationModuleImpl())
            .addModule(PacketEnrichmentModule.class, new PacketEnrichmentImpl())
            .addModule(RichPresenceModule.class, new RichPresenceModuleImpl())
            .addModule(ServerRuleModule.class)
            .addModule(StaffModModule.class, new StaffModModuleImpl())
            .addModule(StopwatchModule.class, new StopwatchModuleImpl())
            .addModule(TeamModule.class, new TeamModuleImpl())
            .addModule(TebexModule.class, new TebexModuleImpl())
            .addModule(TitleModule.class, new TitleModuleImpl())
            .addModule(TntCountdownModule.class, new TntCountdownModuleImpl())
            .addModule(TransferModule.class, new TransferModuleImpl())
            .addModule(VignetteModule.class, new VignetteModuleImpl())
            .addModule(WaypointModule.class, new WaypointModuleImpl());

        try {
            ApolloManager.setConfigPath(this.getDataFolder().toPath());
            ApolloManager.loadConfiguration();
            ((ApolloModuleManagerImpl) Apollo.getModuleManager()).enableModules();
            ApolloManager.saveConfiguration();
        } catch (Throwable throwable) {
            this.getPlatformLogger().log(Level.SEVERE, "Unable to load Apollo configuration and modules!", throwable);
        }

        Messenger messenger = this.getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(this, ApolloManager.PLUGIN_MESSAGE_CHANNEL);
        messenger.registerIncomingPluginChannel(this, ApolloManager.PLUGIN_MESSAGE_CHANNEL,
            (channel, player, bytes) -> ApolloManager.getNetworkManager().receivePacket(player.getUniqueId(), bytes)
        );

        this.getCommand("apollo").setExecutor(new FoliaApolloCommand());
        this.getCommand("lunarclient").setExecutor(new FoliaLunarClientCommand());

        ApolloManager.getStatsManager().enable();
        ApolloManager.getVersionManager().checkForUpdates();
    }

    @Override
    public void onDisable() {
        ((ApolloModuleManagerImpl) Apollo.getModuleManager()).disableModules();
    }

    @Override
    public Kind getKind() {
        return Kind.SERVER;
    }

    @Override
    public Platform getPlatform() {
        return Platform.FOLIA;
    }

    @Override
    public String getApolloVersion() {
        return this.getDescription().getVersion();
    }

    @Override
    public ApolloStats getStats() {
        return this.stats;
    }

    @Override
    public Object getPlugin() {
        return getInstance();
    }

    @Override
    public Logger getPlatformLogger() {
        return Bukkit.getServer().getLogger();
    }

}
