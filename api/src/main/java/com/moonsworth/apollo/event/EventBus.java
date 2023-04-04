package com.moonsworth.apollo.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import static java.util.Objects.requireNonNull;

/**
 * Provides a bus for {@link Event}s.
 *
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EventBus {

    /**
     * Returns the {@link EventBus}.
     *
     * @since 1.0.0
     */
    @Getter private static final EventBus bus = new EventBus();

    private final Map<Class<? extends Event>, CopyOnWriteArrayList<Consumer<? extends Event>>> events = new ConcurrentHashMap<>();

    /**
     * Registers methods decorated with {@link Listen} in the provided
     * instance as event listeners.
     *
     * @param instance the event listeners instance
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public void register(final Object instance) {
        requireNonNull(instance, "instance");
        for(final Method method : this.getEventMethods(instance)) {
            this.events.computeIfAbsent((Class<? extends Event>) method.getParameterTypes()[0], k -> new CopyOnWriteArrayList<>())
                    .add(new ReflectiveConsumer<>(instance, method));
        }
    }

    /**
     * Registers the provided {@link Consumer} as an event listener for the
     * provided event class of type {@code T}.
     *
     * @param event the event class
     * @param consumer the listener
     * @param <T> the event type
     * @return true if the listener was registered, otherwise false
     * @since 1.0.0
     */
    public <T extends Event> boolean register(final Class<T> event, final Consumer<T> consumer) {
        requireNonNull(event, "event");
        requireNonNull(consumer, "consumer");
        return this.events.computeIfAbsent(event, key -> new CopyOnWriteArrayList<>()).add(consumer);
    }

    /**
     * Unregisters methods decorated with {@link Listen} in the provided
     * instance.
     *
     * @param instance the event listeners instance
     * @since 1.0.0
     */
    public void unregister(final Object instance) {
        requireNonNull(instance, "instance");
        for(final Method method : this.getEventMethods(instance)) {
            final List<Consumer<? extends Event>> listeners = this.events.get(method.getParameterTypes()[0]);
            if(listeners != null) {
                listeners.removeIf(consumer -> consumer instanceof ReflectiveConsumer && ((ReflectiveConsumer<?>) consumer).getInstance() == instance);
            }
        }
    }

    /**
     * Unregisters the provided {@link Consumer} for the provided event class
     * of type {@code T}.
     *
     * @param event the event class
     * @param consumer the listener
     * @param <T> the event type
     * @return true if the listener was unregistered, otherwise false
     * @since 1.0.0
     */
    public <T extends Event> boolean unregister(final Class<T> event, final Consumer<T> consumer) {
        requireNonNull(event, "event");
        requireNonNull(consumer, "consumer");
        final CopyOnWriteArrayList<Consumer<? extends Event>> consumers = this.events.get(event);
        return consumers != null && consumers.remove(consumer);
    }

    /**
     * Posts the provided {@code T} event to listeners.
     *
     * @param event the event
     * @param <T> the event type
     * @return the event result
     * @since 1.0.0
     */
    public <T extends Event> EventResult<T> post(final T event) {
        requireNonNull(event, "event");
        final CopyOnWriteArrayList<Consumer<? extends Event>> consumers = this.events.get(event.getClass());
        final List<Throwable> throwables = new ArrayList<>();
        if(consumers != null) {
            for(final Consumer<? extends Event> consumer : consumers) {
                try {
                    ((Consumer<T>) consumer).accept(event);
                } catch(final Throwable throwable) {
                    throwables.add(throwable);
                }
            }
        }
        return new EventResult<>(event, throwables);
    }

    private List<Method> getEventMethods(final Object instance) {
        return Arrays.stream(instance.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Listen.class)
                        && method.getParameterCount() == 1
                        && Event.class.isAssignableFrom(method.getParameterTypes()[0])
                )
                .collect(Collectors.toList());
    }

    /**
     * Represents the result of posting an {@link Event}.
     *
     * @param <T> the event type
     * @since 1.0.0
     */
    @Value
    public static class EventResult<T extends Event> {

        /**
         * Returns the {@link Event}.
         *
         * @since 1.0.0
         */
        T event;

        /**
         * Returns a {@link List} of {@link Throwable}s that were thrown by
         * the event listeners handling the event.
         *
         * @since 1.0.0
         */
        List<Throwable> throwing;

    }

}
