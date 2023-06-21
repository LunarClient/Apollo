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
                ConfigurableSettings.Builder builder = ConfigurableSettings.newBuilder()
                    .setLunarClientMod(configurable.getTarget())
                    .setEnable(configurable.isEnable());

                Map<String, Object> properties = configurable.getProperties();

                if(properties != null) {
                    Map<String, Value> protoProperties = properties.entrySet().stream()
                        .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> (Value) entry.getValue()
                        ));

                    builder.putAllProperties(protoProperties);
                }

                return builder.build();
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
