package com.moonsworth.apollo.event.player;

import com.moonsworth.apollo.event.Event;
import com.moonsworth.apollo.player.ApolloPlayer;
import lombok.Value;

/**
 * Event for when a player is registered in Apollo.
 *
 * <p>This is usually posted when the a player using
 * Lunar Client joins the server. This is only called
 * once in the players session.</p>
 *
 * @since 1.0.0
 */
@Value
public class ApolloRegisterPlayerEvent implements Event {

    /**
     * Returns the {@link ApolloPlayer} that was registered.
     *
     * @since 1.0.0
     */
    ApolloPlayer player;

}
