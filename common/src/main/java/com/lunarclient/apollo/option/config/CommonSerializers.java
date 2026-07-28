/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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

import com.lunarclient.apollo.common.icon.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.common.icon.CustomModelData;
import com.lunarclient.apollo.common.icon.Icon;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.icon.ResourceLocationIcon;
import com.lunarclient.apollo.common.icon.SimpleResourceLocationIcon;
import com.lunarclient.apollo.common.profile.Profile;
import java.awt.Color;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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
        ItemStackIconSerializer itemStackIconSerializer = new ItemStackIconSerializer();

        this.serializer(Color.class, new ColorSerializer());
        this.serializer(Icon.class, new IconSerializer(itemStackIconSerializer));
        this.serializer(ItemStackIcon.class, itemStackIconSerializer);
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

            if (stringValue.length() == 6) {
                return new Color(Integer.parseInt(stringValue, 16));
            }

            if (stringValue.length() == 8) {
                long rgba = Long.parseLong(stringValue, 16);
                int alpha = (int) ((rgba >> 24) & 0xFF);
                int red = (int) ((rgba >> 16) & 0xFF);
                int green = (int) ((rgba >> 8) & 0xFF);
                int blue = (int) (rgba & 0xFF);

                return new Color(red, green, blue, alpha);
            }

            throw new NumberFormatException("Invalid color string length: " + stringValue);
        }

        @Override
        public void serialize(Type type, @Nullable Color color, ConfigurationNode node) throws SerializationException {
            if (color == null) {
                node.set(null);
                return;
            }

            node.set(String.format("#%08X", color.getRGB()));
        }
    }

    @RequiredArgsConstructor
    private static final class IconSerializer implements TypeSerializer<Icon> {

        private final ItemStackIconSerializer itemStackIconSerializer;

        @Override
        public Icon deserialize(Type type, ConfigurationNode node) throws SerializationException {
            if (node.hasChild("name") || node.hasChild("id")) {
                return this.itemStackIconSerializer.deserialize(type, node);
            }

            if (!node.hasChild("resource-location")) {
                throw new SerializationException("Icons require a 'name', 'id' or 'resource-location' field!");
            }

            String resourceLocation = node.node("resource-location").getString();
            if (resourceLocation == null || resourceLocation.isEmpty()) {
                throw new SerializationException("Icon 'resource-location' must not be empty!");
            }

            if (node.hasChild("width") || node.hasChild("height") || node.hasChild("min-u")
                    || node.hasChild("max-u") || node.hasChild("min-v") || node.hasChild("max-v")) {
                return AdvancedResourceLocationIcon.builder()
                    .resourceLocation(resourceLocation)
                    .width((float) node.node("width").getDouble())
                    .height((float) node.node("height").getDouble())
                    .minU((float) node.node("min-u").getDouble())
                    .maxU((float) node.node("max-u").getDouble(1.0D))
                    .minV((float) node.node("min-v").getDouble())
                    .maxV((float) node.node("max-v").getDouble(1.0D))
                    .build();
            }

            if (node.hasChild("size")) {
                return SimpleResourceLocationIcon.builder()
                    .resourceLocation(resourceLocation)
                    .size(node.node("size").getInt())
                    .build();
            }

            return ResourceLocationIcon.builder()
                .resourceLocation(resourceLocation)
                .build();
        }

        @Override
        public void serialize(Type type, @Nullable Icon icon, ConfigurationNode node) throws SerializationException {
            if (icon == null) {
                node.raw(null);
                return;
            }

            if (icon instanceof ItemStackIcon) {
                this.itemStackIconSerializer.serialize(type, (ItemStackIcon) icon, node);
                return;
            }

            if (icon instanceof SimpleResourceLocationIcon) {
                SimpleResourceLocationIcon simple = (SimpleResourceLocationIcon) icon;
                node.node("resource-location").set(simple.getResourceLocation());
                node.node("size").set(simple.getSize());
                return;
            }

            if (icon instanceof AdvancedResourceLocationIcon) {
                AdvancedResourceLocationIcon advanced = (AdvancedResourceLocationIcon) icon;
                node.node("resource-location").set(advanced.getResourceLocation());
                node.node("width").set((double) advanced.getWidth());
                node.node("height").set((double) advanced.getHeight());
                node.node("min-u").set((double) advanced.getMinU());
                node.node("max-u").set((double) advanced.getMaxU());
                node.node("min-v").set((double) advanced.getMinV());
                node.node("max-v").set((double) advanced.getMaxV());
                return;
            }

            if (icon instanceof ResourceLocationIcon) {
                node.node("resource-location").set(((ResourceLocationIcon) icon).getResourceLocation());
                return;
            }

            throw new SerializationException("Unknown icon type: " + icon.getClass().getName());
        }
    }

    private static final class ItemStackIconSerializer implements TypeSerializer<ItemStackIcon> {
        @Override
        public ItemStackIcon deserialize(Type type, ConfigurationNode node) throws SerializationException {
            if (!node.hasChild("name") && !node.hasChild("id")) {
                throw new SerializationException("Item icons require a 'name' or 'id' field!");
            }

            ItemStackIcon.ItemStackIconBuilder builder = ItemStackIcon.builder();
            if (node.hasChild("name")) {
                String name = node.node("name").getString();
                if (name == null || name.isEmpty()) {
                    throw new SerializationException("Item icon 'name' must not be empty!");
                }

                builder.itemName(name);
            } else {
                builder.itemId(node.node("id").getInt());
            }

            if (node.hasChild("custom-model-data")) {
                builder.customModelData(node.node("custom-model-data").getInt());
            }

            if (node.hasChild("model-data")) {
                ConfigurationNode data = node.node("model-data");
                builder.customModelDataObject(CustomModelData.builder()
                    .floats(data.node("floats").getList(Float.class, Collections.emptyList()))
                    .flags(data.node("flags").getList(Boolean.class, Collections.emptyList()))
                    .strings(data.node("strings").getList(String.class, Collections.emptyList()))
                    .colors(data.node("colors").getList(Integer.class, Collections.emptyList()))
                    .build());
            }

            if (node.hasChild("potion")) {
                builder.potion(node.node("potion").getString());
            }

            if (node.hasChild("profile")) {
                builder.profile(this.readProfile(node.node("profile")));
            }

            return builder.build();
        }

        @Override
        public void serialize(Type type, @Nullable ItemStackIcon icon, ConfigurationNode node) throws SerializationException {
            if (icon == null) {
                node.raw(null);
                return;
            }

            if (icon.getItemName() != null) {
                node.node("name").set(icon.getItemName());
            } else {
                node.node("id").set(icon.getItemId());
            }

            if (icon.getCustomModelData() != 0) {
                node.node("custom-model-data").set(icon.getCustomModelData());
            }

            CustomModelData modelData = icon.getCustomModelDataObject();
            if (modelData != null) {
                this.writeList(node.node("model-data", "floats"), Float.class, modelData.getFloats());
                this.writeList(node.node("model-data", "flags"), Boolean.class, modelData.getFlags());
                this.writeList(node.node("model-data", "strings"), String.class, modelData.getStrings());
                this.writeList(node.node("model-data", "colors"), Integer.class, modelData.getColors());
            }

            if (icon.getPotion() != null) {
                node.node("potion").set(icon.getPotion());
            }

            Profile profile = icon.getProfile();
            if (profile != null) {
                if (profile.getId() != null) {
                    node.node("profile", "id").set(profile.getId().toString());
                }

                node.node("profile", "texture").set(profile.getTexture());
                node.node("profile", "signature").set(profile.getSignature());
            }
        }

        private Profile readProfile(ConfigurationNode node) throws SerializationException {
            Profile.ProfileBuilder builder = Profile.builder()
                .texture(node.node("texture").getString(""))
                .signature(node.node("signature").getString(""));

            String id = node.node("id").getString();
            if (id != null) {
                try {
                    builder.id(UUID.fromString(id));
                } catch (IllegalArgumentException exception) {
                    throw new SerializationException("Invalid profile id '" + id + "'!");
                }
            }

            return builder.build();
        }

        private <V> void writeList(ConfigurationNode node, Class<V> type, List<V> values) throws SerializationException {
            if (!values.isEmpty()) {
                node.setList(type, values);
            }
        }
    }

}
