package com.moonsworth.apollo.api.bridge;

public interface ApolloItemStack {

    boolean hasTag(String key);

    void addTag(String key, Object value);

    void removeTag(String key);

    Object get();
}
