package com.moonsworth.apollo.impl.bukkit.v1_18.mixin;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.module.impl.LegacyCombatModule;
import net.minecraft.core.BlockPosition;
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

import java.util.Optional;

@Mixin(EntityLiving.class)
public abstract class EntityLivingMixin_v1_18 {

    @Shadow public abstract Optional<BlockPosition> fa();

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
            method = "a(Lnet/minecraft/world/damagesource/DamageSource;F)Z",
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
