package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.notification.v1.DisplayNotificationMessage;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Notification;

import static java.util.Objects.requireNonNull;

/**
 * Provides the notifications module.
 *
 * @since 1.0.0
 */
public final class NotificationsImpl extends Notifications {

    public NotificationsImpl() {
        super();
    }

    @Override
    public void displayNotification(ApolloPlayer player, Notification notification) {
        requireNonNull(player, "player");
        requireNonNull(notification, "notification");

        ((AbstractApolloPlayer) player).sendPacket(this.toProtobuf(notification));
    }

    @Override
    public void broadcastNotification(Notification notification) {
        requireNonNull(notification, "notification");

        DisplayNotificationMessage message = this.toProtobuf(notification);

        for(ApolloPlayer player : Apollo.getPlayerManager().getPlayers()) {
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
