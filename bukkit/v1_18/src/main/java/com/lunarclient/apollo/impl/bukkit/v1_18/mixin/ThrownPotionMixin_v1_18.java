package com.lunarclient.apollo.impl.bukkit.v1_18.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PotionSplashEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownPotion.class)
public abstract class ThrownPotionMixin_v1_18 extends ThrowableItemProjectile {
    private static float potionHitboxIncrease = 0.25F;
    private static int potionShooterHitTicks = 5;
    private static boolean potionShooterIntensityFloor = true;

    protected ThrownPotionMixin_v1_18(EntityType<? extends ThrowableItemProjectile> entitytypes, Level world) {
        super(entitytypes, world);
    }


    @Inject(
            method = "<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)V",
            at = @At("TAIL")
    )
    public void impl$init(Level world, net.minecraft.world.entity.LivingEntity owner, CallbackInfo ci) {
        this.setDeltaMovement(new Vec3(0.25 + potionHitboxIncrease, 0.25 + potionHitboxIncrease, 0.0 + potionHitboxIncrease));
    }

    @Inject(
            method = "<init>(Lnet/minecraft/world/level/Level;DDD)V",
            at = @At("TAIL")
    )
    public void impl$init(Level world, double x, double y, double z, CallbackInfo ci) {
        this.setDeltaMovement(new Vec3(0.25 + potionHitboxIncrease, 0.25 + potionHitboxIncrease, 0.0 + potionHitboxIncrease));
    }

    @Inject(
            method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V",
            at = @At("TAIL")
    )
    public void impl$init(EntityType type, Level world, CallbackInfo ci) {
        this.setDeltaMovement(new Vec3(0.25 + potionHitboxIncrease, 0.25 + potionHitboxIncrease, 0.0 + potionHitboxIncrease));
    }

    @Redirect(
            method = "applySplash",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/bukkit/event/entity/PotionSplashEvent;getIntensity(Lorg/bukkit/entity/LivingEntity;)D"
            )
    )
    public double impl$vegaIntensity(PotionSplashEvent event, LivingEntity entity) {
        // Vega start
        if (potionShooterIntensityFloor && this.getEffectSource() instanceof Player shooter) {
            if (shooter.isSprinting() && shooter == entity && event.getIntensity(shooter) > 0.5D) {
                return 1.0D;
            }
        }
        return event.getIntensity(entity);
    }
}
