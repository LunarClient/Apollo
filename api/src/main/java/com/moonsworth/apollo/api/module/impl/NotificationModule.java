package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.google.protobuf.ByteString;
import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.protocol.NametagMessage;
import com.moonsworth.apollo.api.protocol.NotificationMessage;
import com.moonsworth.apollo.api.protocol.RenderableString;

import java.util.ArrayList;
import java.util.List;

public class NotificationModule extends ApolloModule {

    public NotificationModule() {
        super("NotificationModule");
    }

    @Override
    public List<ApolloOption> options() {
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


    public void notifyAll(String title, String description) {
        pushNotify(title, description, Apollo.getApolloPlayerManager().getApolloPlayers().toArray(new ApolloPlayer[0]));
    }

    public void pushNotify(String title, String description, ApolloPlayer... viewer) {
        NotificationMessage message = NotificationMessage.newBuilder().setTitle(ByteString.copyFromUtf8(title)).setDescription(ByteString.copyFromUtf8(description)).build();
        for (ApolloPlayer player : viewer) {
            player.sendPacket(message);
        }
    }

}
