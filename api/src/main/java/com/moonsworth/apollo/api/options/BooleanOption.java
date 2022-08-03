package com.moonsworth.apollo.api.options;

import lombok.Getter;

@Getter
public class BooleanOption extends ApolloOption<Boolean> {

    public BooleanOption(String id, OptionProperty property, Boolean value) {
        super(id, value, property);
    }

    @Override
    public void update(String value) {
        this.update(Boolean.parseBoolean(value));
    }
}
