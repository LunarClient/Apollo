package com.moonsworth.apollo.player;

import com.moonsworth.apollo.world.ApolloLocation;
import java.util.Optional;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents a player on Apollo.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public interface ApolloPlayer {

    /**
     * Gets the players minecraft version.
     *
     * @return the players minecraft version
     * @since 1.0.0
     */
    ApolloPlayerVersion getVersion();

    /**
     * Gets the players unique identifier.
     *
     * @return the players unique identifier
     * @since 1.0.0
     */
    UUID getUniqueId();

    /**
     * Gets the players current location.
     *
     * @return the players current location
     * @since 1.0.0
     */
    Optional<ApolloLocation> getLocation();

    /**
     * Returns {@code true} if the player has the specified {@link String}
     * permission, otherwise returns {@code false}.
     *
     * @param permissionNode the permission node
     * @return true if the player has permission, otherwise false
     * @since 1.0.0
     */
    boolean hasPermission(final String permissionNode);

}
