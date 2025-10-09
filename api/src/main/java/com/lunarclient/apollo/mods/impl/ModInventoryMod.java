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
package com.lunarclient.apollo.mods.impl;

import com.lunarclient.apollo.option.NumberOption;
import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * A collection of quality of life tweaks for the inventory.
 *
 * @since 1.1.9
 */
public final class ModInventoryMod {

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("inventory-mod", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Useful for navigating inventory menus on servers.
     *
     * @since 1.1.9
     */
    public static final SimpleOption<Boolean> DONT_RESET_CURSOR = SimpleOption.<Boolean>builder()
        .comment("Useful for navigating inventory menus on servers.")
        .node("inventory-mod", "dont-reset-cursor").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.1.9
     */
    public static final NumberOption<Float> RESET_CURSOR_TIMEOUT = NumberOption.<Float>number()
        .node("inventory-mod", "reset-cursor-timeout").type(TypeToken.get(Float.class))
        .min(0.1F).max(5.0F)
        .defaultValue(0.3F)
        .notifyClient()
        .build();

    private ModInventoryMod() {
    }

}
