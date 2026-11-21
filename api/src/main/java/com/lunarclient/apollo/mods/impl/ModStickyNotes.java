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
 * Make sticky notes to stick to your screen.
 *
 * @since %release_version%
 */
public final class ModStickyNotes {

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("sticky-notes", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * Close the note editor when clicking anywhere other than a note.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STICKY_NOTES_CLOSE_EDITOR_ON_OUTSIDE_CLICK = SimpleOption.<Boolean>builder()
        .comment("Close the note editor when clicking anywhere other than a note")
        .node("sticky-notes", "sticky-notes-close-editor-on-outside-click").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * Resume editing the last note you edited when opening the note editor.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STICKY_NOTES_FOCUS_LAST_EDITED = SimpleOption.<Boolean>builder()
        .comment("Resume editing the last note you edited when opening the note editor")
        .node("sticky-notes", "sticky-notes-focus-last-edited").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Integer> STICKY_NOTES_NOTE_WIDTH = NumberOption.<Integer>number()
        .node("sticky-notes", "sticky-notes-note-width").type(TypeToken.get(Integer.class))
        .min(40).max(300)
        .defaultValue(100)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BACKGROUND = SimpleOption.<Boolean>builder()
        .node("sticky-notes", "background").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> BACKGROUND_COLOR = SimpleOption.<Color>builder()
        .node("sticky-notes", "background-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(247, 226, 122))
        .notifyClient()
        .build();

    /**
     * Follows the background color until changed. Reset it to follow again.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> STICKY_NOTES_TITLE_BAR_COLOR = SimpleOption.<Color>builder()
        .comment("Follows the background color until changed. Reset it to follow again.")
        .node("sticky-notes", "sticky-notes-title-bar-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(217, 198, 107))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> BORDER = SimpleOption.<Boolean>builder()
        .node("sticky-notes", "border").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> BORDER_COLOR = SimpleOption.<Color>builder()
        .node("sticky-notes", "border-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(138, 122, 46))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> BORDER_THICKNESS = NumberOption.<Float>number()
        .node("sticky-notes", "border-thickness").type(TypeToken.get(Float.class))
        .min(0.5F).max(3.0F)
        .defaultValue(0.5F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ROUNDED_CORNERS = SimpleOption.<Boolean>builder()
        .node("sticky-notes", "rounded-corners").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> STICKY_NOTES_TITLE_SCALE = NumberOption.<Float>number()
        .node("sticky-notes", "sticky-notes-title-scale").type(TypeToken.get(Float.class))
        .min(0.5F).max(2.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> TITLE_COLOR = SimpleOption.<Color>builder()
        .node("sticky-notes", "title-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(43, 43, 43))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STICKY_NOTES_CENTER_TITLE = SimpleOption.<Boolean>builder()
        .node("sticky-notes", "sticky-notes-center-title").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STICKY_NOTES_TITLE_SHADOW = SimpleOption.<Boolean>builder()
        .node("sticky-notes", "sticky-notes-title-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final NumberOption<Float> STICKY_NOTES_TEXT_SCALE = NumberOption.<Float>number()
        .node("sticky-notes", "sticky-notes-text-scale").type(TypeToken.get(Float.class))
        .min(0.5F).max(2.0F)
        .defaultValue(1.0F)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Color> TEXT_COLOR = SimpleOption.<Color>builder()
        .node("sticky-notes", "text-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(43, 43, 43))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STICKY_NOTES_CENTER_TEXT = SimpleOption.<Boolean>builder()
        .node("sticky-notes", "sticky-notes-center-text").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> STICKY_NOTES_TEXT_SHADOW = SimpleOption.<Boolean>builder()
        .node("sticky-notes", "sticky-notes-text-shadow").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .notifyClient()
        .build();

    private ModStickyNotes() {
    }

}
