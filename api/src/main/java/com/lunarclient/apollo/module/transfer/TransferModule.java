package com.lunarclient.apollo.module.transfer;

import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.roundtrip.async.Future;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the transfer module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class TransferModule extends ApolloModule {

    TransferModule() {
        super("Transfer");
    }

    @Override
    public Collection<ApolloPlatform.Kind> getSupport() {
        return Arrays.asList(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

    // Simple implementations for the full version below
    /**
     * Sends the {@link PingRequest} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param serverIps all server IPs to ping
     * @return future to be listened to for errors/success
     * @since 1.0.0
     */
    public Future<PingResponse> ping(ApolloPlayer player, List<String> serverIps) {
        return ping(player, PingRequest.builder()
            .serverIps(serverIps)
            .build()
        );
    }

    /**
     * Attempts to transfer the {@link ApolloPlayer} to the given server IP
     *
     * @param player the player
     * @param serverIp the server IP to transfer to
     * @return future to be listened to for errors/success
     * @since 1.0.0
     */
    public Future<TransferResponse> transfer(ApolloPlayer player, String serverIp) {
        return transfer(player, TransferRequest.builder()
            .serverIp(serverIp)
            .build()
        );
    }

    /**
     * Sends the {@link PingRequest} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param request the ping request
     * @return future to be listened to for errors/success
     * @since 1.0.0
     */
    public abstract Future<PingResponse> ping(ApolloPlayer player, PingRequest request);

    /**
     * Sends the {@link TransferRequest} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param request the transfer request
     * @return future to be listened to for errors/success
     * @since 1.0.0
     */
    public abstract Future<TransferResponse> transfer(ApolloPlayer player, TransferRequest request);

}
