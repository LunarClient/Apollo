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
package com.lunarclient.apollo.loader;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides a utility for dynamic dependencies.
 *
 * @since 1.0.0
 */
public final class DynamicDependencies {

    /**
     * Returns an array of dependency resource paths to be loaded dynamically.
     *
     * @return an array of dynamic dependencies
     * @since 1.0.0
     */
    public static String[] discoverDependencies() {
        List<String> resources = new ArrayList<>();
        resources.add("platform/libs.jarinjar");

        try {
            Class.forName("net.kyori.adventure.Adventure");
        } catch(Exception ignored) {
            resources.add("adventure/4/dependencies.jarinjar");
        }

        resources.add("adventure/4/libs.jarinjar");

        return resources.toArray(new String[0]);
    }

    private DynamicDependencies() {
    }

}
