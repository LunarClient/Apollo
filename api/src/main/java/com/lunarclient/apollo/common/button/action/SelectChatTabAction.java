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
package com.lunarclient.apollo.common.button.action;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents a button action that selects a chat tab.
 *
 * @since 1.3.1
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class SelectChatTabAction extends ApolloButtonAction {

    /**
     * The tab id that selects the client's "All" tab.
     *
     * @since 1.3.1
     */
    public static final String ALL_TAB_ID = "all";

    /**
     * Returns the id of the chat tab selected when the button is clicked,
     * or {@link #ALL_TAB_ID} for the "All" tab.
     *
     * <p>The tab isn't switched when it doesn't exist, or when the player
     * turned chat tabs off.</p>
     *
     * @return the chat tab id
     * @since 1.3.1
     */
    private final String tabId;

    /**
     * Returns whether the action also opens the chat.
     *
     * <p>The chat opens even when the tab can't be switched.
     * Without it, the client only shows the name of the tab for a moment
     * while the chat is closed.</p>
     *
     * @return true when the action opens the chat
     * @since 1.3.1
     */
    private final boolean openChat;

}
