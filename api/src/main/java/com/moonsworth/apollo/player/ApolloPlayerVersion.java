package com.moonsworth.apollo.player;

import java.util.EnumSet;
import java.util.Set;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the minecraft version the player is using.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public enum ApolloPlayerVersion {

    v1_7,
    v1_8,
    v1_12,
    v1_16_5,
    v1_17_1,
    v1_18_1,
    v1_18_2,
    v1_19_0,
    v1_19_2,
    v1_19_3,
    v1_19_4;

    private static final Set<ApolloPlayerVersion> VERSION_CACHE = EnumSet.allOf(ApolloPlayerVersion.class);

    public static Set<ApolloPlayerVersion> getVersions() {
        return ApolloPlayerVersion.VERSION_CACHE;
    }

}
