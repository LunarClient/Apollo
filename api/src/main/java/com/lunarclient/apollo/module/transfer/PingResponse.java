package com.lunarclient.apollo.module.transfer;

import com.lunarclient.apollo.roundtrip.ApolloResponse;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Represents the server ping response.
 *
 * @since 1.0.0
 */
@Getter
@SuperBuilder
public final class PingResponse extends ApolloResponse {

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
    @Builder
    public static class PingData {

        /**
         * Returns the pinged server {@link String} IP.
         *
         * @return the server IP
         * @since 1.0.0
         */
        String serverIp;

        /**
         * Returns the pinged server {@link Status} status.
         *
         * @return the ping status
         * @since 1.0.0
         */
        Status status;

        /**
         * Returns the pinged server {@link Integer} ping, in milliseconds.
         *
         * @return the ping
         * @since 1.0.0
         */
        int pingMillis;

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
