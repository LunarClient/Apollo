package com.moonsworth.apollo.api.options;

import lombok.Getter;

@Getter
public class BooleanOption extends ApolloOption<Boolean> {

    public BooleanOption(String id, Boolean value) {
        super(id, value);
    }

    @Override
    public void update(String value) {
        this.update(Boolean.parseBoolean(value));
    }
}
