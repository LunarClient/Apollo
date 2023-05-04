package com.lunarclient.apollo.option.config;

import org.spongepowered.configurate.serialize.TypeSerializer;

/**
 * Represents a configurer.
 *
 * @since 1.0.0
 */
public interface Serializer {

    /**
     * Shorthand for {@link Serializers#register(Class, TypeSerializer)}.
     *
     * @param type the object class
     * @param serializer the type serializer
     * @param <T> the object type
     * @since 1.0.0
     */
    default <T> void serializer(Class<T> type, TypeSerializer<T> serializer) {
        Serializers.register(type, serializer);
    }

}
