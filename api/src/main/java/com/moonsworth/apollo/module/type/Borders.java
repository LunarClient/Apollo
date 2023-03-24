package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.option.ListOption;
import com.moonsworth.apollo.option.Option;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayerVersion;
import com.moonsworth.apollo.player.ui.Border;
import io.leangen.geantyref.TypeToken;
import org.jetbrains.annotations.ApiStatus;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the border module.
 * <p>
 * This module represents the border added in
 * version 1.8 (47) and was back-ported to
 * therefore it's only supported on
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Borders extends ApolloModule {

    /**
     * A list of borders.
     */
    public static final ListOption<Border> BORDERS = Option.<Border>list()
        .node("borders").type(new TypeToken<List<Border>>() {})
        .defaultValue(Collections.emptyList()).notifyClient()
        .build();

    Borders() {
        super("Border");
    }

    @Override
    public Set<ApolloPlayerVersion> getSupportedClientVersions() {
        return EnumSet.of(ApolloPlayerVersion.v1_7);
    }

    /**
     * Adds or updates the {@link Border} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param border the border
     * @since 1.0.0
     */
    public abstract void addOrUpdateBorder(final ApolloPlayer player, final Border border);

    /**
     * Removes the {@link Border} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param border the border
     * @since 1.0.0
     */
    public abstract void removeBorder(final ApolloPlayer player, final Border border);
}
