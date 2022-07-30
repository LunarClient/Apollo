package com.moonsworth.apollo.impl.bukkit.v1_18.mixin;

import com.moonsworth.apollo.api.Apollo;
import com.moonsworth.apollo.api.module.impl.LegacyCombatModule;
import net.minecraft.world.entity.player.EntityHuman;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Function;

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
        boolean legacyCrits = Apollo.getApolloModuleManager().getModule(LegacyCombatModule.class).map(legacyCombatModule -> legacyCombatModule.getLegacyCrits().get()).orElse(false);
        if (legacyCrits) {
            return false;
        }
        // This removes the check, forcing crits to function like 1.8
        return instance.bO();
    }

    // This is for attack speed removal. This is what the client thinks this value is as well.
    @Inject(
            method = "v",
            at = @At("HEAD"),
            cancellable = true
    )
    public void impl$getAttackSpeed(float f, CallbackInfoReturnable<Float> cir) {
        boolean legacyAttackSpeed = Apollo.getApolloModuleManager().getModule(LegacyCombatModule.class).map(legacyCombatModule -> legacyCombatModule.getLegacyAttackSpeed().get()).orElse(false);
        if (legacyAttackSpeed) {
            cir.setReturnValue(1.0f);
        }
    }

}
