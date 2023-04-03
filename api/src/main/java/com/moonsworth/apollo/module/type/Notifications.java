package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.ApolloPlatform;
import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Notification;
import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;
import java.util.Collection;

/**
 * Represents the notification module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Notifications extends ApolloModule {

    Notifications() {
        super("Notifications");
    }

    @Override
    public Collection<ApolloPlatform.Kind> getSupport() {
        return Arrays.asList(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

    /**
     * Sends the {@link Notification} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param notification the notification
     * @since 1.0.0
     */
    public abstract void sendNotification(final ApolloPlayer player, final Notification notification);

    /**
     * Sends the {@link Notification} to all {@link ApolloPlayer}s.
     *
     * @param notification the notification
     * @since 1.0.0
     */
    public abstract void broadcastNotification(final Notification notification);
}
