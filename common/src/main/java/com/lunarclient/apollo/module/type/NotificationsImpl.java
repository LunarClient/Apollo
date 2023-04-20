package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Notification;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.NotificationMessage;

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
    public void sendNotification(ApolloPlayer player, Notification notification) {
        requireNonNull(player, "player");
        requireNonNull(notification, "notification");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.ADD, this.to(notification));
    }

    @Override
    public void broadcastNotification(Notification notification) {
        requireNonNull(notification, "notification");

        for(ApolloPlayer player : Apollo.getPlayerManager().getPlayers()) {
            ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.ADD, this.to(notification));
        }
    }

    private NotificationMessage to(Notification notification) {
        return NotificationMessage.newBuilder()
                .setTitle(notification.getTitle())
                .setDescription(notification.getDescription())
                .setResourceLocation(notification.getResourceLocation())
                .build();
    }

    private Notification from(NotificationMessage message) {
        return Notification.of(
                message.getTitle(),
                message.getDescription(),
                message.getResourceLocation()
        );
    }

}
