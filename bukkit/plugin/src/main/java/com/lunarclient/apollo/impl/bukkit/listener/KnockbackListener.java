package com.lunarclient.apollo.impl.bukkit.listener;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableMap;
import com.lunarclient.apollo.module.type.LegacyCombat;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

@RequiredArgsConstructor
public final class KnockbackListener implements Listener {

    private final LegacyCombat legacyCombat;

    public static double knockbackFriction = 2.0D;
    public static double knockbackHorizontal = 0.35D;
    public static double knockbackVertical = 0.35D;
    public static double knockbackVerticalLimit = 0.4D;
    public static double knockbackExtraHorizontal = 0.425D;
    public static double knockbackExtraVertical = 0.085D;
    public static double defaultKnockbackFriction = 2.0D;
    public static double defaultKnockbackHorizontal = 0.35D;
    public static double defaultKnockbackVertical = 0.35D;
    public static double defaultKnockbackVerticalLimit = 0.4D;
    public static double defaultKnockbackExtraHorizontal = 0.425D;
    public static double defaultKnockbackExtraVertical = 0.085D;

    Map<UUID, Vector> playerKnockbackHashMap = new WeakHashMap<>();

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerVelocityEvent(PlayerVelocityEvent event) {
        if (!playerKnockbackHashMap.containsKey(event.getPlayer().getUniqueId())) {
            return;
        }
        event.setVelocity(playerKnockbackHashMap.get(event.getPlayer().getUniqueId()));
        playerKnockbackHashMap.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
        // Check if sword PvP, not PvE or EvE
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player && !event.isCancelled() && event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            if (event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING) != 0) {
                return;
            }

            if (!(event.getEntity() instanceof Player)) return;
            Player victim = (Player) event.getEntity();

