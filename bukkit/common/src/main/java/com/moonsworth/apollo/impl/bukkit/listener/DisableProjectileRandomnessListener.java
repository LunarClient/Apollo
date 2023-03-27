package com.moonsworth.apollo.impl.bukkit.listener;

import com.moonsworth.apollo.impl.bukkit.ApolloBukkitPlatform;
import com.moonsworth.apollo.module.type.LegacyCombat;
import lombok.AllArgsConstructor;
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

@AllArgsConstructor
public final class DisableProjectileRandomnessListener implements Listener {

    private ApolloBukkitPlatform plugin;
    private LegacyCombat legacyCombat;

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        Projectile projectile = e.getEntity();
        ProjectileSource shooter = projectile.getShooter();

        if (shooter instanceof Player player) {
            if (projectile instanceof EnderPearl && legacyCombat.getOptions().get(LegacyCombat.DISABLE_ENDERPEARL_COOLDOWN)) {
                Bukkit.getScheduler().runTaskLater(plugin, () -> ((Player) shooter).setCooldown(Material.ENDER_PEARL, 0), 1);
            }
            Vector playerDirection = player.getLocation().getDirection().normalize();
            Vector projectileDirection = projectile.getVelocity();

            // Keep original speed
            double originalMagnitude = projectileDirection.length();
            projectileDirection.normalize();

            if (!fuzzyVectorEquals(projectileDirection, playerDirection)) { // If the projectile is not going straight
                if (!fuzzyVectorEquals(projectileDirection, rotateAroundY(playerDirection, 0.17))) {
                    fuzzyVectorEquals(projectileDirection, rotateAroundY(playerDirection, -0.35));
                }
            }

            playerDirection.multiply(originalMagnitude);
            projectile.setVelocity(playerDirection);
        }
    }

    private boolean fuzzyVectorEquals(Vector a, Vector b) {
        final double randomness = legacyCombat.getOptions().get(LegacyCombat.PROJECTILE_RANDOMNESS);
        return Math.abs(a.getX() - b.getX()) < randomness && Math.abs(a.getZ() - b.getZ()) < randomness;
    }

    private Vector rotateAroundY(Vector vector, double angle) {
            double angleCos = Math.cos(angle);
            double angleSin = Math.sin(angle);

            double x = angleCos * vector.getX() + angleSin * vector.getZ();
            double z = -angleSin * vector.getX() + angleCos * vector.getZ();
            return vector.setX(x).setZ(z);
    }
}
