package com.lunarclient.apollo.module.notification;

import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Arrays;
import java.util.Collection;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the notification module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "notification", name = "Notification")
public abstract class NotificationModule extends ApolloModule {

    @Override
    public Collection<ApolloPlatform.Kind> getSupport() {
        return Arrays.asList(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

    /**
     * Displays the {@link Notification} to the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param notification the notification
     * @since 1.0.0
     */
    public abstract void displayNotification(ApolloPlayer viewer, Notification notification);

    /**
     * Displays the {@link Notification} to all {@link ApolloPlayer}s.
     *
     * @param notification the notification
     * @since 1.0.0
     */
    public abstract void broadcastNotification(Notification notification);

}
