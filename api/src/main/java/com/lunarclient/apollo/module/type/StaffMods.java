package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the staff mod module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class StaffMods extends ApolloModule {

    StaffMods() {
        super("StaffMods");
    }

    /**
     * Enables the {@link com.lunarclient.apollo.player.ui.StaffMods}s for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param mod the staff mod
     * @since 1.0.0
     */
    public abstract void enableStaffMods(ApolloPlayer player, com.lunarclient.apollo.player.ui.StaffMods mod);

    /**
     * Disables the {@link com.lunarclient.apollo.player.ui.StaffMods} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param mod the staff mod
     * @since 1.0.0
     */
    public abstract void disableStaffMods(ApolloPlayer player, com.lunarclient.apollo.player.ui.StaffMods mod);

}
