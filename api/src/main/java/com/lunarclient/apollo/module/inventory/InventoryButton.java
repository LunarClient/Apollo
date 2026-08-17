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
import com.lunarclient.apollo.common.button.ApolloButtonSize;
import java.awt.Color;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an inventory button which can be shown on the client, placed
 * in one of the two boxes in the player inventory.
 *
 * @since 1.2.9
 */
@Getter
@SuperBuilder(toBuilder = true)
public final class InventoryButton extends ApolloButton {

    /**
     * The width of each button box.
     *
     * @since 1.2.9
     */
    public static final float BOX_WIDTH = 92.0F;

    /**
     * The height of each button box.
     *
     * @since 1.2.9
     */
    public static final float BOX_HEIGHT = 166.0F;

    /**
     * The maximum number of buttons per box.
     *
     * @since 1.2.9
     */
    public static final int MAX_BUTTONS_PER_BOX = 25;

    /**
     * The suggested default background {@link Color} of an inventory button.
     *
     * @since 1.2.9
     */
    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(255, 255, 255, 40);

    /**
     * The suggested default border {@link Color} of an inventory button.
     *
     * @since 1.2.9
     */
    public static final Color DEFAULT_BORDER_COLOR = new Color(37, 37, 37, 128);

    /**
     * A suggested small square size (26x26); three fit side by side
     * in a button box row.
     *
     * @since 1.2.9
     */
    public static final ApolloButtonSize SIZE_SMALL = ApolloButtonSize.of(26.0F);

    /**
     * A suggested medium square size (40x40); two fit side by side
     * in a button box row.
     *
     * @since 1.2.9
     */
    public static final ApolloButtonSize SIZE_MEDIUM = ApolloButtonSize.of(40.0F);

    /**
     * A suggested large square size (84x84), spanning a full button
     * box row.
     *
     * @since 1.2.9
     */
    public static final ApolloButtonSize SIZE_LARGE = ApolloButtonSize.of(84.0F);

    /**
     * A suggested wide size (80x26), fitting text buttons spanning
     * the box width.
     *
     * @since 1.2.9
     */
    public static final ApolloButtonSize SIZE_WIDE = ApolloButtonSize.of(80.0F, 26.0F);

    /**
     * Returns the {@link InventoryType} this button belongs to.
     *
     * @return the inventory type
     * @since 1.2.9
     */
    @NotNull InventoryType inventoryType;

    /**
     * Returns the {@link InventoryButtonBox} this button is placed in.
     *
     * <p>Two fixed-size boxes ({@value #BOX_WIDTH}x{@value #BOX_HEIGHT})
     * show in the player inventory on its left and right side.</p>
     *
     * @return the button box
     * @since 1.2.9
     */
    @NotNull InventoryButtonBox box;

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
