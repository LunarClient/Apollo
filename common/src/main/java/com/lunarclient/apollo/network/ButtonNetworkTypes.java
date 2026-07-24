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
package com.lunarclient.apollo.network;

import com.lunarclient.apollo.button.v1.Button;
import com.lunarclient.apollo.button.v1.ButtonClientAction;
import com.lunarclient.apollo.button.v1.ButtonContent;
import com.lunarclient.apollo.button.v1.ButtonContentPart;
import com.lunarclient.apollo.button.v1.ButtonShape;
import com.lunarclient.apollo.button.v1.ButtonSize;
import com.lunarclient.apollo.button.v1.ButtonTooltip;
import com.lunarclient.apollo.button.v1.ButtonUpdate;
import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.common.button.ApolloButton;
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.action.ApolloButtonAction;
import com.lunarclient.apollo.common.button.action.ClientAction;
import com.lunarclient.apollo.common.button.action.OpenUrlAction;
import com.lunarclient.apollo.common.button.action.RunCommandAction;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.common.button.content.ApolloButtonContentPart;
import com.lunarclient.apollo.common.button.content.ComponentPart;
import com.lunarclient.apollo.common.button.content.IconPart;
import com.lunarclient.apollo.common.button.content.LiveComponentPart;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

/**
 * Serializes the shared {@code common.button} API types into their
 * {@code button.v1} protobuf counterparts, resolving live content and
 * tooltips for a viewing player.
 *
 * @since 1.2.9
 */
public final class ButtonNetworkTypes {

    /**
     * Serializes the fields of the given button.
     *
     * @param button the button to serialize
     * @param viewer the viewing player live parts are resolved for, or
     *               {@code null} when the button has no live parts
     * @return the protobuf button
     * @since 1.2.9
     */
    public static Button toProtobuf(ApolloButton button, @Nullable ApolloPlayer viewer) {
        Button.Builder builder = Button.newBuilder()
            .setId(button.getId())
            .setPosition(NetworkTypes.toProtobuf(button.getPosition()))
            .setSize(ButtonSize.newBuilder()
                .setWidth(button.getSize().getWidth())
                .setHeight(button.getSize().getHeight())
                .build())
            .setShape(ButtonShape.forNumber(button.getShape().ordinal() + 1))
            .setBackgroundColor(NetworkTypes.toProtobuf(button.getBackgroundColor()))
            .setBorderColor(NetworkTypes.toProtobuf(button.getBorderColor()))
            .setContent(toContentProtobuf(button.getContent(), viewer));

        ButtonTooltip tooltip = toTooltipProtobuf(button.getTooltip(), viewer);
        if (tooltip != null) {
            builder.setTooltip(tooltip);
        }

        ApolloButtonAction onClick = button.getOnClick();
        if (onClick instanceof RunCommandAction) {
            builder.setRunCommand(((RunCommandAction) onClick).getCommand());
        } else if (onClick instanceof OpenUrlAction) {
            builder.setOpenUrl(((OpenUrlAction) onClick).getUrl());
        } else if (onClick instanceof ClientAction) {
            ClientAction clientAction = (ClientAction) onClick;
            builder.setClientAction(ButtonClientAction.forNumber(clientAction.getAction().ordinal() + 1));
        } else if (onClick != null) {
            throw new IllegalArgumentException("Unknown button action type: " + onClick.getClass().getName());
        }

        Color hoveredBackgroundColor = button.getHoveredBackgroundColor();
        if (hoveredBackgroundColor != null) {
            builder.setHoveredBackgroundColor(NetworkTypes.toProtobuf(hoveredBackgroundColor));
        }

        Color hoveredBorderColor = button.getHoveredBorderColor();
        if (hoveredBorderColor != null) {
            builder.setHoveredBorderColor(NetworkTypes.toProtobuf(hoveredBorderColor));
        }

        return builder.build();
    }

    /**
     * Serializes button content, resolving live parts for the given viewer.
     *
     * @param content the content to serialize
     * @param viewer  the viewing player live parts are resolved for, or
     *                {@code null} when the content has no live parts
     * @return the protobuf content
     * @since 1.2.9
     */
    public static ButtonContent toContentProtobuf(ApolloButtonContent content, @Nullable ApolloPlayer viewer) {
        return ButtonContent.newBuilder()
            .addAllParts(resolveContentParts(content, viewer))
            .setScale(content.getScale())
            .build();
    }

