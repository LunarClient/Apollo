package com.moonsworth.apollo.impl.bukkit.listener;

import com.moonsworth.apollo.impl.bukkit.ApolloBukkitPlatform;
import com.moonsworth.apollo.module.type.LegacyCombat;
import lombok.RequiredArgsConstructor;
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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public final class ArmorDurabilityListener implements Listener {

    private final Map<UUID, List<ItemStack>> explosionDamaged = new WeakHashMap<>();
    private final Random random = new Random();

    private final ApolloBukkitPlatform plugin;
    private final LegacyCombat legacyCombat;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onItemDamage(PlayerItemDamageEvent event) {
        if(!legacyCombat.getOptions().get(LegacyCombat.ENABLE_ARMOR_DURABILITY)) {
            return;
        }


        Player player = event.getPlayer();

        final ItemStack item = event.getItem();
        final Material itemType = item.getType();

        // Check if it's a piece of armour they're currently wearing
        if (Arrays.stream(player.getInventory().getArmorContents())
                .noneMatch(armourPiece -> armourPiece != null &&
                        armourPiece.getType() == itemType &&
                        armourPiece.getType() != Material.ELYTRA // ignore elytra as it doesn't provide any protection anyway
                )) {
            return;
        }

        final UUID uuid = player.getUniqueId();
        if (explosionDamaged.containsKey(uuid)) {
            final List<ItemStack> armour = explosionDamaged.get(uuid);
            // ItemStack.equals() checks material, durability and quantity to make sure nothing changed in the meantime
            // We're checking all the pieces this way just in case they're wearing two helmets or something strange
            final List<ItemStack> matchedPieces = armour.stream().filter(piece -> piece.equals(item))
                .collect(Collectors.toList());
            armour.removeAll(matchedPieces);
            if (!matchedPieces.isEmpty()) return;
        }

        int reduction = legacyCombat.getOptions().get(LegacyCombat.ARMOR_DURABILITY_REDUCTION);

        // 60 + (40 / (level + 1) ) % chance that durability is reduced (for each point of durability)
        final int damageChance = 60 + (40 / (item.getEnchantmentLevel(Enchantment.DURABILITY) + 1));

        final int randomInt = random.nextInt(100); // between 0 (inclusive) and 100 (exclusive)
        if (randomInt >= damageChance) {
            reduction = 0;
        }

        event.setDamage(reduction);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerExplosionDamage(EntityDamageEvent event) {
        if (!legacyCombat.getOptions().get(LegacyCombat.ENABLE_ARMOR_DURABILITY)) {
            return;
        }

        if (event.getEntityType() != EntityType.PLAYER) return;

        final EntityDamageEvent.DamageCause cause = event.getCause();
        if (cause != EntityDamageEvent.DamageCause.BLOCK_EXPLOSION &&
                cause != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            return;
        }

        final Player player = (Player) event.getEntity();
        final UUID uuid = player.getUniqueId();
        final List<ItemStack> armour = Arrays.stream(player.getInventory().getArmorContents()).filter(Objects::nonNull).collect(Collectors.toList());
        explosionDamaged.put(uuid, armour);

        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                explosionDamaged.remove(uuid);
            }
        };

        // This delay seems enough for the durability events to fire
        runnable.runTaskLater(plugin, 1);
    }
}
