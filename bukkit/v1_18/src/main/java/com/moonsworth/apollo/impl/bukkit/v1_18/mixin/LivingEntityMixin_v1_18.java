package com.moonsworth.apollo.impl.bukkit.v1_18.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin_v1_18 {

    @Inject(
            method = "canCollideWithBukkit",
            at = @At("HEAD"),
            cancellable = true
    )
    public void impl$canCollide(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        boolean noCollisions = true;//Apollo.getApolloModuleManager().getModule(LegacyCombatModule.class).map(legacyCombatModule -> legacyCombatModule.getDisableEntityCramming().get()).orElse(false);
        if (noCollisions) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "hurt",
            at = @At("HEAD"),
            cancellable = true
    )
    public void impl$damage(DamageSource damagesource, float f, CallbackInfoReturnable<Boolean> cir) {
        boolean noSweep = true;// Apollo.getApolloModuleManager().getModule(LegacyCombatModule.class).map(legacyCombatModule -> legacyCombatModule.getDisableSweep().get()).orElse(false);
        if (damagesource.isSweep() && noSweep) {
            cir.setReturnValue(false);
        }
    }

}
