package com.moonsworth.apollo.event.player;

import com.moonsworth.apollo.event.Event;
import com.moonsworth.apollo.player.ApolloPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class ApolloRegisterPlayerEvent implements Event {

    private final ApolloPlayer player;

}
