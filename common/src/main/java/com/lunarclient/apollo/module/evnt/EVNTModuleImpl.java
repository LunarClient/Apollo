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

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.client.version.MinecraftVersion;
import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.player.ApolloPlayerHandshakeEvent;
import com.lunarclient.apollo.evnt.v1.CharacterAbilityMessage;
import com.lunarclient.apollo.evnt.v1.CloseGuiMessage;
import com.lunarclient.apollo.evnt.v1.EventGameOverviewMessage;
import com.lunarclient.apollo.evnt.v1.EventOverviewMessage;
import com.lunarclient.apollo.evnt.v1.EventPlayerMessage;
import com.lunarclient.apollo.evnt.v1.EventPlayerStatusMessage;
import com.lunarclient.apollo.evnt.v1.EventStatusOverviewMessage;
import com.lunarclient.apollo.evnt.v1.EventTeamStatusMessage;
import com.lunarclient.apollo.evnt.v1.OpenGuiMessage;
import com.lunarclient.apollo.evnt.v1.OverrideCharacterAbilityMessage;
import com.lunarclient.apollo.evnt.v1.OverrideCharacterCosmeticMessage;
import com.lunarclient.apollo.evnt.v1.OverrideCharacterMessage;
import com.lunarclient.apollo.evnt.v1.OverrideCharacterSuitResourcesMessage;
import com.lunarclient.apollo.evnt.v1.OverrideCosmeticResourcesMessage;
import com.lunarclient.apollo.evnt.v1.OverrideHeartTextureMessage;
import com.lunarclient.apollo.evnt.v1.ResetHeartTextureMessage;
import com.lunarclient.apollo.evnt.v1.UpdateCosmeticsMessage;
import com.lunarclient.apollo.module.evnt.event.EventGame;
import com.lunarclient.apollo.module.evnt.event.EventPlayer;
import com.lunarclient.apollo.module.evnt.event.EventPlayerStatus;
import com.lunarclient.apollo.module.evnt.event.EventStatus;
import com.lunarclient.apollo.module.evnt.event.EventTeam;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import lombok.NonNull;
import java.awt.Color;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;

/**
 * Provides the EVNT module.
 *
 * @since 1.0.0 // TODO
 */
public final class EVNTModuleImpl extends EVNTModule {

