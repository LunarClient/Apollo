package com.moonsworth.apollo.impl.bukkit.v1_18.mixin;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.module.impl.LegacyCombatModule;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutNamedSoundEffect;
import net.minecraft.network.protocol.game.PacketPlayOutSetCooldown;
import net.minecraft.network.protocol.game.PacketPlayOutWorldParticles;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.sounds.SoundEffect;
import net.minecraft.sounds.SoundEffects;
import net.minecraft.world.item.Items;
import org.bukkit.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(PlayerConnection.class)
public class PlayerConnectionMixin_v1_18 {

    private static final List<SoundEffect> BLOCKED_SOUND_EFFECTS = ImmutableList.of(
            SoundEffects.ow,
            SoundEffects.ox,
            SoundEffects.oy,
            SoundEffects.oz,
            SoundEffects.oA,
            SoundEffects.oB
    );

    @Inject(
            method = "a(Lnet/minecraft/network/protocol/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void impl$sendPacket(Packet<?> packet, @Nullable GenericFutureListener<? extends Future<? super Void>> genericfuturelistener, CallbackInfo ci) {
        boolean legacySounds = true;//Apollo.getApolloModuleManager().getModule(LegacyCombatModule.class).map(legacyCombatModule -> legacyCombatModule.getDisableSwingSounds().get()).orElse(false);
        boolean noSweep = true;//Apollo.getApolloModuleManager().getModule(LegacyCombatModule.class).map(legacyCombatModule -> legacyCombatModule.getDisableSweep().get()).orElse(false);
        boolean noEnderpearlCooldown =true;// Apollo.getApolloModuleManager().getModule(LegacyCombatModule.class).map(legacyCombatModule -> legacyCombatModule.getDisableEnderpearlCooldown().get()).orElse(false);

        if (packet instanceof PacketPlayOutNamedSoundEffect && legacySounds) {
            if (BLOCKED_SOUND_EFFECTS.contains(((PacketPlayOutNamedSoundEffect) packet).b())) {
                ci.cancel();
            }
        }
        if (packet instanceof PacketPlayOutWorldParticles && noSweep) {
            if (((PacketPlayOutWorldParticles) packet).k().a().toUpperCase().contains("SWEEP")) {
                ci.cancel();
            }
        }
        if (packet instanceof PacketPlayOutSetCooldown && noEnderpearlCooldown) {
            if (((PacketPlayOutSetCooldown) packet).b() == Items.pA) {
                ci.cancel();
            }
        }
    }
}
