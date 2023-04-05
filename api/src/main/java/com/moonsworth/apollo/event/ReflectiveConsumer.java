package com.moonsworth.apollo.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import lombok.Getter;

final class ReflectiveConsumer<T extends Event> implements Consumer<T> {

    @Getter private final Object instance;
    private final Method method;

    ReflectiveConsumer(Object instance, Method method) {
        this.instance = instance;
        this.method = method;

        method.setAccessible(true);
    }

    @Override
    public void accept(T event) {
        try {
            method.invoke(this.instance, event);
        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }

}
