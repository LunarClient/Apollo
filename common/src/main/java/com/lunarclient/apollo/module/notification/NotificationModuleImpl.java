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

import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.notification.v1.DisplayNotificationMessage;
import com.lunarclient.apollo.notification.v1.ResetNotificationsMessage;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import lombok.NonNull;

/**
 * Provides the notifications module.
 *
 * @since 1.0.0
 */
public final class NotificationModuleImpl extends NotificationModule {

    @Override
    public void displayNotification(@NonNull Recipients recipients, @NonNull Notification notification) {
        DisplayNotificationMessage.Builder builder = DisplayNotificationMessage.newBuilder()
            .setTitle(notification.getTitle())
            .setDescription(notification.getDescription())
            .setDisplayTime(NetworkTypes.toProtobuf(notification.getDisplayTime()));

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
