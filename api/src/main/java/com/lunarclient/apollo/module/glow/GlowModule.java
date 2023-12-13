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
package com.lunarclient.apollo.module.glow;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.recipients.Recipients;
import java.awt.Color;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the glow module.
 *
 * @since 1.0.5
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "glow", name = "Glow")
public abstract class GlowModule extends ApolloModule {

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Overrides the glow effect for the glowingPlayer, visible by the viewers.
     *
     * @param recipients    the recipients that are receiving the packet
     * @param glowingPlayer the UUID of the player whose glowing effect will be overwrote
     * @param color         the new color glowingPlayer should glow in.
     * @since 1.0.5
     */
    public abstract void overrideGlow(Recipients recipients, UUID glowingPlayer, Color color);

    /**
     * Resets the glowing effect for the glowingPlayer, visible by the viewers.
     *
     * @param recipients    the recipients that are receiving the packet
     * @param glowingPlayer the UUID of the player whose glowing effect was overwrote
     * @since 1.0.5
     */
    public abstract void resetGlow(Recipients recipients, UUID glowingPlayer);

    /**
     * Resets all glowing effect overrides for the given {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.0.5
     */
    public abstract void resetGlow(Recipients recipients);

}
