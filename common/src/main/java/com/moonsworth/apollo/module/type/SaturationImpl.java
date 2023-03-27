package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.world.ApolloItemStack;

public final class SaturationImpl extends Saturation {

    public SaturationImpl() {
        super();
    }

    @Override
    public boolean hasCustomValue(final ApolloItemStack itemStack, final CustomKey key) {
        return itemStack.hasTag(key.getValue());
    }

    @Override
    public void applyCustomValue(final ApolloItemStack itemStack, final CustomKey key, final Number value) {
        itemStack.addTag(key.getValue(), value);
    }

    @Override
    public void removeCustomValue(final ApolloItemStack itemStack, final CustomKey key) {
        itemStack.removeTag(key.getValue());
    }

}
