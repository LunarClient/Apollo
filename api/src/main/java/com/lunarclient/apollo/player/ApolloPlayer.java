/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.player;

import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.recipients.Recipients;
import com.lunarclient.apollo.world.ApolloWorld;
import java.util.Optional;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents a player on Apollo.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public interface ApolloPlayer extends Recipients {

    /**
     * Gets the players unique identifier.
     *
     * @return the players unique identifier
     * @since 1.0.0
     */
    UUID getUniqueId();

    /**
     * Gets the players name.
     *
     * @return the players name
     * @since 1.0.0
     */
    String getName();

    /**
     * Gets the players current world.
     *
     * @return the players current world
     * @since 1.0.0
     */
    Optional<ApolloWorld> getWorld();

    /**
     * Gets the players current location.
     *
     * @return the players current location
     * @since 1.0.0
     */
    Optional<ApolloLocation> getLocation();

    /**
     * Returns {@code true} if the player has the specified {@link String}
     * permission from the provided {@link Option}, otherwise returns
     * {@code false}.
     *
     * @param options the options container
     * @param option  the option
     * @return true if the player has permission, otherwise false
     * @since 1.0.0
     */
    default boolean hasPermission(Options options, Option<String, ?, ?> option) {
        String value = options.get(option);
        return this.hasPermission(value);
    }

    /**
     * Returns {@code true} if the player has the specified {@link String}
     * permission, otherwise returns {@code false}.
     *
     * @param permissionNode the permission node
     * @return true if the player has permission, otherwise false
     * @since 1.0.0
     */
    boolean hasPermission(String permissionNode);

    /**
     * Returns the player object associated with the platform.
     *
     * @return the associated player object
     * @since 1.0.9
     */
    Object getPlayer();

}
