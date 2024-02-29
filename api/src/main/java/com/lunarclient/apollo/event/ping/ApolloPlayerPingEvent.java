package com.lunarclient.apollo.event.ping;

import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.event.Event;
import lombok.Value;

import java.util.UUID;

@Value
public class ApolloPlayerPingEvent implements Event {
    UUID playerUuid;
    ApolloLocation location;
    boolean isDoublePing;
}
