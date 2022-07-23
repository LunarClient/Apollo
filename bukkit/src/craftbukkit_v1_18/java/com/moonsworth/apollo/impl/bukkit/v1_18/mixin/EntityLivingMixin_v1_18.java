package com.moonsworth.apollo.impl.bukkit.v1_18.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.World;
import org.bukkit.Bukkit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLiving.class)
public class EntityLivingMixin_v1_18 {

    /**
     * @author Tre
     * @reason Under all cases remove collisions
     */
    @Overwrite
    public boolean canCollideWithBukkit(Entity entity) {
        return false;
    }

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
