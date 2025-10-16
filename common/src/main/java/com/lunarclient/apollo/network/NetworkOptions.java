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

import com.google.protobuf.ListValue;
import com.google.protobuf.NullValue;
import com.google.protobuf.Value;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import io.leangen.geantyref.GenericTypeReflector;
import java.awt.Color;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.Nullable;

/**
 * Utility class for sending options to the client.
 *
 * @since 1.0.0
 */
public final class NetworkOptions {

    /**
     * Send a single option to a single player.
     *
     * @param module  the module the option belongs to
     * @param key     the option key
     * @param value   the option value
     * @param players the players to send the option to
     * @since 1.0.0
     */
    public static void sendOption(@Nullable ApolloModule module,
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
     * @param onlyPresent send only the options that have a present value
     * @param players the players to send the module options to
     * @since 1.0.0
     */
    public static void sendOptions(Iterable<ApolloModule> modules,
                                   boolean onlyPresent,
                                   ApolloPlayer... players) {
        for (ApolloPlayer player : players) {
            OverrideConfigurableSettingsMessage.Builder modulesBuilder = OverrideConfigurableSettingsMessage.newBuilder();

            for (ApolloModule module : modules) {
                modulesBuilder.addConfigurableSettings(NetworkOptions.moduleWithOptions(
                    module,
                    onlyPresent
                ).build());
            }

            ((AbstractApolloPlayer) player).sendPacket(modulesBuilder.build());
        }
    }

    /**
     * Wraps the provided value into a protobuf {@link Value}.
     *
     * @param valueBuilder the value builder
     * @param type         the value type
     * @param current      the current value
     * @return the wrapped value
     * @since 1.0.0
     */
    public static Value wrapValue(Value.Builder valueBuilder, Type type, @Nullable Object current) {
        if (current == null) {
            return valueBuilder.setNullValue(NullValue.NULL_VALUE).build();
        }

        Type boxed = GenericTypeReflector.box(type);
        Class<?> clazz = GenericTypeReflector.erase(boxed);

        if (clazz.isEnum()) {
            return valueBuilder.setStringValue(((Enum<?>) current).name()).build();
        } else if (Number.class.isAssignableFrom(clazz)) {
            return valueBuilder.setNumberValue(((Number) current).doubleValue()).build();
        } else if (String.class.isAssignableFrom(clazz)) {
            return valueBuilder.setStringValue((String) current).build();
        } else if (Boolean.class.isAssignableFrom(clazz)) {
            return valueBuilder.setBoolValue((Boolean) current).build();
        } else if (List.class.isAssignableFrom(clazz)) {
            AnnotatedType elementType = NetworkOptions.elementType(boxed);
            ListValue.Builder listBuilder = ListValue.newBuilder();
            for (Object object : (List<?>) current) {
                listBuilder.addValues(NetworkOptions.wrapValue(Value.newBuilder(), elementType.getType(), object));
            }

            return valueBuilder.setListValue(listBuilder.build()).build();
        } else if (Color.class.isAssignableFrom(clazz)) {
            if (current instanceof String) {
                String string = (String) current;
                return valueBuilder.setStringValue(string).build();
            } else if (current instanceof Color) {
                Color currentColor = (Color) current;
                return valueBuilder.setStringValue(Integer.toHexString(currentColor.getRGB())).build();
            } else {
                throw new RuntimeException("Unable to wrap Color value of type '" + clazz.getSimpleName() + "'!");

            }
        }

        throw new RuntimeException("Unable to wrap value of type '" + clazz.getSimpleName() + "'!");
    }

    /**
     * Unwraps the provided protobuf {@link Value} into the appropriate object.
     *
     * @param wrapper the wrapped value
     * @param type    the wrapped type
     * @return the unwrapped value
     * @since 1.0.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static @Nullable Object unwrapValue(Value wrapper, Type type) {
        if (wrapper.hasNullValue()) {
            return null;
        }

        Type boxed = GenericTypeReflector.box(type);
        Class<?> clazz = GenericTypeReflector.erase(boxed);

        if (clazz.isEnum() && wrapper.hasStringValue()) {
            return Enum.valueOf((Class<? extends Enum>) clazz, wrapper.getStringValue());
        } else if (Number.class.isAssignableFrom(clazz) && wrapper.hasNumberValue()) {
            return wrapper.getNumberValue();
        } else if (String.class.isAssignableFrom(clazz) && wrapper.hasStringValue()) {
            return wrapper.getStringValue();
        } else if (Boolean.class.isAssignableFrom(clazz) && wrapper.hasBoolValue()) {
            return wrapper.getBoolValue();
        } else if (List.class.isAssignableFrom(clazz) && wrapper.hasListValue()) {
            AnnotatedType elementType = NetworkOptions.elementType(boxed);
            ListValue listValue = wrapper.getListValue();
            List<Object> list = new ArrayList<>(listValue.getValuesCount());
            for (int i = 0; i < listValue.getValuesCount(); i++) {
                list.add(NetworkOptions.unwrapValue(listValue.getValues(i), elementType.getType()));
            }

            return Collections.unmodifiableList(list);
        } else if (Color.class.isAssignableFrom(clazz) && wrapper.hasStringValue()) {
            return wrapper.getStringValue();
        }

        throw new RuntimeException("Unable to unwrap value of type '" + clazz.getSimpleName() + "'!");
    }

    private static ConfigurableSettings.Builder moduleWithOptions(ApolloModule module, boolean onlyPresent) {
        ConfigurableSettings.Builder builder = NetworkOptions.module(module);
        Options options = module.getOptions();

        for (Option<?, ?, ?> option : options) {
            if (!option.isNotify()) {
                continue;
            }

            Value.Builder valueBuilder = Value.newBuilder();
            Object value = options.get(option);
            if (value == null && onlyPresent) {
                continue;
            }

            Value wrapper = NetworkOptions.wrapValue(valueBuilder, option.getTypeToken().getType(), value);
            builder.putProperties(option.getKey(), wrapper);
        }

        return builder;
    }

    private static AnnotatedType elementType(Type type) {
        AnnotatedType elementType = GenericTypeReflector.annotate(type);
        if(!(elementType instanceof AnnotatedParameterizedType)) {
            throw new RuntimeException("Raw types for lists are not supported!");
        }

        return ((AnnotatedParameterizedType) elementType).getAnnotatedActualTypeArguments()[0];
    }

    private static ConfigurableSettings.Builder module(@Nullable ApolloModule module) {
        return ConfigurableSettings.newBuilder()
            .setApolloModule(module == null ? ApolloManager.PLUGIN_ROOT_MODULE : module.getId())
            .setEnable(module == null || module.isEnabled());
    }

    private NetworkOptions() {
    }

}
