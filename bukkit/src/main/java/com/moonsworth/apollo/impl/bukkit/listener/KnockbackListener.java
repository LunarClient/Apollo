package com.moonsworth.apollo.impl.bukkit.listener;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

public class KnockbackListener implements Listener {

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

}
