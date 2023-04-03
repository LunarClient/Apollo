package com.moonsworth.apollo.module.type;

import com.google.protobuf.Any;
import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Notification;
import lunarclient.apollo.common.MessageOperation;
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
    public void sendNotification(final ApolloPlayer player, final Notification notification) {
        requireNonNull(player, "player");
        requireNonNull(notification, "notification");

        ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                .setModule(this.getName())
                .setOperation(OptionOperation.ADD)
                .setValue(Any.pack(this.to(notification)))
                .build());
    }

    @Override
    public void broadcastNotification(final Notification notification) {
        requireNonNull(notification, "notification");

        for(final ApolloPlayer player : Apollo.getPlayerManager().getPlayers()) {
            ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                    .setModule(this.getName())
                    .setOperation(OptionOperation.ADD)
                    .setValue(Any.pack(this.to(notification)))
                    .build());
        }
    }

    private NotificationMessage to(final Notification notification) {
        return NotificationMessage.newBuilder()
                .setTitle(notification.getTitle())
                .setDescription(notification.getDescription())
                .setResourceLocation(notification.getResourceLocation())
                .build();
    }

    private Notification from(final NotificationMessage message) {
        return Notification.of(
                message.getTitle(),
                message.getDescription(),
                message.getResourceLocation()
        );
    }

}
