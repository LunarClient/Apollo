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
package com.lunarclient.apollo.module.cosmetic;

import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.cosmetic.v1.DisplaySprayMessage;
import com.lunarclient.apollo.cosmetic.v1.EquipNpcCosmeticsMessage;
import com.lunarclient.apollo.cosmetic.v1.RemoveSprayMessage;
import com.lunarclient.apollo.cosmetic.v1.ResetNpcCosmeticsMessage;
import com.lunarclient.apollo.cosmetic.v1.ResetNpcEmotesMessage;
import com.lunarclient.apollo.cosmetic.v1.ResetSpraysMessage;
import com.lunarclient.apollo.cosmetic.v1.StartNpcEmoteMessage;
import com.lunarclient.apollo.cosmetic.v1.StopNpcEmoteMessage;
import com.lunarclient.apollo.cosmetic.v1.UnequipNpcCosmeticsMessage;
import com.lunarclient.apollo.module.cosmetic.options.BodyOptions;
import com.lunarclient.apollo.module.cosmetic.options.CloakOptions;
import com.lunarclient.apollo.module.cosmetic.options.CosmeticOptions;
import com.lunarclient.apollo.module.cosmetic.options.HatOptions;
import com.lunarclient.apollo.module.cosmetic.options.PetOptions;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import static com.lunarclient.apollo.util.Ranges.checkStrictlyPositive;

/**
 * Provides the cosmetic module.
 *
 * @since 1.2.6
 */
public final class CosmeticModuleImpl extends CosmeticModule {

    @Override
    public void equipNpcCosmetics(@NonNull Recipients recipients, @NonNull UUID npcUuid, @NonNull List<Cosmetic> cosmetics) {
        this.equipNpcCosmetics(recipients, npcUuid, cosmetics, false);
    }

    @Override
    public void equipNpcCosmetics(@NonNull Recipients recipients, @NonNull UUID npcUuid, @NonNull List<Cosmetic> cosmetics, boolean copyLocalCosmetics) {
        List<com.lunarclient.apollo.cosmetic.v1.Cosmetic> cosmeticsProto = cosmetics.stream()
            .map(this::toProtobuf)
            .collect(Collectors.toList());

        EquipNpcCosmeticsMessage message = EquipNpcCosmeticsMessage.newBuilder()
            .setNpcUuid(NetworkTypes.toProtobuf(npcUuid))
            .addAllCosmetics(cosmeticsProto)
            .setCopyLocalCosmetics(copyLocalCosmetics)
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void unequipNpcCosmetics(@NonNull Recipients recipients, @NonNull UUID npcUuid, @NonNull List<Integer> cosmeticIds) {
        List<Integer> validatedIds = cosmeticIds.stream()
            .map(id -> checkStrictlyPositive(id, "Cosmetic#id"))
            .collect(Collectors.toList());

        UnequipNpcCosmeticsMessage message = UnequipNpcCosmeticsMessage.newBuilder()
            .setNpcUuid(NetworkTypes.toProtobuf(npcUuid))
            .addAllCosmeticIds(validatedIds)
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetNpcCosmetics(@NonNull Recipients recipients, @NonNull UUID npcUuid) {
        ResetNpcCosmeticsMessage message = ResetNpcCosmeticsMessage.newBuilder()
            .setNpcUuid(NetworkTypes.toProtobuf(npcUuid))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void startNpcEmote(@NonNull Recipients recipients, @NonNull UUID npcUuid, @NonNull Emote emote) {
        StartNpcEmoteMessage message = StartNpcEmoteMessage.newBuilder()
            .setNpcUuid(NetworkTypes.toProtobuf(npcUuid))
            .setEmote(com.lunarclient.apollo.cosmetic.v1.Emote.newBuilder()
                .setId(checkStrictlyPositive(emote.getId(), "Emote#id"))
                .setMetadata(emote.getMetadata())
                .build())
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void stopNpcEmote(@NonNull Recipients recipients, @NonNull UUID npcUuid) {
        StopNpcEmoteMessage message = StopNpcEmoteMessage.newBuilder()
            .setNpcUuid(NetworkTypes.toProtobuf(npcUuid))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetNpcEmotes(@NonNull Recipients recipients) {
        ResetNpcEmotesMessage message = ResetNpcEmotesMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void displaySpray(@NonNull Recipients recipients, @NonNull Spray spray) {
        DisplaySprayMessage message = DisplaySprayMessage.newBuilder()
            .setSprayId(checkStrictlyPositive(spray.getSprayId(), "Spray#sprayId"))
            .setLocation(NetworkTypes.toProtobuf(spray.getLocation()))
            .setFacing(NetworkTypes.toProtobuf(spray.getFacing()))
            .setRotation(spray.getRotation())
            .setDuration(NetworkTypes.toProtobuf(spray.getDuration()))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removeSpray(@NonNull Recipients recipients, int sprayId) {
        RemoveSprayMessage message = RemoveSprayMessage.newBuilder()
            .setSprayId(checkStrictlyPositive(sprayId, "Spray#sprayId"))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removeSpray(@NonNull Recipients recipients, int sprayId, @Nullable ApolloBlockLocation location) {
        RemoveSprayMessage.Builder builder = RemoveSprayMessage.newBuilder()
            .setSprayId(checkStrictlyPositive(sprayId, "Spray#sprayId"));

        if (location != null) {
            builder.setLocation(NetworkTypes.toProtobuf(location));
        }

        RemoveSprayMessage message = builder.build();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetSprays(@NonNull Recipients recipients) {
        ResetSpraysMessage message = ResetSpraysMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    private com.lunarclient.apollo.cosmetic.v1.Cosmetic toProtobuf(Cosmetic cosmetic) {
        com.lunarclient.apollo.cosmetic.v1.Cosmetic.Builder builder = com.lunarclient.apollo.cosmetic.v1.Cosmetic.newBuilder()
            .setId(checkStrictlyPositive(cosmetic.getId(), "Cosmetic#id"));

        CosmeticOptions options = cosmetic.getOptions();
        if (options == null) {
            return builder.build();
        }

        if (options instanceof HatOptions) {
            HatOptions hatOptions = (HatOptions) options;
            builder.setHatOptions(com.lunarclient.apollo.cosmetic.v1.HatOptions.newBuilder()
                .setShowOverHelmet(hatOptions.isShowOverHelmet())
                .setShowOverSkinLayer(hatOptions.isShowOverSkinLayer())
                .setHeightOffset(hatOptions.getHeightOffset())
                .build());
        } else if (options instanceof CloakOptions) {
            CloakOptions cloakOptions = (CloakOptions) options;
            builder.setCloakOptions(com.lunarclient.apollo.cosmetic.v1.CloakOptions.newBuilder()
                .setUseClothPhysics(cloakOptions.isUseClothPhysics())
                .build());
        } else if (options instanceof PetOptions) {
            PetOptions petOptions = (PetOptions) options;
            builder.setPetOptions(com.lunarclient.apollo.cosmetic.v1.PetOptions.newBuilder()
                .setFlipShoulder(petOptions.isFlipShoulder())
                .build());
        } else if (options instanceof BodyOptions) {
            BodyOptions bodyOptions = (BodyOptions) options;
            builder.setBodyOptions(com.lunarclient.apollo.cosmetic.v1.BodyOptions.newBuilder()
                .setShowOverChestplate(bodyOptions.isShowOverChestplate())
                .setShowOverLeggings(bodyOptions.isShowOverLeggings())
                .setShowOverBoots(bodyOptions.isShowOverBoots())
                .build());
        }

        return builder.build();
    }

}
