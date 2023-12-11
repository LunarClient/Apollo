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
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public final class EVNTHitListener implements Listener {

    private final EVNTModule module = Apollo.getModuleManager().getModule(EVNTModule.class);

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        this.setNoDamageTicks(player);
        this.setAttackSpeed(player);
    }

    @EventHandler
    private void onPlayerRespawn(PlayerRespawnEvent event) {
        this.setNoDamageTicks(event.getPlayer());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        event.getPlayer().setMaximumNoDamageTicks(20);
    }

    private void setNoDamageTicks(Player player) {
        if (!this.module.getOptions().get(EVNTModule.OVERRIDE_NO_DAMAGE_TICKS)) {
            return;
        }

        int noDamageTicks = this.module.getOptions().get(EVNTModule.NO_DAMAGE_TICKS);
        player.setMaximumNoDamageTicks(noDamageTicks);
    }

    private void setAttackSpeed(Player player) {
        if (!this.module.getOptions().get(EVNTModule.LEGACY_ATTACK_SPEED)) {
            return;
        }

        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (attribute == null) {
            return;
        }

        double baseValue = attribute.getBaseValue();
        float baseAttackSpeed = this.module.getOptions().get(EVNTModule.ATTACK_SPEED);

        if (baseValue != baseAttackSpeed) {
            attribute.setBaseValue(baseAttackSpeed);
            player.saveData();
        }
    }

}
