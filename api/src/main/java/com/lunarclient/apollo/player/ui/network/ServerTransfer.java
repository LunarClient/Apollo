package com.lunarclient.apollo.player.ui.network;

import com.lunarclient.apollo.roundtrip.ApolloRequest;
import com.lunarclient.apollo.roundtrip.ApolloResponse;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Represents a server transfer.
 *
 * @since 1.0.0
 */
public class ServerTransfer {

    /**
     * Represents the server transfer request.
     *
     * @since 1.0.0
     */
    @EqualsAndHashCode(callSuper = true)
    @Value(staticConstructor = "of")
    public static class Request extends ApolloRequest<Response> {

        /**
         * Returns the transfer request {@link String} server address.
         *
         * @return the request address
         * @since 1.0.0
         */
        String address;
    }

    /**
     * Represents the server transfer response.
     *
     * @since 1.0.0
     */
    @EqualsAndHashCode(callSuper = true)
    @Value(staticConstructor = "of")
    public static class Response extends ApolloResponse {

        /**
         * Returns the transfer response {@link Boolean} accepted state.
         *
         * @return the response accepted state
         * @since 1.0.0
         */
        boolean accepted;
    }
}
