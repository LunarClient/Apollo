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

import com.lunarclient.apollo.common.button.ApolloButton;
import com.lunarclient.apollo.common.button.ApolloButtonSize;
import java.awt.Color;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a chat button which can be shown on the client, placed in a
 * single fixed-size box anchored in the strip between the chat input field
 * and the chat log, visible while the chat screen is open.
 *
 * @since 1.2.9
 */
@Getter
@SuperBuilder(toBuilder = true)
public final class ChatButton extends ApolloButton {

    /**
     * The width of the button box.
     *
     * @since 1.2.9
     */
    public static final float BOX_WIDTH = 320.0F;

    /**
     * The height of the button box.
     *
     * @since 1.2.9
     */
    public static final float BOX_HEIGHT = 20.0F;

    /**
     * The maximum number of buttons in the box.
     *
     * @since 1.2.9
     */
    public static final int MAX_BUTTONS = 25;

    /**
     * A suggested small size (56x16); five fit side by side in the
     * button box.
     *
     * @since 1.2.9
     */
    public static final ApolloButtonSize SIZE_SMALL = ApolloButtonSize.of(56.0F, 16.0F);

    /**
     * A suggested medium size (96x16); three fit side by side in the
     * button box.
     *
     * @since 1.2.9
     */
    public static final ApolloButtonSize SIZE_MEDIUM = ApolloButtonSize.of(96.0F, 16.0F);

    /**
     * A suggested icon size (16x16) for square icon-only buttons.
     *
     * @since 1.2.9
     */
    public static final ApolloButtonSize SIZE_ICON = ApolloButtonSize.of(16.0F);

    /**
     * The suggested default background {@link Color} of a chat button,
     * matching the vanilla chat background fill.
     *
     * @since 1.2.9
     */
    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(0, 0, 0, 128);

    /**
     * The suggested default border {@link Color} of a chat button, matching
     * the background so buttons read as part of the chat.
     *
     * @since 1.2.9
     */
    public static final Color DEFAULT_BORDER_COLOR = new Color(0, 0, 0, 128);

    /**
     * Returns the background {@link Color} of this button.
     *
     * @return the background color
     * @since 1.2.9
     */
    @Builder.Default
    @NotNull Color backgroundColor = DEFAULT_BACKGROUND_COLOR;

    /**
     * Returns the border {@link Color} of this button.
     *
     * @return the border color
     * @since 1.2.9
     */
    @Builder.Default
    @NotNull Color borderColor = DEFAULT_BORDER_COLOR;

}
