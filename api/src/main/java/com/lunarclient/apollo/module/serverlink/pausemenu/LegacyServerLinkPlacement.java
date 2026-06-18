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
package com.lunarclient.apollo.module.serverlink.pausemenu;

/**
 * Represents where the server links button is placed in the pause menu
 * (1.7-1.12), where it is added on a new line by default.
 *
 * @since 1.2.8
 */
public enum LegacyServerLinkPlacement {

    /**
     * Adds the server links button on a dedicated new row, shifting the
     * remaining buttons down.
     *
     * @since 1.2.8
     */
    NEW_ROW,

    /**
     * Replaces the vanilla achievements button with the server links button.
     *
     * @since 1.2.8
     */
    REPLACE_ACHIEVEMENTS,

    /**
     * Replaces the vanilla statistics button with the server links button.
     *
     * @since 1.2.8
     */
    REPLACE_STATISTICS

}
