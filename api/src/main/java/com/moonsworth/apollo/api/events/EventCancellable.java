package com.moonsworth.apollo.api.events;

import lombok.Getter;
import lombok.Setter;

public class EventCancellable extends Event {

    @Getter
    @Setter
    private boolean cancelled;

    public void cancel() {
        this.cancelled = true;
    }

}
