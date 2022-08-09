package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.protocol.ToggleArmorMessage;

import java.util.List;

public class EVNTModule extends ApolloModule {

    public EVNTModule(String name) {
        super(name);
    }

    @Override
    public List<ApolloOption> options() {
        return ImmutableList.of();
    }

    @Override
    public boolean notifyPlayers() {
        return true;
    }

    @Override
    public List<ApolloPlatform.Kind> runsOn() {
        return ImmutableList.of(ApolloPlatform.Kind.SERVER);
    }

    public void togglePlayerArmor(ApolloPlayer player, boolean showArmor) {
        player.sendPacket(ToggleArmorMessage.newBuilder().setShowArmor(showArmor).build());
    }
}
