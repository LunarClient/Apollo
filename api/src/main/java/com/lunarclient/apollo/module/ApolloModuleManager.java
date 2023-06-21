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
package com.lunarclient.apollo.module;

import java.util.Collection;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the module manager for Apollo.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public interface ApolloModuleManager {

    /**
     * Returns {@code true} if the specified {@link ApolloModule} is enabled,
     * otherwise returns {@code false}.
     *
     * @param moduleClass the module class
     * @return true if the module is enabled, otherwise false
     * @since 1.0.0
     */
    boolean isEnabled(Class<? extends ApolloModule> moduleClass);

    /**
     * Returns the type {@code T} {@link ApolloModule} with the specified
     * {@link Class} if it exists, otherwise returns null.
     *
     * @param moduleClass the module class
     * @param <T> the apollo module type
     * @return the apollo module, if present
     * @since 1.0.0
     */
    <T extends ApolloModule> T getModule(Class<T> moduleClass);

    /**
     * Gets a collection of {@link ApolloModule}s.
     *
     * @return a list of apollo modules
     * @since 1.0.0
     */
    Collection<ApolloModule> getModules();

}
