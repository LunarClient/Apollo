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

import com.lunarclient.apollo.common.button.ApolloButton;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.ApiStatus;

/**
 * The abstract base class for the action executed when an
 * {@link ApolloButton} is clicked.
 *
 * <p>Each action is one of {@link RunCommandAction}, {@link OpenUrlAction}
 * or {@link ClientAction}.</p>
 *
 * @since 1.2.9
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ApiStatus.NonExtendable
public abstract class ApolloButtonAction {

    /**
     * Creates an action that runs the given command as the player.
     *
     * <p>Must start with {@code /}.</p>
     *
     * @param command the command to run
     * @return the run command action
     * @since 1.2.9
     */
    public static RunCommandAction runCommand(@NonNull String command) {
        return new RunCommandAction(command);
    }

    /**
     * Creates an action that opens the given URL.
     *
     * @param url the url to open
     * @return the open url action
     * @since 1.2.9
     */
    public static OpenUrlAction openUrl(@NonNull String url) {
        return new OpenUrlAction(url);
    }

    /**
     * Creates an action that executes a built-in
     * {@link ApolloButtonClientAction}.
     *
     * @param action the client action to execute
     * @return the client action
     * @since 1.2.9
     */
    public static ClientAction clientAction(@NonNull ApolloButtonClientAction action) {
        return new ClientAction(action);
    }

}
