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
package com.lunarclient.apollo.common.button;

import com.lunarclient.apollo.common.button.action.ApolloButtonAction;
import com.lunarclient.apollo.common.button.action.ApolloButtonClientAction;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.common.location.HudPosition;
import java.awt.Color;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a button which can be shown on the client.
 *
 * @since 1.2.9
 */
@Getter
@SuperBuilder(toBuilder = true)
@ApiStatus.NonExtendable
public abstract class ApolloButton {

    /**
     * Returns the button {@link String} id.
     *
     * <p>Displaying another button with the same id replaces the
     * previous one.</p>
     *
     * @return the button id
     * @since 1.2.9
     */
    @NotNull String id;

    /**
     * Returns the {@link HudPosition} of this button, relative to the
     * top-left corner of the container it is placed in.
     *
     * <p>The button must fit inside the container: {@code 0 <= x},
     * {@code 0 <= y}, {@code x + width <= container width} and
     * {@code y + height <= container height}. See the surface type for
     * its container dimensions (e.g. {@code InventoryButton#BOX_WIDTH}).</p>
     *
     * @return the button position
     * @since 1.2.9
     */
    @NotNull HudPosition position;

    /**
     * Returns the {@link ApolloButtonSize} of this button.
     *
     * <p>Use one of the surface's suggested sizes (e.g.
     * {@code InventoryButton.SIZE_MEDIUM}) or a fully custom
     * {@link ApolloButtonSize#of(float, float)}.</p>
     *
     * @return the button size
     * @since 1.2.9
     */
    @NotNull ApolloButtonSize size;

    /**
     * Returns the {@link ApolloButtonShape} of this button.
     *
     * @return the button shape
     * @since 1.2.9
     */
    @NotNull ApolloButtonShape shape;

    /**
     * Returns the {@link ApolloButtonContent} rendered inside this button.
     *
     * <p>Built via {@code ApolloButtonContent.builder()}, appending static
     * components, icons or live per-player parts that are re-resolved
     * whenever the content is sent (see the owning module's live button
     * broadcast option, e.g. {@code InventoryModule#BROADCAST_LIVE_BUTTONS}).</p>
     *
     * @return the button content
     * @since 1.2.9
     */
    @NotNull ApolloButtonContent content;

    /**
     * Returns the {@link ApolloButtonTooltip} shown while this button is hovered.
     *
     * <p>Built via {@code ApolloButtonTooltip.of(...)} for static lines,
     * or {@code ApolloButtonTooltip.live(...)} for per-player lines that
     * are re-resolved whenever the button content is sent (see the owning
     * module's live button broadcast option, e.g.
     * {@code InventoryModule#BROADCAST_LIVE_BUTTONS}).</p>
     *
     * @return the tooltip, or {@code null} for no tooltip
     * @since 1.2.9
     */
    @Builder.Default
    @Nullable ApolloButtonTooltip tooltip = null;

    /**
     * Returns the {@link ApolloButtonAction} executed when this button
     * is clicked.
     *
     * <p>Built via {@link ApolloButtonAction#runCommand(String)},
     * {@link ApolloButtonAction#openUrl(String)} or
     * {@link ApolloButtonAction#clientAction(ApolloButtonClientAction)}.</p>
     *
     * @return the click action, or {@code null}
     * @since 1.2.9
     */
    @Builder.Default
    @Nullable ApolloButtonAction onClick = null;

    /**
     * Returns the background {@link Color} used while this button is hovered.
     *
     * @return the hovered background color, or {@code null}
     * @since 1.2.9
     */
    @Builder.Default
    @Nullable Color hoveredBackgroundColor = null;

    /**
     * Returns the border {@link Color} used while this button is hovered.
     *
     * @return the hovered border color, or {@code null}
     * @since 1.2.9
     */
    @Builder.Default
    @Nullable Color hoveredBorderColor = null;

    /**
     * Returns the background {@link Color} of this button.
     *
     * @return the background color
     * @since 1.2.9
     */
    public abstract Color getBackgroundColor();

    /**
     * Returns the border {@link Color} of this button.
     *
     * @return the border color
     * @since 1.2.9
     */
    public abstract Color getBorderColor();

}
