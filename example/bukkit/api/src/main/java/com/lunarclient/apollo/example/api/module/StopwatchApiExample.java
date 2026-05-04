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
package com.lunarclient.apollo.example.api.module;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.ApolloColors;
import com.lunarclient.apollo.common.location.HudPosition;
import com.lunarclient.apollo.example.module.impl.StopwatchExample;
import com.lunarclient.apollo.module.stopwatch.Stopwatch;
import com.lunarclient.apollo.module.stopwatch.StopwatchModule;
import com.lunarclient.apollo.module.stopwatch.Timer;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.time.Duration;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class StopwatchApiExample extends StopwatchExample {

    private final StopwatchModule stopwatchModule = Apollo.getModuleManager().getModule(StopwatchModule.class);

    @Override
    public void addStopwatchExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.stopwatchModule.addStopwatch(apolloPlayer, Stopwatch.builder()
                .id("parkour-stopwatch")
                .name("Parkour")
                .resetOnStart(true)
                .preventModification(true)
                .hideWhenStopped(false)
                .displayFormat("mm:ss")
                .textColor(ApolloColors.DARK_AQUA)
                .hudPosition(HudPosition.builder()
                    .x(-30)
                    .y(30)
                    .build()
                )
                .build());
        });
    }

    @Override
    public void removeStopwatchExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.stopwatchModule.removeStopwatch(apolloPlayer, "parkour-stopwatch"));
    }

    @Override
    public void startStopwatchExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.stopwatchModule.startStopwatch(apolloPlayer, "parkour-stopwatch"));
    }

    @Override
    public void stopStopwatchExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.stopwatchModule.stopStopwatch(apolloPlayer, "parkour-stopwatch"));
    }

    @Override
    public void resetStopwatchExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.stopwatchModule.resetStopwatch(apolloPlayer, "parkour-stopwatch"));
    }

    @Override
    public void resetStopwatchesExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.stopwatchModule::resetStopwatches);
    }

    @Override
    public void addTimerExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.stopwatchModule.addTimer(apolloPlayer, Timer.builder()
                .id("game-timer")
                .name("Countdown")
                .duration(Duration.ofSeconds(45))
                .loop(false)
                .preventModification(true)
                .hideWhenStopped(false)
                .displayFormat("mm:ss")
                .titleText(Component.text("Time's up!", NamedTextColor.RED))
                .inGameNotification(true)
                .textColor(ApolloColors.LIGHT_PURPLE)
                .hudPosition(HudPosition.builder()
                    .x(-10)
                    .y(30)
                    .build()
                )
                .build());
        });
    }

    @Override
    public void removeTimerExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.stopwatchModule.removeTimer(apolloPlayer, "game-timer"));
    }

    @Override
    public void startTimerExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.stopwatchModule.startTimer(apolloPlayer, "game-timer"));
    }

    @Override
    public void stopTimerExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.stopwatchModule.stopTimer(apolloPlayer, "game-timer"));
    }

    @Override
    public void resetTimerExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.stopwatchModule.resetTimer(apolloPlayer, "game-timer"));
    }

    @Override
    public void resetTimersExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.stopwatchModule::resetTimers);
    }

}
