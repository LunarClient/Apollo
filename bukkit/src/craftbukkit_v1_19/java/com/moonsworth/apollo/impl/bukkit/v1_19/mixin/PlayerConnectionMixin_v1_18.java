package com.moonsworth.apollo.impl.bukkit.v1_19.mixin;

import com.google.common.collect.ImmutableList;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutNamedSoundEffect;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.sounds.SoundEffect;
import net.minecraft.sounds.SoundEffects;
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
        if (packet instanceof PacketPlayOutNamedSoundEffect) {
            if (BLOCKED_SOUND_EFFECTS.contains(((PacketPlayOutNamedSoundEffect) packet).b())) {
                ci.cancel();
            }
        }
    }
}
