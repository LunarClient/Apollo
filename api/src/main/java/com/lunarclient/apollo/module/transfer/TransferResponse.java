package com.lunarclient.apollo.module.transfer;

import com.lunarclient.apollo.roundtrip.ApolloResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Represents a server transfer response.
 *
 * @since 1.0.0
 */
@Getter
@SuperBuilder
public final class TransferResponse extends ApolloResponse {

    /**
     * Returns the transfer {@link Status} status.
     *
     * @return the transfer status
     * @since 1.0.0
     */
    Status status;

    /**
     * Represents the transfer state.
     *
     * @since 1.0.0
     */
    public enum Status {

        ACCEPTED,
        REJECTED

    }

}
