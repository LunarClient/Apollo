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
package com.lunarclient.apollo.api.response;

import com.lunarclient.apollo.api.ApiResponse;
import lombok.Getter;

/**
 * Represents the apollo version response.
 *
 * @since 1.0.0
 */
@Getter
public final class VersionResponse implements ApiResponse {

    /**
     * Returns the apollo {@link String} version.
     *
     * @return the apollo version
     * @since 1.0.0
     */
    String version;

    /**
     * Returns the apollo {@link Assets}.
     *
     * @return the apollo assets
     * @since 1.0.9
     */
    Assets assets;

    /**
     * Represents the apollo assets.
     *
     * @since 1.0.9
     */
    @Getter
    public static final class Assets {

        /**
         * Returns the latest version of apollo {@link String} Bukkit download URL.
         *
         * @return the apollo bukkit download url
         * @since 1.0.9
         */
        String bukkit;

        /**
         * Returns the latest version of apollo {@link String} Bungee download URL.
         *
         * @return the apollo bungee download url
         * @since 1.0.9
         */
        String bungee;

        /**
         * Returns the latest version of apollo {@link String} Velocity download URL.
         *
         * @return the apollo velocity download url
         * @since 1.0.9
         */
        String velocity;

        /**
         * Returns the latest version of apollo {@link String} Folia download URL.
         *
         * @return the apollo folia download url
         * @since 1.1.8
         */
        String folia;

        /**
         * Returns the latest version of apollo {@link String} Minestom download URL.
         *
         * @return the apollo minestom download url
         * @since 1.2.0
         */
        String minestom;

    }

}
