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
package com.lunarclient.apollo.module.serverrule;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.ListOption;
import com.lunarclient.apollo.option.NumberOption;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the server rule module.
 *
 * @since 1.0.0
 */
@ModuleDefinition(id = "server_rule", name = "Server Rule")
public final class ServerRuleModule extends ApolloModule {

    /**
     * Whether the player should see a popup prior to disconnecting.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> COMPETITIVE_GAME = Option.<Boolean>builder()
        .comment("Set to 'true' to enable leaving game warning, otherwise 'false'.")
        .node("competitive-game").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    /**
     * A list of commands that will trigger the competitive popup.
     *
     * @since 1.0.0
     */
    public static final ListOption<String> COMPETITIVE_COMMANDS = Option.<String>list()
        .comment("A list of commands that will trigger the competitive popup.")
        .node("competitive-commands").type(new TypeToken<List<String>>() {})
        .defaultValue(new ArrayList<>(Arrays.asList("/server", "/servers", "/hub")))
        .notifyClient().build();

    /**
     * Disables shaders.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_SHADERS = Option.<Boolean>builder()
        .comment("Set to 'true' to disable shaders, otherwise 'false'.")
        .node("disable-shaders").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    /**
     * Disables chunk reloading (F3 + A) and disables shaders reload while using Iris (R).
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_CHUNK_RELOADING = Option.<Boolean>builder()
        .comment("Set to 'true' to disable chunk reloading, otherwise 'false'.")
        .node("disable-chunk-reloading").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    /**
     * Disables broadcast menu (F6).
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_BROADCASTING = Option.<Boolean>builder()
        .comment("Set to 'true' to disable broadcasting, otherwise 'false'.")
        .node("disable-broadcasting").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    /**
     * Anti portal traps.
     *
     * <p>Allows players to open their chat while in a portal.</p>
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ANTI_PORTAL_TRAPS = Option.<Boolean>builder()
        .comment("Set to 'true' to enable anti portal traps, otherwise 'false'.")
        .node("anti-portal-traps").type(TypeToken.get(Boolean.class))
        .defaultValue(true).notifyClient().build();

    /**
     * Override brightness.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> OVERRIDE_BRIGHTNESS = Option.<Boolean>builder()
        .comment("Set to 'true' to override brightness, otherwise 'false'.")
        .node("override-brightness").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    /**
     * Sets brightness amount.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> BRIGHTNESS = Option.<Integer>number()
        .comment("Set the brightness amount.")
        .node("brightness").type(TypeToken.get(Integer.class))
        .defaultValue(50).min(1).max(10000).notifyClient().build();

    /**
     * Override nametag render distance.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> OVERRIDE_NAMETAG_RENDER_DISTANCE = Option.<Boolean>builder()
        .comment("Set to 'true' to override nametag render distance, otherwise 'false'.")
        .node("override-nametag-render-distance").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    /**
     * Sets the nametag render distance amount.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> NAMETAG_RENDER_DISTANCE = Option.<Integer>number()
        .comment("Set the nametag render distance amount.")
        .node("nametag-render-distance").type(TypeToken.get(Integer.class))
        .defaultValue(64).min(1).max(96).notifyClient().build();

    /**
     * Override max chat length.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> OVERRIDE_MAX_CHAT_LENGTH = Option.<Boolean>builder()
        .comment("Set to 'true' to override max chat length, otherwise 'false'.")
        .node("override-max-chat-length").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    /**
     * Sets the max chat length amount.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> MAX_CHAT_LENGTH = Option.<Integer>number()
        .comment("Set the nametag render distance amount.")
        .node("max-chat-length").type(TypeToken.get(Integer.class))
        .defaultValue(256).min(1).max(256).notifyClient().build();

    ServerRuleModule() {
        this.registerOptions(
            ServerRuleModule.COMPETITIVE_GAME,
            ServerRuleModule.COMPETITIVE_COMMANDS,
            ServerRuleModule.DISABLE_SHADERS,
            ServerRuleModule.DISABLE_CHUNK_RELOADING,
            ServerRuleModule.DISABLE_BROADCASTING,
            ServerRuleModule.ANTI_PORTAL_TRAPS,
            ServerRuleModule.OVERRIDE_BRIGHTNESS,
            ServerRuleModule.BRIGHTNESS,
            ServerRuleModule.OVERRIDE_NAMETAG_RENDER_DISTANCE,
            ServerRuleModule.NAMETAG_RENDER_DISTANCE,
            ServerRuleModule.OVERRIDE_MAX_CHAT_LENGTH,
            ServerRuleModule.MAX_CHAT_LENGTH
        );
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

}
