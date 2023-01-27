package com.moonsworth.apollo.api;

import com.moonsworth.apollo.api.bridge.ApolloPlayer;

import javax.annotation.Nullable;

/**
 * A platform that supports Apollo.
 */
public interface ApolloPlatform {

    Kind getKind();

    enum Kind {
        SERVER,
        PROXY,
    }

    /**
     * Wrap a Player object with this player
     *
     * @param player player object to wrap
     * @return a wrapped player object with extended functionality
     */
    @Nullable
    ApolloPlayer<?> tryWrapPlayer(Object player);
}
