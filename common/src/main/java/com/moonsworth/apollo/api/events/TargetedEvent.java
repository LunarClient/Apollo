package com.moonsworth.apollo.api.events;

import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class TargetedEvent extends Event {
    private final ApolloPlayer player;
}
