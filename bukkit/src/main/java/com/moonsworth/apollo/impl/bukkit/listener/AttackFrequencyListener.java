package com.moonsworth.apollo.impl.bukkit.listener;

import com.moonsworth.apollo.api.module.impl.LegacyCombatModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

@RequiredArgsConstructor
public class AttackFrequencyListener implements Listener {
    private final LegacyCombatModule legacyCombatModule;
    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        player.setMaximumNoDamageTicks(legacyCombatModule.getNoDamageTicks().get());
    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent e) {
        e.getPlayer().setMaximumNoDamageTicks(20);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        final Player player = e.getPlayer();
        player.setMaximumNoDamageTicks(legacyCombatModule.getNoDamageTicks().get());
    }

}
