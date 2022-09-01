package com.moonsworth.apollo.api.options;

import java.util.List;

public class StringListOption extends ApolloOption<List<String>> {

    public StringListOption(String id, List<String> value, OptionProperty property) {
        super(id, value, property);
    }

    @Override
    public void update(String value) {
        System.out.println(value);
    }

}
