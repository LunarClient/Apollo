package com.moonsworth.apollo.option;

import com.google.protobuf.NullValue;
import com.google.protobuf.Value;
import com.moonsworth.apollo.event.EventBus;
import com.moonsworth.apollo.event.option.ApolloUpdateOptionEvent;
import org.jetbrains.annotations.Nullable;

/**
 * Provides convenience methods for converting options to and from protobuf.
 *
 * @since 1.0.0
 */
public abstract class AbstractOptions implements Options {

    /**
     * Wraps the provided value into a protobuf {@link Value}.
     *
     * @param valueBuilder the value builder
     * @param current the current value
     * @return the wrapped value
     * @since 1.0.0
     */
    public Value wrapValue(Value.Builder valueBuilder, Object current) {
        if(current instanceof Number) {
            valueBuilder.setNumberValue(((Number) current).doubleValue());
        } else if(current instanceof String) {
            valueBuilder.setStringValue((String) current);
        } else if(current instanceof Boolean) {
            valueBuilder.setBoolValue((Boolean) current);
        } else {
            valueBuilder.setNullValue(NullValue.NULL_VALUE);
        }

        return valueBuilder.build();
    }

    /**
     * Unwraps the provided protobuf {@link Value} into the appropriate object.
     *
     * @param wrapper the wrapped value
     * @return the unwrapped value
     * @since 1.0.0
     */
    public @Nullable Object unwrapValue(Value wrapper) {
        if(wrapper.hasNumberValue()) {
            return wrapper.getNumberValue();
        } else if(wrapper.hasStringValue()) {
            return wrapper.getStringValue();
        } else if(wrapper.hasBoolValue()) {
            return wrapper.getBoolValue();
        }

        return null;
    }

    boolean postUpdate(Option<?, ?, ?> option, @Nullable Object value) {
        final EventBus.EventResult<ApolloUpdateOptionEvent> eventResult = EventBus.getBus().post(new ApolloUpdateOptionEvent(this, option, value));
        boolean cancelled = eventResult.getEvent().isCancelled();
        for(Throwable throwable : eventResult.getThrowing()) {
            throwable.printStackTrace();
        }
        return cancelled;
    }

}
