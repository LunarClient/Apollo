package com.moonsworth.apollo.api.events;

import java.util.function.Consumer;

public interface Listener {

    default <T extends Event> void handle(Class<T> clazz, Consumer<T> consumer) {
        EventBus.getBus().register(clazz, consumer);
    }

}
