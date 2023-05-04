package com.lunarclient.apollo.module.transfer;

import com.lunarclient.apollo.roundtrip.ApolloResponse;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a server transfer response.
 *
 * @since 1.0.0
 */
@Getter
@Builder
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
