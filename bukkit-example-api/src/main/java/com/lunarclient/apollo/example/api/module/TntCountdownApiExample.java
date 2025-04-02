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
package com.lunarclient.apollo.example.api.module;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.module.impl.TntCountdownExample;
import com.lunarclient.apollo.module.tntcountdown.TntCountdownModule;
import com.lunarclient.apollo.recipients.Recipients;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class TntCountdownApiExample extends TntCountdownExample implements Listener {

    private final TntCountdownModule tntCountdownModule = Apollo.getModuleManager().getModule(TntCountdownModule.class);

    public TntCountdownApiExample() {
        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getInstance());
    }

    @Override
    public void setTntCountdownExample() {
        this.tntCountdownModule.getOptions().set(TntCountdownModule.TNT_TICKS, 160);
    }

    @Override
    public void overrideTntCountdownExample(Player viewer) {
        int customTicks = 200;

        TNTPrimed entity = viewer.getWorld().spawn(viewer.getLocation(), TNTPrimed.class);
        entity.setFuseTicks(customTicks);

        ApolloEntity apolloEntity = new ApolloEntity(entity.getEntityId(), entity.getUniqueId());
        this.tntCountdownModule.setTntCountdown(Recipients.ofEveryone(), apolloEntity, customTicks);
    }

    @Override
    public void clearTntCountdownOptionExample() {
        this.tntCountdownModule.getOptions().remove(TntCountdownModule.TNT_TICKS, 160);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onTntSpawn(EntitySpawnEvent event) {
        String entityName = event.getEntityType().name();
        if (!entityName.equals("PRIMED_TNT") && !entityName.equals("TNT")) {
            return;
        }

        TNTPrimed primed = (TNTPrimed) event.getEntity();
        int customTicks = this.tntCountdownModule.getOptions().get(TntCountdownModule.TNT_TICKS);
        int defaultTicks = TntCountdownModule.TNT_TICKS.getDefaultValue();
        int currentTicks = primed.getFuseTicks();

        if (currentTicks != defaultTicks && !this.tntCountdownModule.getOptions().get(TntCountdownModule.OVERRIDE_CUSTOM_TICKS)) {
            customTicks = currentTicks;

            this.tntCountdownModule.setTntCountdown(Recipients.ofEveryone(),
                new ApolloEntity(primed.getEntityId(), primed.getUniqueId()),
                customTicks
            );
        }

        primed.setFuseTicks(customTicks);
    }

}
