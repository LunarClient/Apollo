package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.ApolloPlatform;
import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.network.ServerPing;
import com.moonsworth.apollo.player.ui.network.ServerTransfer;
import com.moonsworth.apollo.roundtrip.async.Handler;
import java.util.Arrays;
import java.util.Collection;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the transfer module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Transfer extends ApolloModule {

    Transfer() {
        super("Transfer");
    }

    @Override
    public Collection<ApolloPlatform.Kind> getSupport() {
        return Arrays.asList(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

    /**
     * Sends the {@link ServerPing.Request} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param ping the ping request
     * @since 1.0.0
     */
    public abstract void ping(ApolloPlayer player, ServerPing.Request ping, Handler<ServerPing.Response> response);

    /**
     * Sends the {@link ServerTransfer.Request} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param transfer the transfer request
     * @since 1.0.0
     */
    public abstract void transfer(ApolloPlayer player, ServerTransfer.Request transfer, Handler<ServerTransfer.Response> response);
}
