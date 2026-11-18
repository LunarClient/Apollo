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
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.common.button.content.ApolloButtonContentPart;
import com.lunarclient.apollo.common.button.content.ComponentPart;
import com.lunarclient.apollo.common.pattern.TextPattern;
import com.lunarclient.apollo.common.pattern.TextPatternType;
import com.lunarclient.apollo.module.button.ApolloButtonSerializer;
import java.awt.Color;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.Component;
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

        ConfigurationNode displayNameNode = node.node("display-name");
        ApolloButtonContent displayName;
        if (displayNameNode.isMap()) {
            displayName = ApolloButtonSerializer.readContent(displayNameNode);
        } else {
            String text = displayNameNode.getString();
            if (text == null || text.isEmpty()) {
                throw new SerializationException("Required field display-name must not be empty!");
            }

            displayName = ApolloButtonContent.builder()
                .append(ApolloComponent.fromLegacyAmpersand(text))
                .build();
        }

        List<TextPattern> patterns = new ArrayList<>();
        for (ConfigurationNode pattern : node.node("patterns").childrenList()) {
            patterns.add(this.readPattern(pattern));
        }

        ChatTab.ChatTabBuilder builder = ChatTab.builder()
            .id(id)
            .displayName(displayName)
            .backgroundColor(node.node("background-color").get(Color.class))
            .patterns(patterns)
            .inputPrefix(node.node("input-prefix").getString(""))
            .hideFromAll(node.node("hide-from-all").getBoolean(false));

        if (node.hasChild("tooltip")) {
            List<Component> lines = new ArrayList<>();
            for (ConfigurationNode line : node.node("tooltip").childrenList()) {
                lines.add(ApolloComponent.fromLegacyAmpersand(line.getString("")));
            }

            builder.tooltip(ApolloButtonTooltip.of(lines));
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

        ApolloButtonContent displayName = tab.getDisplayName();
        List<ApolloButtonContentPart> parts = displayName.getParts();
        if (parts.size() == 1 && parts.get(0) instanceof ComponentPart) {
            node.node("display-name").set(ApolloComponent.toLegacyAmpersand(((ComponentPart) parts.get(0)).getComponent()));
        } else {
            ApolloButtonSerializer.writeContent(node.node("display-name"), displayName);
        }

        ApolloButtonTooltip tooltip = tab.getTooltip();
        if (tooltip != null) {
            if (tooltip.isLive() || tooltip.getLines() == null) {
                throw new SerializationException("Live tooltips cannot be stored in the config!");
            }

            for (Component line : tooltip.getLines()) {
                node.node("tooltip").appendListNode().set(ApolloComponent.toLegacyAmpersand(line));
            }
        }

        if (tab.getBackgroundColor() != null) {
            node.node("background-color").set(Color.class, tab.getBackgroundColor());
        }

        for (TextPattern pattern : tab.getPatterns()) {
            ConfigurationNode patternNode = node.node("patterns").appendListNode();
            patternNode.node(this.getPatternField(pattern.getType())).set(pattern.getValue());

            if (pattern.isCaseSensitive()) {
                patternNode.node("case-sensitive").set(true);
            }
        }

        node.node("input-prefix").set(tab.getInputPrefix());
        node.node("hide-from-all").set(tab.isHideFromAll());
    }

    private TextPattern readPattern(ConfigurationNode node) throws SerializationException {
        if (!node.isMap()) {
            throw new SerializationException("Patterns require a 'regex', 'contains' or 'starts-with' field!");
        }

        for (Object key : node.childrenMap().keySet()) {
            String field = String.valueOf(key);
            if (!field.equals("regex") && !field.equals("contains") && !field.equals("starts-with")
                && !field.equals("case-sensitive")) {
                throw new SerializationException("Unknown pattern field '" + field + "'!");
            }
        }

        int typeFields = (node.hasChild("regex") ? 1 : 0) + (node.hasChild("contains") ? 1 : 0)
            + (node.hasChild("starts-with") ? 1 : 0);
        if (typeFields != 1) {
            throw new SerializationException("Patterns require exactly one 'regex', 'contains' or 'starts-with' field!");
        }

        TextPattern pattern;
        if (node.hasChild("regex")) {
            pattern = TextPattern.regex(this.readPatternValue(node, "regex"));
        } else if (node.hasChild("contains")) {
            pattern = TextPattern.contains(this.readPatternValue(node, "contains"));
        } else {
            pattern = TextPattern.startsWith(this.readPatternValue(node, "starts-with"));
        }

        return pattern.withCaseSensitive(node.node("case-sensitive").getBoolean(false));
    }

    private String readPatternValue(ConfigurationNode node, String field) throws SerializationException {
        String value = node.node(field).getString();
        if (value == null || value.isEmpty()) {
            throw new SerializationException("Pattern '" + field + "' must not be empty!");
        }

        return value;
    }

    private String getPatternField(TextPatternType type) throws SerializationException {
        switch (type) {
            case REGEX:
                return "regex";
            case CONTAINS:
                return "contains";
            case STARTS_WITH:
                return "starts-with";
            default:
                throw new SerializationException("Unknown pattern type: " + type);
        }
    }

}
