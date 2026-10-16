/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.module.totemcounter;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.recipients.Recipients;
import io.leangen.geantyref.TypeToken;
import java.util.Collection;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the totem counter module.
 *
 * <p>Overrides the totem pops shown by the client's Totem Counter mod.
 * The client keeps counting totem pops on its own on top of the overridden
 * values, unless {@link #DISABLE_LOCAL_TRACKING} is enabled.</p>
 *
 * @since 1.3.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "totem_counter", name = "Totem Counter")
public abstract class TotemCounterModule extends ApolloModule {

    /**
     * Whether the client stops tracking and resetting totem pops on its own.
     *
     * @since 1.3.0
     */
    public static final SimpleOption<Boolean> DISABLE_LOCAL_TRACKING = Option.<Boolean>builder()
        .comment("Set to 'true' to stop the client from tracking and resetting totem pops on its own, otherwise 'false'.")
        .node("disable-local-tracking").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    protected TotemCounterModule() {
        this.registerOptions(
            TotemCounterModule.DISABLE_LOCAL_TRACKING
        );
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Overrides the {@link TotemCounter}s for the {@link Recipients}.
     *
     * @param recipients    the recipients that are receiving the packet
     * @param totemCounters the totem counters
     * @since 1.3.0
     */
    public abstract void overrideTotemCounters(Recipients recipients, Collection<TotemCounter> totemCounters);

    /**
     * Overrides the {@link TotemCounter} for the {@link Recipients}.
     *
     * @param recipients   the recipients that are receiving the packet
     * @param totemCounter the totem counter
     * @since 1.3.0
     */
    public abstract void overrideTotemCounter(Recipients recipients, TotemCounter totemCounter);

    /**
     * Resets the {@link TotemCounter} for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param playerUuid the player whose totem counter we are manipulating
     * @since 1.3.0
     */
    public abstract void resetTotemCounter(Recipients recipients, UUID playerUuid);

    /**
     * Resets the {@link TotemCounter} for the {@link Recipients}.
     *
     * @param recipients   the recipients that are receiving the packet
     * @param totemCounter the totem counter
     * @since 1.3.0
     */
    public abstract void resetTotemCounter(Recipients recipients, TotemCounter totemCounter);

    /**
     * Resets the {@link TotemCounter}s for the {@link Recipients}.
     *
     * @param recipients  the recipients that are receiving the packet
     * @param playerUuids the players whose totem counters we are manipulating
     * @since 1.3.0
     */
    public abstract void resetTotemCounter(Recipients recipients, Collection<UUID> playerUuids);

    /**
     * Resets all {@link TotemCounter}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.3.0
     */
    public abstract void resetTotemCounters(Recipients recipients);

}
