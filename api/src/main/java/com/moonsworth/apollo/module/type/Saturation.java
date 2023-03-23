package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.world.ApolloItemStack;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents the saturation module.
 *
 * @since 1.0.0
 */
public abstract class Saturation extends ApolloModule {

    public Saturation() {
        super("Saturation");
    }

    /**
     * Checks if the {@link ApolloItemStack} has the {@link CustomKey} value set.
     *
     * @param itemStack the item stack
     * @param key the key
     * @return boolean true if the item has provided value
     * @since 1.0.0
     */
    public abstract boolean hasCustomValue(final ApolloItemStack itemStack, final CustomKey key);

    /**
     * Adds the {@link CustomKey} key to the provided {@link ApolloItemStack} with {@link Number} value.
     *
     * @param itemStack the item stack
     * @param key the key
     * @param value the value
     *
     * @since 1.0.0
     */
    public abstract void applyCustomValue(final ApolloItemStack itemStack, final CustomKey key, final Number value);

    /**
     * Removes the {@link CustomKey} key from the provided {@link ApolloItemStack}.
     *
     * @param itemStack the item stack
     * @param key the key
     *
     * @since 1.0.0
     */
    public abstract void removeCustomValue(final ApolloItemStack itemStack, final CustomKey key);

    @Getter
    @RequiredArgsConstructor
    public enum CustomKey {

        HUNGER("hunger"),
        SATURATION("saturation");

        private final String value;
    }
}
