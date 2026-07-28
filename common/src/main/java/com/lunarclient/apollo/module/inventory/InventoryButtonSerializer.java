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
package com.lunarclient.apollo.module.inventory;

import com.lunarclient.apollo.common.button.ApolloButton;
import com.lunarclient.apollo.module.button.ApolloButtonSerializer;
import java.awt.Color;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

/**
 * Reads and writes {@link InventoryButton}s from the module configuration;
 * see {@link ApolloButtonSerializer} for the shared button fields.
 *
 * @since 1.2.9
 */
public final class InventoryButtonSerializer extends ApolloButtonSerializer<InventoryButton> {

    @Override
    protected ApolloButton.ApolloButtonBuilder<? extends InventoryButton, ?> createBuilder(ConfigurationNode node) throws SerializationException {
        InventoryButton.InventoryButtonBuilder<?, ?> builder = InventoryButton.builder()
            .inventoryType(this.parseEnum(InventoryType.class,
                this.virtualNode(node, "inventory-type").getString(), "inventory-type"))
            .box(this.parseEnum(InventoryButtonBox.class, this.virtualNode(node, "box").getString(), "box"));

        Color background = node.node("background-color").get(Color.class);
        if (background != null) {
            builder.backgroundColor(background);
        }

        Color border = node.node("border-color").get(Color.class);
        if (border != null) {
            builder.borderColor(border);
        }

        return builder;
    }

    @Override
    protected void serializeSurface(InventoryButton button, ConfigurationNode node) throws SerializationException {
        node.node("inventory-type").set(button.getInventoryType().name());
        node.node("box").set(button.getBox().name());
    }

}
