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
package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.limb.ArmorPiece;
import com.lunarclient.apollo.module.limb.BodyPart;
import com.lunarclient.apollo.module.limb.LimbModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.EnumSet;
import java.util.Optional;
import org.bukkit.entity.Player;

public class LimbExample {

    private final LimbModule limbModule = Apollo.getModuleManager().getModule(LimbModule.class);

    public void hideArmorExample(Player viewer, Player target) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.limbModule.hideArmorPieces(apolloPlayer,
                target.getUniqueId(),
                EnumSet.of(ArmorPiece.HELMET, ArmorPiece.LEGGINGS)
            );
        });
    }

    public void resetArmorExample(Player viewer, Player target) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.limbModule.resetArmorPieces(apolloPlayer,
                target.getUniqueId(),
                EnumSet.of(ArmorPiece.HELMET, ArmorPiece.LEGGINGS)
            );
        });
    }

    public void hideBodyExample(Player viewer, Player target) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.limbModule.hideBodyParts(apolloPlayer,
                target.getUniqueId(),
                EnumSet.of(BodyPart.HEAD, BodyPart.RIGHT_ARM)
            );
        });
    }

    public void resetBodyExample(Player viewer, Player target) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.limbModule.resetBodyParts(apolloPlayer,
                target.getUniqueId(),
                EnumSet.of(BodyPart.HEAD, BodyPart.RIGHT_ARM)
            );
        });
    }

}
