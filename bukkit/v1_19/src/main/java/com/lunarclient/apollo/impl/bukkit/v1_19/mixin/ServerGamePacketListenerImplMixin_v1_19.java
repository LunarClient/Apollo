package com.lunarclient.apollo.impl.bukkit.v1_19.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.network.PacketSendListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundCooldownPacket;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ServerGamePacketListenerImplMixin_v1_19 {

    private static final List<SoundEvent> BLOCKED_SOUND_EFFECTS = ImmutableList.of(
            SoundEvents.PLAYER_ATTACK_CRIT,
            SoundEvents.PLAYER_ATTACK_KNOCKBACK,
            SoundEvents.PLAYER_ATTACK_NODAMAGE,
            SoundEvents.PLAYER_ATTACK_STRONG,
            SoundEvents.PLAYER_ATTACK_SWEEP,
            SoundEvents.PLAYER_ATTACK_WEAK
    );

    @Inject(
            method = "send(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketSendListener;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void impl$sendPacket(Packet<?> packet, PacketSendListener callbacks, CallbackInfo ci) {
        if (packet instanceof ClientboundSoundPacket) {
            if (BLOCKED_SOUND_EFFECTS.contains(((ClientboundSoundPacket) packet).getSound().value())) {
                ci.cancel();
            }
        }
        if (packet instanceof ClientboundLevelParticlesPacket) {
            if (((ClientboundLevelParticlesPacket) packet).getParticle().writeToString().toUpperCase().contains("SWEEP")) {
                ci.cancel();
            }
        }
        if (packet instanceof ClientboundCooldownPacket) {
            if (((ClientboundCooldownPacket) packet).getItem() == Items.ENDER_PEARL) {
                ci.cancel();
            }
        }
    }
}
