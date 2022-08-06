package com.moonsworth.apollo.api.events.impl.player;

import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.Event;
import com.moonsworth.apollo.api.events.TargetedEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class EventApolloPlayerUnregister extends TargetedEvent {

    public EventApolloPlayerUnregister(ApolloPlayer player) {
        super(player);
    }
}
