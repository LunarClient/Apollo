package com.moonsworth.apollo.option;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayerVersion;
import com.moonsworth.apollo.protocol.AddOption;
import com.moonsworth.apollo.protocol.ModuleOption;
import com.moonsworth.apollo.protocol.ModuleOptions;
import com.moonsworth.apollo.protocol.Modules;
import com.moonsworth.apollo.protocol.SetOption;
import com.moonsworth.apollo.protocol.ValueWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public final class NetworkOptions {

    public static Function<ValueWrapper, ModuleOption> SET_OPERATOR = value -> ModuleOption.newBuilder()
            .setSet(SetOption.newBuilder().setValue(value).build())
            .build();

    public static Function<ValueWrapper, ModuleOption> ADD_OPERATOR = value -> ModuleOption.newBuilder()
            .setAdd(AddOption.newBuilder().setValue(value).build())
            .build();

    private static final Map<String, OptionConverter<Object, Message>> messageToConverter = new HashMap<>();
    private static final Map<Class<?>, OptionConverter<Object, Message>> objectToConverter = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <I, O extends Message>  void register(final Class<I> objectClass, final O message, final OptionConverter<I, O> converter) {
        final String messageUrl = Any.pack(message).getTypeUrl();
        messageToConverter.put(messageUrl, (OptionConverter<Object, Message>) converter);
        objectToConverter.put(objectClass, (OptionConverter<Object, Message>) converter);
    }

    @SuppressWarnings("unchecked")
    public static <I, O extends Message> @Nullable OptionConverter<I, O> get(final String messageUrl) {
        return (OptionConverter<I, O>) NetworkOptions.messageToConverter.get(messageUrl);
    }

    @SuppressWarnings("unchecked")
    public static <I, O extends Message> @Nullable OptionConverter<I, O> get(final Class<? extends I> objectClass) {
        return (OptionConverter<I, O>) NetworkOptions.objectToConverter.get(objectClass);
    }

    public static void sendOption(final ApolloModule module,
                                  final Option<?, ?, ?> key,
                                  final ModuleOption value,
                                  final Iterable<ApolloPlayer> players) {
        if(!key.isNotify()) return;

        final Modules.Builder modulesBuilder = Modules.newBuilder();
        final ModuleOptions.Builder moduleBuilder = NetworkOptions.module(module);
        moduleBuilder.putOptions(key.getKey(), value);
        modulesBuilder.putModules(module.getName(), moduleBuilder.build());

        for(final ApolloPlayer player : players) {
            checkPlayerVersionSupport(module, player);

            ((AbstractApolloPlayer) player).sendPacket(modulesBuilder.build());
        }
    }

    public static void sendOptions(final Iterable<ApolloModule> modules,
                                   final Function<ValueWrapper, ModuleOption> operatorFunction,
                                   final ApolloPlayer... players) {
        for(final ApolloPlayer player : players) {
            final Modules.Builder modulesBuilder = Modules.newBuilder();
            for(final ApolloModule module : modules) {
                checkPlayerVersionSupport(module, player);

                modulesBuilder.putModules(module.getName(), NetworkOptions.moduleWithOptions(
                        module,
                        operatorFunction,
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

    private static ModuleOptions.Builder moduleWithOptions(final ApolloModule module,
                                                           final Function<ValueWrapper, ModuleOption> operatorFunction,
                                                           final @Nullable ApolloPlayer player) {
        final ModuleOptions.Builder builder = NetworkOptions.module(module);
        final Options options = player != null ? module.getOptions().get(player) : module.getOptions();
        for(final Option<?, ?, ?> option : options) {
            if(!option.isNotify()) continue;
            final Object value = options.get(option);
            final ValueWrapper wrapper = ((AbstractOptions) options).wrapElement(value);
            final ModuleOption moduleOption = operatorFunction.apply(wrapper);
            builder.putOptions(option.getKey(), moduleOption);
        }
        return builder;
    }

    private static ModuleOptions.Builder module(final ApolloModule module) {
        return ModuleOptions.newBuilder().setEnabled(module.isEnabled());
    }

}
