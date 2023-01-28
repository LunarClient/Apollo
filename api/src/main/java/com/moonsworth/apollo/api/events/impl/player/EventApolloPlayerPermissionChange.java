package com.moonsworth.apollo.api.events.impl.player;

import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.TargetedEvent;

public class EventApolloPlayerPermissionChange extends TargetedEvent {

    public EventApolloPlayerPermissionChange(ApolloPlayer player) {
        super(player);
    }
}
