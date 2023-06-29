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
     * @param type       the object class
     * @param serializer the type serializer
     * @param <T>        the object type
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
