package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloItemStack;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;

import java.util.ArrayList;
import java.util.List;

public class SaturationModule extends ApolloModule {

    public SaturationModule() {
        super("SaturationModule");
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
        return ImmutableList.of(ApolloPlatform.Kind.SERVER);
    }

    public boolean hasCustomSaturation(ApolloItemStack itemStack) {
        return itemStack.hasTag("saturation");
    }

    public ApolloItemStack applyCustomSaturation(ApolloItemStack itemStack, float saturation) {
        itemStack.addTag("saturation", saturation);
        return itemStack;
    }


    public ApolloItemStack removeCustomSaturation(ApolloItemStack itemStack) {
        itemStack.removeTag("saturation");
        return itemStack;
    }
}
