package com.lunarclient.apollo.module.notification;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.notification.v1.DisplayNotificationMessage;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.NonNull;

/**
 * Provides the notifications module.
 *
 * @since 1.0.0
 */
public final class NotificationModuleImpl extends NotificationModule {

    @Override
    public void displayNotification(@NonNull ApolloPlayer viewer, @NonNull Notification notification) {
        ((AbstractApolloPlayer) viewer).sendPacket(this.toProtobuf(notification));
    }

    @Override
    public void broadcastNotification(@NonNull Notification notification) {
        DisplayNotificationMessage message = this.toProtobuf(notification);

        for (ApolloPlayer player : Apollo.getPlayerManager().getPlayers()) {
            ((AbstractApolloPlayer) player).sendPacket(message);
        }
    }

    private DisplayNotificationMessage toProtobuf(Notification notification) {
        return DisplayNotificationMessage.newBuilder()
            .setTitle(notification.getTitle())
            .setDescription(notification.getDescription())
            .setResourceLocation(notification.getResourceLocation())
            .build();
    }

}

