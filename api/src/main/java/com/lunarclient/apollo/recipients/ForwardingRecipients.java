/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
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
package com.lunarclient.apollo.recipients;

import java.util.function.Consumer;

/**
 * Represents recipients that forward actions to individual recipients.
 *
 * @since 1.0.0
 */
@FunctionalInterface
public interface ForwardingRecipients extends Recipients {

    /**
     * Retrieves the individual recipients
     * that this forwarding recipient represents.
     *
     * @return an iterable containing recipients
     * @since 1.0.0
     */
    Iterable<? extends Recipients> recipients();

    /**
     * Performs the given action on each recipient.
     *
     * @param action the action
     * @since 1.0.0
     */
    default void forEach(Consumer<? super Recipients> action) {
        for (Recipients recipients : this.recipients()) {
            action.accept(recipients);
        }
    }

}
