package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayerVersion;
import com.lunarclient.apollo.player.ui.Beam;
import com.lunarclient.apollo.player.ui.Border;
import java.util.EnumSet;
import java.util.Set;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the border module.
 *
 * <p>This module represents the border added in version 1.8 (47) and was
 * back-ported to therefore it's only supported on 1.7.</p>
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Borders extends ApolloModule {

    Borders() {
        super("Border");
    }

    @Override
    public Set<ApolloPlayerVersion> getSupportedClientVersions() {
        return EnumSet.of(ApolloPlayerVersion.v1_7);
    }

    /**
     * Displays the {@link Border} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param border the border
     * @since 1.0.0
     */
    public abstract void displayBorder(ApolloPlayer player, Border border);

    /**
     * Removes the {@link Border} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param borderId the border id
     * @since 1.0.0
     */
    public abstract void removeBorder(ApolloPlayer player, String borderId);

    /**
     * Removes the {@link Border} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param border the border
     * @since 1.0.0
     */
    public abstract void removeBorder(ApolloPlayer player, Border border);

    /**
     * Resets all {@link Borders}s for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void resetBorders(ApolloPlayer player);

}
