package com.moonsworth.apollo.api.events;

import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;

public class ReflectiveConsumer<T extends Event> implements Consumer<T> {
    @Getter private final Object instance;
    private final Method method;

    public ReflectiveConsumer(Object instance, Method method) {
        this.instance = instance;
        this.method = method;

        method.setAccessible(true);
    }

    @Override
    public void accept(T t) {
        try {
            method.invoke(this.instance, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
