package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayerVersion;
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
     * Adds the {@link Border} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param border the border
     * @since 1.0.0
     */
    public abstract void addBorder(ApolloPlayer player, Border border);

    /**
     * Removes the {@link Border} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param border the border
     * @since 1.0.0
     */
    public abstract void removeBorder(ApolloPlayer player, Border border);
}
