package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.StaffMod;
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
     * Gives the {@link StaffMod} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param mod the staff mod
     * @since 1.0.0
     */
    public abstract void giveModule(ApolloPlayer player, StaffMod mod);

    /**
     * Removes the {@link StaffMod} from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param mod the staff mod
     * @since 1.0.0
     */
    public abstract void removeModule(ApolloPlayer player, StaffMod mod);

    /**
     * Gives all {@link StaffMod}s to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void giveAllModules(ApolloPlayer player);

    /**
     * Removes all {@link StaffMod}s from the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void removeAllModules(ApolloPlayer player);

}
