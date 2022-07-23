package com.moonsworth.apollo.impl.bukkit.v1_18.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.player.EntityHuman;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityHuman.class)
public class EntityHumanMixin_v1_18 {

    @Redirect(
            method = "d(Lnet/minecraft/world/entity/Entity;)V",
            at = @At(
                    target = "Lnet/minecraft/world/entity/player/EntityHuman;bO()Z",
                    value = "INVOKE",
                    ordinal = 1
            )
    )
    public boolean impl$criticalSprint(EntityHuman instance) {
        // This removes the check, forcing crits to function like 1.8
        return false;
    }


}
