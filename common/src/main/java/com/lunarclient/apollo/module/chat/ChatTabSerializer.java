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
package com.lunarclient.apollo.module.chat;

import com.lunarclient.apollo.common.ApolloComponent;
import java.lang.reflect.Type;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

/**
 * Reads and writes {@link ChatTab}s from the module configuration.
 *
 * @since 1.3.1
 */
public final class ChatTabSerializer implements TypeSerializer<ChatTab> {

    @Override
    public ChatTab deserialize(Type type, ConfigurationNode node) throws SerializationException {
        String id = node.node("id").getString();
        if (id == null || id.isEmpty()) {
            throw new SerializationException("Required field id must not be empty!");
        }

        ChatTab.ChatTabBuilder builder = ChatTab.builder()
            .id(id)
            .displayName(ApolloComponent.fromLegacyAmpersand(node.node("display-name").getString(id)))
            .inputPrefix(node.node("input-prefix").getString(""))
            .hideFromAll(node.node("hide-from-all").getBoolean(false));

        for (ConfigurationNode pattern : node.node("patterns").childrenList()) {
            builder.pattern(pattern.getString(""));
        }

        return builder.build();
    }

    @Override
    public void serialize(Type type, @Nullable ChatTab tab, ConfigurationNode node) throws SerializationException {
        if (tab == null) {
            node.raw(null);
            return;
        }

        node.node("id").set(tab.getId());
        node.node("display-name").set(ApolloComponent.toLegacyAmpersand(tab.getDisplayName()));
        node.node("patterns").setList(String.class, tab.getPatterns());
        node.node("input-prefix").set(tab.getInputPrefix());
        node.node("hide-from-all").set(tab.isHideFromAll());
    }

}
