package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloBlockPos;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.protocol.*;
import com.moonsworth.apollo.api.utils.UUIDUtils;
import com.moonsworth.apollo.api.utils.Vector3DUtils;

import java.util.List;
import java.util.UUID;

public class EVNTModule extends ApolloModule {

    public EVNTModule() {
        super("EVNTModule");
    }

    @Override
    public List<ApolloOption> options() {
        return ImmutableList.of();
    }

    @Override
    public boolean notifyPlayers() {
        return true;
    }

    @Override
    public List<ApolloPlatform.Kind> runsOn() {
        return ImmutableList.of(ApolloPlatform.Kind.SERVER);
    }

    public void togglePlayerArmor(ApolloPlayer player, boolean showArmor) {
        player.sendPacket(ToggleArmorMessage.newBuilder().setShowArmor(showArmor).build());
    }

    public void equipCharacter(ApolloPlayer player, CharacterType character, boolean enabled) {
        player.sendPacket(EquipCharacterMessage.newBuilder().setId(character).setEquipped(enabled).build());
    }

    /**
     * Displays a vignette texture (such as a pumpkin head).
     *
     * @param texture The texture to display
     * @param opacity The opacity to render the texture with in the range [0.0, 1.0]
     */
    public void displayVignette(ApolloPlayer player, String texture, float opacity) {
        var builder = DisplayVignetteMessage.newBuilder();
        if (texture != null) {
            builder = builder.addTexture(texture);
        }
        builder = builder.setOpacity(opacity);
        player.sendPacket(builder.build());
    }

    public void clearVignette(ApolloPlayer player) {
        player.sendPacket(DisplayVignetteMessage.newBuilder().setOpacity(0).build());
    }

    public void addBeacon(ApolloPlayer player, int id, int color, ApolloBlockPos position) {
        player.sendPacket(
                DisplayBeaconMessage.newBuilder().setId(id).setColor(color).setPosition(position.toProto()).build());
    }

    public void removeBeacon(ApolloPlayer player, int id) {
        player.sendPacket(RemoveBeaconMessage.newBuilder().setId(id).build());
    }

    public void displayGui(ApolloPlayer player, OpenGuiMessage.Gui gui) {
        player.sendPacket(OpenGuiMessage.newBuilder().setGuiId(gui).build());
    }

    public void closeGui(ApolloPlayer player) {
        //Pass in -1 to close the gui
        player.sendPacket(OpenGuiMessage.newBuilder().setClose(true).build());
    }

    public void hideArmor(ApolloPlayer sendTo, UUID affectedPlayer, ArmorPart armorPart, boolean hidden) {
        sendTo.sendPacket(
                HideArmor.newBuilder().setAffectedPlayer(UUIDUtils.fromUUID(affectedPlayer)).setArmor(armorPart)
                        .setHidden(hidden).build());
    }

    public void hideBodyPart(ApolloPlayer sendTo, UUID affectedPlayer, BodyPart bone, boolean hidden) {
        sendTo.sendPacket(HideBodyPart.newBuilder().setAffectedPlayer(UUIDUtils.fromUUID(affectedPlayer)).setBone(bone)
                .setHidden(hidden).build());
    }

    public void flipAllEntities(ApolloPlayer player, boolean flipped) {
        player.sendPacket(FlipAllEntitiesMessage.newBuilder().setFlipped(flipped).build());
    }

    public void setRainbowSheep(ApolloPlayer player, UUID entity, boolean rainbow) {
        player.sendPacket(
                EnableRainbowSheepMessage.newBuilder().setSheepEntityID(UUIDUtils.fromUUID(entity)).setRainbow(rainbow)
                        .build());
    }

    public void translateEntityNametag(ApolloPlayer player, UUID entity, double x, double y, double z) {
        player.sendPacket(TranslateNametagMessage.newBuilder().setEntityID(UUIDUtils.fromUUID(entity)).setTranslation(
                Vector3DUtils.fromVector(x, y, z)).build());
    }

    public void handleDemo() {
        registerPacket(EquipCharacterMessage.getDefaultInstance(), (player, message) -> player.sendPacket(
                EquipCharacterMessage.newBuilder().setEquipped(true).setId(message.getId()).build()));
    }

    public void updateCharacterResources(ApolloPlayer player, CharacterType character, String modelPath, String animationPath, String texturePath) {
        player.sendPacket(UpdateCosmeticResources.newBuilder().setCharacter(character).addModelPath(modelPath)
                .addAnimationPath(animationPath).addTexturePath(texturePath).build());
    }
}
