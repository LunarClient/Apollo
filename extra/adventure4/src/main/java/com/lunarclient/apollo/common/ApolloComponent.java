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
package com.lunarclient.apollo.common;

import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * Represents a component which can be shown on the client.
 *
 * @since 1.0.0
 */
public final class ApolloComponent {

    /**
     * Returns a new component from the provided JSON {@link String}.
     *
     * @param json the json string for this component
     * @return the component from the json string
     * @since 1.0.0
     */
    public static Component fromJson(@NonNull String json) {
        return GsonComponentSerializer.gson().deserializeOrNull(json);
    }

    /**
     * Returns this component as a JSON {@link String}.
     *
     * @param component the component to make into a json string
     * @return the json string for this component
     * @since 1.0.0
     */
    public static String toJson(@NonNull Component component) {
        return GsonComponentSerializer.gson().serialize(component);
    }

    /**
     * Returns this component as a legacy {@link String}.
     *
     * @param component the component to make into a legacy string
     * @return the legacy string for this component
     * @since 1.0.5
     */
    public static String toLegacy(@NonNull Component component) {
        return LegacyComponentSerializer.legacySection().serialize(component);
    }

    private ApolloComponent() {
    }

}
