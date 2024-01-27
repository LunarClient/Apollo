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
package com.lunarclient.apollo.module.vignette;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Range;

/**
 * Represents a vignette which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class Vignette {

    /**
     * Returns the vignette {@link String} resource location.
     *
     * <p>Represents a path to an icon that will appear for the player.</p>
     *
     * @return the resource location
     * @since 1.0.0
     */
    String resourceLocation;

    /**
     * Returns the vignette {@link Float} opacity.
     *
     * <p>Set to 0 to hide the vignette.</p>
     *
     * @return the opacity
     * @since 1.0.0
     */
    @Range(from = 0, to = 1) float opacity;

}
