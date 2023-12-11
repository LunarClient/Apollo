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

public final class EVNTRegenListener implements Listener {

    private final Map<UUID, Long> healTimes = new WeakHashMap<>();
    private final EVNTModule module = Apollo.getModuleManager().getModule(EVNTModule.class);

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void onRegen(EntityRegainHealthEvent event) {
        if (!this.module.getOptions().get(EVNTModule.OVERRIDE_REGEN)) {
            return;
        }

        if (event.getEntityType() != EntityType.PLAYER || event.getRegainReason() != EntityRegainHealthEvent.RegainReason.SATIATED) {
            return;
        }

        Player player = (Player) event.getEntity();

        event.setCancelled(true);

        // Get exhaustion & saturation values before healing modifies them
        float previousExhaustion = player.getExhaustion();

        // Check that it has been at least x seconds since last heal
        long currentTime = System.currentTimeMillis();
        boolean hasLastHealTime = this.healTimes.containsKey(player.getUniqueId());
        long lastHealTime = this.healTimes.computeIfAbsent(player.getUniqueId(), id -> currentTime);

        int regenInterval = this.module.getOptions().get(EVNTModule.REGEN_INTERVAL);
        int regenHealAmount = this.module.getOptions().get(EVNTModule.REGEN_HEAL_AMOUNT);
        int regenExhaustionHealAmount = this.module.getOptions().get(EVNTModule.REGEN_EXHAUSTION_HEAL_AMOUNT);

        // If we're skipping this heal, we must fix the exhaustion in the following tick
        if (hasLastHealTime && currentTime - lastHealTime <= regenInterval) {
            Bukkit.getScheduler().runTask(
                ApolloBukkitPlatform.getInstance().getPlugin(),
                () -> player.setExhaustion(previousExhaustion)
            );
            return;
        }

        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        double playerHealth = player.getHealth();

        if (playerHealth < maxHealth) {
            player.setHealth(Math.min(Math.max(playerHealth + regenHealAmount, 0.0), maxHealth));
            this.healTimes.put(player.getUniqueId(), currentTime);
        }

        // Calculate new exhaustion value, must be between 0 and 4. If above, it will reduce the saturation in the following tick.
        Bukkit.getScheduler().runTask(
            ApolloBukkitPlatform.getInstance().getPlugin(),
            // We do this in the next tick because bukkit doesn't stop the exhaustion change when cancelling the event
            () -> player.setExhaustion(previousExhaustion + regenExhaustionHealAmount)
        );
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        this.healTimes.remove(event.getPlayer().getUniqueId());
    }

}
