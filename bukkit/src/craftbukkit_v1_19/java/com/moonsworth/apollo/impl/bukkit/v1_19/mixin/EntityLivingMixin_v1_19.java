package com.moonsworth.apollo.impl.bukkit.v1_19.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityLiving;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Example Mixin into EntityLiving for 1.19 CraftBukkit
 */
@Mixin(EntityLiving.class)
public class EntityLivingMixin_v1_19 {

    @Shadow private float ci;

    @Inject(
            method = "a(Lnet/minecraft/world/damagesource/DamageSource;F)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    public void impl$damage(DamageSource damagesource, float f, CallbackInfoReturnable<Boolean> cir) {
        if (damagesource.isSweep()) {
            cir.setReturnValue(false);
        }
    }

}
