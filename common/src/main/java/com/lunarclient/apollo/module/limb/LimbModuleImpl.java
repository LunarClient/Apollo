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
package com.lunarclient.apollo.module.limb;

import com.lunarclient.apollo.limb.v1.HideArmorPiecesMessage;
import com.lunarclient.apollo.limb.v1.HideBodyPartMessage;
import com.lunarclient.apollo.limb.v1.ResetArmorPiecesMessage;
import com.lunarclient.apollo.limb.v1.ResetBodyPartMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;

/**
 * Provides the limb module.
 *
 * @since 1.0.0
 */
public final class LimbModuleImpl extends LimbModule {

    @Override
    public void hideArmorPieces(@NonNull ApolloPlayer viewer, @NonNull UUID playerUuid, @NonNull Collection<ArmorPiece> armorPieces) {
        Set<com.lunarclient.apollo.limb.v1.ArmorPiece> pieces = armorPieces.stream()
            .map(this::toProtobuf)
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(HideArmorPiecesMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(playerUuid))
            .addAllArmorPieces(pieces)
            .build()
        );
    }

    @Override
    public void resetArmorPieces(@NonNull ApolloPlayer viewer, @NonNull UUID playerUuid, @NonNull Collection<ArmorPiece> armorPieces) {
        Set<com.lunarclient.apollo.limb.v1.ArmorPiece> pieces = armorPieces.stream()
            .map(this::toProtobuf)
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(ResetArmorPiecesMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(playerUuid))
            .addAllArmorPieces(pieces)
            .build()
        );
    }

    @Override
    public void hideBodyParts(@NonNull ApolloPlayer viewer, @NonNull UUID playerUuid, @NonNull Collection<BodyPart> bodyParts) {
        Set<com.lunarclient.apollo.limb.v1.BodyPart> parts = bodyParts.stream()
            .map(this::toProtobuf)
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(HideBodyPartMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(playerUuid))
            .addAllBodyParts(parts)
            .build()
        );
    }

    @Override
    public void resetBodyParts(@NonNull ApolloPlayer viewer, @NonNull UUID playerUuid, @NonNull Collection<BodyPart> bodyParts) {
        Set<com.lunarclient.apollo.limb.v1.BodyPart> parts = bodyParts.stream()
            .map(this::toProtobuf)
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(ResetBodyPartMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(playerUuid))
            .addAllBodyParts(parts)
            .build()
        );
    }

    private com.lunarclient.apollo.limb.v1.ArmorPiece toProtobuf(ArmorPiece armorPiece) {
        return com.lunarclient.apollo.limb.v1.ArmorPiece.forNumber(armorPiece.ordinal() + 1);
    }

    private com.lunarclient.apollo.limb.v1.BodyPart toProtobuf(BodyPart bodyPart) {
        return com.lunarclient.apollo.limb.v1.BodyPart.forNumber(bodyPart.ordinal() + 1);
    }

}
