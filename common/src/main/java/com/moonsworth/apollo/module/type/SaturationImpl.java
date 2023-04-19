package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.world.ApolloItemStack;

import static java.util.Objects.requireNonNull;

public final class SaturationImpl extends Saturation {

    public SaturationImpl() {
        super();
    }

    @Override
    public boolean hasCustomValue(ApolloItemStack itemStack, CustomKey key) {
        requireNonNull(itemStack, "itemStack");
        requireNonNull(key, "key");

        return itemStack.hasTag(key.getValue());
    }

    @Override
    public void applyCustomValue(ApolloItemStack itemStack, CustomKey key, Number value) {
        requireNonNull(itemStack, "itemStack");
        requireNonNull(key, "key");
        requireNonNull(value, "value");

        itemStack.addTag(key.getValue(), value);
    }

    @Override
    public void removeCustomValue(ApolloItemStack itemStack, CustomKey key) {
        requireNonNull(itemStack, "itemStack");
        requireNonNull(key, "key");

        itemStack.removeTag(key.getValue());
    }

}
