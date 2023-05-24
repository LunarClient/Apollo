package com.lunarclient.apollo.module.modsetting;

import com.google.protobuf.Value;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.option.config.Serializer;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

/**
 * Provides the mod settings module.
 *
 * @since 1.0.0
 */
public final class ModSettingModuleImpl extends ModSettingModule implements Serializer {

    /**
     * Creates a new instance of {@link ModSettingModuleImpl}.
     *
     * @since 1.0.0
     */
    public ModSettingModuleImpl() {
        super();
        this.serializer(ModSettings.class, new ModSettingSerializer());
    }

    @Override
    public void sendSettings(@NonNull ApolloPlayer viewer, @NonNull ModsSettings settings) {
        ((AbstractApolloPlayer) viewer).sendPacket(this.toProtobuf(settings));
    }

    @Override
    public void resetSettings(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(OverrideConfigurableSettingsMessage.getDefaultInstance());
    }

    @Override
    public void broadcastSettings(@NonNull ModsSettings settings) {
        OverrideConfigurableSettingsMessage message = this.toProtobuf(settings);

        for (ApolloPlayer player : Apollo.getPlayerManager().getPlayers()) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    private OverrideConfigurableSettingsMessage toProtobuf(ModsSettings settings) {
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

    private static final class ModSettingSerializer implements TypeSerializer<ModSettings> {
        @Override
        public ModSettings deserialize(Type type, ConfigurationNode node) throws SerializationException {
            return ModSettings.builder()
                .target(node.node("target").getString())
                .enable(node.node("enable").getBoolean())
                .properties(node.node("properties").childrenMap().entrySet().stream()
                    .filter(entry -> entry.getKey() instanceof String && !entry.getValue().virtual())
                    .map(entry -> new AbstractMap.SimpleEntry<>((String) entry.getKey(), entry.getValue().raw()))
                    .collect(Collectors.toMap(
                        AbstractMap.SimpleEntry::getKey,
                        AbstractMap.SimpleEntry::getValue
                    )))
                .build();
        }

        @Override
        public void serialize(Type type, @Nullable ModSettings settings, ConfigurationNode node) throws SerializationException {
            if(settings == null) {
                node.raw(null);
                return;
            }

            node.node("target").set(String.class, settings.getTarget());
            node.node("enable").set(settings.isEnable());
            node.node("properties").set(settings.getProperties());
        }

    }

}
