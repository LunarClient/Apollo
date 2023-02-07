package com.moonsworth.apollo.api.events;

public interface EventCancellable {

    boolean isCancelled();

    void setCancelled(boolean cancel);
}
