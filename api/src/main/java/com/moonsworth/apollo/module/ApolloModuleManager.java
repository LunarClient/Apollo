package com.moonsworth.apollo.module;

import org.jetbrains.annotations.ApiStatus;

import java.util.Collection;
import java.util.Optional;

/**
 * Represents the module manager for Apollo.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public interface ApolloModuleManager {

    /**
     * Returns {@code true} if the specified {@link ApolloModule} is enabled,
     * otherwise returns {@code false}.
     *
     * @param moduleClass the module class
     * @return true if the module is enabled, otherwise false
     * @since 1.0.0
     */
    boolean isEnabled(final Class<? extends ApolloModule> moduleClass);

    /**
     * Returns the type {@code T} {@link ApolloModule} with the specified
     * {@link Class} if it exists, otherwise returns {@link Optional#empty()}.
     *
     * @param moduleClass the module class
     * @param <T> the apollo module type
     * @return the apollo module, if present
     * @since 1.0.0
     */
    <T extends ApolloModule> Optional<T> getModule(final Class<T> moduleClass);

    /**
     * Gets a collection of {@link ApolloModule}s.
     *
     * @return a list of apollo modules
     * @since 1.0.0
     */
    Collection<ApolloModule> getModules();

}