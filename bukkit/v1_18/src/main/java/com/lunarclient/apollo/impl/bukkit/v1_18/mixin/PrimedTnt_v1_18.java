package com.lunarclient.apollo.impl.bukkit.v1_18.mixin;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.type.TntCountdown;
import net.minecraft.world.entity.item.PrimedTnt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PrimedTnt.class)
public abstract class PrimedTnt_v1_18 {
    @ModifyConstant(method = "<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/entity/LivingEntity;)V", constant = @Constant(intValue = 80))
    public int fuseTicks(int constant) {
        return Apollo.getModuleManager()
                .getModule(TntCountdown.class)
                .flatMap(module -> module.getOptions().getDirect(TntCountdown.TNT_TICKS))
                .orElse(constant);
    }
}
