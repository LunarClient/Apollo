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
package com.lunarclient.apollo.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents a configuration target.
 *
 * @since 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum ConfigTarget {

    GENERAL_SETTINGS("config.yml", "General and module specific settings. https://lunarclient.dev/apollo/server-owners/config", new String[]{"modules"}),
    MOD_SETTINGS("mods.yml", "Lunar Client mod option overrides. https://lunarclient.dev/apollo/server-owners/config", new String[0]);

    /**
     * The file name of the configuration.
     *
     * @return the file name
     * @since 1.0.0
     */
    private final String fileName;

    /**
     * The header comment for the configuration.
     *
     * @return the header comment
     * @since 1.0.0
     */
    private final String headerComment;

    /**
     * The modules node in the configuration to place the module options under.
     *
     * @return the modules node
     * @since 1.0.0
     */
    private final String[] modulesNode;

}
