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

import java.awt.Color;
import java.lang.reflect.Type;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

/**
 * Provides some common type serializers for the configuration.
 *
 * @since 1.0.0
 */
public final class CommonSerializers implements Serializer {

    /**
     * Registers the common type serializers.
     *
     * @since 1.0.0
     */
    public CommonSerializers() {
        this.serializer(Color.class, new ColorSerializer());
    }

    private static final class ColorSerializer implements TypeSerializer<Color> {
        @Override
        public Color deserialize(Type type, ConfigurationNode node) throws SerializationException {
            String value = node.getString();
            if (value == null || value.isEmpty()) {
                return null;
            }

            String stringValue = node.getString();
            if (stringValue.startsWith("#")) {
                stringValue = stringValue.substring(1);
            }

            if (stringValue.length() != 8) {
                throw new NumberFormatException("Invalid color string length: " + stringValue);
            }

            long rgba = Long.parseLong(stringValue, 16);
            int alpha = (int) ((rgba >> 24) & 0xFF);
            int red = (int) ((rgba >> 16) & 0xFF);
            int green = (int) ((rgba >> 8) & 0xFF);
            int blue = (int) (rgba & 0xFF);

            return new Color(red, green, blue, alpha);
        }

        @Override
        public void serialize(Type type, @Nullable Color color, ConfigurationNode node) throws SerializationException {
            if (color == null) {
                node.set("#FFFFFF");
                return;
            }

            node.set(String.format("#%06X", (0xFFFFFF & color.getRGB())));
        }
    }

}
