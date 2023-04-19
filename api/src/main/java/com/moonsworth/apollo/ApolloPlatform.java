package com.moonsworth.apollo;

import org.jetbrains.annotations.ApiStatus;

/**
 * Represents a platform that supports Apollo.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public interface ApolloPlatform {

    /**
     * Returns this kind of platform.
     *
     * @return this kind of platform
     * @since 1.0.0
     */
    Kind getKind();

    /**
     * Represents the kind of server a platform is.
     *
     * @since 1.0.0
     */
    enum Kind {
        SERVER,
        PROXY
    }
}
