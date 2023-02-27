package com.moonsworth.apollo.api.events.impl.player;

import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.TargetedEvent;

public class EventApolloPlayerUnregister extends TargetedEvent {

    public EventApolloPlayerUnregister(ApolloPlayer player) {
        super(player);
    }
}
