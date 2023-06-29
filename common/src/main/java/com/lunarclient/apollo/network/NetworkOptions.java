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
package com.lunarclient.apollo.network;

import com.google.protobuf.Value;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.option.OptionsImpl;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;

/**
 * Utility class for sending options to the client.
 *
 * @since 1.0.0
 */
public final class NetworkOptions {

    private NetworkOptions() {

    }

    /**
     * Send a single option to a single player.
     *
     * @param module  the module the option belongs to
     * @param key     the option key
     * @param value   the option value
     * @param players the players to send the option to
     * @since 1.0.0
     */
    public static void sendOption(ApolloModule module,
                                  Option<?, ?, ?> key,
                                  Value value,
                                  Iterable<ApolloPlayer> players) {
        if (!key.isNotify()) {
            return;
        }

        OverrideConfigurableSettingsMessage.Builder modulesBuilder = OverrideConfigurableSettingsMessage.newBuilder();
        ConfigurableSettings.Builder moduleBuilder = NetworkOptions.module(module);
        moduleBuilder.putProperties(key.getKey(), value);
        modulesBuilder.addConfigurableSettings(moduleBuilder.build());

        for (ApolloPlayer player : players) {
            ((AbstractApolloPlayer) player).sendPacket(modulesBuilder.build());
        }
    }

    /**
     * Sends the provided {@link ApolloModule}s options to the provided
     * {@link ApolloPlayer}s.
     *
     * @param modules the modules to send the options of
     * @param players the players to send the module options to
     * @since 1.0.0
     */
    public static void sendOptions(Iterable<ApolloModule> modules,
                                   ApolloPlayer... players) {
        for (ApolloPlayer player : players) {
            OverrideConfigurableSettingsMessage.Builder modulesBuilder = OverrideConfigurableSettingsMessage.newBuilder();

            for (ApolloModule module : modules) {
                modulesBuilder.addConfigurableSettings(NetworkOptions.moduleWithOptions(
                    module
                ).build());
            }

            ((AbstractApolloPlayer) player).sendPacket(modulesBuilder.build());
        }
    }

    private static ConfigurableSettings.Builder moduleWithOptions(ApolloModule module) {
        ConfigurableSettings.Builder builder = NetworkOptions.module(module);
        Options options = module.getOptions();

        for (Option<?, ?, ?> option : options) {
            if (!option.isNotify()) {
                continue;
            }

            Value.Builder valueBuilder = Value.newBuilder();
            Object value = options.get(option);
            Value wrapper = ((OptionsImpl) options).wrapValue(valueBuilder, value);
            builder.putProperties(option.getKey(), wrapper);
        }

        return builder;
    }

    private static ConfigurableSettings.Builder module(ApolloModule module) {
        return ConfigurableSettings.newBuilder()
            .setApolloModule(module.getId())
            .setEnable(module.isEnabled());
    }
}
