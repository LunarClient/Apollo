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
package com.lunarclient.apollo.mods;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Value;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.mods.ApolloUpdateModOptionEvent;
import com.lunarclient.apollo.network.NetworkOptions;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.StatusOptionsImpl;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Manages Apollo mods for players.
 *
 * @since 1.2.1
 */
@Getter
public final class ApolloModsManager implements ApolloListener {

    private final Container container;
    private final StatusOptionsImpl playerOptions;

    /**
     * Constructs the {@link ApolloModsManager}.
     *
     * @since 1.2.1
     */
    public ApolloModsManager() {
        EventBus.getBus().register(this);

        this.container = ApolloModsManager.loadModOptions();
        this.playerOptions = new StatusOptionsImpl(this.container.getModStatusOptions());
    }

    /**
     * Load all options from auto-generated mod classes into a {@link Container} object.
     *
     * @return the container
     * @since 1.2.1
     */
    public static Container loadModOptions() {
        Map<String, Option<?, ?, ?>> modStatusOptions = new LinkedHashMap<>();
        List<Option<?, ?, ?>> modSettingsOptions = new ArrayList<>();

        try {
            Field defaultValueField = Option.class.getDeclaredField("defaultValue");
            defaultValueField.setAccessible(true);

            for (Class<?> mod : Mods.ALL_MODS) {
                Field[] fields = mod.getDeclaredFields();

                for (Field field : fields) {
                    field.setAccessible(true);
                    Option<?, ?, ?> option = (Option<?, ?, ?>) field.get(null);

                    if (option == null) {
                        continue;
                    }

                    modStatusOptions.put(option.getKey(), option);

                    Option<?, ?, ?> copy = option.clone();
                    defaultValueField.set(copy, null);
                    modSettingsOptions.add(copy);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Container(modStatusOptions, modSettingsOptions);
    }

    /**
     * Holds the loaded options.
     *
     * @since 1.2.1
     */
    @Getter
    @RequiredArgsConstructor
    public static class Container {

        /**
         * Returns all registered mod options with default values.
         *
         * @return the map of option key to option
         * @since 1.2.1
         */
        private final Map<String, Option<?, ?, ?>> modStatusOptions;

        /**
         * Returns all registered mod options with default values removed.
         *
         * @return the list of options without default values
         * @since 1.2.1
         */
        private final List<Option<?, ?, ?>> modSettingsOptions;

    }

    @Listen
    private void onApolloReceivePacket(ApolloReceivePacketEvent event) {
        ApolloPlayer player = event.getPlayer();
        Any packet = event.getPacket();

        if(packet.is(OverrideConfigurableSettingsMessage.class) || packet.is(ConfigurableSettings.class)) {
            this.handleConfiguration(player, packet);
        }
    }

    private void handleConfiguration(ApolloPlayer player, Any any) {
        // Unpack the settings first.
        List<ConfigurableSettings> settings;
        try {
            if (any.is(OverrideConfigurableSettingsMessage.class)) {
                OverrideConfigurableSettingsMessage message = any.unpack(OverrideConfigurableSettingsMessage.class);
                settings = message.getConfigurableSettingsList();
            } else {
                settings = Collections.singletonList(any.unpack(ConfigurableSettings.class));
            }
        } catch (InvalidProtocolBufferException exception) {
            throw new RuntimeException(exception);
        }

        for (ConfigurableSettings setting : settings) {
            if (!setting.hasApolloModule()) {
                continue;
            }

            if (!setting.getApolloModule().equals("mod_status")) {
                continue;
            }

            for (Map.Entry<String, Value> entry : setting.getPropertiesMap().entrySet()) {
                Option<?, ?, ?> option = this.playerOptions.getOptionsByKey().get(entry.getKey());

                Object unwrappedValue = NetworkOptions.unwrapValue(
                    entry.getValue(),
                    option.getTypeToken().getType()
                );

                this.playerOptions.set(player, option, unwrappedValue);

                EventBus.EventResult<ApolloUpdateModOptionEvent> eventResult = EventBus.getBus()
                    .post(new ApolloUpdateModOptionEvent(player, option, unwrappedValue));

                for (Throwable throwable : eventResult.getThrowing()) {
                    throwable.printStackTrace();
                }
            }
        }
    }
}
