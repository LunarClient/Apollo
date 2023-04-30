package com.lunarclient.apollo.module.staffmod;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the staff mod module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class StaffModModule extends ApolloModule {

    StaffModModule() {
        super("Staff Mod");
    }

    /**
     * Enables the {@link StaffMod}s for the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param mods the staff mods
     * @since 1.0.0
     */
    public abstract void enableStaffMods(ApolloPlayer viewer, List<StaffMod> mods);

    /**
     * Disables the {@link StaffMod} from the {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param mods the staff mods
     * @since 1.0.0
     */
    public abstract void disableStaffMods(ApolloPlayer viewer, List<StaffMod> mods);

}
