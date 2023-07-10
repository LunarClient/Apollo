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
package com.lunarclient.apollo.module.vignette;

import com.lunarclient.apollo.audience.Audience;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the vignette module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "vignette", name = "Vignette")
public abstract class VignetteModule extends ApolloModule {

    /**
     * Displays the {@link Vignette} to the {@link Audience}.
     *
     * @param audience the audience that is receiving the packet
     * @param vignette the vignette
     * @since 1.0.0
     */
    public abstract void displayVignette(Audience audience, Vignette vignette);

    /**
     * Resets the {@link Vignette} for the {@link Audience}.
     *
     * @param audience the audience that is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetVignette(Audience audience);

}
