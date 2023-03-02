package com.moonsworth.apollo.api.events;

import com.moonsworth.apollo.api.events.annotation.Listen;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class EventBus {
    @Getter
    private static final EventBus bus = new EventBus();

    protected ConcurrentHashMap<Class<? extends Event>, CopyOnWriteArrayList<Consumer<? extends Event>>> eventMap;

    private EventBus() {
        this.eventMap = new ConcurrentHashMap<>();
    }

    @SuppressWarnings("unchecked")
    public void register(Object instance) {
        for (Method m : getEventMethods(instance)) {
            eventMap.computeIfAbsent((Class<? extends Event>) m.getParameterTypes()[0], k -> new CopyOnWriteArrayList<>()).add(new ReflectiveConsumer<>(instance, m));
        }
    }

    public void unregister(Object instance) {
        for (Method m : getEventMethods(instance)) {
            List<Consumer<? extends Event>> listeners = eventMap.get(m.getParameterTypes()[0]);
            if (listeners != null) {
                listeners.removeIf(c -> c instanceof ReflectiveConsumer && ((ReflectiveConsumer<?>) c).getInstance() == instance);
            }
        }
    }

    private List<Method> getEventMethods(Object instance) {
        return Arrays.stream(instance.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Listen.class) && m.getParameterCount() == 1 && Event.class.isAssignableFrom(m.getParameterTypes()[0]))
                .collect(Collectors.toList());
    }

    public <T extends Event> boolean register(Class<T> event, Consumer<T> consumer) {
        return eventMap.computeIfAbsent(event, k -> new CopyOnWriteArrayList<>()).add(consumer);
    }

    public <T extends Event> boolean unregister(Class<T> event, Consumer<T> consumer) {
        CopyOnWriteArrayList<Consumer<? extends Event>> consumers = eventMap.get(event);
        return consumers != null && consumers.remove(consumer);
    }

    public <T extends Event> T post(T event) {
        return this.post(event, null);
    }

    @SuppressWarnings("unchecked")
    public <T extends Event> T post(T event, Runnable action) {
        // If there is an error with getting the consumers, we want to throw that, and break.
        try {
            CopyOnWriteArrayList<Consumer<? extends Event>> consumers = eventMap.get(event.getClass());

            if (consumers != null) {
                for (Consumer<? extends Event> c : consumers) {
                    // If there is an error with a single consumer, we want to catch that
                    // log the error, but don't print the stack for performance(?).
                    try {
                        Consumer<T> consumer = (Consumer<T>) c;
                        consumer.accept(event);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }

            if(action != null && event instanceof EventCancellable cancellable && !cancellable.isCancelled()) {
                action.run();
            }

            return event;
        } catch (Exception | Error e) {
            if (e instanceof AbstractMethodError || e instanceof IllegalAccessError) {
                throw e;
            }

            e.printStackTrace();
        }

        return null;
    }
}
