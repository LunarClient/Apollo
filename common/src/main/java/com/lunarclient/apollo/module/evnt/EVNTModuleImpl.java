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
package com.lunarclient.apollo.module.evnt;

import com.lunarclient.apollo.evnt.v1.CloseGuiMessage;
import com.lunarclient.apollo.evnt.v1.OpenGuiMessage;
import com.lunarclient.apollo.evnt.v1.OverrideCharacterMessage;
import com.lunarclient.apollo.evnt.v1.OverrideCosmeticResourcesMessage;
import com.lunarclient.apollo.evnt.v1.OverrideHeartTextureMessage;
import com.lunarclient.apollo.evnt.v1.ResetHeartTextureMessage;
import com.lunarclient.apollo.evnt.v1.UpdateCosmeticsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import lombok.NonNull;

import java.util.List;

/**
 * Provides the EVNT module.
 *
 * @since 1.0.0 // TODO
 */
public final class EVNTModuleImpl extends EVNTModule {

    @Override
    public void overrideHeartTexture(@NonNull Recipients recipients, int x, boolean hardcore) {
        OverrideHeartTextureMessage message = OverrideHeartTextureMessage.newBuilder()
            .setLocationX(x)
            .setHardcore(hardcore)
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetHeartTexture(@NonNull Recipients recipients) {
        ResetHeartTextureMessage message = ResetHeartTextureMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void openGui(@NonNull Recipients recipients, @NonNull GuiType type) {
        OpenGuiMessage message = OpenGuiMessage.newBuilder()
            .setGuiType(com.lunarclient.apollo.evnt.v1.GuiType.forNumber(type.ordinal() + 1))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void closeGui(@NonNull Recipients recipients) {
        CloseGuiMessage message = CloseGuiMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void overrideCosmeticResources(@NonNull Recipients recipients, @NonNull CharacterResource resource) {
        OverrideCosmeticResourcesMessage.Builder builder = OverrideCosmeticResourcesMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(resource.getPlayerUuid()))
            .setCharacterType(com.lunarclient.apollo.evnt.v1.CharacterType.forNumber(resource.getType().ordinal() + 1));

        String modelPath = resource.getModelPath();
        String animationPath = resource.getAnimationPath();
        String texturePath = resource.getTexturePath();

        if (modelPath != null) {
            builder.setModelPath(modelPath);
        }

        if (animationPath != null) {
            builder.setAnimationPath(animationPath);
        }

        if (texturePath != null) {
            builder.setAnimationPath(texturePath);
        }

        OverrideCosmeticResourcesMessage message = builder.build();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void updateCosmetics(@NonNull Recipients recipients, @NonNull List<String> models, @NonNull List<String> animations) {
        UpdateCosmeticsMessage message = UpdateCosmeticsMessage.newBuilder()
            .addAllModelLocations(models)
            .addAllAnimationLocations(animations)
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void overrideCharacter(@NonNull Recipients recipients, @NonNull Character character) {
        OverrideCharacterMessage message = OverrideCharacterMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(character.getPlayerUuid()))
            .setCharacterType(com.lunarclient.apollo.evnt.v1.CharacterType.forNumber(character.getType().ordinal() + 1))
            .setColor(NetworkTypes.toProtobuf(character.getColor()))
            .setEquipped(character.isEquipped())
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

}
