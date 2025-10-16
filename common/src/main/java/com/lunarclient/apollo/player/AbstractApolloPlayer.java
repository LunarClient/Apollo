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
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.async.Future;
import com.lunarclient.apollo.async.future.UncertainFuture;
import com.lunarclient.apollo.client.mod.LunarClientMod;
import com.lunarclient.apollo.client.version.LunarClientVersion;
import com.lunarclient.apollo.client.version.MinecraftVersion;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.mods.ModStatus;
import com.lunarclient.apollo.module.tebex.TebexEmbeddedCheckoutSupport;
import com.lunarclient.apollo.roundtrip.ApolloRequest;
import com.lunarclient.apollo.roundtrip.ApolloResponse;
import com.lunarclient.apollo.world.ApolloWorld;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

/**
 * Provides convenience methods for sending packets to the client.
 *
 * @since 1.0.0
 */
@Getter @Setter
public abstract class AbstractApolloPlayer implements ApolloPlayer {

    private MinecraftVersion minecraftVersion;
    private LunarClientVersion lunarClientVersion;
    private List<LunarClientMod> installedMods;
    private TebexEmbeddedCheckoutSupport tebexEmbeddedCheckoutSupport;
    private ModStatus modStatus;

    @Override
    public Optional<ApolloWorld> getWorld() {
        return Optional.empty();
    }

    @Override
    public Optional<ApolloLocation> getLocation() {
        return Optional.empty();
    }

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
        ApolloManager.getRoundtripManager().registerListener(request, future);

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
