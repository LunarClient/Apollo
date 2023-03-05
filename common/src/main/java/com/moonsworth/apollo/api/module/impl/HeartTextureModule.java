package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.protocol.HeartTextureMessage;

import java.util.ArrayList;
import java.util.List;

public class HeartTextureModule extends ApolloModule {

    public HeartTextureModule() {
        super("HeartTextureModule");
    }

    @Override
    public List<ApolloOption<?>> options() {
        return new ArrayList<>();
    }

    @Override
    public boolean notifyPlayers() {
        return true;
    }

    @Override
    public List<ApolloPlatform.Kind> runsOn() {
        return ImmutableList.of(ApolloPlatform.Kind.SERVER);
    }

    public void setHeartXLocationOverride(ApolloPlayer player, int x, boolean hardCore) {
        player.sendPacket(HeartTextureMessage.newBuilder().setXLocation(x).setHardCore(hardCore).build());
    }

    public void clearHeartLocation(ApolloPlayer player) {
        player.sendPacket(HeartTextureMessage.newBuilder().setXLocation(-1).build());
    }
}
