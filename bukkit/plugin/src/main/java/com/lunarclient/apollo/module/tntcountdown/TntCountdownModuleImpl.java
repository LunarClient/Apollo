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
package com.lunarclient.apollo.module.tntcountdown;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloBukkitPlatform;
import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.tntcountdown.v1.SetTntCountdownMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

/**
 * Provides the tnt countdown module.
 *
 * @since 1.0.0
 */
public final class TntCountdownModuleImpl extends TntCountdownModule implements Listener {

    @Override
    protected void onEnable() {
       Bukkit.getPluginManager().registerEvents(this, ApolloBukkitPlatform.getInstance());
    }

    @Override
    public void setTntCountdown(ApolloEntity entity, int ticks) {
        Entity target = Bukkit.getEntity(entity.getEntityUuid());
        if(!(target instanceof TNTPrimed)) return;

        ((TNTPrimed) target).setFuseTicks(ticks);

        SetTntCountdownMessage message = SetTntCountdownMessage.newBuilder()
            .setEntityId(NetworkTypes.toProtobuf(entity))
            .setDurationTicks(ticks)
            .build();

        for (ApolloPlayer viewer : Apollo.getPlayerManager().getPlayers()) {
            ((AbstractApolloPlayer) viewer).sendPacket(message);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onTntSpawn(EntitySpawnEvent event) {
        // We only care about TNT
        if(event.getEntityType() != EntityType.PRIMED_TNT) return;

        TNTPrimed primed = (TNTPrimed) event.getEntity();
        int customFuse = this.getOptions().get(TntCountdownModule.TNT_TICKS);

        // We only care about TNT with a non-standard fuse as well.
        if(primed.getFuseTicks() == customFuse) return;
        primed.setFuseTicks(customFuse);
    }

}
