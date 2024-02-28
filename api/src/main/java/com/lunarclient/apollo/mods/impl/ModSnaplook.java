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

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * A mod class.
 *
 * @since %release_version%
 */
public final class ModSnaplook {

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("snaplook", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * When zoomed in the camera movement will move smoothly (cinematic camera).
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SMOOTH_CAMERA = SimpleOption.<Boolean>builder()
        .comment("When zoomed in the camera movement will move smoothly (cinematic camera)")
        .node("snaplook", "smooth-camera").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * This feature uses press and hold by default. Useful if you want to bypass a certain camera orientation.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SNAPLOOK_TOGGLE_MODE = SimpleOption.<Boolean>builder()
        .comment("This feature uses press and hold by default. Useful if you want to bypass a certain camera orientation.")
        .node("snaplook", "snaplook-toggle-mode").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    private ModSnaplook() {
    }

}
