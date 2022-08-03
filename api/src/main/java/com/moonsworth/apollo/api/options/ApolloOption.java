package com.moonsworth.apollo.api.options;

import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public abstract class ApolloOption<T> {

    /**
     * The value of this property
     */
    @Getter(AccessLevel.PROTECTED)
    private T value, defaultValue;

    /**
     * The name and ID of this property
     */
    @Getter
    private String id;

    /**
     * Called when the value gets updated, if present
     */
    private final List<Consumer<T>> onUpdate = new CopyOnWriteArrayList<>();

    @Getter
    private OptionProperty property;

    private boolean hasLoaded = false;

    public ApolloOption(String id, T value, OptionProperty property) {
        this.id = id;
        this.property = property;
        this.value = this.defaultValue = value;
    }

    public T get() {
        return value;
    }

    public T getDefault() {
        return defaultValue;
    }

    /**
     * Called when the value gets updated
     */
    public <S extends ApolloOption<T>> S onUpdate(Consumer<T> consumer) {
        this.onUpdate.add(consumer);
        return (S) this;
    }

    /**
     * Parse a string to the options value.
     *
     * @param value The string to be parsed
     */
    public abstract void update(String value);

    public void update(T value) {
        if (!hasLoaded || !Objects.equals(this.value, value)) {
            this.value = value;
            for (Consumer<T> c : onUpdate) {
                c.accept(value);
            }
            hasLoaded = true;
        }
    }

    public void load(String value) {
        if (value == null) {
            update(defaultValue);
            return;
        }
        this.update(value);
    }

}
