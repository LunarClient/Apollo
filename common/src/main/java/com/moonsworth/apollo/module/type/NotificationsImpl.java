package com.moonsworth.apollo.module.type;

import com.google.common.collect.Lists;
import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Notification;
import com.moonsworth.apollo.protocol.NotificationMessage;

import static java.util.Objects.requireNonNull;

/**
 * Provides the notifications module.
 *
 * @since 1.0.0
 */
public final class NotificationsImpl extends Notifications {

    public NotificationsImpl() {
        super();

        NetworkOptions.register(Notification.class, NotificationMessage.getDefaultInstance(), new OptionConverter<Notification, NotificationMessage>() {
            @Override
            public NotificationMessage to(final Notification object) throws IllegalArgumentException {
                return NotificationMessage.newBuilder()
                    .setTitle(object.getTitle())
                    .setDescription(object.getDescription())
                    .setResourceLocation(object.getResourceLocation())
                    .build();
            }

            @Override
            public Notification from(final NotificationMessage message) throws IllegalArgumentException {
                return Notification.of(
                    message.getTitle(),
                    message.getDescription(),
                    message.getResourceLocation()
                );
            }
        });
    }

    @Override
    public void notify(final ApolloPlayer player, final Notification notification) {
        requireNonNull(player, "player");
        requireNonNull(notification, "notification");
        this.getOptions().get(player).set(Notifications.NOTIFICATIONS, Lists.newArrayList(notification));
    }

    @Override
    public void notifyAll(final Notification notification) {
        requireNonNull(notification, "notification");
        this.getOptions().set(Notifications.NOTIFICATIONS, Lists.newArrayList(notification));
    }
}
