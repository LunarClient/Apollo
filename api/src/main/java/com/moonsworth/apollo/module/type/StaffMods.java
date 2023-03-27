package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.StaffMod;
import org.jetbrains.annotations.ApiStatus;

// Uncompleted

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

    public abstract void giveModule(final ApolloPlayer player, final StaffMod.Module module);

    public abstract void removeModule(final ApolloPlayer player, final StaffMod.Module module);

    public abstract void giveAllModules(final ApolloPlayer player);

    public abstract void removeAllModules(final ApolloPlayer player);
}
