package com.moonsworth.apollo.impl.bukkit.listener;

import com.moonsworth.apollo.module.type.LegacyCombat;
import lombok.RequiredArgsConstructor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public final class AttackSpeedListener implements Listener {
    private final LegacyCombat legacyCombat;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!legacyCombat.getOptions().get(LegacyCombat.ENABLE_LEGACY_ATTACK_SPEED)) {
            return;
        }

        Player player = event.getPlayer();
        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (attribute == null) {
            return;
        }

        double baseValue = attribute.getBaseValue();
        float baseAttackSpeed = legacyCombat.getOptions().get(LegacyCombat.ATTACK_SPEED);

        if (baseValue != baseAttackSpeed) {
            attribute.setBaseValue(baseAttackSpeed);
            event.getPlayer().saveData();
        }
    }
}
