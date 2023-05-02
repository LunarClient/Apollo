package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.limb.ArmorPiece;
import com.lunarclient.apollo.module.limb.BodyPart;
import com.lunarclient.apollo.module.limb.LimbModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.Set;

public class LimbExample {

    private final LimbModule limbModule = Apollo.getModuleManager().getModule(LimbModule.class);

    public void hideArmorExample(Player viewer, Player target) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.limbModule.hideArmorPieces(apolloPlayer,
                target.getUniqueId(),
                Set.of(ArmorPiece.HELMET, ArmorPiece.LEGGINGS)
            );
        });
    }

    public void resetArmorExample(Player viewer, Player target) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.limbModule.resetArmorPieces(apolloPlayer,
                target.getUniqueId(),
                Set.of(ArmorPiece.HELMET, ArmorPiece.LEGGINGS)
            );
        });
    }

    public void hideBodyExample(Player viewer, Player target) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.limbModule.hideBodyParts(apolloPlayer,
                target.getUniqueId(),
                Set.of(BodyPart.HEAD, BodyPart.RIGHT_ARM)
            );
        });
    }

    public void resetBodyExample(Player viewer, Player target) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.limbModule.resetBodyParts(apolloPlayer,
                target.getUniqueId(),
                Set.of(BodyPart.HEAD, BodyPart.RIGHT_ARM)
            );
        });
    }
}
