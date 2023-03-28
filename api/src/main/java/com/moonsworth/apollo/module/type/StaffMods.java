package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.StaffMod;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the staff mod module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class StaffMods extends ApolloModule {

    // TODO: PermissionNodeOption

    StaffMods() {
        super("StaffMods");
    }


    /**
     * Gives the {@link StaffMod} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param mod the staff mod
     * @since 1.0.0
     */
    public abstract void giveModule(final ApolloPlayer player, final StaffMod mod);

    /**
     * Removes the {@link StaffMod} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param mod the staff mod
     * @since 1.0.0
     */
    public abstract void removeModule(final ApolloPlayer player, final StaffMod mod);

    /**
     * Gives all {@link StaffMod}s to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void giveAllModules(final ApolloPlayer player);

    /**
     * Removes all {@link StaffMod}s from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void removeAllModules(final ApolloPlayer player);
}
