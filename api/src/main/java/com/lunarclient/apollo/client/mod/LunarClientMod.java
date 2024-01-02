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
package com.lunarclient.apollo.client.mod;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a Lunar Client external mod.
 *
 * @since 1.0.6
 */
@Getter
@Builder
public final class LunarClientMod {

    /**
     * Returns the mod {@link String} id (e.g. 'sodium').
     *
     * @return the mod id
     * @since 1.0.6
     */
    String id;

    /**
     * Returns the mod {@link String} display name (e.g. 'Sodium').
     *
     * @return the mod display name
     * @since 1.0.6
     */
    String displayName;

    /**
     * Returns the mod {@link String} version (e.g. '1.2.21').
     *
     * @return the mod display name
     * @since 1.0.6
     */
    @Nullable String version;

    /**
     * Returns the mod {@link LunarClientModType} type.
     *
     * @return the mod type
     * @since 1.0.6
     */
    LunarClientModType type;

}
