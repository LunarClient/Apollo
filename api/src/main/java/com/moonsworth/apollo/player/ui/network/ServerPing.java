package com.moonsworth.apollo.player.ui.network;

import com.moonsworth.apollo.roundtrip.ApolloRequest;
import com.moonsworth.apollo.roundtrip.ApolloResponse;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Represents a server ping which the client will ping.
 * <p>
 *
 * @since 1.0.0
 */
public class ServerPing {

    /**
     * Represents the server ping request.
     *
     * @since 1.0.0
     */
    @EqualsAndHashCode(callSuper = true)
    @Value(staticConstructor = "of")
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
    @EqualsAndHashCode(callSuper = true)
    @Value(staticConstructor = "of")
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
        @Value(staticConstructor = "of")
        public static class PingData {

            /**
             * Returns the pinged server {@link String} address.
             *
             * @return the server address
             * @since 1.0.0
             */
            String address;

            /**
             * Returns the pinged server {@link State} state.
             *
             * @return the ping state
             * @since 1.0.0
             */
            State state;

            /**
             * Returns the pinged server {@link Integer} ping.
             * <>p</>
             * If the {@link State} returns {@link State#TIMED_OUT},
             * this value will be {@link Integer#MIN_VALUE}
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
            public enum State {

                SUCCESS,
                TIMED_OUT
            }
        }
    }
}
