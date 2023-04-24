package com.lunarclient.apollo.module.type;

import com.google.protobuf.Value;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

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
    public void sendSettings(ApolloPlayer player, com.lunarclient.apollo.option.type.configurable.ConfigurableSettings settings) {
        requireNonNull(player, "player");
        requireNonNull(settings, "configurable");

        ((AbstractApolloPlayer) player).sendPacket(this.toProtobuf(settings));
    }

    @Override
    public void resetSettings(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(OverrideConfigurableSettingsMessage.getDefaultInstance());
    }

    @Override
    public void broadcastSettings(com.lunarclient.apollo.option.type.configurable.ConfigurableSettings settings) {
        requireNonNull(settings, "configurable");

        OverrideConfigurableSettingsMessage message = this.toProtobuf(settings);

        for(ApolloPlayer player : Apollo.getPlayerManager().getPlayers()) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    public OverrideConfigurableSettingsMessage toProtobuf(com.lunarclient.apollo.option.type.configurable.ConfigurableSettings settings) {
        Set<ConfigurableSettings> configurableSettings = settings.getSettings().stream()
            .map(configurable -> {
                Map<String, Value> properties = configurable.getProperties().entrySet().stream()
                    .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (Value) entry.getValue()
                    ));

                return ConfigurableSettings.newBuilder()
                    .setLunarClientMod(configurable.getTarget())
                    .setEnable(configurable.isEnable())
                    .putAllProperties(properties)
                    .build();
            })
            .collect(Collectors.toSet());

        return OverrideConfigurableSettingsMessage.newBuilder()
            .addAllConfigurableSettings(configurableSettings)
            .build();
    }

}
