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
package com.lunarclient.apollo.module.cosmetic;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Range;

/**
 * Represents an emote that can be played on an NPC.
 *
 * @since 1.2.7
 */
@Getter
@Builder
public final class Emote {

    /**
     * Returns the Lunar Client emote id.
     *
     * <p>The value must be greater than 0.</p>
     *
     * @return the emote id
     * @since 1.2.7
     */
    @Range(from = 1, to = Integer.MAX_VALUE) int id;

    /**
     * Returns the metadata used to select a variant for emotes that support one.
     *
     * <p>The value is ignored by emotes that do not use it. For example, a coin flip emote may use
     * {@code 1} for heads and {@code 2} for tails, while a rock paper scissors emote may use {@code 1}
     * for rock, {@code 2} for paper, and {@code 3} for scissors.</p>
     *
     * @return the emote metadata
     * @since 1.2.7
     */
    int metadata;

}
