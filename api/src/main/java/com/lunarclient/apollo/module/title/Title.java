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
package com.lunarclient.apollo.module.title;

import java.time.Duration;
import lombok.Builder;
import lombok.Getter;
import net.kyori.adventure.text.Component;

/**
 * Represents a title which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class Title {

    /**
     * Returns the title {@link TitleType} type.
     *
     * @return the title type
     * @since 1.0.0
     */
    TitleType type;

    /**
     * Returns the title {@link Component} message.
     *
     * @return the title message
     * @since 1.0.0
     */
    Component message;

    /**
     * Returns the title {@link Float} scale.
     *
     * @return the title scale
     * @since 1.0.0
     */
    float scale;

    /**
     * Returns the title {@link Duration} display time.
     *
     * @return the title display time
     * @since 1.0.0
     */
    Duration displayTime;

    /**
     * Returns the title {@link Duration} fade in time.
     *
     * @return the title fade in time
     * @since 1.0.0
     */
    Duration fadeInTime;

    /**
     * Returns the title {@link Duration} fade out time.
     *
     * @return the title fade out time
     * @since 1.0.0
     */
    Duration fadeOutTime;

    /**
     * Returns the title {@link Float} interpolation scale.
     *
     * <p>If the provided interpolation scale is greater than {@link Title#scale},
     * the title will expand. However, if the {@link Title#scale} is greater than
     * the interpolation scale, the title will shrink.</p>
     *
     * @return the title interpolation scale
     * @since 1.0.7
     */
    float interpolationScale;

    /**
     * Returns the title {@link Float} interpolation rate.
     *
     * <p>The rate that the title will expand or shrink every tick (50ms)
     * between {@link Title#scale} and {@link Title#interpolationScale}.</p>
     *
     * @return the title interpolation rate
     * @since 1.0.7
     */
    float interpolationRate;

}
