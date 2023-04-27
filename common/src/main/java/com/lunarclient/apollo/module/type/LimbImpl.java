package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.limb.v1.ArmorPiece;
import com.lunarclient.apollo.limb.v1.BodyPart;
import com.lunarclient.apollo.limb.v1.HideArmorPiecesMessage;
import com.lunarclient.apollo.limb.v1.HideBodyPartMessage;
import com.lunarclient.apollo.limb.v1.ResetArmorPiecesMessage;
import com.lunarclient.apollo.limb.v1.ResetBodyPartMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.limb.Armor;
import com.lunarclient.apollo.player.ui.limb.Body;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Provides the limb module.
 *
 * @since 1.0.0
 */
public class LimbImpl extends Limb {

    public LimbImpl() {
        super();
    }

    @Override
    public void hideArmorPieces(ApolloPlayer player, Armor armor) {
        requireNonNull(player, "player");
        requireNonNull(armor, "armor");

        Set<ArmorPiece> pieces = armor.getArmorPiece().stream()
            .map(piece -> ArmorPiece.forNumber(piece.ordinal() + 1))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) player).sendPacket(HideArmorPiecesMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(armor.getPlayer()))
            .addAllArmorPieces(pieces)
            .build());
    }

    @Override
    public void resetArmorPieces(ApolloPlayer player, Armor armor) {
        requireNonNull(player, "player");
        requireNonNull(armor, "armor");

        Set<ArmorPiece> pieces = armor.getArmorPiece().stream()
            .map(piece -> ArmorPiece.forNumber(piece.ordinal() + 1))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) player).sendPacket(ResetArmorPiecesMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(armor.getPlayer()))
            .addAllArmorPieces(pieces)
            .build());
    }

    @Override
    public void hideBodyParts(ApolloPlayer player, Body body) {
        requireNonNull(player, "player");
        requireNonNull(body, "body");

        Set<BodyPart> parts = body.getBodyPart().stream()
            .map(part -> BodyPart.forNumber(part.ordinal() + 1))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) player).sendPacket(HideBodyPartMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(body.getPlayer()))
            .addAllBodyParts(parts)
            .build());
    }

    @Override
    public void resetBodyParts(ApolloPlayer player, Body body) {
        requireNonNull(player, "player");
        requireNonNull(body, "body");

        Set<BodyPart> parts = body.getBodyPart().stream()
            .map(part -> BodyPart.forNumber(part.ordinal() + 1))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) player).sendPacket(ResetBodyPartMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(body.getPlayer()))
            .addAllBodyParts(parts)
            .build());
    }

}
