package com.moonsworth.apollo.module.type;


import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.ModSetting;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.ModSettingMessage;

/**
 * Provides the mod settings module.
 *
 * @since 1.0.0
 */
public final class ModSettingsImpl extends ModSettings {

    public ModSettingsImpl() {
        super();
    }

    @Override
    public void sendSetting(ApolloPlayer player, ModSetting setting) {
        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.ADD, this.to(setting));
    }

    @Override
    public void broadcastSetting(ModSetting setting) {
        for(ApolloPlayer player : Apollo.getPlayerManager().getPlayers()) {
            ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.ADD, this.to(setting));
        }
    }

    private ModSettingMessage to(ModSetting setting) {
        return ModSettingMessage.newBuilder()
            .setModId(setting.getModId())
            .setEnabled(setting.isEnabled())
            .putAllProperties(setting.getProperties())
            .build();
    }

    private ModSetting from(ModSettingMessage setting) {
        return ModSetting.of(
            setting.getModId(),
            setting.getEnabled(),
            setting.getPropertiesMap()
        );
    }
}
