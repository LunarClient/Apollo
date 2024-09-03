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
package com.lunarclient.apollo.example.utilities.objects.icon;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Range;

/**
 * Represents an advanced resource location icon.
 *
 * <p>The primary use case for this is rendering images from a
 * sprite-sheet, also known as image sprite.</p>
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class AdvancedResourceLocationIcon extends Icon {

    /**
     * Returns the icon {@link String} resource location.
     *
     * <p>Represents a path to an icon that will appear for the player.</p>
     *
     * @return the icon resource location
     * @since 1.0.0
     */
    String resourceLocation;

    /**
     * Returns the icon width {@link Float}.
     *
     * <p>Size of the image width (in pixels). Must be equal to or greater
     * than 0.</p>
     *
     * @return the icon width
     * @since 1.0.0
     */
    @Range(from = 0, to = Integer.MAX_VALUE) float width;

    /**
     * Returns the icon height {@link Float}.
     *
     * <p>Size of the image height (in pixels). Must be equal to or greater
     * than 0.</p>
     *
     * @return the icon height
     * @since 1.0.0
     */
    @Range(from = 0, to = Integer.MAX_VALUE) float height;

    /**
     * Returns the icon min u {@link Float}.
     *
     * <p>Range of 0-1 (the x location on a TextureAtlas).</p>
     *
     * @return the icon min u
     * @since 1.0.0
     */
    @Range(from = 0, to = 1) float minU;

    /**
     * Returns the icon max u {@link Float}.
     *
     * <p>Range of 0-1 (the x location on a TextureAtlas).</p>
     *
     * @return the icon max u
     * @since 1.0.0
     */
    @Range(from = 0, to = 1) float maxU;

    /**
     * Returns the icon min v {@link Float}.
     *
     * <p>Range of 0-1 (the y location on a TextureAtlas).</p>
     *
     * @return the icon min v
     * @since 1.0.0
     */
    @Range(from = 0, to = 1) float minV;

    /**
     * Returns the icon max v {@link Float}.
     *
     * <p>Range of 0-1 (the y location on a TextureAtlas).</p>
     *
     * @return the icon max v
     * @since 1.0.0
     */
    @Range(from = 0, to = 1) float maxV;

}
