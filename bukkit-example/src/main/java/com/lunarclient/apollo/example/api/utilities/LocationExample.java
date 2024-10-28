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
package com.lunarclient.apollo.example.api.utilities;

import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.common.location.ApolloPlayerLocation;

public final class LocationExample {

    public static ApolloBlockLocation blockLocationExample() {
        return ApolloBlockLocation.builder()
            .world("world")
            .x(0)
            .y(100)
            .z(0)
            .build();
    }

    public static ApolloLocation locationExample() {
        return ApolloLocation.builder()
            .world("world")
            .x(50.5D)
            .y(100)
            .z(50.0D)
            .build();
    }

    public static ApolloPlayerLocation playerLocationExample() {
        return ApolloPlayerLocation.builder()
            .location(ApolloLocation.builder()
                .world("world")
                .x(50.5D)
                .y(100)
                .z(50.0D)
                .build())
            .yaw(180.0F)
            .pitch(90.0F)
            .build();
    }

    private LocationExample() {
    }

}
