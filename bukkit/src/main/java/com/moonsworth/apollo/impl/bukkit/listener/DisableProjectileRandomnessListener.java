package com.moonsworth.apollo.impl.bukkit.listener;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class DisableProjectileRandomnessListener implements Listener {

    private static final double EPSILON = 0.1D;

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        Projectile projectile = e.getEntity();
        ProjectileSource shooter = projectile.getShooter();

        if (shooter instanceof Player player) {
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
        return Math.abs(a.getX() - b.getX()) < EPSILON && Math.abs(a.getZ() - b.getZ()) < EPSILON;
    }

    private Vector rotateAroundY(Vector vector, double angle) {
            double angleCos = Math.cos(angle);
            double angleSin = Math.sin(angle);

            double x = angleCos * vector.getX() + angleSin * vector.getZ();
            double z = -angleSin * vector.getX() + angleCos * vector.getZ();
            return vector.setX(x).setZ(z);
    }
}
