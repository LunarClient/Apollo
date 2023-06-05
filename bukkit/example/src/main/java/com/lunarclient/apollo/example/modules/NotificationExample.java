package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.notification.Notification;
import com.lunarclient.apollo.module.notification.NotificationModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Optional;

public class NotificationExample {

    private final NotificationModule notificationModule = Apollo.getModuleManager().getModule(NotificationModule.class);

    private final Notification uhcAnnouncement = Notification.builder()
        .title("UHC Announcement")
        .description("UHC starts in 5 minutes...")
        .resourceLocation("icons/golden_apple.png") // This field is optional
        .displayTime(Duration.ofSeconds(5))
        .build();

    public void displayNotificationExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.notificationModule.displayNotification(apolloPlayer, this.uhcAnnouncement));
    }

    public void broadcastNotificationExample() {
        this.notificationModule.broadcastNotification(this.uhcAnnouncement);
    }

}
