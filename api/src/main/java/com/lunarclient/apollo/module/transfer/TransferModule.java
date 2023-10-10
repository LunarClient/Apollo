/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.module.transfer;

import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.async.Future;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.player.ApolloPlayer;
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
@ModuleDefinition(id = "transfer", name = "Transfer")
public abstract class TransferModule extends ApolloModule {

    @Override
    public Collection<ApolloPlatform.Kind> getSupportedPlatforms() {
        return Arrays.asList(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

    // Simple implementations for the full version below

    /**
     * Sends the {@link PingRequest} to the {@link ApolloPlayer}.
     *
     * @param player    the player
     * @param serverIps all server IPs to ping
     * @return future to be listened to for errors/success
     * @since 1.0.0
     */
    public Future<PingResponse> ping(ApolloPlayer player, List<String> serverIps) {
        return this.ping(player, PingRequest.builder()
            .serverIps(serverIps)
            .build()
        );
    }

    /**
     * Attempts to transfer the {@link ApolloPlayer} to the given server IP.
     *
     * @param player   the player
     * @param serverIp the server IP to transfer to
     * @return future to be listened to for errors/success
     * @since 1.0.0
     */
    public Future<TransferResponse> transfer(ApolloPlayer player, String serverIp) {
        return this.transfer(player, TransferRequest.builder()
            .serverIp(serverIp)
            .build()
        );
    }

    /**
     * Sends the {@link PingRequest} to the {@link ApolloPlayer}.
     *
     * @param player  the player
     * @param request the ping request
     * @return future to be listened to for errors/success
     * @since 1.0.0
     */
    public abstract Future<PingResponse> ping(ApolloPlayer player, PingRequest request);

    /**
     * Sends the {@link TransferRequest} to the {@link ApolloPlayer}.
     *
     * @param player  the player
     * @param request the transfer request
     * @return future to be listened to for errors/success
     * @since 1.0.0
     */
    public abstract Future<TransferResponse> transfer(ApolloPlayer player, TransferRequest request);

}
