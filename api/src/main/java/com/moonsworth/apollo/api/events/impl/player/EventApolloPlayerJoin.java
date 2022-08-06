package com.moonsworth.apollo.api.events.impl.player;

import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.TargetedEvent;

public class EventApolloPlayerJoin extends TargetedEvent {

    public EventApolloPlayerJoin(ApolloPlayer player) {
        super(player);
    }
}
