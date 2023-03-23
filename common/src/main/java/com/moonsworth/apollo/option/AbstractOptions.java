package com.moonsworth.apollo.option;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import com.google.protobuf.NullValue;
import com.moonsworth.apollo.event.EventBus;
import com.moonsworth.apollo.event.option.ApolloUpdateOptionEvent;
import com.moonsworth.apollo.protocol.ListWrapper;
import com.moonsworth.apollo.protocol.StructWrapper;
import com.moonsworth.apollo.protocol.ValueWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractOptions implements Options {

    protected boolean postUpdate(final Option<?, ?, ?> option, final @Nullable Object value) {
        final EventBus.EventResult<ApolloUpdateOptionEvent> eventResult = EventBus.getBus().post(new ApolloUpdateOptionEvent(this, option, value));
        boolean cancelled = eventResult.getEvent().isCancelled();
        for(final Throwable throwable : eventResult.getThrowing()) {
            throwable.printStackTrace();
        }
        return cancelled;
    }

    @SuppressWarnings("unchecked")
    protected ValueWrapper wrapElement(final @Nullable Object current) {
        final ValueWrapper.Builder wrapBuilder = ValueWrapper.newBuilder();
        if(current instanceof Collection) {
            final ListWrapper.Builder wrapper = ListWrapper.newBuilder();
            for(final Object element : (Collection<Object>) current) {
                wrapper.addValues(wrapElement(element));
            }
            return wrapBuilder.setListValue(wrapper.build()).build();
        } else if(current instanceof Map) {
            final StructWrapper.Builder wrapper = StructWrapper.newBuilder();
            for(final Map.Entry<Object, Object> entry : ((Map<Object, Object>) current).entrySet()) {
                wrapper.putFields(entry.getKey().toString(), wrapElement(entry.getValue()));
            }
            return wrapBuilder.setStructValue(wrapper.build()).build();
        } else if(current != null) {
            final OptionConverter<Object, Message> converter = NetworkOptions.get(current.getClass());
            if(converter != null) {
                final Message message = converter.to(current);
                return wrapBuilder.setAnyValue(Any.pack(message)).build();
            } else {
                return wrapValue(wrapBuilder, current);
            }
        }

        return wrapBuilder.setNullValue(NullValue.NULL_VALUE).build();
    }

    protected @Nullable Object unwrapElement(final @Nullable ValueWrapper valueWrapper) {
        if(valueWrapper != null) {
            if(valueWrapper.hasListValue()) {
                final List<Object> collection = new ArrayList<>();
                for(final ValueWrapper element : valueWrapper.getListValue().getValuesList()) {
                    collection.add(unwrapElement(element));
                }
                return collection;
            } else if(valueWrapper.hasStructValue()) {
                final Map<String, Object> map = new HashMap<>();
                for(final Map.Entry<String, ValueWrapper> entry : valueWrapper.getStructValue().getFieldsMap().entrySet()) {
                    map.put(entry.getKey(), unwrapElement(entry.getValue()));
                }
                return map;
            } else if(valueWrapper.hasAnyValue()) {
                final Any any = valueWrapper.getAnyValue();
                final OptionConverter<Object, Message> converter = NetworkOptions.get(any.getTypeUrl());
                if(converter != null) {
                    return converter.from(any);
                }
            } else {
                return unwrapValue(valueWrapper);
            }
        }

        return null;
    }

    ValueWrapper wrapValue(final ValueWrapper.Builder valueBuilder, final Object current) {
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

    @Nullable Object unwrapValue(final ValueWrapper wrapper) {
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
