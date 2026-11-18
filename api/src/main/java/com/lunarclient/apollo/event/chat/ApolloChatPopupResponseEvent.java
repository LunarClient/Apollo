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
package com.lunarclient.apollo.event.chat;

import com.lunarclient.apollo.event.Event;
import com.lunarclient.apollo.module.chat.ChatPopup;
import com.lunarclient.apollo.module.chat.ChatPopupOption;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an event that is fired when a {@link ChatPopup} closes on the
 * client.
 *
 * <p>It isn't fired when the server removes the popup, or for the popups
 * that are still displayed when the player disconnects.</p>
 *
 * @since 1.3.1
 */
@Value
public class ApolloChatPopupResponseEvent implements Event {

    /**
     * The {@link ApolloPlayer} that responded to the popup.
     *
     * @return the player
     * @since 1.3.1
     */
    ApolloPlayer player;

    /**
     * The {@link ChatPopup} id.
     *
     * @return the chat popup id
     * @since 1.3.1
     */
    String popupId;

    /**
     * The {@link ChatPopup} that was displayed to the player.
     *
     * @return the chat popup
     * @since 1.3.1
     */
    ChatPopup popup;

    /**
     * The {@link Status} that tells why the popup closed.
     *
     * @return the response status
     * @since 1.3.1
     */
    Status status;

    /**
     * The id of the {@link ChatPopupOption} that the player picked, or null
     * unless the status is {@link Status#PICKED}.
     *
     * @return the chat popup option id
     * @since 1.3.1
     */
    @Nullable
    String optionId;

    /**
     * Represents why a {@link ChatPopup} closed.
     *
     * @since 1.3.1
     */
    public enum Status {

        /**
         * The player picked an option.
         *
         * <p>An option with a run command or open URL action only counts as
         * picked once its command runs or its URL opens, after the player
         * confirms it.
         * A client action or a select chat tab action counts as picked right
         * away, even when it has nothing to do.
         * Sending an option's run command, for example from chat, also picks
         * it, in every popup with that command.</p>
         *
         * @since 1.3.1
         */
        PICKED,

        /**
         * The player closed the popup without picking an option, or picked an
         * option whose action didn't happen, because they cancelled its prompt
         * or its URL couldn't open.
         *
         * @since 1.3.1
         */
        DISMISSED,

        /**
         * The display time of the popup ran out.
         *
         * @since 1.3.1
         */
        EXPIRED,

        /**
         * The client threw the popup away without showing it, because it
         * had too many popups.
         *
         * @since 1.3.1
         */
        DROPPED

    }

}
