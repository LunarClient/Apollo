package com.lunarclient.apollo.event.ping;

import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.event.Event;
import lombok.Value;

import java.util.UUID;

/**
 * An event that is fired when a player ping message is received from a player.
 *
 * @since 1.1.1
 */
@Value
public class ApolloPlayerPingEvent implements Event {

    /**
     * @return The UUID of the player who sent the ping.
     */
    UUID playerUuid;

    /**
     * @return The in-world location of the ping.
     */
    ApolloLocation location;

    /**
     * @return true if the ping is a double-click ping
     */
    boolean isDoublePing;

}
