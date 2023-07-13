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
package com.lunarclient.apollo.module.waypoint;

import com.lunarclient.apollo.audience.Audience;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.option.config.Serializer;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.waypoint.v1.DisplayWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.RemoveWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.ResetWaypointsMessage;
import java.awt.Color;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

/**
 * Provides the waypoints module.
 *
 * @since 1.0.0
 */
public final class WaypointModuleImpl extends WaypointModule implements Serializer {

    /**
     * Creates a new instance of {@link WaypointModuleImpl}.
     *
     * @since 1.0.0
     */
    public WaypointModuleImpl() {
        super();
        this.serializer(Waypoint.class, new WaypointSerializer());
        this.handle(ApolloRegisterPlayerEvent.class, this::onPlayerRegister);
    }

    @Override
    public void displayWaypoint(@NonNull Audience audience, @NonNull Waypoint waypoint) {
        DisplayWaypointMessage message = this.toProtobuf(waypoint);
        audience.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removeWaypoint(@NonNull Audience audience, @NonNull String waypointName) {
        RemoveWaypointMessage message = RemoveWaypointMessage.newBuilder()
            .setName(waypointName)
            .build();

        audience.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removeWaypoint(@NonNull Audience audience, @NonNull Waypoint waypoint) {
        this.removeWaypoint(audience, waypoint.getName());
    }

    @Override
    public void resetWaypoints(@NonNull Audience audience) {
        ResetWaypointsMessage message = ResetWaypointsMessage.getDefaultInstance();
        audience.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    private void onPlayerRegister(ApolloRegisterPlayerEvent event) {
        ApolloPlayer player = event.getPlayer();
        List<Waypoint> waypoints = this.getOptions().get(player, WaypointModule.DEFAULT_WAYPOINTS);

        if (waypoints != null) {
            for (Waypoint waypoint : waypoints) {
                ((AbstractApolloPlayer) player).sendPacket(this.toProtobuf(waypoint));
            }
        }
    }

    private DisplayWaypointMessage toProtobuf(Waypoint waypoint) {
        return DisplayWaypointMessage.newBuilder()
            .setName(waypoint.getName())
            .setLocation(NetworkTypes.toProtobuf(waypoint.getLocation()))
            .setColor(NetworkTypes.toProtobuf(waypoint.getColor()))
            .setPreventRemoval(waypoint.isPreventRemoval())
            .setHidden(waypoint.isHidden())
            .build();
    }

    private static final class WaypointSerializer implements TypeSerializer<Waypoint> {
        @Override
        public Waypoint deserialize(Type type, ConfigurationNode node) throws SerializationException {
            return Waypoint.builder()
                .name(this.virtualNode(node, "name").getString())
                .location(ApolloBlockLocation.builder()
                    .world(this.virtualNode(node, "location", "world").getString())
                    .x(this.virtualNode(node, "location", "x").getInt())
                    .y(this.virtualNode(node, "location", "y").getInt())
                    .z(this.virtualNode(node, "location", "z").getInt())
                    .build()
                )
                .color(Color.decode(this.virtualNode(node, "color").getString("#FFFFFF")))
                .preventRemoval(this.virtualNode(node, "prevent-removal").getBoolean())
                .hidden(this.virtualNode(node, "hidden").getBoolean())
                .build();
        }

        @Override
        public void serialize(Type type, @Nullable Waypoint waypoint, ConfigurationNode node) throws SerializationException {
            if (waypoint == null) {
                node.raw(null);
                return;
            }

            node.node("name").set(waypoint.getName());
            node.node("location", "world").set(waypoint.getLocation().getWorld());
            node.node("location", "x").set(waypoint.getLocation().getX());
            node.node("location", "y").set(waypoint.getLocation().getY());
            node.node("location", "z").set(waypoint.getLocation().getZ());
            node.node("color").set(String.format("#%06X", (0xFFFFFF & waypoint.getColor().getRGB())));
            node.node("prevent-removal").set(waypoint.isPreventRemoval());
            node.node("hidden").set(waypoint.isHidden());
        }

        private ConfigurationNode virtualNode(ConfigurationNode source, Object... path) throws SerializationException {
            if (!source.hasChild(path)) {
                throw new SerializationException("Required field " + Arrays.toString(path) + " not found!");
            }

            return source.node(path);
        }

    }

}
