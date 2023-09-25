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
 * Record your game sessions and replay them from any perspective.
 *
 * @since 1.0.0
 */
public final class ModReplaymod {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("replaymod", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> INDICATOR = SimpleOption.<Boolean>builder()
        .node("replaymod", "indicator").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> AUTO_POST_PROCESS = SimpleOption.<Boolean>builder()
        .node("replaymod", "auto-post-process").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_SERVER_IP = SimpleOption.<Boolean>builder()
        .node("replaymod", "show-server-ip").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> AUTO_SYNC = SimpleOption.<Boolean>builder()
        .node("replaymod", "auto-sync").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static NumberOption<Integer> TIMELINE_LENGTH = NumberOption.<Integer>number()
        .node("replaymod", "timeline-length").type(TypeToken.get(Integer.class))
        .defaultValue(1800).min(1500).max(2100)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ASK_FOR_OPEN_EYE = SimpleOption.<Boolean>builder()
        .node("replaymod", "ask-for-open-eye").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SKIP_POST_RENDER_GUI = SimpleOption.<Boolean>builder()
        .node("replaymod", "skip-post-render-gui").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SKIP_POST_SCREENSHOT_GUI = SimpleOption.<Boolean>builder()
        .node("replaymod", "skip-post-screenshot-gui").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> PATH_PREVIEW = SimpleOption.<Boolean>builder()
        .node("replaymod", "path-preview").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> LUNAR_UI = SimpleOption.<Boolean>builder()
        .node("replaymod", "lunar-ui").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> NOTIFICATIONS = SimpleOption.<Boolean>builder()
        .node("replaymod", "notifications").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> RECORD_SINGLEPLAYER = SimpleOption.<Boolean>builder()
        .node("replaymod", "record-singleplayer").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> RECORD_SERVER = SimpleOption.<Boolean>builder()
        .node("replaymod", "record-server").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> AUTO_START_RECORDING = SimpleOption.<Boolean>builder()
        .node("replaymod", "auto-start-recording").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> RENAME_DIALOG = SimpleOption.<Boolean>builder()
        .node("replaymod", "rename-dialog").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> SHOW_CHAT = SimpleOption.<Boolean>builder()
        .node("replaymod", "show-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    private ModReplaymod() {
    }

}
