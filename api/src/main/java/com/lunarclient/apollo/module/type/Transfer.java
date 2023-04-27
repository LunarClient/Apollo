package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.transfer.ServerPing;
import com.lunarclient.apollo.player.ui.transfer.ServerTransfer;
import com.lunarclient.apollo.roundtrip.async.Handler;
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
