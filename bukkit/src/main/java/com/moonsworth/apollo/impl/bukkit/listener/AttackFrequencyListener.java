package com.moonsworth.apollo.impl.bukkit.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class AttackFrequencyListener implements Listener {
    private static final int NO_DAMAGE_TICKS = 19;

    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        player.setMaximumNoDamageTicks(NO_DAMAGE_TICKS);
    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent e) {
        e.getPlayer().setMaximumNoDamageTicks(20);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        final Player player = e.getPlayer();
        player.setMaximumNoDamageTicks(NO_DAMAGE_TICKS);
    }

}
