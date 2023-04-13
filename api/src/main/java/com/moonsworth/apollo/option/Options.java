package com.moonsworth.apollo.option;

import com.moonsworth.apollo.player.ApolloPlayer;
import java.util.Optional;
import java.util.function.BiFunction;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a container of options.
 *
 * @since 1.0.0
 */
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
    <T, C extends Option<T, ?, ?>> T get(C option);

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
    <T, C extends Option<T, ?, ?>> Optional<T> getDirect(C option);

    /**
     * Sets the provided {@code T} value for the provided {@link Option}.
     *
     * @param option the option
     * @param value the value
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void set(Option<?, ?, ?> option, @Nullable T value);

    /**
     * Adds the provided {@code T} value for the provided {@link Option} if it
     * does not already exist.
     *
     * @param option the option
     * @param value the value
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void add(Option<?, ?, ?> option, T value);

    /**
     * Removes the provided {@code T} value for the provided {@link Option} if
     * it equals the provided value.
     *
     * @param option the option
     * @param compare the value to compare to
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void remove(Option<?, ?, ?> option, @Nullable T compare);

    /**
     * Replaces the {@code T} value for the provided {@link Option} with the
     * output of the provided function.
     *
     * @param option the option
     * @param remappingFunction the function
     * @param <T> the value type
     * @since 1.0.0
     */
    <T> void replace(Option<?, ?, ?> option, BiFunction<Option<?, ?, ?>, T, T> remappingFunction);

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
         * @param <C> the option type
         * @return the value or default if present
         * @since 1.0.0
         */
        default <T, C extends Option<T, ?, ?>> @Nullable T get(ApolloPlayer player, C option) {
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
         * @param <C> the option type
         * @return the optional stored value
         * @since 1.0.0
         */
        default <T, C extends Option<T, ?, ?>> Optional<T> getDirect(ApolloPlayer player, C option) {
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
        default <T> void set(ApolloPlayer player, Option<?, ?, ?> option, @Nullable T value) {
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
        default <T> void add(ApolloPlayer player, Option<?, ?, ?> option, T value) {
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
        default <T> void remove(ApolloPlayer player, Option<?, ?, ?> option, @Nullable T compare) {
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
        default <T> void replace(ApolloPlayer player, Option<?, ?, ?> option, BiFunction<Option<?, ?, ?>, T, T> remappingFunction) {
            this.get(player).replace(option, remappingFunction);
        }

        /**
         * Returns the {@link Single} view for the provided {@link ApolloPlayer}.
         *
         * @param player the player
         * @return the single view
         * @since 1.0.0
         */
        Options.Single get(ApolloPlayer player);

    }

    /**
     * Represents a single {@link ApolloPlayer}s view of the option container.
     *
     * @since 1.0.0
     */
    interface Single extends Options {

        /**
         * Returns the {@link ApolloPlayer} this view is for.
         *
         * @return the player
         * @since 1.0.0
         */
        ApolloPlayer getPlayer();

    }

}
