/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.module.evnt;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloBukkitPlatform;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

public final class EVNTKnockbackListener implements Listener {

    // TODO: use Option API
    public static double KNOCKBACK_FRICTION = 2.0D;
    public static double KNOCKBACK_HORIZONTAL = 0.35D;
    public static double KNOCKBACK_VERTICAL = 0.35D;
    public static double KNOCKBACK_VERTICAL_LIMIT = 0.4D;
    public static double KNOCKBACK_EXTRA_HORIZONTAL = 0.425D;
    public static double KNOCKBACK_EXTRA_VERTICAL = 0.085D;

    private static final boolean FISHING_KNOCKBACK_NON_PLAYER_ENTITIES = true;
    private static final double FISHING_DAMAGE = 0.2D;

    private final Map<UUID, Vector> playerKnockbackHashMap = new WeakHashMap<>();
    private final EVNTModule module = Apollo.getModuleManager().getModule(EVNTModule.class);

    public EVNTKnockbackListener() {
        Bukkit.getPluginManager().registerEvents(this, ApolloBukkitPlatform.getInstance().getPlugin());
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private void onPlayerVelocity(PlayerVelocityEvent event) {
        Player player = event.getPlayer();
        Vector velocity = this.playerKnockbackHashMap.remove(player.getUniqueId());

        if (velocity != null) {
            event.setVelocity(velocity);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onEntityDamageEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        if (event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            return;
        }

        if (event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING) != 0) {
            return;
        }

        Player victim = (Player) event.getEntity();
        Player attacker = (Player) event.getDamager();

        // Disable netherite kb, the knockback resistance attribute makes the velocity event not be called
        // Also it makes players sometimes just not take any knockback, and reduces knockback
        // This affects both PvP and PvE, so put it above the PvP check
        // We technically don't have to check the version but bad server jars might break if we do
        // TODO: NMS
//        for (AttributeModifier modifier : victim.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getModifiers()) {
//            victim.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).removeModifier(modifier);
//        }

        Location attackerLocation = attacker.getLocation();
        Location victimLocation = victim.getLocation();

        // Figure out base knockback direction
        double offsetX = attackerLocation.getX() - victimLocation.getX();
        double offsetZ;

        for (offsetZ = attackerLocation.getZ() - victimLocation.getZ();
                offsetX * offsetX + offsetZ * offsetZ < 1.0E-4D;
                offsetZ = (Math.random() - Math.random()) * 0.01D) {
            offsetX = (Math.random() - Math.random()) * 0.01D;
        }

        double magnitude = Math.sqrt(offsetX * offsetX + offsetZ * offsetZ);

        // Get player knockback taken before any friction applied
        Vector playerVelocity = victim.getVelocity();

        // apply friction then add the base knockback
        playerVelocity.setX((playerVelocity.getX() / KNOCKBACK_FRICTION) - (offsetX / magnitude * KNOCKBACK_HORIZONTAL));
        playerVelocity.setY((playerVelocity.getY() / KNOCKBACK_FRICTION) + KNOCKBACK_VERTICAL);
        playerVelocity.setZ((playerVelocity.getZ() / KNOCKBACK_FRICTION) - (offsetZ / magnitude * KNOCKBACK_HORIZONTAL));

        // Calculate bonus knockback for sprinting or knockback enchantment levels
        int i = attacker.getItemInHand().getEnchantmentLevel(Enchantment.KNOCKBACK);
        if (attacker.isSprinting()) ++i;

        if (playerVelocity.getY() > KNOCKBACK_VERTICAL_LIMIT)
            playerVelocity.setY(KNOCKBACK_VERTICAL_LIMIT);

        // Apply bonus knockback
        if (i > 0) {
            Vector bonusVelocity = new Vector(
                (-Math.sin(attackerLocation.getYaw() * 3.1415927F / 180.0F) * (float) i * KNOCKBACK_EXTRA_HORIZONTAL),
                KNOCKBACK_EXTRA_VERTICAL,
                Math.cos(attackerLocation.getYaw() * 3.1415927F / 180.0F) * (float) i * KNOCKBACK_EXTRA_HORIZONTAL);

            playerVelocity.add(bonusVelocity);
        }

        // Allow netherite to affect the horizontal knockback
        double resistance = 1; /*TODO - victim.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getValue();*/
        playerVelocity.multiply(new Vector(resistance, 1, resistance));

        // Knockback is sent immediately in 1.8+, there is no reason to send packets manually
        this.playerKnockbackHashMap.put(victim.getUniqueId(), playerVelocity);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void onProjectileHit(ProjectileHitEvent event) {
        // TODO
//        Entity hookEntity = event.getEntity();
//
//        if (event.getEntityType() != EntityType.FISHING_HOOK) {
//            return;
//        }
//
//        Entity hitEntity = null; /* TODO event.getHitEntity()*/;
//
//        if (hitEntity == null) {
//            return;
//        }
//
//        if (!(hitEntity instanceof LivingEntity)) {
//            return;
//        }
//
//        Entity livingEntity = hitEntity;
//
//        if (!FISHING_KNOCKBACK_NON_PLAYER_ENTITIES && !(hitEntity instanceof Player)) {
//            return;
//        }
//
//        FishHook hook = (FishHook) hookEntity;
//        Player rodder = (Player) hook.getShooter();
//
//        if (!FISHING_KNOCKBACK_NON_PLAYER_ENTITIES) {
//            Player player = (Player) hitEntity;
//
//            if (player.equals(rodder) || player.getGameMode() == GameMode.CREATIVE) {
//                return;
//            }
//        }
//
//        //Check if cooldown time has elapsed
//        if (livingEntity.getNoDamageTicks() > livingEntity.getMaximumNoDamageTicks() / 2f) {
//            return;
//        }
//
//        double damage = FISHING_DAMAGE;
//
//        EntityDamageEvent entityDamage = new EntityDamageByEntityEvent(rodder, hitEntity,
//            EntityDamageEvent.DamageCause.PROJECTILE,
//            new EnumMap<>(ImmutableMap.of(EntityDamageEvent.DamageModifier.BASE, damage)),
//            new EnumMap<>(ImmutableMap.of(EntityDamageEvent.DamageModifier.BASE, Functions.constant(damage))));
//
//        Bukkit.getPluginManager().callEvent(entityDamage);
//
//        if (entityDamage.isCancelled()) {
//            return;
//        }
//
//        boolean mainHand = true;
//        PlayerInventory inventory = rodder.getInventory();
//
//        ItemStack item = inventory.getItemInMainHand();
//        if (item.getType() != Material.FISHING_ROD) {
//            mainHand = false;
//            item = inventory.getItemInOffHand();
//        }
//
//        short durability = (short) (item.getDurability() + 1);
//        if (durability >= item.getType().getMaxDurability()) {
//            if (mainHand) {
//                inventory.setItemInMainHand(null);
//            } else {
//                inventory.setItemInOffHand(null);
//            }
//        } else {
//            item.setDurability(durability);
//        }
//
//        livingEntity.damage(damage);
//        livingEntity.setVelocity(this.calculateKnockbackVelocity(livingEntity.isOnGround(), hook.getLocation()));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void onPlayerFish(PlayerFishEvent event) {
        if (event.getState() != PlayerFishEvent.State.CAUGHT_ENTITY) {
            return;
        }

        if (!(event.getCaught() instanceof Monster)) {
            event.getHook().remove();
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!this.module.getOptions().get(EVNTModule.DISABLE_PROJECTILE_DAMAGE)) {
            return;
        }

        if (event.getDamage() != 0.0) {
            return;
        }

        float damage = 0.0001F;
        event.setDamage(damage);

        if (event.isApplicable(EntityDamageEvent.DamageModifier.ABSORPTION)) {
            event.setDamage(EntityDamageEvent.DamageModifier.ABSORPTION, 0);
        }
    }

    private Vector calculateKnockbackVelocity(boolean playerIsOnGround, Location hook) {
        Vector direction = hook.getDirection();

        double kx = direction.getX() / 2.5;
        double kz = direction.getZ() / 2.5;

        kx = kx - kx * 2;
        double upVel = 0.372;

        if (!playerIsOnGround) {
            upVel = 0;
        }

        return new Vector(kx, upVel, kz);
    }

}
