package com.moonsworth.apollo.impl.bukkit.listener;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AttackSpeedListener implements Listener {
    private static final int BASE_PLAYER_ATTACKS_SPEED = 16;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (attribute == null) {
            return;
        }

        double baseValue = attribute.getBaseValue();

        if (baseValue != BASE_PLAYER_ATTACKS_SPEED) {

            attribute.setBaseValue(BASE_PLAYER_ATTACKS_SPEED);
            event.getPlayer().saveData();
        }
    }
}
