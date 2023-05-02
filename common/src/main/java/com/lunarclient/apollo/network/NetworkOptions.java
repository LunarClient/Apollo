package com.lunarclient.apollo.network;

import com.google.protobuf.Value;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.option.AbstractOptions;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayerVersion;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

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
     * @param module the module the option belongs to
     * @param key the option key
     * @param value the option value
     * @param players the players to send the option to
     * @since 1.0.0
     */
    public static void sendOption(ApolloModule module,
                                  Option<?, ?, ?> key,
                                  Value value,
                                  Iterable<ApolloPlayer> players) {
        if(!key.isNotify()) return;

        OverrideConfigurableSettingsMessage.Builder modulesBuilder = OverrideConfigurableSettingsMessage.newBuilder();
        ConfigurableSettings.Builder moduleBuilder = NetworkOptions.module(module);
        moduleBuilder.putProperties(key.getKey(), value);
        modulesBuilder.addConfigurableSettings(moduleBuilder.build());

        for(ApolloPlayer player : players) {
            if(!doesSupportPlayerVersion(module, player)) continue;

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
        for(ApolloPlayer player : players) {
            OverrideConfigurableSettingsMessage.Builder modulesBuilder = OverrideConfigurableSettingsMessage.newBuilder();

            for(ApolloModule module : modules) {
                if(!doesSupportPlayerVersion(module, player)) continue;

                modulesBuilder.addConfigurableSettings(NetworkOptions.moduleWithOptions(
                        module,
                        player
                ).build());
            }

            ((AbstractApolloPlayer) player).sendPacket(modulesBuilder.build());
        }
    }

    private static boolean doesSupportPlayerVersion(ApolloModule module, ApolloPlayer player) {
        Set<ApolloPlayerVersion> supportedVersions = module.getSupportedClientVersions();
        ApolloPlayerVersion playerVersion = player.getVersion();

        return supportedVersions.contains(playerVersion);
    }

    private static ConfigurableSettings.Builder moduleWithOptions(ApolloModule module,
                                                           @Nullable ApolloPlayer player) {
        ConfigurableSettings.Builder builder = NetworkOptions.module(module);
        Options options = player != null ? module.getOptions().get(player) : module.getOptions();

        for(Option<?, ?, ?> option : options) {
            if(!option.isNotify()) continue;

            Value.Builder valueBuilder = Value.newBuilder();
            Object value = options.get(option);
            Value wrapper = ((AbstractOptions) options).wrapValue(valueBuilder, value);
            builder.putProperties(option.getKey(), wrapper);
        }

        return builder;
    }

    private static ConfigurableSettings.Builder module(ApolloModule module) {
        return ConfigurableSettings.newBuilder()
                .setApolloModule(module.getName())
                .setEnable(module.isEnabled());
    }

}
