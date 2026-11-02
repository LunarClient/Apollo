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
package com.lunarclient.apollo.module.chat;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import net.kyori.adventure.text.Component;

/**
 * Represents a chat tab which can be shown on the client, in the tab bar
 * above the chat input while the chat screen is open.
 *
 * <p>The client sorts chat messages into the tab with the tab's patterns,
 * and these messages also show in the client's "All" tab unless
 * {@link #hideFromAll} is set. {@link ChatModule#displayChatTabMessage}
 * adds a message straight to the tab instead, and that message never shows
 * in the "All" tab.</p>
 *
 * @since 1.3.1
 */
@Getter
@Builder
public final class ChatTab {

    /**
     * The maximum number of tabs that a server can show.
     *
     * @since 1.3.1
     */
    public static final int MAX_TABS = 16;

    /**
     * The maximum number of patterns of a tab.
     *
     * @since 1.3.1
     */
    public static final int MAX_PATTERNS = 16;

    /**
     * The maximum length of a pattern.
     *
     * @since 1.3.1
     */
    public static final int MAX_PATTERN_LENGTH = 256;

    /**
     * Returns the chat tab {@link String} id.
     *
     * @return the chat tab id
     * @since 1.3.1
     */
    String id;

    /**
     * Returns the chat tab display name {@link Component}, shown on the tab
     * itself.
     *
     * @return the chat tab display name
     * @since 1.3.1
     */
    Component displayName;

    /**
     * Returns the chat tab patterns, Java regular expressions that the
     * client matches against the plain text of each chat message, without
     * formatting codes.
     *
     * <p>A message goes into the tab when one of the patterns finds a match
     * in it, so a pattern needs a leading "^" to only match the start of a
     * message. The message also stays in the "All" tab, unless
     * {@link #hideFromAll} is set.</p>
     *
     * @return the chat tab patterns
     * @since 1.3.1
     */
    @Singular List<String> patterns;

    /**
     * Returns the chat tab input prefix, which the client puts in front of
     * every chat message that the player sends while the tab is open.
     * For example, with "/tc ", typing "hi" in the tab sends "/tc hi".
     *
     * <p>The input prefix only changes what the player sends, the patterns
     * decide which messages go into the tab.
     * Commands that the player types are sent unchanged. An empty prefix
     * sends every message unchanged. A prefix that starts with "/" is a
     * command, so the client puts a space after it when it doesn't end with
     * one.
     * Other prefixes like "!" are put right in front of the message.</p>
     *
     * @return the chat tab input prefix
     * @since 1.3.1
     */
    @Builder.Default String inputPrefix = "";

    /**
     * Returns whether the tab's messages only show in the tab, and not in
     * the client's "All" tab.
     *
     * @return true when the messages only show in the tab
     * @since 1.3.1
     */
    boolean hideFromAll;

}
