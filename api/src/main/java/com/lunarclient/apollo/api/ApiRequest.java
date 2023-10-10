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
package com.lunarclient.apollo.api;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * The {@link ApiRequest} interface represents a request object for making API calls.
 *
 * @param <T> the expected {@link ApiResponse}
 * @since 1.0.0
 */
public interface ApiRequest<T extends ApiResponse> {

    /**
     * Gets the service type (e.g. API, Analytics).
     *
     * @return the service type
     * @since 1.0.0
     */
    ApiServiceType getService();

    /**
     * Gets the type of API request (e.g. GET, POST).
     *
     * @return the API request type
     * @since 1.0.0
     */
    ApiRequestType getType();

    /**
     * Gets the URL endpoint for the API request.
     *
     * @return the route or URL endpoint
     * @since 1.0.0
     */
    String getRoute();

    /**
     * Gets the gson token type.
     *
     * @return the token type
     * @since 1.0.0
     */
    default Type getResponseType() {
        ParameterizedType parameterized = (ParameterizedType) this.getClass().getGenericInterfaces()[0];
        return parameterized.getActualTypeArguments()[0];
    }

}
