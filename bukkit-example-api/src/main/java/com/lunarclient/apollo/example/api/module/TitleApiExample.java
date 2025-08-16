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
package com.lunarclient.apollo.example.api.module;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.example.module.impl.TitleExample;
import com.lunarclient.apollo.module.title.Title;
import com.lunarclient.apollo.module.title.TitleModule;
import com.lunarclient.apollo.module.title.TitleType;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.time.Duration;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class TitleApiExample extends TitleExample {

    private final TitleModule titleModule = Apollo.getModuleManager().getModule(TitleModule.class);

    @Override
    public void displayTitleExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.titleModule.displayTitle(apolloPlayer, Title.builder()
            .type(TitleType.TITLE)
            .message(Component.text()
                .content("Hello, player!")
                .color(NamedTextColor.GREEN)
                .decorate(TextDecoration.BOLD)
                .build())
            .scale(1.0f)
            .displayTime(Duration.ofMillis(1500L))
            .fadeInTime(Duration.ofMillis(250))
            .fadeOutTime(Duration.ofMillis(300))
            .build()));
    }

    @Override
    public void displayTitleInterpolatedExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.titleModule.displayTitle(apolloPlayer, Title.builder()
            .type(TitleType.TITLE)
            .message(Component.text()
                .content("This title expands!")
                .color(NamedTextColor.GREEN)
                .decorate(TextDecoration.BOLD)
                .build())
            .scale(0.1f)
            .interpolationScale(1.0f)
            .interpolationRate(0.01f)
            .displayTime(Duration.ofMillis(5000L))
            .fadeInTime(Duration.ofMillis(250))
            .fadeOutTime(Duration.ofMillis(300))
            .build()));
    }

    @Override
    public void resetTitlesExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.titleModule::resetTitles);
    }

    @Override
    public void setClearTitleOnServerSwitch(boolean value) {
        this.titleModule.getOptions().set(TitleModule.CLEAR_TITLE_ON_SERVER_SWITCH, value);
    }

}
