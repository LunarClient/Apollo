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
package com.lunarclient.apollo.module.transfer;

import com.lunarclient.apollo.roundtrip.ApolloResponse;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.Range;

/**
 * Represents the server ping response.
 *
 * @since 1.0.0
 */
@Getter
@SuperBuilder
public final class PingResponse extends ApolloResponse {

    /**
     * Returns a {@link List} of {@link PingData}s.
     *
     * @return the server data list
     * @since 1.0.0
     */
    List<PingData> data;

    /**
     * Represents the server data.
     *
     * @since 1.0.0
     */
    @Getter
    @Builder
    public static class PingData {

        /**
         * Returns the pinged server {@link String} IP.
         *
         * @return the server IP
         * @since 1.0.0
         */
        String serverIp;

        /**
         * Returns the pinged server {@link Status} status.
         *
         * @return the ping status
         * @since 1.0.0
         */
        Status status;

        /**
         * Returns the pinged server {@link Integer} ping, in milliseconds.
         *
         * @return the ping
         * @since 1.0.0
         */
        @Range(from = 0, to = Integer.MAX_VALUE) int pingMillis;

        /**
         * Represents the ping data state.
         *
         * @since 1.0.0
         */
        public enum Status {

            SUCCESS,
            TIMED_OUT

        }

    }

}
