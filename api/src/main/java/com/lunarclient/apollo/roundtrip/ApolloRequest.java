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
package com.lunarclient.apollo.roundtrip;

import java.util.UUID;
import lombok.Getter;
import org.jetbrains.annotations.Range;

/**
 * Represents an Apollo Request.
 *
 * @param <T> the expected {@link ApolloResponse}
 * @since 1.0.0
 */
@Getter
public class ApolloRequest<T extends ApolloResponse> {

    /**
     * The timeout for the {@link ApolloRequest}.
     *
     * @since 1.0.0
     */
    public static final long TIMEOUT = 5_000L;

    /**
     * The unique identifier for the {@link ApolloRequest}.
     *
     * @since 1.0.0
     */
    @Getter private final UUID requestId;

    /**
     * The time when the {@link ApolloRequest} was sent.
     *
     * @since 1.0.0
     */
    private final @Range(from = 0, to = Long.MAX_VALUE) long sentTime;

    /**
     * Constructs a new {@link ApolloRequest}.
     *
     * @since 1.0.0
     */
    public ApolloRequest() {
        this.requestId = UUID.randomUUID();
        this.sentTime = System.currentTimeMillis();
    }

}
