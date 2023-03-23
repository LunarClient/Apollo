package com.moonsworth.apollo.world;

import org.jetbrains.annotations.ApiStatus;

// TODO: implement and document

/**
 * Represents the item stack on Apollo
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public interface ApolloItemStack {

    boolean hasTag(String key);

    void addTag(String key, Object value);

    void removeTag(String key);

    Object build();
}
