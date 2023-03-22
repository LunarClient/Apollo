package com.moonsworth.apollo.event.option;

import com.moonsworth.apollo.event.EventCancellable;
import com.moonsworth.apollo.option.Option;
import com.moonsworth.apollo.option.Options;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public final class ApolloUpdateOptionEvent implements EventCancellable {

    private final Options container;
    private final Option<?, ?, ?> option;
    private final Object value;

    @Setter private boolean cancelled;

}
