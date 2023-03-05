package com.moonsworth.apollo.api.player;

import java.util.UUID;

/**
 * Represents a player on Apollo.
 *
 * @since 1.0.0
 */
public interface ApolloPlayer {

    /**
     * Gets the players unique identifier.
     *
     * @return the players unique identifier
     * @since 1.0.0
     */
    UUID getUniqueId();

    /**
     * Returns {@code true} if the player has the specified {@link String}
     * permission, otherwise returns {@code false}.
     *
     * @param permissionNode the permission node
     * @return true if the player has permission, otherwise false
     * @since 1.0.0
     */
    boolean hasPermission(String permissionNode);

}
