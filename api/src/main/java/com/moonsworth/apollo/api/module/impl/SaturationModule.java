package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloItemStack;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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

    public boolean hasCustomValue(ApolloItemStack itemStack, CustomKey key) {
        return itemStack.hasTag(key.getKey());
    }

    public ApolloItemStack applyCustomValue(ApolloItemStack itemStack, CustomKey key, Number value) {
        itemStack.addTag(key.getKey(), value);
        return itemStack;
    }


    public ApolloItemStack removeCustomValue(ApolloItemStack itemStack, CustomKey key) {
        itemStack.removeTag(key.getKey());
        return itemStack;
    }

    @Getter
    @RequiredArgsConstructor
    public enum CustomKey {
        
        HUNGER("hunger"),
        SATURATION("saturation");
        
        private final String key;
    } 
}
