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
package com.lunarclient.apollo.mods.impl;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;
import java.awt.Color;

/**
 * Shows you where your teammates are on the HUD.
 *
 * @since 1.0.0
 */
public final class ModTeamView {

    /**
     * No documentation available.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("team-view", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * On supported servers, use our Apollo plugin for team detection.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> APOLLO_TEAMS = SimpleOption.<Boolean>builder()
        .comment("On supported servers, use our Apollo plugin for team detection.")
        .node("team-view", "apollo-teams").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * If you are in a lobby, or in a game where your party members can be shown without giving you an unfair advantage (e.g. Skyblock), show them. Otherwise, if you're in a team game, show your teammate(s).
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> TEAMVIEW_HYPIXEL = SimpleOption.<Boolean>builder()
        .comment("If you are in a lobby, or in a game where your party members can be shown without giving you an unfair advantage (e.g. Skyblock), show them. Otherwise, if you're in a team game, show your teammate(s).")
        .node("team-view", "teamview-hypixel").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Color> HYPIXEL_TEAM_COLOR = SimpleOption.<Color>builder()
        .node("team-view", "hypixel-team-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(43, 255, 65))
        .notifyClient()
        .build();

    /**
     * No documentation available.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Color> HYPIXEL_PARTY_COLOR = SimpleOption.<Color>builder()
        .node("team-view", "hypixel-party-color").type(TypeToken.get(Color.class))
        .defaultValue(new Color(246, 198, 52))
        .notifyClient()
        .build();

    private ModTeamView() {
    }

}