    /**
     * Creates a new instance of {@link EVNTModuleImpl}.
     *
     * @since 1.0.0
     */
    public EVNTModuleImpl() {
        super();
        this.handle(ApolloReceivePacketEvent.class, this::onCharacterSelection);
        this.handle(ApolloPlayerHandshakeEvent.class, this::onApolloPlayerHandshake);
    }


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
            .setCharacterType(this.toProtobuf(resource.getType()));

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
            builder.setTexturePath(texturePath);
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
    public void overrideCharacterSuitAccess(@NonNull Recipients recipients, @NonNull CharacterType type, @NonNull List<String> suitNames) {
        OverrideCharacterSuitResourcesMessage message = OverrideCharacterSuitResourcesMessage.newBuilder()
            .setCharacterType(this.toProtobuf(type))
            .addAllSuitNames(suitNames)
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void overrideCharacterCosmetic(@NonNull Recipients recipients, @NonNull UUID playerUuid, @NonNull CharacterType type) {
        this.overrideCharacterCosmetic(recipients, playerUuid, type, null);
    }

    @Override
    public void overrideCharacterCosmetic(@NonNull Recipients recipients, @NonNull UUID playerUuid, @NonNull CharacterType type, @Nullable String suitName) {
        OverrideCharacterCosmeticMessage.Builder builder = OverrideCharacterCosmeticMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(playerUuid))
            .setCharacterType(this.toProtobuf(type));

        if (suitName != null) {
            builder.setSuitName(suitName);
        }

        OverrideCharacterCosmeticMessage message = builder.build();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void overrideCharacterAbility(@NonNull Recipients recipients, @NonNull List<CharacterAbility> abilities) {
        OverrideCharacterAbilityMessage message = OverrideCharacterAbilityMessage.newBuilder()
            .addAllAbilities(abilities.stream().map(this::toProtobuf).collect(Collectors.toList()))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void overrideCharacter(@NonNull Recipients recipients, @NonNull Character character) {
        OverrideCharacterMessage.Builder builder = OverrideCharacterMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(character.getPlayerUuid()))
            .setCharacterType(this.toProtobuf(character.getType()))
            .setColor(NetworkTypes.toProtobuf(character.getColor()))
            .setEquipped(character.isEquipped());

        if (character.getSuitName() != null) {
            builder.setSuitName(character.getSuitName());
        }

        OverrideCharacterMessage message = builder.build();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void updateGameOverview(@NonNull Recipients recipients, @NonNull EventGame game) {
        EventGameOverviewMessage message = EventGameOverviewMessage.newBuilder()
            .setTeamOneStatus(this.toProtobuf(game.getTeamOne()))
            .setTeamTwoStatus(this.toProtobuf(game.getTeamTwo()))
            .setTierThreeHealth(game.getTierThreeHealth())
            .setGameStartTime(game.getGameStartTime())
            .setWitherShields(game.isWitherShields())
            .setLockGameTime(game.isLockGameTime())
            .setMidRespawnTime(game.getMidRespawnTime())
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void updateStatusOverview(@NonNull Recipients recipients, @NonNull EventStatus status) {
        EventStatusOverviewMessage message = EventStatusOverviewMessage.newBuilder()
            .addAllTeamOneStatus(status.getTeamOne().stream().map(this::toProtobuf).collect(Collectors.toList()))
            .addAllTeamTwoStatus(status.getTeamTwo().stream().map(this::toProtobuf).collect(Collectors.toList()))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void updateEventOverview(@NonNull Recipients recipients, @NonNull List<EventPlayerStatus> playerStatuses) {
        EventOverviewMessage message = EventOverviewMessage.newBuilder()
            .addAllPlayers(playerStatuses.stream().map(this::toProtobuf).collect(Collectors.toList()))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    private void onCharacterSelection(ApolloReceivePacketEvent event) {
        if (!this.getOptions().get(EVNTModule.DEBUG)) {
            return;
        }

        event.unpack(OverrideCharacterMessage.class).ifPresent(packet -> {
            ApolloPlayer apolloPlayer = event.getPlayer();

            this.overrideCharacter(apolloPlayer, Character.builder()
                    .playerUuid(apolloPlayer.getUniqueId())
                    .type(this.fromProtobuf(packet.getCharacterType()))
                    .color(Color.RED)
                    .equipped(true)
                .build());
        });
    }

    private void onApolloPlayerHandshake(ApolloPlayerHandshakeEvent event) {
        if (this.getOptions().get(EVNTModule.DISABLE_NOTIFY_MISMATCH)) {
            return;
        }

        boolean isRunningCorrectVersion = event.getMinecraftVersion() == MinecraftVersion.V1_18_2;
        boolean isOnCorrectBranch = event.getLunarClientVersion().getGitBranch().contains("evnt");

        if (isRunningCorrectVersion && isOnCorrectBranch) {
            return;
        }

        String state = null;
        if (!isRunningCorrectVersion && !isOnCorrectBranch) {
            state = "branch and version!";
        } else if (!isRunningCorrectVersion) {
            state = "version!";
        } else {
            state = "branch!";
        }

        Component message = Component.text(event.getPlayer().getName(), NamedTextColor.RED)
            .append(Component.text(" has connected without using the correct ", NamedTextColor.GRAY))
            .append(Component.text(state, NamedTextColor.RED, TextDecoration.BOLD));

        Apollo.getPlayerManager().getPlayers().stream()
            .filter(player -> player.hasPermission("apollo.evnt.notify"))
            .forEach(player -> player.sendMessage(message));
    }

    private com.lunarclient.apollo.evnt.v1.CharacterType toProtobuf(@NonNull CharacterType type) {
        return com.lunarclient.apollo.evnt.v1.CharacterType.forNumber(type.ordinal() + 1);
    }

    private CharacterType fromProtobuf(@NonNull com.lunarclient.apollo.evnt.v1.CharacterType type) {
        return CharacterType.values()[type.ordinal() - 1];
    }

    private com.lunarclient.apollo.evnt.v1.CharacterAbilityMessage toProtobuf(@NonNull CharacterAbility ability) {
        List<String> abilities = ability.getAbilities().stream()
            .map(ApolloComponent::toJson)
            .collect(Collectors.toList());

        return CharacterAbilityMessage.newBuilder()
            .setCharacterType(this.toProtobuf(ability.getType()))
            .addAllAbilitiesAdventure(abilities)
            .build();
    }

    private com.lunarclient.apollo.evnt.v1.EventTeamStatusMessage toProtobuf(@NonNull EventTeam team) {
        return EventTeamStatusMessage.newBuilder()
            .setTopWitherHealth(team.getTopWitherHealth())
            .setTopCrystalHealth(team.getTopCrystalHealth())
            .setMiddleWitherHealth(team.getMiddleWitherHealth())
            .setMiddleCrystalHealth(team.getMiddleCrystalHealth())
            .setBottomWitherHealth(team.getBottomWitherHealth())
            .setBottomCrystalHealth(team.getBottomCrystalHealth())
            .setDragonHealth(team.getDragonHealth())
            .build();
    }

    private com.lunarclient.apollo.evnt.v1.EventPlayerStatusMessage toProtobuf(@NonNull EventPlayer player) {
        return EventPlayerStatusMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(player.getPlayerUuid()))
            .setHealth(player.getHealth())
            .setUltimatePercentage(player.getUltimatePercentage())
            .setRespawnAt(player.getRespawnAt())
            .build();
    }

    private com.lunarclient.apollo.evnt.v1.EventPlayerMessage toProtobuf(@NonNull EventPlayerStatus player) {
        return EventPlayerMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(player.getPlayerUuid()))
            .setPlayerNameAdventure(ApolloComponent.toJson(player.getPlayerName()))
            .setCharacterType(this.toProtobuf(player.getType()))
            .setTeamOne(player.isTeamOne())
            .build();
    }

}
