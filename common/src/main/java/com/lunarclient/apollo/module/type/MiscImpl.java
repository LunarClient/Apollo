package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.misc.FlippedEntity;
import com.lunarclient.apollo.player.ui.misc.RainbowSheep;
import com.lunarclient.apollo.player.ui.misc.Vignette;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.FlipEntityMessage;
import lunarclient.apollo.modules.RainbowSheepMessage;
import lunarclient.apollo.modules.VignetteMessage;

import static java.util.Objects.requireNonNull;

/**
 * Provides the misc module.
 *
 * @since 1.0.0
 */
public final class MiscImpl extends Misc {

    public MiscImpl() {
        super();
    }

    @Override
    public void flipEntities(ApolloPlayer player, FlippedEntity... entities) {
        requireNonNull(player, "player");
        requireNonNull(entities, "entities");

        List<FlipEntityMessage.Entity> flippedEntities = Arrays.stream(entities).map(entity ->
            FlipEntityMessage.Entity.newBuilder()
                .setEntityId(NetworkTypes.toProtobuf(entity.getEntity()))
                .setFlipped(entity.isFlipped())
                .build()
        ).collect(Collectors.toList());

        FlipEntityMessage message = FlipEntityMessage.newBuilder()
            .addAllEntities(flippedEntities)
            .build();

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, message);
    }

    @Override
    public void flipAllEntities(ApolloPlayer player) {
        requireNonNull(player, "player");

        FlipEntityMessage message = FlipEntityMessage.newBuilder()
            .addAllEntities(null)
            .build();

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, message);
    }

    @Override
    public void rainbowSheep(ApolloPlayer player, RainbowSheep... sheep) {
        requireNonNull(player, "player");
        requireNonNull(sheep, "sheep");

        List<RainbowSheepMessage.Sheep> rainbowSheep = Arrays.stream(sheep).map(entity ->
            RainbowSheepMessage.Sheep.newBuilder()
                .setEntityId(NetworkTypes.toProtobuf(entity.getEntity()))
                .setRainbow(entity.isRainbow())
                .build()
        ).collect(Collectors.toList());

        RainbowSheepMessage message = RainbowSheepMessage.newBuilder()
            .addAllSheep(rainbowSheep)
            .build();

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, message);
    }

    @Override
    public void rainbowSheep(ApolloPlayer player) {
        requireNonNull(player, "player");

        RainbowSheepMessage message = RainbowSheepMessage.newBuilder()
            .addAllSheep(null)
            .build();

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, message);
    }

    @Override
    public void displayVignette(ApolloPlayer player, Vignette vignette) {
        requireNonNull(player, "player");
        requireNonNull(vignette, "vignette");

        VignetteMessage message = VignetteMessage.newBuilder()
            .setTexture(vignette.getResourceLocation())
            .setOpacity(vignette.getOpacity())
            .build();

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, message);
    }
}
