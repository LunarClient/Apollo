package com.moonsworth.apollo.option;

import com.google.protobuf.NullValue;
import com.google.protobuf.Value;
import com.moonsworth.apollo.event.EventBus;
import com.moonsworth.apollo.event.option.ApolloUpdateOptionEvent;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractOptions implements Options {

    protected boolean postUpdate(final Option<?, ?, ?> option, final @Nullable Object value) {
        final EventBus.EventResult<ApolloUpdateOptionEvent> eventResult = EventBus.getBus().post(new ApolloUpdateOptionEvent(this, option, value));
        boolean cancelled = eventResult.getEvent().isCancelled();
        for(final Throwable throwable : eventResult.getThrowing()) {
            throwable.printStackTrace();
        }
        return cancelled;
    }

    public Value wrapValue(final Value.Builder valueBuilder, final Object current) {
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

    public @Nullable Object unwrapValue(final Value wrapper) {
        if(wrapper.hasNumberValue()) {
            return wrapper.getNumberValue();
        } else if(wrapper.hasStringValue()) {
            return wrapper.getStringValue();
        } else if(wrapper.hasBoolValue()) {
            return wrapper.getBoolValue();
        }

        return null;
    }

}
