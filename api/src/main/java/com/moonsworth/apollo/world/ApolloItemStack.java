package com.moonsworth.apollo.world;

import org.jetbrains.annotations.ApiStatus;

// TODO: implement and document
// https://github.com/LunarClient/Apollo/blob/4b3c64629423db31b864fddae11c2eb742b51625/bukkit/common/src/main/java/com/moonsworth/apollo/impl/bukkit/wrapper/BukkitItemStack.java

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
