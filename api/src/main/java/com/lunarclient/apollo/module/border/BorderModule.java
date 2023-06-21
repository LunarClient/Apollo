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
package com.lunarclient.apollo.module.border;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the border module.
 *
 * <p>This module represents the border added in
 * version 1.8 (47) and was back-ported to 1.7.</p>
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "border", name = "Border")
public abstract class BorderModule extends ApolloModule {

    /**
     * Displays the {@link Border} to the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param border the border
     * @since 1.0.0
     */
    public abstract void displayBorder(ApolloPlayer viewer, Border border);

    /**
     * Removes the {@link Border} from the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param borderId the border id
     * @since 1.0.0
     */
    public abstract void removeBorder(ApolloPlayer viewer, String borderId);

    /**
     * Removes the {@link Border} from the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param border the border
     * @since 1.0.0
     */
    public abstract void removeBorder(ApolloPlayer viewer, Border border);

    /**
     * Resets all {@link BorderModule}s for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetBorders(ApolloPlayer viewer);

}
