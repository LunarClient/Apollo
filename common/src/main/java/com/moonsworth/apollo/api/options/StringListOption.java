package com.moonsworth.apollo.api.options;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class StringListOption extends ApolloOption<List<String>> {

    public StringListOption(String id, List<String> value, OptionProperty property) {
        super(id, value, property);
    }

    @Override
    public void update(String value) {
        JsonElement array = JsonParser.parseString(value);
        if (!array.isJsonArray()) {
            return;
        }
        List<String> newList = new ArrayList<>();
        for (JsonElement jsonElement : array.getAsJsonArray()) {
            newList.add(jsonElement.getAsString());
        }
        update(newList);
    }

}
