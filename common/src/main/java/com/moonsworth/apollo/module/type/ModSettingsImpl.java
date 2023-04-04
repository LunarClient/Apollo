package com.moonsworth.apollo.module.type;


import com.google.protobuf.Any;
import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.ModSetting;
import lunarclient.apollo.common.MessageOperation;
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
    public void sendSetting(final ApolloPlayer player, final ModSetting setting) {
        ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
            .setModule(this.getName())
            .setOperation(OptionOperation.ADD)
            .setValue(Any.pack(this.to(setting)))
            .build());
    }

    @Override
    public void broadcastSetting(final ModSetting setting) {
        for(final ApolloPlayer player : Apollo.getPlayerManager().getPlayers()) {
            ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                .setModule(this.getName())
                .setOperation(OptionOperation.ADD)
                .setValue(Any.pack(this.to(setting)))
                .build());
        }
    }

    private ModSettingMessage to(final ModSetting setting) {
        return ModSettingMessage.newBuilder()
            .setModId(setting.getModId())
            .setEnabled(setting.isEnabled())
            .putAllProperties(setting.getProperties())
            .build();
    }

    private ModSetting from(final ModSettingMessage setting) {
        return ModSetting.of(
            setting.getModId(),
            setting.getEnabled(),
            setting.getPropertiesMap()
        );
    }
}
