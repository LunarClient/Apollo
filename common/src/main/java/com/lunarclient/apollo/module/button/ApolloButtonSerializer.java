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
package com.lunarclient.apollo.module.button;

import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.common.button.ApolloButton;
import com.lunarclient.apollo.common.button.ApolloButtonShape;
import com.lunarclient.apollo.common.button.ApolloButtonSize;
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.action.ApolloButtonAction;
import com.lunarclient.apollo.common.button.action.ApolloButtonClientAction;
import com.lunarclient.apollo.common.button.action.ClientAction;
import com.lunarclient.apollo.common.button.action.OpenUrlAction;
import com.lunarclient.apollo.common.button.action.RunCommandAction;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.common.button.content.ApolloButtonContentPart;
import com.lunarclient.apollo.common.button.content.ComponentPart;
import com.lunarclient.apollo.common.button.content.IconPart;
import com.lunarclient.apollo.common.button.content.LiveComponentPart;
import com.lunarclient.apollo.common.icon.Icon;
import com.lunarclient.apollo.common.location.HudPosition;
import java.awt.Color;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

/**
 * Reads and writes the shared {@link ApolloButton} properties from a module
 * configuration; each button surface extends this with its own placement
 * fields.
 *
 * @param <T> the surface button type
 * @since 1.2.9
 */
public abstract class ApolloButtonSerializer<T extends ApolloButton> implements TypeSerializer<T> {

    @Override
    public T deserialize(Type type, ConfigurationNode node) throws SerializationException {
        try {
            ApolloButtonContent.Builder content = ApolloButtonContent.builder();
            for (ConfigurationNode part : node.node("content", "parts").childrenList()) {
                if (part.hasChild("text")) {
                    content.append(ApolloComponent.fromLegacyAmpersand(part.node("text").getString("")));
                } else if (part.hasChild("icon")) {
                    Icon icon = part.node("icon").get(Icon.class);
                    if (icon == null) {
                        throw new SerializationException("Button content part 'icon' must not be empty!");
                    }

                    content.append(icon);
                } else {
                    throw new SerializationException("Button content parts require a 'text' or 'icon' field!");
                }
            }

            if (node.hasChild("content", "scale")) {
                content.scale((float) node.node("content", "scale").getDouble(1.0D));
            }

            String id = this.virtualNode(node, "id").getString();
            if (id == null || id.isEmpty()) {
                throw new SerializationException("Required field id must not be empty!");
            }

            ApolloButton.ApolloButtonBuilder<? extends T, ?> builder = this.createBuilder(node);
            builder.id(id);
            builder.position(HudPosition.of(
                (float) this.virtualNode(node, "position", "x").getDouble(),
                (float) this.virtualNode(node, "position", "y").getDouble()));
            builder.size(ApolloButtonSize.of(
                (float) this.virtualNode(node, "size", "width").getDouble(),
                (float) this.virtualNode(node, "size", "height").getDouble()));
            builder.shape(this.parseEnum(ApolloButtonShape.class, this.virtualNode(node, "shape").getString(), "shape"));
            builder.content(content.build());
            builder.hoveredBackgroundColor(node.node("hovered-background-color").get(Color.class));
            builder.hoveredBorderColor(node.node("hovered-border-color").get(Color.class));

            if (node.hasChild("tooltip")) {
                List<Component> lines = new ArrayList<>();
                for (ConfigurationNode line : node.node("tooltip").childrenList()) {
                    lines.add(ApolloComponent.fromLegacyAmpersand(line.getString("")));
                }

                builder.tooltip(ApolloButtonTooltip.of(lines));
            }

            if (node.hasChild("on-click")) {
                builder.onClick(this.readAction(node.node("on-click")));
            }

            return builder.build();
        } catch (IllegalArgumentException exception) {
            throw new SerializationException(exception.getMessage());
        }
    }