    private static List<ButtonContentPart> resolveContentParts(ApolloButtonContent content,
                                                               @Nullable ApolloPlayer viewer) {
        List<ButtonContentPart> parts = new ArrayList<>();

        for (ApolloButtonContentPart part : content.getParts()) {
            ButtonContentPart.Builder partBuilder = ButtonContentPart.newBuilder();

            if (part instanceof LiveComponentPart) {
                Function<ApolloPlayer, Component> resolver = ((LiveComponentPart) part).getResolver();
                partBuilder.setAdventureJsonText(ApolloComponent.toJson(resolveLive(resolver, viewer)));
            } else if (part instanceof ComponentPart) {
                partBuilder.setAdventureJsonText(ApolloComponent.toJson(((ComponentPart) part).getComponent()));
            } else if (part instanceof IconPart) {
                partBuilder.setIcon(NetworkTypes.toProtobuf(((IconPart) part).getIcon()));
            } else {
                throw new IllegalArgumentException("Unknown button content part type: " + part.getClass().getName());
            }

            parts.add(partBuilder.build());
        }

        return parts;
    }

    /**
     * Serializes a button tooltip, resolving live tooltips for the given
     * viewer.
     *
     * @param tooltip the tooltip to serialize, or {@code null}
     * @param viewer  the viewing player a live tooltip is resolved for, or
     *                {@code null} when the tooltip is static
     * @return the protobuf tooltip
     * @since 1.2.9
     */
    public static @Nullable ButtonTooltip toTooltipProtobuf(@Nullable ApolloButtonTooltip tooltip,
                                                            @Nullable ApolloPlayer viewer) {
        List<String> lines = resolveTooltipLines(tooltip, viewer);
        if (lines == null) {
            return null;
        }

        return ButtonTooltip.newBuilder()
            .addAllAdventureJsonLines(lines)
            .build();
    }

    /**
     * Builds a partial-update fragment.
     *
     * @param content        the new content, or {@code null} to keep the current one
     * @param tooltip        the new tooltip, or {@code null} to clear it
     * @param tooltipPresent whether the tooltip field should be set at all
     * @param viewer         the viewing player live values are resolved for, or {@code null}
     * @return the protobuf update
     * @since 1.2.9
     */
    public static ButtonUpdate toUpdateProtobuf(@Nullable ApolloButtonContent content,
                                                @Nullable ApolloButtonTooltip tooltip,
                                                boolean tooltipPresent,
                                                @Nullable ApolloPlayer viewer) {
        ButtonUpdate.Builder builder = ButtonUpdate.newBuilder();

        if (content != null) {
            builder.setContent(toContentProtobuf(content, viewer));
        }

        if (tooltipPresent) {
            ButtonTooltip tooltipProto = toTooltipProtobuf(tooltip, viewer);
            if (tooltipProto != null) {
                builder.setTooltip(tooltipProto);
            }
        }

        return builder.build();
    }

    private static Component resolveLive(Function<ApolloPlayer, Component> resolver, @Nullable ApolloPlayer viewer) {
        if (viewer == null) {
            return Component.empty();
        }

        try {
            Component component = resolver.apply(viewer);
            return component != null ? component : Component.empty();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return Component.empty();
        }
    }

    private static @Nullable List<String> resolveTooltipLines(@Nullable ApolloButtonTooltip tooltip,
                                                              @Nullable ApolloPlayer viewer) {
        if (tooltip == null) {
            return Collections.emptyList();
        }

        List<Component> lines = tooltip.getLines();

        Function<ApolloPlayer, List<Component>> resolver = tooltip.getResolver();
        if (resolver != null && viewer != null) {
            lines = null;
            try {
                lines = resolver.apply(viewer);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            if (lines == null) {
                return null;
            }
        }

        if (lines == null) {
            return Collections.emptyList();
        }

        try {
            return lines.stream()
                .map(ApolloComponent::toJson)
                .collect(Collectors.toList());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    private ButtonNetworkTypes() {
    }

}
