package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.world.ApolloItemStack;

public final class SaturationImpl extends Saturation {

    public SaturationImpl() {
        super();
    }

    @Override
    public boolean hasCustomValue(ApolloItemStack itemStack, CustomKey key) {
        return itemStack.hasTag(key.getValue());
    }

    @Override
    public void applyCustomValue(ApolloItemStack itemStack, CustomKey key, Number value) {
        itemStack.addTag(key.getValue(), value);
    }

    @Override
    public void removeCustomValue(ApolloItemStack itemStack, CustomKey key) {
        itemStack.removeTag(key.getValue());
    }

}