    @Override
    public void serialize(Type type, @Nullable T button, ConfigurationNode node) throws SerializationException {
        if (button == null) {
            node.raw(null);
            return;
        }

        node.node("id").set(button.getId());
        this.serializeSurface(button, node);
        node.node("position", "x").set((double) button.getPosition().getX());
        node.node("position", "y").set((double) button.getPosition().getY());
        node.node("size", "width").set((double) button.getSize().getWidth());
        node.node("size", "height").set((double) button.getSize().getHeight());
        node.node("shape").set(button.getShape().name());
        node.node("background-color").set(Color.class, button.getBackgroundColor());
        node.node("border-color").set(Color.class, button.getBorderColor());

        if (button.getHoveredBackgroundColor() != null) {
            node.node("hovered-background-color").set(Color.class, button.getHoveredBackgroundColor());
        }

        if (button.getHoveredBorderColor() != null) {
            node.node("hovered-border-color").set(Color.class, button.getHoveredBorderColor());
        }

        node.node("content", "scale").set((double) button.getContent().getScale());
        for (ApolloButtonContentPart part : button.getContent().getParts()) {
            this.writePart(node.node("content", "parts").appendListNode(), part);
        }

        ApolloButtonTooltip tooltip = button.getTooltip();
        if (tooltip != null) {
            if (tooltip.isLive() || tooltip.getLines() == null) {
                throw new SerializationException("Live tooltips cannot be stored in the config!");
            }

            for (Component line : tooltip.getLines()) {
                node.node("tooltip").appendListNode().set(ApolloComponent.toLegacyAmpersand(line));
            }
        }

        ApolloButtonAction action = button.getOnClick();
        if (action instanceof RunCommandAction) {
            node.node("on-click", "run-command").set(((RunCommandAction) action).getCommand());
        } else if (action instanceof OpenUrlAction) {
            node.node("on-click", "open-url").set(((OpenUrlAction) action).getUrl());
        } else if (action instanceof ClientAction) {
            node.node("on-click", "client-action").set(((ClientAction) action).getAction().name());
        }
    }

    /**
     * Creates the surface builder, reading the surface placement
     * fields from the given node.
     *
     * @param node the button node
     * @return the surface builder
     * @throws SerializationException if a surface field is invalid
     * @since 1.2.9
     */
    protected abstract ApolloButton.ApolloButtonBuilder<? extends T, ?> createBuilder(ConfigurationNode node) throws SerializationException;

    /**
     * Writes the surface placement fields to the given node.
     *
     * @param button the button to serialize
     * @param node   the button node
     * @throws SerializationException if a surface field cannot be written
     * @since 1.2.9
     */
    protected void serializeSurface(T button, ConfigurationNode node) throws SerializationException {
    }

    /**
     * Parses an enum constant.
     *
     * @param type  the enum type
     * @param value the config value
     * @param field the field name used in error messages
     * @param <E>   the enum type
     * @return the parsed constant
     * @throws SerializationException if the value is missing or unknown
     * @since 1.2.9
     */
    protected <E extends Enum<E>> E parseEnum(Class<E> type, @Nullable String value, String field) throws SerializationException {
        if (value == null) {
            throw new SerializationException("Required field " + field + " not found!");
        }

        try {
            return Enum.valueOf(type, value.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new SerializationException("Unknown " + field + " '" + value + "'!");
        }
    }

    /**
     * Returns the node at the given path, requiring it to exist.
     *
     * @param source the source node
     * @param path   the child path
     * @return the child node
     * @throws SerializationException if the child does not exist
     * @since 1.2.9
     */
    protected ConfigurationNode virtualNode(ConfigurationNode source, Object... path) throws SerializationException {
        if (!source.hasChild(path)) {
            throw new SerializationException("Required field " + Arrays.toString(path) + " not found!");
        }

        return source.node(path);
    }

    private ApolloButtonAction readAction(ConfigurationNode node) throws SerializationException {
        if (node.hasChild("run-command")) {
            return ApolloButtonAction.runCommand(node.node("run-command").getString(""));
        }

        if (node.hasChild("open-url")) {
            return ApolloButtonAction.openUrl(node.node("open-url").getString(""));
        }

        if (node.hasChild("client-action")) {
            return ApolloButtonAction.clientAction(this.parseEnum(ApolloButtonClientAction.class,
                node.node("client-action").getString(), "client-action"));
        }

        throw new SerializationException("on-click requires a 'run-command', 'open-url' or 'client-action' field!");
    }

    private void writePart(ConfigurationNode node, ApolloButtonContentPart part) throws SerializationException {
        if (part instanceof LiveComponentPart) {
            throw new SerializationException("Live content parts cannot be stored in the config!");
        }

        if (part instanceof ComponentPart) {
            node.node("text").set(ApolloComponent.toLegacyAmpersand(((ComponentPart) part).getComponent()));
            return;
        }

        if (part instanceof IconPart) {
            node.node("icon").set(Icon.class, ((IconPart) part).getIcon());
            return;
        }

        throw new SerializationException("Unknown button content part type: " + part.getClass().getName());
    }

}
