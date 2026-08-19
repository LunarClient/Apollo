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
package com.lunarclient.apollo.module.button;

import com.google.protobuf.Message;
import com.lunarclient.apollo.button.v1.ButtonUpdate;
import com.lunarclient.apollo.common.button.ApolloButton;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.List;
import org.jetbrains.annotations.Nullable;

/**
 * The per-surface strategy behind {@link ButtonModuleSupport}.
 *
 * @param <B> the button type
 * @param <P> the protobuf message
 * @since 1.2.9
 */
public interface ButtonSurface<B extends ApolloButton, P extends Message> {

    /**
     * Validates a button before it is sent, throwing {@link IllegalArgumentException} on invalid buttons.
     *
     * @param button the button to validate
     * @since 1.2.9
     */
    void validate(B button);

    /**
     * Builds the surface wrapper element for a button, embedding the shared
     * {@code button.v1.Button} payload alongside the surface placement fields.
     *
     * @param button the button to serialize
     * @param viewer the viewing player live values are resolved for, or {@code null}
     * @return the wrapper element
     * @since 1.2.9
     */
    P toDisplayElement(B button, @Nullable ApolloPlayer viewer);

    /**
     * Builds the surface display message from wrapper elements.
     *
     * @param elements the wrapper elements
     * @return the display message
     * @since 1.2.9
     */
    Message createDisplay(List<P> elements);

    /**
     * Builds the surface update message for a button id.
     *
     * @param buttonId the button id
     * @param update   the shared update fragment
     * @return the update message
     * @since 1.2.9
     */
    Message createUpdate(String buttonId, ButtonUpdate update);

    /**
     * Builds the surface remove message for a button id.
     *
     * @param buttonId the button id
     * @return the remove message
     * @since 1.2.9
     */
    Message createRemove(String buttonId);

    /**
     * Builds the surface reset message.
     *
     * @return the reset message
     * @since 1.2.9
     */
    Message createReset();

    /**
     * Returns whether per-player surface open/close tracking is active.
     *
     * @return whether open tracking is active
     * @since 1.2.9
     */
    boolean isOpenTrackingActive();

}
