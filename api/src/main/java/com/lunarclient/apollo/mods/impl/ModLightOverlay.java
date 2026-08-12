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
package com.lunarclient.apollo.mods.impl;

import com.lunarclient.apollo.option.NumberOption;
import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;
import java.awt.Color;

/**
 * Shows the light levels of blocks where mobs can spawn.
 *
 * @since %release_version%
 */
public final class ModLightOverlay {

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("light-overlay", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * The rendering limit (in chunks) for light overlay values. Higher values might lower your FPS.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> RENDER_RANGE_LIMIT = NumberOption.<Integer>number()
        .comment("The rendering limit (in chunks) for light overlay values. Higher values might lower your FPS.")
        .node("light-overlay", "render-range-limit").type(TypeToken.get(Integer.class))
        .min(1).max(12)
        .defaultValue(2)
        .notifyClient()
        .build();

    /**
     * With this option on, the overlay updates aren't deferred, and happen as fast as possible.This looks nicer (less "pop in"), but turning this option on comes at a potential performance cost.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> FAST_UPDATES = SimpleOption.<Boolean>builder()
        .comment("With this option on, the overlay updates aren't deferred, and happen as fast as possible.This looks nicer (less \"pop in\"), but turning this option on comes at a potential performance cost.")
        .node("light-overlay", "fast-updates").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Reduce the amount of overlays that are rendered, increasing performance, especially at higher rendering ranges.This comes at the expense of overlays at the edges of the screen sometimes popping in and out as you look around.It's highly recommended that this option is kept on, especially if using higher rendering ranges.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CULLING = SimpleOption.<Boolean>builder()
        .comment("Reduce the amount of overlays that are rendered, increasing performance, especially at higher rendering ranges.This comes at the expense of overlays at the edges of the screen sometimes popping in and out as you look around.It's highly recommended that this option is kept on, especially if using higher rendering ranges.")
        .node("light-overlay", "culling").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * With this option on, the real, effective light value is displayed. With this option off, only the block light is used, completely ignoring the sky/day light.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> INCLUDE_SKY_LIGHT = SimpleOption.<Boolean>builder()
        .comment("With this option on, the real, effective light value is displayed. With this option off, only the block light is used, completely ignoring the sky/day light.")
        .node("light-overlay", "include-sky-light").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Only show on blocks which have light levels that hostile mobs can spawn on.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_UNSPAWNABLE_LIGHT = SimpleOption.<Boolean>builder()
        .comment("Only show on blocks which have light levels that hostile mobs can spawn on")
        .node("light-overlay", "hide-unspawnable-light").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Set a custom threshold for Light Check. This overrides the vanilla hostile mob spawning value, and shows the light overlay on all blocks on which the light level falls below this threshold.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> CUSTOM_LIGHT_THRESHOLD = SimpleOption.<Boolean>builder()
        .comment("Set a custom threshold for Light Check. This overrides the vanilla hostile mob spawning value, and shows the light overlay on all blocks on which the light level falls below this threshold")
        .node("light-overlay", "custom-light-threshold").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> THRESHOLD = NumberOption.<Integer>number()
        .node("light-overlay", "threshold").type(TypeToken.get(Integer.class))
        .min(0).max(15)
        .defaultValue(15)
        .notifyClient()
        .build();

    /**
     * Show a number for the light value of each block.Turning this option on might lower your FPS.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_LIGHT_VALUE = SimpleOption.<Boolean>builder()
        .comment("Show a number for the light value of each block.Turning this option on might lower your FPS.")
        .node("light-overlay", "show-light-value").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> CROSS_THICKNESS = NumberOption.<Float>number()
        .node("light-overlay", "cross-thickness").type(TypeToken.get(Float.class))
        .min(0.5F).max(10.0F)
        .defaultValue(2.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> TEXT_COLOR = SimpleOption.<Color>builder()
        .node("light-overlay", "text-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 255, 255))
        .notifyClient()
        .build();

    /**
     * Color where hostile mobs can't spawn.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> BRIGHT_COLOR = SimpleOption.<Color>builder()
        .comment("Color where hostile mobs can't spawn")
        .node("light-overlay", "bright-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(0, 255, 0))
        .notifyClient()
        .build();

    /**
     * Color where hostile mobs can spawn.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> DARK_COLOR = SimpleOption.<Color>builder()
        .comment("Color where hostile mobs can spawn")
        .node("light-overlay", "dark-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(255, 0, 0))
        .notifyClient()
        .build();

    /**
     * When this is off, the color is picked based on the light level allowing hostile mobs to spawn. When this is on, the color is interpolated smoothly.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> LIGHT_OVERLAY_DYNAMIC_COLOR = SimpleOption.<Boolean>builder()
        .comment("When this is off, the color is picked based on the light level allowing hostile mobs to spawn. When this is on, the color is interpolated smoothly.")
        .node("light-overlay", "light-overlay-dynamic-color").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    private ModLightOverlay() {
    }

}
