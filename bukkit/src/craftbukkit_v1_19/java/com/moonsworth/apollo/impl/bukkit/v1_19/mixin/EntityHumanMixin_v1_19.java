package com.moonsworth.apollo.impl.bukkit.v1_19.mixin;

import net.minecraft.world.entity.player.EntityHuman;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(EntityHuman.class)
public class EntityHumanMixin_v1_19 {

    @Redirect(
            method = "d(Lnet/minecraft/world/entity/Entity;)V",
            at = @At(
                    target = "Lnet/minecraft/world/entity/player/EntityHuman;bS()Z",
                    value = "INVOKE",
                    ordinal = 1
            )
    )
    public boolean impl$criticalSprint(EntityHuman instance) {
        // This removes the check, forcing crits to function like 1.8
        return false;
    }


}
