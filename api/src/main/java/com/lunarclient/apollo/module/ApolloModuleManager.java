package com.lunarclient.apollo.module;

import java.util.Collection;
import org.jetbrains.annotations.ApiStatus;

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
    boolean isEnabled(Class<? extends ApolloModule> moduleClass);

    /**
     * Returns the type {@code T} {@link ApolloModule} with the specified
     * {@link Class} if it exists, otherwise returns null.
     *
     * @param moduleClass the module class
     * @param <T> the apollo module type
     * @return the apollo module, if present
     * @since 1.0.0
     */
    <T extends ApolloModule> T getModule(Class<T> moduleClass);

    /**
     * Gets a collection of {@link ApolloModule}s.
     *
     * @return a list of apollo modules
     * @since 1.0.0
     */
    Collection<ApolloModule> getModules();

}
