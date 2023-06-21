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
package com.lunarclient.apollo.module.tntcountdown;

import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the tnt countdown module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "tnt_countdown", name = "TNT Countdown")
public abstract class TntCountdownModule extends ApolloModule {

    /**
     * Set the amount of ticks before the TNT explodes.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> TNT_TICKS = NumberOption.<Integer>number()
            .comment("Set the amount of ticks before the TNT explodes.")
            .node("tnt-ticks").type(TypeToken.get(Integer.class))
            .defaultValue(80).min(1).max(Integer.MAX_VALUE)
            .notifyClient().build();

    TntCountdownModule() {
        this.registerOptions(
            TntCountdownModule.TNT_TICKS
        );
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Set the amount of ticks before the specified TNT explodes.
     *
     * @param entity the TNT entity
     * @param ticks the ticks until explosion
     * @since 1.0.0
     */
    public abstract void setTntCountdown(ApolloEntity entity, int ticks);

}
