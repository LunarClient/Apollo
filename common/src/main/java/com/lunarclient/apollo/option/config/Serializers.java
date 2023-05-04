package com.lunarclient.apollo.option.config;

import lombok.NonNull;
import org.spongepowered.configurate.serialize.TypeSerializer;
import org.spongepowered.configurate.serialize.TypeSerializerCollection;

/**
 * Provides a way to register {@link TypeSerializer}s.
 *
 * @since 1.0.0
 */
public final class Serializers {

    private static final TypeSerializerCollection.Builder builder = TypeSerializerCollection.builder();

    /**
     * Registers the provided {@link TypeSerializer} for the provided object.
     *
     * @param type the object class
     * @param serializer the type serializer
     * @param <T> the object type
     * @since 1.0.0
     */
    public static <T> void register(@NonNull Class<T> type, @NonNull TypeSerializer<T> serializer) {
        Serializers.builder.register(type, serializer);
    }

    /**
     * Returns the {@link TypeSerializerCollection}.
     *
     * @return the type serializers
     * @since 1.0.0
     */
    public static TypeSerializerCollection serializers() {
        return Serializers.builder.build();
    }

    private Serializers() {
    }

}
