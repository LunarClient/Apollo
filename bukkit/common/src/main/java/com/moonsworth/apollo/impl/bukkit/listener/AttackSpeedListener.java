package com.moonsworth.apollo.impl.bukkit.listener;

import com.moonsworth.apollo.api.module.impl.LegacyCombatModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class AttackSpeedListener implements Listener {
    private final LegacyCombatModule legacyCombatModule;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!legacyCombatModule.getLegacyAttackSpeed().get()) {
            return;
        }
        Player player = event.getPlayer();
        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (attribute == null) {
            return;
        }

        double baseValue = attribute.getBaseValue();
        float baseAttackSpeed = legacyCombatModule.getAttackSpeed().get();

        if (baseValue != baseAttackSpeed) {

            attribute.setBaseValue(baseAttackSpeed);
            event.getPlayer().saveData();
        }
    }
}
