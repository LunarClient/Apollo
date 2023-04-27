package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Notification;
import java.util.Arrays;
import java.util.Collection;
import org.jetbrains.annotations.ApiStatus;

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
     * Displays the {@link Notification} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param notification the notification
     * @since 1.0.0
     */
    public abstract void displayNotification(ApolloPlayer player, Notification notification);

    /**
     * Displays the {@link Notification} to all {@link ApolloPlayer}s.
     *
     * @param notification the notification
     * @since 1.0.0
     */
    public abstract void broadcastNotification(Notification notification);

}
