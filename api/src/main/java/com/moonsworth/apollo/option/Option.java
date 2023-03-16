package com.moonsworth.apollo.option;

import io.leangen.geantyref.TypeToken;
import lombok.Getter;

import static java.util.Objects.requireNonNull;

@Getter
public abstract class Option<T, M extends OptionBuilder<T, M, I>, I extends Option<T, M, I>> {

    public static <T> SimpleOption.SimpleOptionBuilder<T> builder() {
        return new SimpleOption.SimpleOptionBuilder<>();
    }

    public static <T extends Number & Comparable<T>> NumberOption.NumberOptionBuilder<T> number() {
        return new NumberOption.NumberOptionBuilder<>();
    }

    private final String[] node;
    private final TypeToken<T> typeToken;
    private final String comment;
    private final T defaultValue;
    private final boolean notify;

    Option(final M builder) {
        this.node = requireNonNull(builder.node, "node");
        this.typeToken = requireNonNull(builder.typeToken, "typeToken");

        this.comment = builder.comment;
        this.defaultValue = builder.defaultValue;
        this.notify = builder.notify;
    }

    public String getKey() {
        return String.join(".", this.getNode());
    }

}
