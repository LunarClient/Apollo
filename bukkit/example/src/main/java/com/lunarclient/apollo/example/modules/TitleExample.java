package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.Component;
import com.lunarclient.apollo.module.title.Title;
import com.lunarclient.apollo.module.title.TitleModule;
import com.lunarclient.apollo.module.title.TitleType;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.awt.Color;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class TitleExample {

    private final TitleModule titleModule = Apollo.getModuleManager().getModule(TitleModule.class);

    private final Title title = Title.builder()
        .type(TitleType.TITLE)
        .message(Component.builder()
            .content("Hello, player!")
            .color(Color.GREEN)
            .decorators(List.of(Component.TextDecorators.BOLD))
            .build())
        .scale(1.0f)
        .displayTime(Duration.ofMillis(1500L))
        .fadeInTime(Duration.ofMillis(250))
        .fadeOutTime(Duration.ofMillis(300))
        .build();

    public void displayTitleMessageExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.titleModule.displayTitle(apolloPlayer, this.title));
    }

    public void broadcastTitleExample() {
        this.titleModule.broadcastTitle(this.title);
    }

    public void resetTitlesExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.titleModule::resetTitles);
    }

}
