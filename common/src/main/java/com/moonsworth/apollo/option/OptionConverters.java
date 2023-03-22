package com.moonsworth.apollo.option;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class OptionConverters {

    private static final Map<String, OptionConverter<Object, Message>> messageToConverter = new HashMap<>();
    private static final Map<Class<?>, OptionConverter<Object, Message>> objectToConverter = new HashMap<>();

    public static <I, O extends Message>  void register(final Class<I> objectClass, final O message, final OptionConverter<I, O> converter) {
        final String messageUrl = Any.pack(message).getTypeUrl();
        messageToConverter.put(messageUrl, (OptionConverter<Object, Message>) converter);
        objectToConverter.put(objectClass, (OptionConverter<Object, Message>) converter);
    }

    public static @Nullable OptionConverter<Object, Message> get(final String messageUrl) {
        return OptionConverters.messageToConverter.get(messageUrl);
    }

    public static @Nullable OptionConverter<Object, Message> get(final Class<?> objectClass) {
        return OptionConverters.objectToConverter.get(objectClass);
    }

}
