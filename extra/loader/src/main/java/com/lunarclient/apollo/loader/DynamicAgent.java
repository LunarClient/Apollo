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

import java.lang.instrument.Instrumentation;
import sun.management.Agent;

/**
 * Provides the {@link DynamicLoader} with access to the
 * {@link Instrumentation} instance.
 *
 * @since 1.0.0
 */
public final class DynamicAgent extends Agent {

    private static Instrumentation INSTRUMENTATION = null;

    /**
     * Called by the JVM when the agent is loaded.
     *
     * @param agentArgs the agent arguments
     * @param instrumentation the instrumentation instance
     * @since 1.0.0
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        agentmain(agentArgs, instrumentation);
    }

    /**
     * Called by the JVM when the agent is loaded.
     *
     * @param agentArgs the agent arguments
     * @param instrumentation the instrumentation instance
     * @since 1.0.0
     */
    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        if (DynamicAgent.INSTRUMENTATION == null) DynamicAgent.INSTRUMENTATION = instrumentation;
        if (DynamicAgent.INSTRUMENTATION == null) throw new NullPointerException("Unable to get instrumentation instance!");
    }

    /**
     * Returns the instrumentation instance.
     *
     * @return the instrumentation instance
     * @since 1.0.0
     */
    public static Instrumentation getInstrumentation() {
        return DynamicAgent.INSTRUMENTATION;
    }

}
