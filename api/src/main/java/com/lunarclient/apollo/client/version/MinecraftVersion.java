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
package com.lunarclient.apollo.client.version;

/**
 * Represents the Minecraft version.
 *
 * @since 1.0.6
 */
public enum MinecraftVersion {

    V1_7,
    V1_8,
    V1_9,
    V1_10,
    V1_11,
    V1_12,
    V1_16_1,
    V1_16_5,
    V1_17_0,
    V1_17_1,
    V1_18_1,
    V1_18_2,
    V1_19_pre,
    V1_19_0,
    V1_19_2,
    V1_19_3,
    V1_19_4,
    V1_20_0,
    V1_20_1,
    V1_20_2,
    V1_20_3,
    V1_20_4,
    UNKNOWN;

    /**
     * Returns true if the current version is after (chronologically) the compared version.
     *
     * @param version The compared version
     * @return The chronological order of the current version compared to the param
     * @since 1.0.6
     */
    public boolean isAfter(MinecraftVersion version) {
        return version.ordinal() < this.ordinal();
    }

    /**
     * Returns true if the current version is after (chronologically)
     * the compared version or is the exact same version.
     *
     * @param version The compared version
     * @return The chronological order of the current version compared to the param
     * @since 1.0.6
     */
    public boolean isAfterInclusive(MinecraftVersion version) {
        return version.ordinal() <= this.ordinal();
    }

    /**
     * Returns true if the current version is before (chronologically) the compared version.
     *
     * @param version The compared version
     * @return The chronological order of the current version compared to the param
     * @since 1.0.6
     */
    public boolean isBefore(MinecraftVersion version) {
        return version.ordinal() > this.ordinal();
    }

    /**
     * Returns true if the current version is before (chronologically)
     * the compared version or is the exact same version.
     *
     * @param version The compared version
     * @return The chronological order of the current version compared to the param
     * @since 1.0.6
     */
    public boolean isBeforeInclusive(MinecraftVersion version) {
        return version.ordinal() >= this.ordinal();
    }

}
