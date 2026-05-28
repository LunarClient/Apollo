/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.example.api.module;

import com.google.common.collect.Lists;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.example.module.impl.CosmeticExample;
import com.lunarclient.apollo.example.nms.CommandCosmetic;
import com.lunarclient.apollo.module.cosmetic.Cosmetic;
import com.lunarclient.apollo.module.cosmetic.CosmeticModule;
import com.lunarclient.apollo.module.cosmetic.Emote;
import com.lunarclient.apollo.module.cosmetic.Spray;
import com.lunarclient.apollo.module.cosmetic.options.BodyOptions;
import com.lunarclient.apollo.module.cosmetic.options.CloakOptions;
import com.lunarclient.apollo.module.cosmetic.options.CosmeticOptions;
import com.lunarclient.apollo.module.cosmetic.options.HatOptions;
import com.lunarclient.apollo.module.cosmetic.options.PetOptions;
import com.lunarclient.apollo.module.packetenrichment.raytrace.Direction;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class CosmeticApiExample extends CosmeticExample {

    private final CosmeticModule cosmeticModule = Apollo.getModuleManager().getModule(CosmeticModule.class);

    @Override
    public void equipNpcCosmeticsExample(Player viewer, UUID npcUuid) {
        List<Cosmetic> cosmetics = Lists.newArrayList(
            Cosmetic.builder()
                .id(434)
                .build(),
            Cosmetic.builder()
                .id(3654)
                .build(),
            Cosmetic.builder()
                .id(5095)
                .options(PetOptions.builder()
                    .flipShoulder(true)
                    .build())
                .build(),
            Cosmetic.builder()
                .id(3)
                .options(CloakOptions.builder()
                    .useClothPhysics(true)
                    .build())
                .build(),
            Cosmetic.builder()
                .id(3977)
                .build()
        );

        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.cosmeticModule.equipNpcCosmetics(apolloPlayer, npcUuid, cosmetics));
    }

    @Override
    public void equipNpcCosmeticsCopyLocalExample(Player viewer, UUID npcUuid) {
        this.cosmeticModule.equipNpcCosmetics(Recipients.ofEveryone(), npcUuid, new ArrayList<>(), true);
    }

    @Override
    public void equipNpcCosmeticsInternal(Player viewer, UUID npcUuid, List<Integer> cosmeticIds) {
        List<Cosmetic> cosmetics = cosmeticIds.stream()
            .map(id -> Cosmetic.builder().id(id).build())
            .collect(Collectors.toList());

        this.cosmeticModule.equipNpcCosmetics(Recipients.ofEveryone(), npcUuid, cosmetics);
    }

    @Override
    public void equipNpcCosmeticInternal(Player viewer, UUID npcUuid, CommandCosmetic spec) {
        this.cosmeticModule.equipNpcCosmetics(Recipients.ofEveryone(), npcUuid, Lists.newArrayList(this.toApiCosmetic(spec)));
    }

    @Override
    public void equipNpcCosmeticToViewer(Player viewer, UUID npcUuid, CommandCosmetic spec) {
        Apollo.getPlayerManager().getPlayer(viewer.getUniqueId()).ifPresent(apolloPlayer ->
            this.cosmeticModule.equipNpcCosmetics(apolloPlayer, npcUuid, Lists.newArrayList(this.toApiCosmetic(spec))));
    }

    private Cosmetic toApiCosmetic(CommandCosmetic spec) {
        return Cosmetic.builder()
            .id(spec.getId())
            .options(this.toApiOptions(spec.getOptions()))
            .build();
    }

    private CosmeticOptions toApiOptions(CommandCosmetic.Options options) {
        if (options instanceof CommandCosmetic.Hat) {
            CommandCosmetic.Hat hat = (CommandCosmetic.Hat) options;
            return HatOptions.builder()
                .showOverHelmet(hat.isShowOverHelmet())
                .showOverSkinLayer(hat.isShowOverSkinLayer())
                .heightOffset(hat.getHeightOffset())
                .build();
        } else if (options instanceof CommandCosmetic.Cloak) {
            CommandCosmetic.Cloak cloak = (CommandCosmetic.Cloak) options;
            return CloakOptions.builder()
                .useClothPhysics(cloak.isUseClothPhysics())
                .build();
        } else if (options instanceof CommandCosmetic.Pet) {
            CommandCosmetic.Pet pet = (CommandCosmetic.Pet) options;
            return PetOptions.builder()
                .flipShoulder(pet.isFlipShoulder())
                .build();
        } else if (options instanceof CommandCosmetic.Body) {
            CommandCosmetic.Body body = (CommandCosmetic.Body) options;
            return BodyOptions.builder()
                .showOverChestplate(body.isShowOverChestplate())
                .showOverLeggings(body.isShowOverLeggings())
                .showOverBoots(body.isShowOverBoots())
                .build();
        }

        return null;
    }

    @Override
    public void unequipNpcCosmeticsExample(Player viewer, UUID npcUuid) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            List<Integer> cosmeticIds = Lists.newArrayList(434, 3654, 5095, 3, 3977);
            this.cosmeticModule.unequipNpcCosmetics(apolloPlayer, npcUuid, cosmeticIds);
        });
    }

    @Override
    public void unequipNpcCosmeticsInternal(Player viewer, UUID npcUuid, List<Integer> cosmeticIds) {
        this.cosmeticModule.unequipNpcCosmetics(Recipients.ofEveryone(), npcUuid, cosmeticIds);
    }

    @Override
    public void resetNpcCosmeticsExample(Player viewer, UUID npcUuid) {
        this.cosmeticModule.resetNpcCosmetics(Recipients.ofEveryone(), npcUuid);
    }

    @Override
    public void startNpcEmoteExample(Player viewer, UUID npcUuid) {
        Emote emote = Emote.builder()
            .id(56)
            .build();

        this.cosmeticModule.startNpcEmote(Recipients.ofEveryone(), npcUuid, emote);
    }

    @Override
    public void startNpcEmoteInternal(Player viewer, UUID npcUuid, int emoteId, int metadata) {
        Emote emote = Emote.builder()
            .id(emoteId)
            .metadata(metadata)
            .build();

        this.cosmeticModule.startNpcEmote(Recipients.ofEveryone(), npcUuid, emote);
    }

    @Override
    public void stopNpcEmoteExample(Player viewer, UUID npcUuid) {
        this.cosmeticModule.stopNpcEmote(Recipients.ofEveryone(), npcUuid);
    }

    @Override
    public void resetNpcEmotesExample() {
        this.cosmeticModule.resetNpcEmotes(Recipients.ofEveryone());
    }

    @Override
    public void displaySprayExample(Player viewer, int sprayId) {
        Block block = viewer.getTargetBlockExact(10);
        if (block == null) {
            return;
        }

        Material material = block.getType();
        if (material.isAir() || !material.isSolid()) {
            return;
        }

        ApolloBlockLocation location = ApolloBlockLocation.builder()
            .world(block.getWorld().getName())
            .x(block.getX())
            .y(block.getY())
            .z(block.getZ())
            .build();

        Spray spray = Spray.builder()
            .sprayId(sprayId)
            .location(location)
            .facing(Direction.UP)
            .rotation(0f)
            .duration(Duration.ofSeconds(60))
            .build();

        this.cosmeticModule.displaySpray(Recipients.ofEveryone(), spray);
    }

    @Override
    public void removeSprayExample(int sprayId) {
        this.cosmeticModule.removeSpray(Recipients.ofEveryone(), sprayId);
    }

    @Override
    public void resetSpraysExample() {
        this.cosmeticModule.resetSprays(Recipients.ofEveryone());
    }

}
