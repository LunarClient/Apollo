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

/**
 * Freecam mode - Only available in singleplayer.
 *
 * @since %release_version%
 */
public final class ModSpeclook {

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("speclook", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * When enabled, quickly tapping the activation key will toggle it instead.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SPEC_LOOK_TOGGLE = SimpleOption.<Boolean>builder()
        .comment("When enabled, quickly tapping the activation key will toggle it instead")
        .node("speclook", "spec-look-toggle").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Base movement speed in freecam. Scrolling while in freecam also adjusts speed.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> SPEC_LOOK_SPEED = NumberOption.<Float>number()
        .comment("Base movement speed in freecam. Scrolling while in freecam also adjusts speed")
        .node("speclook", "spec-look-speed").type(TypeToken.get(Float.class))
        .min(0.1F).max(10.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * Hides your own player while in freecam.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SPEC_LOOK_HIDE_PLAYER = SimpleOption.<Boolean>builder()
        .comment("Hides your own player while in freecam")
        .node("speclook", "spec-look-hide-player").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Automatically exits freecam when you take damage.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SPEC_LOOK_DISABLE_WHEN_DAMAGED = SimpleOption.<Boolean>builder()
        .comment("Automatically exits freecam when you take damage")
        .node("speclook", "spec-look-disable-when-damaged").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    private ModSpeclook() {
    }

}
