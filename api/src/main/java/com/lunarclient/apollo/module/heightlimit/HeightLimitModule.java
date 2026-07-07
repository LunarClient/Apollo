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
package com.lunarclient.apollo.module.heightlimit;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.ListOption;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.recipients.Recipients;
import io.leangen.geantyref.TypeToken;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the height limit module.
 *
 * <p>Sets the build-height limit shown by the clients Height Limit mod
 * (block overlay and HUD display). The client resolves the active limit
 * against the world the player is currently in.</p>
 *
 * @since 1.2.9
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "height_limit", name = "Height Limit")
public abstract class HeightLimitModule extends ApolloModule {

    /**
     * Returns the default list of height limits to send to the player.
     *
     * @since 1.2.9
     */
    public static final ListOption<HeightLimit> DEFAULT_HEIGHT_LIMITS = Option.<HeightLimit>list()
        .comment("Sets the default height limits to send to the player.")
        .node("default-height-limits").type(new TypeToken<List<HeightLimit>>() {})
        .defaultValue(new ArrayList<>())
        .build();

    protected HeightLimitModule() {
        this.registerOptions(
            ApolloModule.ENABLE_OPTION_OFF,
            HeightLimitModule.DEFAULT_HEIGHT_LIMITS
        );
    }

    /**
     * Overrides the {@link HeightLimit} for the {@link Recipients}.
     *
     * <p>Sending a height limit for an already-known world replaces
     * that worlds entry.</p>
     *
     * @param recipients  the recipients that are receiving the packet
     * @param heightLimit the height limit
     * @since 1.2.9
     */
    public abstract void overrideHeightLimit(Recipients recipients, HeightLimit heightLimit);

    /**
     * Removes the {@link HeightLimit} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param world      the world name
     * @since 1.2.9
     */
    public abstract void removeHeightLimit(Recipients recipients, String world);

    /**
     * Removes the {@link HeightLimit} from the {@link Recipients}.
     *
     * @param recipients  the recipients that are receiving the packet
     * @param heightLimit the height limit
     * @since 1.2.9
     */
    public abstract void removeHeightLimit(Recipients recipients, HeightLimit heightLimit);

    /**
     * Resets all {@link HeightLimit}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.2.9
     */
    public abstract void resetHeightLimits(Recipients recipients);

}
