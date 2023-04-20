package com.lunarclient.apollo.impl.bukkit.listener;

import com.lunarclient.apollo.impl.bukkit.ApolloBukkitPlatform;
import com.lunarclient.apollo.module.type.LegacyCombat;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

@AllArgsConstructor
public final class RegenListener implements Listener {

    private final Map<UUID, Long> healTimes = new WeakHashMap<>();
    private final ApolloBukkitPlatform plugin;
    private final LegacyCombat legacyCombat;


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onRegen(EntityRegainHealthEvent e) {
        if (e.getEntityType() != EntityType.PLAYER || e.getRegainReason() != EntityRegainHealthEvent.RegainReason.SATIATED) {
            return;
        }

        final Player player = (Player) e.getEntity();

        e.setCancelled(true);

        // Get exhaustion & saturation values before healing modifies them
        final float previousExhaustion = player.getExhaustion();

        // Check that it has been at least x seconds since last heal
        final long currentTime = System.currentTimeMillis();
        final boolean hasLastHealTime = healTimes.containsKey(player.getUniqueId());
        final long lastHealTime = healTimes.computeIfAbsent(player.getUniqueId(), id -> currentTime);

        // If we're skipping this heal, we must fix the exhaustion in the following tick
        if (hasLastHealTime && currentTime - lastHealTime <= legacyCombat.getOptions().get(LegacyCombat.REGEN_INTERVAL)) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> player.setExhaustion(previousExhaustion), 1L);
            return;
        }

        final double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        final double playerHealth = player.getHealth();

        if (playerHealth < maxHealth) {
            player.setHealth(Math.min(Math.max(playerHealth + legacyCombat.getOptions().get(LegacyCombat.REGEN_AMOUNT), 0.0), maxHealth));
            healTimes.put(player.getUniqueId(), currentTime);
        }

        // Calculate new exhaustion value, must be between 0 and 4. If above, it will reduce the saturation in the following tick.

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            // We do this in the next tick because bukkit doesn't stop the exhaustion change when cancelling the event
            player.setExhaustion(previousExhaustion + legacyCombat.getOptions().get(LegacyCombat.REGEN_EXHAUSTION));
        }, 1L);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        healTimes.remove(e.getPlayer().getUniqueId());
    }

}
