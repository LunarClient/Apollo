package com.moonsworth.apollo.api.module;

import com.moonsworth.apollo.api.ApolloPlatform;

import java.util.Collection;
import java.util.Collections;

/**
 * Represents a module for Apollo.
 *
 * @since 1.0.0
 */
public interface ApolloModule {

    /**
     * Returns {@code true} if the module is enabled, otherwise returns
     * {@code false}.
     *
     * @return true if the module is enabled, otherwise false
     * @since 1.0.0
     */
    boolean isEnabled();

    /**
     * Returns the module {@link String} name.
     *
     * @return the module name
     * @since 1.0.0
     */
    String getName();

    /**
     * Returns a {@link Collection} of supported {@link ApolloPlatform.Kind}.
     *
     * @return a collection of supported kinds of platforms
     * @since 1.0.0
     */
    default Collection<ApolloPlatform.Kind> getSupport() {
        return Collections.singletonList(ApolloPlatform.Kind.SERVER);
    }

    /**
     * Returns {@code true} if the client will be notified of this module,
     * otherwise returns {@code false}.
     *
     * <p>Setting this to true will also send any configuration options
     * that can be sent to the client.</p>
     *
     * @return true if the client should be notified, otherwise false
     * @since 1.0.0
     */
    default boolean isClientNotify() {
        return false;
    }

}
