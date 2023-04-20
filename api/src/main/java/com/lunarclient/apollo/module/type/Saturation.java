package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.world.ApolloItemStack;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the saturation module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Saturation extends ApolloModule {

    Saturation() {
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
    public abstract boolean hasCustomValue(ApolloItemStack itemStack, CustomKey key);

    /**
     * Adds the {@link CustomKey} key to the provided {@link ApolloItemStack} with {@link Number} value.
     *
     * @param itemStack the item stack
     * @param key the key
     * @param value the value
     * @since 1.0.0
     */
    public abstract void applyCustomValue(ApolloItemStack itemStack, CustomKey key, Number value);

    /**
     * Removes the {@link CustomKey} key from the provided {@link ApolloItemStack}.
     *
     * @param itemStack the item stack
     * @param key the key
     * @since 1.0.0
     */
    public abstract void removeCustomValue(ApolloItemStack itemStack, CustomKey key);

    @Getter
    @RequiredArgsConstructor
    public enum CustomKey {

        HUNGER("hunger"),
        SATURATION("saturation");

        private final String value;
    }
}
