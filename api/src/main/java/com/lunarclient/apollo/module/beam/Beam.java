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
package com.lunarclient.apollo.module.beam;

import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import java.awt.Color;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a beacon beam which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class Beam {

    /**
     * Returns the beam {@link String} id.
     *
     * @return the beam id
     * @since 1.0.0
     */
    String id;

    /**
     * Returns the beam {@link Color}.
     *
     * @return the beam color
     * @since 1.0.0
     */
    Color color;

    /**
     * Returns the beam {@link ApolloBlockLocation}.
     *
     * @return the beam block location
     * @since 1.0.0
     */
    ApolloBlockLocation location;

}
