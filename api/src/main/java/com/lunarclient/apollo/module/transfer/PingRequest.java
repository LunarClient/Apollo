package com.lunarclient.apollo.module.transfer;

import com.lunarclient.apollo.roundtrip.ApolloRequest;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents the server ping request.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class PingRequest extends ApolloRequest<PingResponse> {

    /**
     * Returns a {@link List} of {@link String} server IPs.
     *
     * @return the server IP list
     * @since 1.0.0
     */
    List<String> serverIps;

}
