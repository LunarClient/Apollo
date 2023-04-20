package com.lunarclient.apollo.player;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

import static java.util.Objects.requireNonNull;

/**
 * Represents the player manager for Apollo.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public interface ApolloPlayerManager {

    /**
     * Returns {@code true} if the specified player {@link UUID} has Apollo
     * support, otherwise returns {@code false}.
     *
     * @param playerIdentifier the player identifier
     * @return true if the player supports apollo, otherwise false
     * @since 1.0.0
     */
    default boolean hasSupport(UUID playerIdentifier) {
        requireNonNull(playerIdentifier, "playerIdentifier");
        return this.getPlayer(playerIdentifier).isPresent();
    }

    /**
     * Gets the {@link ApolloPlayer} for the specified {@link UUID} if it
     * exists, otherwise returns {@link Optional#empty()}.
     *
     * @param playerIdentifier the platform player
     * @return the apollo player, if present
     * @since 1.0.0
     */
    Optional<ApolloPlayer> getPlayer(UUID playerIdentifier);

    /**
     * Gets a collection of {@link ApolloPlayer}s that support Apollo.
     *
     * @return a list of apollo players
     * @since 1.0.0
     */
    Collection<ApolloPlayer> getPlayers();

}