            // Disable netherite kb, the knockback resistance attribute makes the velocity event not be called
            // Also it makes players sometimes just not take any knockback, and reduces knockback
            // This affects both PvP and PvE, so put it above the PvP check
            // We technically don't have to check the version but bad server jars might break if we do
            for (AttributeModifier modifier : victim.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getModifiers())
                victim.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).removeModifier(modifier);

            if (!(event.getDamager() instanceof Player)) return;
            if (!event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) return;
            if (event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING) != 0) return;

            Player attacker = (Player) event.getDamager();

            // Figure out base knockback direction
            double d0 = attacker.getLocation().getX() - victim.getLocation().getX();
            double d1;

            for (d1 = attacker.getLocation().getZ() - victim.getLocation().getZ();
                 d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D)
                d0 = (Math.random() - Math.random()) * 0.01D;

            double magnitude = Math.sqrt(d0 * d0 + d1 * d1);

            // Get player knockback taken before any friction applied
            Vector playerVelocity = victim.getVelocity();

            // apply friction then add the base knockback
            playerVelocity.setX((playerVelocity.getX() / knockbackFriction) - (d0 / magnitude * knockbackHorizontal));
            playerVelocity.setY((playerVelocity.getY() / knockbackFriction) + knockbackVertical);
            playerVelocity.setZ((playerVelocity.getZ() / knockbackFriction) - (d1 / magnitude * knockbackHorizontal));

            // Calculate bonus knockback for sprinting or knockback enchantment levels
            int i = attacker.getItemInHand().getEnchantmentLevel(Enchantment.KNOCKBACK);
            if (attacker.isSprinting()) ++i;

            if (playerVelocity.getY() > knockbackVerticalLimit)
                playerVelocity.setY(knockbackVerticalLimit);

            // Apply bonus knockback
            if (i > 0) {
                playerVelocity.add(new Vector((-Math.sin(attacker.getLocation().getYaw() * 3.1415927F / 180.0F) *
                        (float) i * knockbackExtraHorizontal), knockbackExtraVertical,
                        Math.cos(attacker.getLocation().getYaw() * 3.1415927F / 180.0F) *
                                (float) i * knockbackExtraHorizontal));
            }

            // Allow netherite to affect the horizontal knockback
            double resistance = 1 - victim.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getValue();
            playerVelocity.multiply(new Vector(resistance, 1, resistance));


            // Knockback is sent immediately in 1.8+, there is no reason to send packets manually
            playerKnockbackHashMap.put(victim.getUniqueId(), playerVelocity);
        }
    }

    private static final boolean FISHING_KNOCKBACK_NON_PLAYER_ENTITIES = true;
    private static final double FISHING_DAMAGE = 0.2D;
    // Fishing Rod KB

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onProjectileHit(ProjectileHitEvent e) {
        Entity hookEntity = e.getEntity();

        if (e.getEntityType() != EntityType.FISHING_HOOK) {
            return;
        }

        Entity hitEntity = e.getHitEntity();

        if (hitEntity == null) {
            return;
        }

        if (!(hitEntity instanceof LivingEntity livingEntity)) {
            return;
        }

        if (!FISHING_KNOCKBACK_NON_PLAYER_ENTITIES && !(hitEntity instanceof Player)) {
            return;
        }

        FishHook hook = (FishHook) hookEntity;
        Player rodder = (Player) hook.getShooter();

        if (!FISHING_KNOCKBACK_NON_PLAYER_ENTITIES) {
            Player player = (Player) hitEntity;

            if (player.equals(rodder) || player.getGameMode() == GameMode.CREATIVE) {
                return;
            }
        }

        //Check if cooldown time has elapsed
        if (livingEntity.getNoDamageTicks() > livingEntity.getMaximumNoDamageTicks() / 2f) {
            return;
        }

        double damage = FISHING_DAMAGE;

        EntityDamageEvent event = new EntityDamageByEntityEvent(rodder, hitEntity,
                EntityDamageEvent.DamageCause.PROJECTILE,
                new EnumMap<>(ImmutableMap.of(EntityDamageEvent.DamageModifier.BASE, damage)),
                new EnumMap<>(ImmutableMap.of(EntityDamageEvent.DamageModifier.BASE, Functions.constant(damage))));
        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled()) {
            return;
        }

        boolean mainHand = true;
        ItemStack item = rodder.getInventory().getItemInMainHand();
        if (item.getType() != Material.FISHING_ROD) {
            mainHand = false;
            item = rodder.getInventory().getItemInOffHand();
        }

        short durability = (short) (item.getDurability() + 1);
        if (durability >= item.getType().getMaxDurability()) {
            if (mainHand) {
                rodder.getInventory().setItemInMainHand(null);
            } else {
                rodder.getInventory().setItemInOffHand(null);
            }
        } else {
            item.setDurability(durability);
        }

        livingEntity.damage(damage);
        livingEntity.setVelocity(calculateKnockbackVelocity(livingEntity.isOnGround(), hook.getLocation()));
    }

    private Vector calculateKnockbackVelocity(boolean playerIsOnGround, Location hook) {
        double kx = hook.getDirection().getX() / 2.5;
        double kz = hook.getDirection().getZ() / 2.5;
        kx = kx - kx * 2;

        double upVel = 0.372;
        if (!playerIsOnGround) {
            upVel = 0;
        }


        return new Vector(kx, upVel, kz);
    }

    /**
     * This is to cancel dragging the entity closer when you reel in
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    private void onPlayerFish(PlayerFishEvent e) {
        if (e.getState() != PlayerFishEvent.State.CAUGHT_ENTITY) {
            return;
        }

        if (!(e.getCaught() instanceof Mob)) {
            e.getHook().remove();
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityHit(EntityDamageByEntityEvent e) {
        if (!legacyCombat.getOptions().get(LegacyCombat.ENABLE_PROJECTILE_DAMAGE)) {
            return;
        }

        if (e.getDamage() != 0.0) {
            return;
        }

        float damage = 0.0001F;

        e.setDamage(damage);
        if (e.isApplicable(EntityDamageEvent.DamageModifier.ABSORPTION)) {
            e.setDamage(EntityDamageEvent.DamageModifier.ABSORPTION, 0);
        }
    }

}
