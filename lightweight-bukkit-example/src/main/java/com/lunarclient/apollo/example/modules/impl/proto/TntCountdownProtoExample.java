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
package com.lunarclient.apollo.example.modules.impl.proto;

import com.google.protobuf.Value;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.impl.TntCountdownExample;
import com.lunarclient.apollo.example.utilities.ProtobufPacketUtil;
import com.lunarclient.apollo.example.utilities.ProtobufUtil;
import com.lunarclient.apollo.tntcountdown.v1.SetTntCountdownMessage;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class TntCountdownProtoExample extends TntCountdownExample implements Listener {

    private static Method entityGetter;

    static {
        try {
            TntCountdownProtoExample.entityGetter = Bukkit.class.getDeclaredMethod("getEntity", UUID.class);
        } catch (Throwable throwable) {
            // Ignore for legacy versions.
        }
    }

    public TntCountdownProtoExample() {
        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getPlugin());
    }

    @Override
    public void setTntCountdownExample() {
        Map<String, Value> properties = new HashMap<>();
        properties.put("tnt-ticks", Value.newBuilder().setNumberValue(160).build());

        ConfigurableSettings settings = ProtobufPacketUtil.createModuleMessage("tnt_countdown", properties);
        ProtobufPacketUtil.broadcastPacket(settings);
    }

    @Override
    public void overrideTntCountdownExample(Player viewer) {
        Location location = viewer.getLocation();
        TNTPrimed entity = viewer.getWorld().spawn(location, TNTPrimed.class);
        int customTicks = 200;

        TNTPrimed target = null;
        if (TntCountdownProtoExample.entityGetter != null) {
            try {
                target = (TNTPrimed) TntCountdownProtoExample.entityGetter.invoke(null, entity.getUniqueId());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } else {
            for (World world : Bukkit.getWorlds()) {
                for (TNTPrimed compare : world.getEntitiesByClass(TNTPrimed.class)) {
                    if (compare.getUniqueId().equals(entity.getUniqueId())) {
                        target = compare;
                        break;
                    }
                }
            }
        }

        if (target != null) {
            target.setFuseTicks(customTicks);
        }

        SetTntCountdownMessage message = SetTntCountdownMessage.newBuilder()
            .setEntityId(ProtobufUtil.createEntityIdProto(entity.getEntityId(), entity.getUniqueId()))
            .setDurationTicks(customTicks)
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void clearTntCountdownOptionExample() {
        Map<String, Value> properties = new HashMap<>();
        properties.put("tnt-ticks", Value.newBuilder().setNumberValue(80).build());

        ConfigurableSettings settings = ProtobufPacketUtil.createModuleMessage("tnt_countdown", properties);
        ProtobufPacketUtil.broadcastPacket(settings);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onTntSpawn(EntitySpawnEvent event) {
        String entityName = event.getEntityType().name();
        if (!entityName.equals("PRIMED_TNT") && !entityName.equals("TNT")) {
            return;
        }

        TNTPrimed primed = (TNTPrimed) event.getEntity();
        int customTicks = 200;
        int defaultTicks = 80;
        int currentTicks = primed.getFuseTicks();

        if (currentTicks != defaultTicks) {
            customTicks = currentTicks;

            SetTntCountdownMessage message = SetTntCountdownMessage.newBuilder()
                .setEntityId(ProtobufUtil.createEntityIdProto(primed.getEntityId(), primed.getUniqueId()))
                .setDurationTicks(customTicks)
                .build();

            ProtobufPacketUtil.broadcastPacket(message);
        }

        primed.setFuseTicks(customTicks);
    }

}
