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
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public final class EVNTProjectileListener implements Listener {

    private final EVNTModule module = Apollo.getModuleManager().getModule(EVNTModule.class);

    @EventHandler(ignoreCancelled = true)
    private void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();

        if (!(shooter instanceof Player)) {
            return;
        }

        Player player = (Player) shooter;

        if (projectile instanceof EnderPearl && this.module.getOptions().get(EVNTModule.DISABLE_ENDERPEARL_COOLDOWN)) {
            Bukkit.getScheduler().runTask(
                ApolloBukkitPlatform.getInstance().getPlugin(),
                () -> player.setCooldown(Material.ENDER_PEARL, 0)
            );
        }

        if (!this.module.getOptions().get(EVNTModule.OVERRIDE_PROJECTILE_RANDOMNESS)) {
            return;
        }

        double randomness = this.module.getOptions().get(EVNTModule.PROJECTILE_RANDOMNESS);

        Vector playerDirection = player.getLocation().getDirection().normalize();
        Vector projectileDirection = projectile.getVelocity();

        // Keep original speed
        double originalMagnitude = projectileDirection.length();
        projectileDirection.normalize();

        if (!this.fuzzyVectorEquals(randomness, projectileDirection, playerDirection)
                && !this.fuzzyVectorEquals(randomness, projectileDirection, this.rotateAroundY(playerDirection, 0.17))) {
            this.fuzzyVectorEquals(randomness, projectileDirection, this.rotateAroundY(playerDirection, -0.35));
        }

        playerDirection.multiply(originalMagnitude);
        projectile.setVelocity(playerDirection);
    }

    private boolean fuzzyVectorEquals(double randomness, Vector a, Vector b) {
        return Math.abs(a.getX() - b.getX()) < randomness
            && Math.abs(a.getZ() - b.getZ()) < randomness;
    }

    private Vector rotateAroundY(Vector vector, double angle) {
        double angleCos = Math.cos(angle);
        double angleSin = Math.sin(angle);

        double x = angleCos * vector.getX() + angleSin * vector.getZ();
        double z = -angleSin * vector.getX() + angleCos * vector.getZ();

        return vector.setX(x).setZ(z);
    }

}
