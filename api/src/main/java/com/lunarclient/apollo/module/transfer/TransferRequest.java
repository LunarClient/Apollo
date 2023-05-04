package com.lunarclient.apollo.module.transfer;

import com.lunarclient.apollo.roundtrip.ApolloRequest;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a server transfer request.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class TransferRequest extends ApolloRequest<TransferResponse> {

    /**
     * Returns the transfer request {@link String} server IP.
     *
     * @return the server IP
     * @since 1.0.0
     */
    String serverIp;

}
