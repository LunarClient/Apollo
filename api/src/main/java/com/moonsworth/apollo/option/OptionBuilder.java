package com.moonsworth.apollo.option;

import io.leangen.geantyref.TypeToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.util.Objects.requireNonNull;

/**
 * Represents an option builder.
 *
 * @param <T> the value type
 * @param <M> the option builder type
 * @param <I> the option type
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class OptionBuilder<T, M extends OptionBuilder<T, M, I>, I extends Option<T, M, I>> {

    String[] node;
    TypeToken<T> typeToken;
    String comment = null;
    T defaultValue = null;
    boolean notify;

    /**
     * Sets the option node to the provided {@link String} array and returns
     * this builder.
     *
     * @param key the node string array
     * @return this builder
     * @since 1.0.0
     */
    public M node(final String... key) {
        this.node = requireNonNull(key, "key");
        return (M) this;
    }

    /**
     * Sets the option type to the provided {@link TypeToken} and returns
     * this builder.
     *
     * @param typeToken the type token
     * @return this builder
     * @since 1.0.0
     */
    public M type(final TypeToken<T> typeToken) {
        this.typeToken = requireNonNull(typeToken, "typeToken");
        return (M) this;
    }

    /**
     * Sets the option comment to the provided {@link String} and returns
     * this builder.
     *
     * @param comment the comment
     * @return this builder
     * @since 1.0.0
     */
    public M comment(final String comment) {
        this.comment = comment;
        return (M) this;
    }

    /**
     * Sets the option default value to the provided {@code T} value and
     * returns this builder.
     *
     * @param value the default value
     * @return this builder
     * @since 1.0.0
     */
    public M defaultValue(final T value) {
        this.defaultValue = value;
        return (M) this;
    }

    /**
     * Sets whether the option should be aware to the client to {@code true}
     * and returns this builder.
     *
     * @return this builder
     * @since 1.0.0
     */
    public M notifyClient() {
        this.notify = true;
        return (M) this;
    }

    /**
     * Returns a new {@code I} option from this builder.
     *
     * @return a new option
     * @since 1.0.0
     */
    public abstract I build();

}
