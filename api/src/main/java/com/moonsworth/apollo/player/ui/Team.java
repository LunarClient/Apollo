package com.moonsworth.apollo.player.ui;

import com.moonsworth.apollo.world.ApolloLocation;
import lombok.Value;

import java.awt.*;
import java.util.List;
import java.util.UUID;

/**
 * Represents a team which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Team {

    /**
     * Returns a {@link List} of {@link Teammate}s.
     *
     * @return the nametag
     * @since 1.0.0
     */
    List<Teammate> teammates;

    /**
     * Represents a teammate which can be shown on the client.
     *
     * @since 1.0.0
     */
    @Value(staticConstructor = "of")
    public static class Teammate {

        /**
         * Returns the teammate player {@link UUID} uuid.
         *
         * @return the player uuid
         * @since 1.0.0
         */
        UUID player;

        /**
         * Returns the teammate {@link Color}.
         *
         * @return the teammate color
         * @since 1.0.0
         */
        Color color;

        /**
         * Returns the teammate {@link ApolloLocation}.
         * <p>
         * The provided location is only used when the player is out of
         * render distance for the observer. If you know that the players
         * are close, you don't need to send the location.
         *
         * @return the teammate location
         * @since 1.0.0
         */
        ApolloLocation location;
    }
}
