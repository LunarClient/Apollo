package com.moonsworth.apollo.impl.bukkit.v1_19.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin_v1_19 {

    /**
     * @author Tre
     * @reason Under all cases remove collisions
     */
    @Overwrite
    public boolean canCollideWithBukkit(Entity entity) {
        return false;
    }


    @Inject(
            method = "hurt",
            at = @At("HEAD"),
            cancellable = true
    )
    public void impl$damage(DamageSource damagesource, float f, CallbackInfoReturnable<Boolean> cir) {
        if (damagesource.isSweep()) {
            cir.setReturnValue(false);
        }
    }

}
