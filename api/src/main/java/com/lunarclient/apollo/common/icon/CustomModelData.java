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
package com.lunarclient.apollo.common.icon;

import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents the custom model data attached to an {@link ItemStackIcon}.
 *
 * <p>Mirrors the Minecraft 1.21.4 {@code CustomModelData} item component, which
 * replaced the single integer value with four parallel lists.</p>
 *
 * @since 1.2.7
 */
@Getter
@Builder
public final class CustomModelData {

    /**
     * Returns the custom model data {@link Float} values.
     *
     * @return the custom model data floats
     * @since 1.2.7
     */
    @Builder.Default
    List<Float> floats = Collections.emptyList();

    /**
     * Returns the custom model data {@link Boolean} flags.
     *
     * @return the custom model data flags
     * @since 1.2.7
     */
    @Builder.Default
    List<Boolean> flags = Collections.emptyList();

    /**
     * Returns the custom model data {@link String} values.
     *
     * @return the custom model data strings
     * @since 1.2.7
     */
    @Builder.Default
    List<String> strings = Collections.emptyList();

    /**
     * Returns the custom model data color {@link Integer} values.
     *
     * @return the custom model data colors
     * @since 1.2.7
     */
    @Builder.Default
    List<Integer> colors = Collections.emptyList();

}
