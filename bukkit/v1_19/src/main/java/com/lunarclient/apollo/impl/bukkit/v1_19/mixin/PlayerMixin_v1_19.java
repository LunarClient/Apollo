package com.lunarclient.apollo.impl.bukkit.v1_19.mixin;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Player.class)
public abstract class PlayerMixin_v1_19 {

    @Redirect(
            method = "attack",
            at = @At(
                    target = "Lnet/minecraft/world/entity/player/Player;isSprinting()Z",
                    value = "INVOKE",
                    ordinal = 1
            )
    )
    public boolean impl$criticalSprint(Player instance) {
        // This removes the check, forcing crits to function like 1.8
        return false;
    }

    // This is for attack speed removal. This is what the client thinks this value is as well.
    @Inject(
            method = "getAttackStrengthScale",
            at = @At("HEAD"),
            cancellable = true
    )
    public void impl$getAttackSpeed(float f, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(1.0f);
    }



}
