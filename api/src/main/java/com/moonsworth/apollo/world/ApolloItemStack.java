package com.moonsworth.apollo.world;

import org.jetbrains.annotations.ApiStatus;

// TODO: implement and document
// https://github.com/LunarClient/Apollo/blob/4b3c64629423db31b864fddae11c2eb742b51625/bukkit/common/src/main/java/com/moonsworth/apollo/impl/bukkit/wrapper/BukkitItemStack.java

/**
 * Represents an item stack in Apollo.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public interface ApolloItemStack {

    /**
     * Returns whether this item stack contains the provided tag.
     *
     * @param key the tag key
     * @return true if the tag exists, otherwise false
     * @since 1.0.0
     */
    boolean hasTag(String key);

    /**
     * Adds the provided tag to this item stack.
     *
     * @param key the tag key
     * @param value the tag value
     * @since 1.0.0
     */
    void addTag(String key, Object value);

    /**
     * Removes the provided tag from this item stack.
     *
     * @param key the tag key
     * @since 1.0.0
     */
    void removeTag(String key);

    /**
     * Returns a new item back.
     *
     * @return the item stack
     * @since 1.0.0
     */
    Object build();
}
