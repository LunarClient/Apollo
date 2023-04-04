package com.moonsworth.apollo.network;

import com.google.protobuf.Value;
import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.option.AbstractOptions;
import com.moonsworth.apollo.option.Option;
import com.moonsworth.apollo.option.Options;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayerVersion;
import java.util.Set;
import lunarclient.apollo.common.ModuleConfig;
import lunarclient.apollo.common.Modules;
import org.jetbrains.annotations.Nullable;

public final class NetworkOptions {

    public static void sendOption(final ApolloModule module,
                                  final Option<?, ?, ?> key,
                                  final Value value,
                                  final Iterable<ApolloPlayer> players) {
        if(!key.isNotify()) return;

        final Modules.Builder modulesBuilder = Modules.newBuilder();
        final ModuleConfig.Builder moduleBuilder = NetworkOptions.module(module);
        moduleBuilder.putOptions(key.getKey(), value);
        modulesBuilder.addModules(moduleBuilder.build());

        for(final ApolloPlayer player : players) {
            checkPlayerVersionSupport(module, player);

            ((AbstractApolloPlayer) player).sendPacket(modulesBuilder.build());
        }
    }

    public static void sendOptions(final Iterable<ApolloModule> modules,
                                   final ApolloPlayer... players) {
        for(final ApolloPlayer player : players) {
            final Modules.Builder modulesBuilder = Modules.newBuilder();
            for(final ApolloModule module : modules) {
                checkPlayerVersionSupport(module, player);

                modulesBuilder.addModules(NetworkOptions.moduleWithOptions(
                        module,
                        player
                ).build());
            }

            ((AbstractApolloPlayer) player).sendPacket(modulesBuilder.build());
        }
    }

    private static void checkPlayerVersionSupport(final ApolloModule module, final ApolloPlayer player) {
        final Set<ApolloPlayerVersion> supportedVersions = module.getSupportedClientVersions();
        final ApolloPlayerVersion playerVersion = player.getVersion();

        if(!supportedVersions.contains(playerVersion)) {
            throw new IllegalStateException(String.format("Module %s doesn't support client version %s!",
                module.getName(), playerVersion.name()));
        }
    }

    private static ModuleConfig.Builder moduleWithOptions(final ApolloModule module,
                                                           final @Nullable ApolloPlayer player) {
        final ModuleConfig.Builder builder = NetworkOptions.module(module);
        final Options options = player != null ? module.getOptions().get(player) : module.getOptions();
        for(final Option<?, ?, ?> option : options) {
            if(!option.isNotify()) continue;
            final Value.Builder valueBuilder = Value.newBuilder();
            final Object value = options.get(option);
            final Value wrapper = ((AbstractOptions) options).wrapValue(valueBuilder, value);
            builder.putOptions(option.getKey(), wrapper);
        }
        return builder;
    }

    private static ModuleConfig.Builder module(final ApolloModule module) {
        return ModuleConfig.newBuilder()
                .setName(module.getName())
                .setEnable(module.isEnabled());
    }

}
