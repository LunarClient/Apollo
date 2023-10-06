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
package com.lunarclient.apollo.async;

import com.lunarclient.apollo.api.ApiResponse;

/**
 * Represents a future result of an asynchronous
 * operation invoked when the operation is completed.
 *
 * @param <T> the type of the response object that will be returned
 * @since 1.0.0
 */
public interface Future<T extends ApiResponse> {

    /**
     * Registers a success handler to be invoked
     * when the operation completes successfully.
     *
     * @param handler the handler
     * @return future the future
     * @since 1.0.0
     */
    Future<T> onSuccess(Handler<T> handler);

    /**
     * Registers a failure handler to be invoked
     * when the operation does not complete successfully.
     *
     * @param throwable the throwable
     * @return future the future
     * @since 1.0.0
     */
    Future<T> onFailure(Handler<Throwable> throwable);

    /**
     * Invokes all registered success handlers with the given response.
     *
     * @param response the response object to handle
     * @since 1.0.0
     */
    void handleSuccess(T response);

    /**
     * Invokes all registered success handlers with the given throwable.
     *
     * @param throwable the throwable to handle
     * @since 1.0.0
     */
    void handleFailure(Throwable throwable);

}
