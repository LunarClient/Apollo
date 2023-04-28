package com.lunarclient.apollo.player.ui.transfer;

import com.lunarclient.apollo.roundtrip.ApolloRequest;
import com.lunarclient.apollo.roundtrip.ApolloResponse;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a server ping which the client will ping.
 *
 * @since 1.0.0
 */
public class ServerPing {

    /**
     * Represents the server ping request.
     *
     * @since 1.0.0
     */
    @Getter
    @Builder(setterPrefix = "with")
    public static class Request extends ApolloRequest<Response> {

        /**
         * Returns a {@link List} of {@link String} addresses.
         *
         * @return the address list
         * @since 1.0.0
         */
        List<String> addresses;
    }

    /**
     * Represents the server ping response.
     *
     * @since 1.0.0
     */
    @Getter
    @Builder(setterPrefix = "with")
    public static class Response extends ApolloResponse {

        /**
         * Returns a {@link List} of {@link PingData}s.
         *
         * @return the server data list
         * @since 1.0.0
         */
        List<PingData> data;

        /**
         * Represents the server data.
         *
         * @since 1.0.0
         */
        @Getter
        @Builder(setterPrefix = "with")
        public static class PingData {

            /**
             * Returns the pinged server {@link String} address.
             *
             * @return the server address
             * @since 1.0.0
             */
            String address;

            /**
             * Returns the pinged server {@link Status} status.
             *
             * @return the ping status
             * @since 1.0.0
             */
            Status status;

            /**
             * Returns the pinged server {@link Integer} ping.
             *
             * @return the ping
             * @since 1.0.0
             */
            int ping;

            /**
             * Represents the ping data state.
             *
             * @since 1.0.0
             */
            public enum Status {

                SUCCESS,
                TIMED_OUT
            }
        }
    }
}
