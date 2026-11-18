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

import com.lunarclient.apollo.event.chat.ApolloChatPopupResponseEvent;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a chat popup which can be shown on the client, a notification
 * style popup with options that the player can pick, for example to accept
 * or deny a friend request.
 *
 * <p>When the popup closes, the client sends a response to the server,
 * which posts an {@link ApolloChatPopupResponseEvent}.
 * No response is sent when the server removes the popup.</p>
 *
 * @since 1.3.1
 */
@Getter
@Builder(toBuilder = true)
public final class ChatPopup {

    /**
     * The maximum number of options of a popup.
     *
     * @since 1.3.1
     */
    public static final int MAX_OPTIONS = 3;

    /**
     * The maximum number of popups that the client keeps for a player,
     * including its own popups for servers like Hypixel.
     *
     * <p>Over it, the client drops the oldest popup that it hasn't shown yet,
     * and responds with {@link ApolloChatPopupResponseEvent.Status#DROPPED}
     * for it.</p>
     *
     * @since 1.3.1
     */
    public static final int MAX_POPUPS = 20;

    /**
     * The maximum length of a title. The client cuts off a longer title.
     *
     * <p>Only the visible characters count, formatting codes don't, and the
     * client never cuts inside a formatting code.</p>
     *
     * @since 1.3.1
     */
    public static final int MAX_TITLE_LENGTH = 128;

    /**
     * The maximum length of a description. The client cuts off a longer
     * description.
     *
     * <p>Only the visible characters count, formatting codes don't, and the
     * client never cuts inside a formatting code.</p>
     *
     * @since 1.3.1
     */
    public static final int MAX_DESCRIPTION_LENGTH = 512;

    /**
     * Returns the chat popup {@link String} id.
     *
     * @return the chat popup id
     * @since 1.3.1
     */
    @NotNull String id;

    /**
     * Returns the chat popup title {@link Component}.
     *
     * @return the chat popup title
     * @since 1.3.1
     */
    @NotNull Component title;

    /**
     * Returns the chat popup description {@link Component}, or null for a
     * popup without a description.
     *
     * @return the chat popup description
     * @since 1.3.1
     */
    @Nullable
    Component description;

    /**
     * Returns the chat popup {@link Duration} display time, usually the time
     * until the request that the popup asks about expires.
     *
     * <p>Without a display time, the popup stays until the player closes it
     * or the server removes it.</p>
     *
     * @return the chat popup display time
     * @since 1.3.1
     */
    @Nullable
    Duration displayTime;

    /**
     * Returns the chat popup {@link ChatPopupOption}s, which the client draws
     * as buttons in this order.
     *
     * @return the chat popup options
     * @since 1.3.1
     */
    @Builder.Default
    @NotNull List<ChatPopupOption> options = Collections.emptyList();

}
