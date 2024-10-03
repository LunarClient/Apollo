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

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.notification.Notification;
import com.lunarclient.apollo.module.notification.NotificationModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.time.Duration;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class NotificationExample {

    private final NotificationModule notificationModule = Apollo.getModuleManager().getModule(NotificationModule.class);

    private final Notification uhcAnnouncement = Notification.builder()
        .titleComponent(Component.text("UHC Announcement", NamedTextColor.GREEN))
        .descriptionComponent(Component.text("UHC starts in 5 minutes...", NamedTextColor.RED)
            .append(Component.newline())
            .append(Component.text("Get ready!", NamedTextColor.WHITE))
            .append(Component.newline())
            .append(Component.text("Good luck!", NamedTextColor.GOLD))
        )
        .resourceLocation("icons/golden_apple.png") // This field is optional
        .displayTime(Duration.ofSeconds(5))
        .build();

    public void displayNotificationExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.notificationModule.displayNotification(apolloPlayer, this.uhcAnnouncement));
    }

    public void resetNotificationsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.notificationModule::resetNotifications);
    }

}
