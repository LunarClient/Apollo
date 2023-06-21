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
package com.lunarclient.apollo.module.modsetting;

import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.ListOption;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.player.ApolloPlayer;
import io.leangen.geantyref.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the mod settings module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "mod_setting", name = "Mod Setting")
public abstract class ModSettingModule extends ApolloModule {

    private static final ModSettings SKYBLOCK_ADDONS_SETTING = ModSettings.builder()
            .target("skyblockAddons")
            .enable(false)
            .properties(null)
            .build();

    /**
     * A list of mod settings.
     *
     * @since 1.0.0
     */
    public static final ListOption<ModSettings> MOD_SETTINGS = Option.<ModSettings>list()
        .comment("A list of mod settings to send to the client.")
        .node("settings").type(new TypeToken<List<ModSettings>>() {})
        .defaultValue(new ArrayList<>(Collections.singletonList(SKYBLOCK_ADDONS_SETTING)))
        .notifyClient()
        .build();

    ModSettingModule() {
        this.registerOptions(MOD_SETTINGS);
    }

    @Override
    public Collection<ApolloPlatform.Kind> getSupportedPlatforms() {
        return Arrays.asList(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Sends the {@link ModsSettings} to the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param settings the settings
     * @since 1.0.0
     */
    public abstract void sendSettings(ApolloPlayer viewer, ModsSettings settings);

    /**
     * Resets all {@link ModsSettings}s for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetSettings(ApolloPlayer viewer);

    /**
     * Sends the {@link ModsSettings} to all {@link ApolloPlayer}s.
     *
     * @param settings the settings
     * @since 1.0.0
     */
    public abstract void broadcastSettings(ModsSettings settings);

}
