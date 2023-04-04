package com.moonsworth.apollo.option;

import com.moonsworth.apollo.player.ApolloPlayer;
import java.util.Optional;
import java.util.function.BiFunction;
import org.jetbrains.annotations.Nullable;

public interface Options extends Iterable<Option<?, ?, ?>> {

    /**
     * Returns an empty {@link Options.Container}.
     *
     * @return an empty options container
     * @since 1.0.0
     */
    static Options.Container empty() {
        return EmptyOptions.EMPTY;
    }

    /**
     * Returns the {@code T} value stored for the provided {@link Option} if
     * it exists, or the default value.
     *
     * @param option the option
     * @param <T> the value type
     * @param <C> the option type
     * @return the value or default if present
     * @since 1.0.0
     */
    <T, C extends Option<T, ?, ?>> T get(final C option);

    /**
     * Returns an {@link Optional} of the {@code T} value stored for the
     * provided {@link Option} if it exists, otherwise returns
     * {@link Optional#empty()}.
     *
     * @param option the option
     * @param <T> the value type
     * @param <C> the option type
     * @return the optional stored value
     * @since 1.0.0
     */
    <T, C extends Option<T, ?, ?>> Optional<T> getDirect(final C option);

    /**
     * Sets the provided {@code T} value for the provided {@link Option}.
     *
     * @param option the option
     * @param value the value
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void set(final Option<?, ?, ?> option, final @Nullable T value);

    /**
     * Adds the provided {@code T} value for the provided {@link Option} if it
     * does not already exist.
     *
     * @param option the option
     * @param value the value
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void add(final Option<?, ?, ?> option, final T value);

    /**
     * Removes the provided {@code T} value for the provided {@link Option} if
     * it equals the provided value.
     *
     * @param option the option
     * @param compare the value to compare to
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void remove(final Option<?, ?, ?> option, final @Nullable T compare);

    /**
     * Replaces the {@code T} value for the provided {@link Option} with the
     * output of the provided function.
     *
     * @param option the option
     * @param remappingFunction the function
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void replace(final Option<?, ?, ?> option, final BiFunction<Option<?, ?, ?>, T, T> remappingFunction);

    /**
     * Represents a base option container.
     *
     * @since 1.0.0
     */
    interface Container extends Options {

        /**
         * Returns the {@code T} value stored for the provided {@link ApolloPlayer}
         * and {@link Option} if it exists, or the default value if it exists.
         *
         * @param player the player
         * @param option the option
         * @param <T> the value type
         * @return the value or default if present
         * @since 1.0.0
         */
        default <T, C extends Option<T, ?, ?>> @Nullable T get(final ApolloPlayer player, final C option) {
            return this.get(player).get(option);
        }

        /**
         * Returns an {@link Optional} of the {@code T} value stored for the
         * provided {@link ApolloPlayer} and {@link Option} if it exists,
         * otherwise returns {@link Optional#empty()}.
         *
         * @param player the player
         * @param option the option
         * @param <T> the value type
         * @return the optional stored value
         * @since 1.0.0
         */
        default <T, C extends Option<T, ?, ?>> Optional<T> getDirect(final ApolloPlayer player, final C option) {
            return this.get(player).getDirect(option);
        }

        /**
         * Sets the provided {@code T} value for the provided {@link ApolloPlayer}
         * and {@link Option}.
         *
         * @param player the player
         * @param option the option
         * @param value the value
         * @param <T> the value type
         * @since 1.0.0
         */
        default <T> void set(final ApolloPlayer player, final Option<?, ?, ?> option, final @Nullable T value) {
            this.get(player).set(option, value);
        }

        /**
         * Adds the provided {@code T} value for the provided {@link ApolloPlayer}
         * and {@link Option} if it does not already exist.
         *
         * @param player the player
         * @param option the option
         * @param value the value
         * @param <T> the value type
         * @since 1.0.0
         */
        default <T> void add(final ApolloPlayer player, final Option<?, ?, ?> option, final T value) {
            this.get(player).add(option, value);
        }

        /**
         * Removes the provided {@code T} value for the provided {@link ApolloPlayer}
         * and {@link Option} if it equals the provided value.
         *
         * @param player the player
         * @param option the option
         * @param compare the value to compare to
         * @param <T> the value type
         * @since 1.0.0
         */
        default <T> void remove(final ApolloPlayer player, final Option<?, ?, ?> option, final @Nullable T compare) {
            this.get(player).remove(option, compare);
        }

        /**
         * Replaces the {@code T} value for the provided {@link ApolloPlayer} and
         * {@link Option} with the output of the provided function.
         *
         * @param player the player
         * @param option the option
         * @param remappingFunction the function
         * @param <T> the value type
         * @since 1.0.0
         */
        default <T> void replace(final ApolloPlayer player, final Option<?, ?, ?> option, final BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {
            this.get(player).replace(option, remappingFunction);
        }

        Options.Single get(final ApolloPlayer player);

    }

    /**
     * Represents a single {@link ApolloPlayer}s view of the option container.
     *
     * @since 1.0.0
     */
    interface Single extends Options {

        ApolloPlayer getPlayer();

    }

}
