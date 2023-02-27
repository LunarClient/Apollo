package com.moonsworth.apollo.impl.bukkit.v1_18.mixin;

import com.google.common.collect.ImmutableList;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
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

import javax.annotation.Nullable;
import java.util.List;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ServerGamePacketListenerImplMixin_v1_18 {

    private static final List<SoundEvent> BLOCKED_SOUND_EFFECTS = ImmutableList.of(
            SoundEvents.PLAYER_ATTACK_CRIT,
            SoundEvents.PLAYER_ATTACK_KNOCKBACK,
            SoundEvents.PLAYER_ATTACK_NODAMAGE,
            SoundEvents.PLAYER_ATTACK_STRONG,
            SoundEvents.PLAYER_ATTACK_SWEEP,
            SoundEvents.PLAYER_ATTACK_WEAK
    );

    @Inject(
            method = "send(Lnet/minecraft/network/protocol/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void impl$sendPacket(Packet<?> packet, @Nullable GenericFutureListener<? extends Future<? super Void>> genericfuturelistener, CallbackInfo ci) {
        boolean legacySounds = true;//Apollo.getApolloModuleManager().getModule(LegacyCombatModule.class).map(legacyCombatModule -> legacyCombatModule.getDisableSwingSounds().get()).orElse(false);
        boolean noSweep = true;//Apollo.getApolloModuleManager().getModule(LegacyCombatModule.class).map(legacyCombatModule -> legacyCombatModule.getDisableSweep().get()).orElse(false);
        boolean noEnderpearlCooldown =true;// Apollo.getApolloModuleManager().getModule(LegacyCombatModule.class).map(legacyCombatModule -> legacyCombatModule.getDisableEnderpearlCooldown().get()).orElse(false);

        if (packet instanceof ClientboundSoundPacket && legacySounds) {
            if (BLOCKED_SOUND_EFFECTS.contains(((ClientboundSoundPacket) packet).getSound())) {
                ci.cancel();
            }
        }

        if (packet instanceof ClientboundLevelParticlesPacket && noSweep) {
            if (((ClientboundLevelParticlesPacket) packet).getParticle().writeToString().toUpperCase().contains("SWEEP")) {
                ci.cancel();
            }
        }
        // ClientboundCooldownPacket
        if (packet instanceof ClientboundCooldownPacket && noEnderpearlCooldown) {
            // ItemEnderpearl
            if (((ClientboundCooldownPacket) packet).getItem() == Items.ENDER_PEARL) {
                ci.cancel();
            }
        }
    }
}
