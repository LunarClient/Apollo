package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.google.protobuf.ByteString;
import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.protocol.NotificationMessage;

import java.util.ArrayList;
import java.util.List;

public class NotificationModule extends ApolloModule {

    public NotificationModule() {
        super("NotificationModule");
    }

    @Override
    public List<ApolloOption<?>> options() {
        return new ArrayList<>();
    }

    @Override
    public boolean notifyPlayers() {
        return false;
    }

    @Override
    public List<ApolloPlatform.Kind> runsOn() {
        return ImmutableList.of(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

    /**
     * Notify all ApolloPlayers online
     *
     * @param title       The title of the notification (accepts legacy colors)
     * @param description The body of the notification (accepts legacy colors)
     */
    public void notifyAll(String title, String description) {
        pushNotify(title, description, null, Apollo.getApolloPlayerManager().getApolloPlayers().toArray(new ApolloPlayer[0]));
    }

    /**
     * Notify all ApolloPlayers online
     *
     * @param title            The title of the notification (accepts legacy colors)
     * @param description      The body of the notification (accepts legacy colors)
     * @param resourceLocation The notification icon that will appear on the client.
     *                         By default, (null) it'll display a generic info message.
     */
    public void notifyAll(String title, String description, String resourceLocation) {
        pushNotify(title, description, resourceLocation, Apollo.getApolloPlayerManager().getApolloPlayers().toArray(new ApolloPlayer[0]));
    }

    /**
     * Notify a collection of ApolloPlayer.
     * @param title            The title of the notification (accepts legacy colors)
     * @param description      The body of the notification (accepts legacy colors)
     * @param resourceLocation The notification icon that will appear on the client.
     *                         By default, (null) it'll display a generic info message.
     * @param viewer All the recipients of the notification
     */
    public void pushNotify(String title, String description, String resourceLocation, ApolloPlayer<?>... viewer) {
        var builder = NotificationMessage.newBuilder().setTitle(ByteString.copyFromUtf8(title)).setDescription(ByteString.copyFromUtf8(description));
        if (resourceLocation != null) {
            builder = builder.setResourceLocation(ByteString.copyFromUtf8(resourceLocation));
        }
        NotificationMessage notificationMessage = builder.build();
        for (ApolloPlayer<?> player : viewer) {
            player.sendPacket(notificationMessage);
        }
    }

}
