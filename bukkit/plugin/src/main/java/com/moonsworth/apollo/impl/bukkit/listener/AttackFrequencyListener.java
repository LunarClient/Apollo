package com.moonsworth.apollo.impl.bukkit.listener;

import com.moonsworth.apollo.module.type.LegacyCombat;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

@RequiredArgsConstructor
public final class AttackFrequencyListener implements Listener {
    private final LegacyCombat legacyCombat;
    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        player.setMaximumNoDamageTicks(legacyCombat.getOptions().get(LegacyCombat.NO_DAMAGE_TICKS));
    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent e) {
        e.getPlayer().setMaximumNoDamageTicks(20);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        final Player player = e.getPlayer();
        player.setMaximumNoDamageTicks(legacyCombat.getOptions().get(LegacyCombat.NO_DAMAGE_TICKS));
    }

}
