package com.moonsworth.apollo.option;

import io.leangen.geantyref.TypeToken;

import static java.util.Objects.requireNonNull;

@SuppressWarnings("unchecked")
public abstract class OptionBuilder<T, M extends OptionBuilder<T, M, I>, I extends Option<T, M, I>> {

    String[] node;
    TypeToken<T> typeToken;
    String comment = null;
    T defaultValue = null;
    boolean notify;

    OptionBuilder() {}

    public M node(final String... key) {
        this.node = requireNonNull(key, "key");
        return (M) this;
    }

    public M type(final TypeToken<T> typeToken) {
        this.typeToken = requireNonNull(typeToken, "typeToken");
        return (M) this;
    }

    public M comment(final String comment) {
        this.comment = comment;
        return (M) this;
    }

    public M defaultValue(final T value) {
        this.defaultValue = value;
        return (M) this;
    }

    public M notifyClient() {
        this.notify = true;
        return (M) this;
    }

    public abstract I build();

}
