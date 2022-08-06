package com.moonsworth.apollo.api.options;

public class PermissionNodeOption extends ApolloOption<String>{

    public PermissionNodeOption(String id, String value) {
        super(id, value, OptionProperty.SERVER);
    }
}
