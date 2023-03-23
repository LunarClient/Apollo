package com.moonsworth.apollo.event.option;

import com.moonsworth.apollo.event.EventCancellable;
import com.moonsworth.apollo.option.Option;
import com.moonsworth.apollo.option.Options;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public final class ApolloUpdateOptionEvent implements EventCancellable {

    private final Options container;
    private final Option<?, ?, ?> option;
    private final Object value;

    @NonFinal @Setter private boolean cancelled;

}
