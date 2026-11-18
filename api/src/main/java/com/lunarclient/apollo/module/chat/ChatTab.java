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

import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.action.SelectChatTabAction;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.common.pattern.TextPattern;
import java.awt.Color;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
@Builder(toBuilder = true)
public final class ChatTab {

    /**
     * The maximum number of server tabs that the client keeps at the same
     * time, in total across every display call.
     *
     * @since 1.3.1
     */
    public static final int MAX_TABS = 16;

    /**
     * The maximum length of a display name.
     *
     * <p>The client cuts off a longer name, and only counts the visible
     * characters of the text parts.
     * Formatting codes don't count, and the client never cuts inside a
     * formatting code.</p>
     *
     * @since 1.3.1
     */
    public static final int MAX_DISPLAY_NAME_LENGTH = 32;

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
     * <p>It must not be {@link SelectChatTabAction#ALL_TAB_ID}, because
     * that id selects the "All" tab.</p>
     *
     * @return the chat tab id
     * @since 1.3.1
     */
    @NotNull String id;

    /**
     * Returns the chat tab display name {@link ApolloButtonContent}, shown on
     * the tab itself.
     *
     * <p>The client ignores the content scale, because the tabs have the
     * height of a chat line.
     * Live parts are re-resolved like the ones of a {@link ChatButton}, see
     * {@link ChatModule#BROADCAST_LIVE_TABS}.</p>
     *
     * @return the chat tab display name
     * @since 1.3.1
     */
    @NotNull ApolloButtonContent displayName;

    /**
     * Returns the {@link ApolloButtonTooltip} shown while the tab is hovered.
     *
     * @return the tooltip, or {@code null} for no tooltip
     * @since 1.3.1
     */
    @Builder.Default
    @Nullable ApolloButtonTooltip tooltip = null;

    /**
     * Returns the background {@link Color} of the tab.
     *
     * @return the background color, or {@code null} for the background of
     *     the chat input field
     * @since 1.3.1
     */
    @Builder.Default
    @Nullable Color backgroundColor = null;

    /**
     * Returns the chat tab {@link TextPattern}s, which the client matches
     * against the plain text of each chat message, without formatting codes.
     *
     * <p>A message goes into the tab when one of the patterns matches it.
     * The message also stays in the "All" tab, unless {@link #hideFromAll}
     * is set.
     * A tab without patterns only gets the messages that
     * {@link ChatModule#displayChatTabMessage} adds to it.</p>
     *
     * @return the chat tab patterns
     * @since 1.3.1
     */
    @Builder.Default
    @NotNull List<TextPattern> patterns = Collections.emptyList();

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
    @Builder.Default
    @NotNull String inputPrefix = "";

    /**
     * Returns whether the tab's messages only show in the tab, and not in
     * the client's "All" tab.
     *
     * @return true when the messages only show in the tab
     * @since 1.3.1
     */
    boolean hideFromAll;

}
