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
 * Allows you to smoothly zoom in and out.
 *
 * @since 1.0.0
 */
public final class ModZoom {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("zoom", "enabled").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * Quickly pressing and releasing the key will toggle zoom.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> TOGGLE_KEY_ZOOM = SimpleOption.<Boolean>builder()
        .comment("Quickly pressing and releasing the key will toggle zoom")
        .node("zoom", "toggle-key-zoom").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * When zoomed in the camera movement will move smoothly (cinematic camera).
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SMOOTH_CAMERA = SimpleOption.<Boolean>builder()
        .comment("When zoomed in the camera movement will move smoothly (cinematic camera)")
        .node("zoom", "smooth-camera").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * Add a transition when zooming in and out.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SMOOTH_ZOOM = SimpleOption.<Boolean>builder()
        .comment("Add a transition when zooming in and out.")
        .node("zoom", "smooth-zoom").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * Adjust the zoom depth using Mouse Scroll Wheel.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> VARIABLE_ZOOM = SimpleOption.<Boolean>builder()
        .comment("Adjust the zoom depth using Mouse Scroll Wheel")
        .node("zoom", "variable-zoom").type(TypeToken.get(Boolean.class))
        .notifyClient()
        .build();

    /**
     * Change the initial zoom depth.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> ZOOM_DIVISOR = NumberOption.<Integer>number()
        .comment("Change the initial zoom depth.")
        .node("zoom", "zoom-divisor").type(TypeToken.get(Integer.class))
        .min(2).max(10)
        .notifyClient()
        .build();

    /**
     * Change the camera sensitivity when zoomed in.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Float> CAMERA_SENSITIVITY = NumberOption.<Float>number()
        .comment("Change the camera sensitivity when zoomed in.")
        .node("zoom", "camera-sensitivity").type(TypeToken.get(Float.class))
        .min(0.1F).max(2.0F)
        .notifyClient()
        .build();

    private ModZoom() {
    }

}
