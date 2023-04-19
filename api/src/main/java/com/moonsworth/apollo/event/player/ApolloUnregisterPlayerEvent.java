package com.moonsworth.apollo.event.player;

import com.moonsworth.apollo.event.Event;
import com.moonsworth.apollo.player.ApolloPlayer;
import lombok.Value;

/**
 * Event for when a player is unregistered from Apollo.
 *
 * <p>This is usually posted when the player using
 * Lunar Client leaves the server. This is only called
 * once in the players session.</p>
 *
 * @since 1.0.0
 */
@Value
public class ApolloUnregisterPlayerEvent implements Event {

    /**
     * Returns the {@link ApolloPlayer} that was unregistered.
     *
     * @since 1.0.0
     */
    ApolloPlayer player;

}
