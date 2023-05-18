package com.lunarclient.apollo.event.option;

import com.lunarclient.apollo.event.EventCancellable;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an event that is fired when an option is updated.
 *
 * @since 1.0.0
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class ApolloUpdateOptionEvent implements EventCancellable {

    /**
     * The {@link Options} container that the option is in.
     *
     * @return the options container
     * @since 1.0.0
     */
    Options container;

    /**
     * The {@link ApolloPlayer} that the option was updated for, or
     * {@code null} if it was a global option.
     *
     * @return the player
     * @since 1.0.0
     */
    @Nullable ApolloPlayer player;

    /**
     * The {@link Option} that was updated.
     *
     * @return the option
     * @since 1.0.0
     */
    Option<?, ?, ?> option;

    /**
     * The new value of the option.
     *
     * @return the new value
     * @since 1.0.0
     */
    Object value;

    @NonFinal @Setter boolean cancelled;

}
