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
package com.lunarclient.apollo.roundtrip.async.future;

import com.lunarclient.apollo.roundtrip.ApolloResponse;
import com.lunarclient.apollo.roundtrip.async.Future;
import com.lunarclient.apollo.roundtrip.async.Handler;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

/**
 * Represents a {@link Future} result of an asynchronous
 * operation that may or may not complete successfully.
 *
 * @param <T> the type of the response object that will be returned
 * @since 1.0.0
 */
@Data
public final class UncertainFuture<T extends ApolloResponse> implements Future<T> {

    /**
     * A {@link Set} of success handlers that will be
     * invoked if the operation completes successfully.
     *
     * @since 1.0.0
     */
    private Set<Handler<T>> success = new HashSet<>();

    /**
     * A {@link Set} of failure handlers that will be
     * invoked if the operation does not complete successfully.
     *
     * @since 1.0.0
     */
    private Set<Handler<Throwable>> failure = new HashSet<>();

    /**
     * Registers a success handler to be invoked
     * when the operation completes successfully.
     *
     * @param handler the handler
     * @since 1.0.0
     */
    @Override
    public UncertainFuture<T> onSuccess(Handler<T> handler) {
        this.success.add(handler);
        return this;
    }

    /**
     * Registers a failure handler to be invoked
     * when the operation does not complete successfully.
     *
     * @param throwable the throwable
     * @since 1.0.0
     */
    @Override
    public UncertainFuture<T> onFailure(Handler<Throwable> throwable) {
        this.failure.add(throwable);
        return this;
    }

}
