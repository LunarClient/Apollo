package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Nametag;
import org.jetbrains.annotations.ApiStatus;

// Uncompleted

/**
 * Represents the nametag module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Nametags extends ApolloModule {

    Nametags() {
        super("Nametags");
    }

    public abstract void sendNametag(final Nametag nametag, final ApolloPlayer... viewers);

    public abstract void resetNametag(final Nametag nametag, final ApolloPlayer... viewers);
}
