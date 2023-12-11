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
package com.lunarclient.apollo.module.combat;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Represents the combat module.
 *
 * @since 1.0.4
 */
@ModuleDefinition(id = "combat", name = "Combat")
public final class CombatModule extends ApolloModule {

    /**
     * Whether the player gets a hit delay for a missed hit.
     *
     * @since 1.0.4
     */
    public static final SimpleOption<Boolean> DISABLE_MISS_PENALTY = Option.<Boolean>builder()
        .comment("Set to 'true' to disable the hit delay on 1.8, otherwise 'false'.")
        .node("competitive-game").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    CombatModule() {
        this.registerOptions(
            CombatModule.DISABLE_MISS_PENALTY
        );
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

}
