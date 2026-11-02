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
 * Represents an event that is fired when a player picks an option of a
 * {@link ChatPopup}, or closes the popup without picking one.
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
     * The id of the {@link ChatPopupOption} that the player picked, or null
     * when the player closed the popup without picking an option.
     *
     * @return the chat popup option id
     * @since 1.3.1
     */
    @Nullable
    String optionId;

}
