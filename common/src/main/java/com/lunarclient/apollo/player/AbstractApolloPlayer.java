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
package com.lunarclient.apollo.player;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.roundtrip.ApolloRequest;
import com.lunarclient.apollo.roundtrip.ApolloResponse;
import com.lunarclient.apollo.roundtrip.async.Future;
import com.lunarclient.apollo.roundtrip.async.future.UncertainFuture;

/**
 * Provides convenience methods for sending packets to the client.
 *
 * @since 1.0.0
 */
public abstract class AbstractApolloPlayer implements ApolloPlayer {

    /**
     * Sends the provided message packet to the client
     * with an expected {@link Future} response.
     *
     * @param <T>     the future representing the response
     * @param request the apollo request
     * @param message the message
     * @return the apollo response
     * @since 1.0.0
     */
    public <T extends ApolloResponse> Future<T> sendRoundTripPacket(ApolloRequest<T> request, Message message) {
        this.sendPacket(message);

        UncertainFuture<T> future = new UncertainFuture<>();
        Apollo.getRoundtripManager().registerListener(request, future);

        return future;
    }

    /**
     * Sends the provided message packet to the client.
     *
     * @param message the message
     * @since 1.0.0
     */
    public void sendPacket(Message message) {
        ApolloManager.getNetworkManager().sendPacket(this, Any.pack(message));
    }

    /**
     * Sends the provided raw packet to the client.
     *
     * @param messages the messages
     * @since 1.0.0
     */
    public abstract void sendPacket(byte[] messages);

}
