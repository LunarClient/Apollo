package com.moonsworth.apollo.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import lombok.Getter;

final class ReflectiveConsumer<T extends Event> implements Consumer<T> {

    @Getter private final Object instance;
    private final Method method;

    ReflectiveConsumer(final Object instance, final Method method) {
        this.instance = instance;
        this.method = method;

        method.setAccessible(true);
    }

    @Override
    public void accept(final T event) {
        try {
            method.invoke(this.instance, event);
        } catch (final IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }

}
