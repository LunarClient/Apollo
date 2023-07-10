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
package com.lunarclient.apollo.example.modules;

import com.google.common.collect.Lists;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.Component;
import com.lunarclient.apollo.module.title.Title;
import com.lunarclient.apollo.module.title.TitleModule;
import com.lunarclient.apollo.module.title.TitleType;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.awt.Color;
import java.time.Duration;
import java.util.Optional;
import org.bukkit.entity.Player;

public class TitleExample {

    private final TitleModule titleModule = Apollo.getModuleManager().getModule(TitleModule.class);

    private final Title helloTitle = Title.builder()
        .type(TitleType.TITLE)
        .message(Component.builder()
            .content("Hello, player!")
            .color(Color.GREEN)
            .decorators(Lists.newArrayList(Component.TextDecorators.BOLD))
            .build())
        .scale(1.0f)
        .displayTime(Duration.ofMillis(1500L))
        .fadeInTime(Duration.ofMillis(250))
        .fadeOutTime(Duration.ofMillis(300))
        .build();

    public void displayTitleExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        // Displaying the title built by the title builder above, "helloTitle", to the player.
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.titleModule.displayTitle(apolloPlayer, this.helloTitle));
    }

    public void resetTitlesExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.titleModule::resetTitles);
    }

}
