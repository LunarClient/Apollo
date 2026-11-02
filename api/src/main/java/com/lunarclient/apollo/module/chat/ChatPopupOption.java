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
import lombok.Builder;
import lombok.Getter;
import net.kyori.adventure.text.Component;

/**
 * Represents an option of a {@link ChatPopup}, which the client draws as a
 * button on the popup.
 *
 * <p>When the player picks the option, the client sends the option id to
 * the server, which posts an {@link ApolloChatPopupResponseEvent}.</p>
 *
 * @since 1.3.1
 */
@Getter
@Builder
public final class ChatPopupOption {

    /**
     * Returns the chat popup option {@link String} id.
     *
     * @return the chat popup option id
     * @since 1.3.1
     */
    String id;

    /**
     * Returns the chat popup option label {@link Component}, shown on the
     * option's button.
     *
     * @return the chat popup option label
     * @since 1.3.1
     */
    Component label;

}
