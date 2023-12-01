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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public final class EVNTDurabilityListener implements Listener {

    // TODO: actually init the listeners

    private final Map<UUID, List<ItemStack>> explosionDamaged = new WeakHashMap<>();
    private final EVNTModule module = Apollo.getModuleManager().getModule(EVNTModule.class);

    public EVNTDurabilityListener() {
        Bukkit.getPluginManager().registerEvents(this, ApolloBukkitPlatform.getInstance().getPlugin());
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private void onPlayerItemDamage(PlayerItemDamageEvent event) {
        if (!this.module.getOptions().get(EVNTModule.OVERRIDE_ARMOR_DURABILITY_REDUCTION)) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Material itemType = item.getType();

        if (Arrays.stream(player.getInventory().getArmorContents())
                .noneMatch(armourPiece -> armourPiece != null && armourPiece.getType() == itemType)) {
            return;
        }

        UUID uuid = player.getUniqueId();
        List<ItemStack> armour = this.explosionDamaged.get(uuid);

        if (armour != null) {
            // ItemStack.equals() checks material, durability and quantity to make sure nothing changed in the meantime
            // We're checking all the pieces this way just in case they're wearing two helmets or something strange
            List<ItemStack> matchedPieces = armour.stream()
                .filter(piece -> piece.equals(item))
                .collect(Collectors.toList());

            armour.removeAll(matchedPieces);

            if (!matchedPieces.isEmpty()) {
                return;
            }
        }

        int reduction = this.module.getOptions().get(EVNTModule.ARMOR_DURABILITY_REDUCTION);

        // 60 + (40 / (level + 1) ) % chance that durability is reduced (for each point of durability)
        int damageChance = 60 + (40 / (item.getEnchantmentLevel(Enchantment.DURABILITY) + 1));

        if (ThreadLocalRandom.current().nextInt(100) >= damageChance) {
            reduction = 0;
        }

        event.setDamage(reduction);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onEntityDamage(EntityDamageEvent event) {
        if (!this.module.getOptions().get(EVNTModule.OVERRIDE_ARMOR_DURABILITY_REDUCTION)) {
            return;
        }

        if (event.getEntityType() != EntityType.PLAYER) {
            return;
        }

        EntityDamageEvent.DamageCause cause = event.getCause();

        if (cause != EntityDamageEvent.DamageCause.BLOCK_EXPLOSION &&
                cause != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            return;
        }

        Player player = (Player) event.getEntity();
        UUID uuid = player.getUniqueId();
        List<ItemStack> armour = Arrays.stream(player.getInventory().getArmorContents())
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        this.explosionDamaged.put(uuid, armour);

        Bukkit.getScheduler().runTask(
            ApolloBukkitPlatform.getInstance().getPlugin(),
            () -> this.explosionDamaged.remove(uuid)
        );
    }

}
