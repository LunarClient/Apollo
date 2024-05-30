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
package com.lunarclient.apollo.module.notification;

import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.notification.v1.DisplayNotificationMessage;
import com.lunarclient.apollo.notification.v1.ResetNotificationsMessage;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import lombok.NonNull;
import net.kyori.adventure.text.Component;

/**
 * Provides the notifications module.
 *
 * @since 1.0.0
 */
public final class NotificationModuleImpl extends NotificationModule {

    @Override
    public void displayNotification(@NonNull Recipients recipients, @NonNull Notification notification) {
        DisplayNotificationMessage.Builder builder = DisplayNotificationMessage.newBuilder()
            .setDisplayTime(NetworkTypes.toProtobuf(notification.getDisplayTime()));

        String title = notification.getTitle();
        if (title != null) {
            builder.setTitle(title);
        }

        String description = notification.getDescription();
        if (description != null) {
            builder.setDescription(description);
        }

        Component titleComponent = notification.getTitleComponent();
        if (titleComponent != null) {
            builder.setTitleAdventureJsonLines(ApolloComponent.toJson(titleComponent));
        }

        Component descriptionComponent = notification.getDescriptionComponent();
        if (descriptionComponent != null) {
            builder.setDescriptionAdventureJsonLines(ApolloComponent.toJson(descriptionComponent));
        }

        String resourceLocation = notification.getResourceLocation();
        if (resourceLocation != null) {
            builder.setResourceLocation(resourceLocation);
        }

        DisplayNotificationMessage message = builder.build();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetNotifications(@NonNull Recipients recipients) {
        ResetNotificationsMessage message = ResetNotificationsMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

}
