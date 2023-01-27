package com.moonsworth.apollo.api.events.impl.player;

import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.TargetedEvent;

public class EventApolloPlayerRegister extends TargetedEvent {

    public EventApolloPlayerRegister(ApolloPlayer<?> player) {
        super(player);
    }
}
