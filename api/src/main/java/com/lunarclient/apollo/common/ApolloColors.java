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
package com.lunarclient.apollo.common;

import java.awt.Color;

/**
 * `java.awt.Color`s that correspond 1:1 with Bukkit's traditional `ChatColor`s
 *
 * @since 1.0.0
 */
public final class ApolloColors {

    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color DARK_BLUE = new Color(0, 0, 170);
    public static final Color DARK_GREEN = new Color(0, 170, 0);
    public static final Color DARK_AQUA = new Color(0, 170, 170);
    public static final Color DARK_RED = new Color(170, 0, 0);
    public static final Color DARK_PURPLE = new Color(170, 0, 170);
    public static final Color GOLD = new Color(255, 170, 0);
    public static final Color GRAY = new Color(170, 170, 170);
    public static final Color DARK_GRAY = new Color(85, 85, 85);
    public static final Color BLUE = new Color(85, 85, 255);
    public static final Color GREEN = new Color(85, 255, 85);
    public static final Color AQUA = new Color(85, 255, 255);
    public static final Color RED = new Color(255, 85, 85);
    public static final Color LIGHT_PURPLE = new Color(255, 85, 255);
    public static final Color YELLOW = new Color(255, 255, 85);
    public static final Color WHITE = new Color(255, 255, 255);

    private ApolloColors() {

    }

}
