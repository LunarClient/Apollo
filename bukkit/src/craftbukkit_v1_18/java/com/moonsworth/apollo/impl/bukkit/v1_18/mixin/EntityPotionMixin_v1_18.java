package com.moonsworth.apollo.impl.bukkit.v1_18.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.projectile.EntityPotion;
import net.minecraft.world.entity.projectile.EntityProjectile;
import net.minecraft.world.entity.projectile.IProjectile;
import net.minecraft.world.level.World;
import net.minecraft.world.phys.Vec3D;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PotionSplashEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPotion.class)
public abstract class EntityPotionMixin_v1_18 extends EntityProjectile {
    private static float potionHitboxIncrease = 0.25F;
    private static int potionShooterHitTicks = 5;
    private static boolean potionShooterIntensityFloor = true;

    protected EntityPotionMixin_v1_18(EntityTypes<? extends EntityProjectile> entitytypes, World world) {
        super(entitytypes, world);
    }


    @Inject(
            method = "<init>(Lnet/minecraft/world/level/World;Lnet/minecraft/world/entity/EntityLiving;)V",
            at = @At("TAIL")
    )
    public void impl$init(World world, EntityLiving entityliving, CallbackInfo ci) {
        this.g(new Vec3D(0.25 + potionHitboxIncrease, 0.25 + potionHitboxIncrease, 0.0 + potionHitboxIncrease));
    }

    @Inject(
            method = "<init>(Lnet/minecraft/world/level/World;DDD)V",
            at = @At("TAIL")
    )
    public void impl$init(World world, double d0, double d1, double d2, CallbackInfo ci) {
        this.g(new Vec3D(0.25 + potionHitboxIncrease, 0.25 + potionHitboxIncrease, 0.0 + potionHitboxIncrease));

    }

    @Inject(
            method = "<init>(Lnet/minecraft/world/entity/EntityTypes;Lnet/minecraft/world/level/World;)V",
            at = @At("TAIL")
    )
    public void impl$init(EntityTypes entitytypes, World world, CallbackInfo ci) {
        this.g(new Vec3D(0.25 + potionHitboxIncrease, 0.25 + potionHitboxIncrease, 0.0 + potionHitboxIncrease));
    }

    @Redirect(
            method = "a(Ljava/util/List;Lnet/minecraft/world/entity/Entity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/bukkit/event/entity/PotionSplashEvent;getIntensity(Lorg/bukkit/entity/LivingEntity;)D"
            )
    )
    public double impl$vegaIntensity(PotionSplashEvent event, LivingEntity entity) {
        // Vega start
        if (potionShooterIntensityFloor && this.y() instanceof Player shooter) {
            if (shooter.isSprinting() && shooter == entity && event.getIntensity(shooter) > 0.5D) {
                return 1.0D;
            }
        }
        return event.getIntensity(entity);
    }
}
