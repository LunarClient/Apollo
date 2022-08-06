package com.moonsworth.apollo.api.events.impl.player;

import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.Event;
import com.moonsworth.apollo.api.events.TargetedEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class EventApolloPlayerRegister extends TargetedEvent {
    public EventApolloPlayerRegister(ApolloPlayer player) {
        super(player);
    }
}
