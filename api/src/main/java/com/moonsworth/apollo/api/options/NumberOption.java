package com.moonsworth.apollo.api.options;

import lombok.Getter;
import lombok.Setter;

@Getter
public class NumberOption<T extends Number & Comparable<T>> extends ApolloOption<T> {

    @Getter
    @Setter
    private T min, max;

    public NumberOption(String id, OptionProperty property, T value, T min, T max) {
        super(id, value, property);
        this.min = min;
        this.max = max;
    }

    @Override
    public void update(String value) {
        this.update(parse(Double.parseDouble(value)));
    }

    @Override
    public void update(T value) {
        //Limit the value to the correct bounds
        if (value.compareTo(this.min) < 0) {
            value = this.min;
        }
        if (value.compareTo(this.max) > 0) {
            value = this.max;
        }
        super.update(value);
    }

    @SuppressWarnings("unchecked")
    private <K extends Number> K parse(Double doubleValue) {
        Class clazz = getValue().getClass();
        Object value;
        if (clazz == Integer.class) {
            value = (int) Math.round(doubleValue);
        } else if (clazz == Float.class) {
            value = doubleValue.floatValue();
        } else if (clazz == Byte.class) {
            value = (byte) Math.round(doubleValue);
        } else if (clazz == Long.class) {
            value = Math.round(doubleValue);
        } else if (clazz == Short.class) {
            value = (short) Math.round(doubleValue);
        } else {
            value = doubleValue;
        }

        return (K) value;
    }
}
