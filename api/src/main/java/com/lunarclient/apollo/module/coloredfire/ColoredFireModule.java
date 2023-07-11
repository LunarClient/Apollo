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
package com.lunarclient.apollo.module.coloredfire;

import com.lunarclient.apollo.audience.Audience;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import java.awt.Color;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the colored fire module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "colored_fire", name = "Colored Fire")
public abstract class ColoredFireModule extends ApolloModule {

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Overrides the burning fire color for the burningPlayer, visible by the viewers.
     *
     * @param audience      the audience that is receiving the packet
     * @param burningPlayer the UUID of the player whose burning color will be overwrote
     * @param color         the new color burningPlayer should burn in.
     * @since 1.0.0
     */
    public abstract void overrideColoredFire(Audience audience, UUID burningPlayer, Color color);

    /**
     * Resets the burning fire color for the burningPlayer, visible by the viewers.
     *
     * @param audience      the audience that is receiving the packet
     * @param burningPlayer the UUID of the player whose burning color was overwrote
     * @since 1.0.0
     */
    public abstract void resetColoredFire(Audience audience, UUID burningPlayer);

    /**
     * Resets all colored fire overrides for the given {@link Audience}.
     *
     * @param audience the audience that is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetColoredFires(Audience audience);

}
