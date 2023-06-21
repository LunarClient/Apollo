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
package com.lunarclient.apollo.module.beam;

import com.google.protobuf.ByteString;
import com.lunarclient.apollo.beam.v1.DisplayBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.RemoveBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.ResetBeaconBeamsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import lombok.NonNull;

/**
 * Provides the beams module.
 *
 * @since 1.0.0
 */
public final class BeamModuleImpl extends BeamModule {

    @Override
    public void displayBeam(@NonNull ApolloPlayer viewer, @NonNull Beam beam) {
        ((AbstractApolloPlayer) viewer).sendPacket(DisplayBeaconBeamMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(beam.getId()))
            .setLocation(NetworkTypes.toProtobuf(beam.getLocation()))
            .setColor(NetworkTypes.toProtobuf(beam.getColor()))
            .build()
        );
    }

    @Override
    public void removeBeam(@NonNull ApolloPlayer viewer, @NonNull String beamId) {
        ((AbstractApolloPlayer) viewer).sendPacket(RemoveBeaconBeamMessage.newBuilder()
            .setId(ByteString.copyFromUtf8(beamId))
            .build()
        );
    }

    @Override
    public void removeBeam(@NonNull ApolloPlayer viewer, @NonNull Beam beam) {
        this.removeBeam(viewer, beam.getId());
    }

    @Override
    public void resetBeams(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(ResetBeaconBeamsMessage.getDefaultInstance());
    }

}
