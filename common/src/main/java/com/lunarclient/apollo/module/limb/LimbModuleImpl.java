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
