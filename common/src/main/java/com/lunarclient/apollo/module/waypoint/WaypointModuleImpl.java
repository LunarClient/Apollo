/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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

import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.option.config.Serializer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import com.lunarclient.apollo.waypoint.v1.DisplayWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.HideWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.RemoveWaypointMessage;
import com.lunarclient.apollo.waypoint.v1.ResetWaypointsMessage;
import com.lunarclient.apollo.waypoint.v1.ShowWaypointMessage;
import java.awt.Color;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import static com.lunarclient.apollo.util.Ranges.checkRange;

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
    public void displayWaypoint(@NonNull Recipients recipients, @NonNull Waypoint waypoint) {
        DisplayWaypointMessage message = this.toProtobuf(waypoint);
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void removeWaypoint(@NonNull Recipients recipients, @NonNull String waypointName) {
        RemoveWaypointMessage message = RemoveWaypointMessage.newBuilder()
            .setName(waypointName)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void removeWaypoint(@NonNull Recipients recipients, @NonNull Waypoint waypoint) {
        this.removeWaypoint(recipients, waypoint.getName());
    }

    @Override
    public void resetWaypoints(@NonNull Recipients recipients) {
        ResetWaypointsMessage message = ResetWaypointsMessage.getDefaultInstance();
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void showWaypoint(@NonNull Recipients recipients, @NonNull String waypointName) {
        ShowWaypointMessage message = ShowWaypointMessage.newBuilder()
            .setName(waypointName)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void hideWaypoint(@NonNull Recipients recipients, @NonNull String waypointName) {
        HideWaypointMessage message = HideWaypointMessage.newBuilder()
            .setName(waypointName)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    private void onPlayerRegister(ApolloRegisterPlayerEvent event) {
        ApolloPlayer player = event.getPlayer();
        List<Waypoint> waypoints = this.getOptions().get(player, WaypointModule.DEFAULT_WAYPOINTS);

        if (waypoints != null) {
            for (Waypoint waypoint : waypoints) {
                ApolloManager.getNetworkManager().sendPacket(player, this.toProtobuf(waypoint));
            }
        }
    }

    private DisplayWaypointMessage toProtobuf(Waypoint waypoint) {
        DisplayWaypointMessage.Builder builder = DisplayWaypointMessage.newBuilder()
            .setName(waypoint.getName())
            .setLocation(NetworkTypes.toProtobuf(waypoint.getLocation()))
            .setColor(NetworkTypes.toProtobuf(waypoint.getColor()))
            .setPreventRemoval(waypoint.isPreventRemoval())
            .setHidden(waypoint.isHidden())
            .setShowBeam(waypoint.isShowBeam())
            .setHighlightBlock(waypoint.isHighlightBlock());

        float highlightBlockLineWidth = waypoint.getHighlightBlockLineWidth();
        if (highlightBlockLineWidth != 0.0F) {
            builder.setHighlightBlockLineWidth(checkRange(highlightBlockLineWidth, 1.5F, 7.5F, "Waypoint#highlightBlockLineWidth"));
        }

        WaypointTextStyle style = waypoint.getTextStyle();
        if (style != null) {
            builder.setStyle(this.toProtobuf(style));
        }

        return builder.build();
    }

    private com.lunarclient.apollo.waypoint.v1.WaypointTextStyle toProtobuf(WaypointTextStyle style) {
        return com.lunarclient.apollo.waypoint.v1.WaypointTextStyle.newBuilder()
            .setShowText(style.isShowText())
            .setOnlyShowTextWhenLookingNear(style.isOnlyShowTextWhenLookingNear())
            .setShowIcons(style.isShowIcons())
            .setTextIconScale(checkRange(style.getTextIconScale(), 0.1F, 3.0F, "WaypointTextStyle#textIconScale"))
            .setLabelScale(checkRange(style.getLabelScale(), 0.1F, 2.0F, "WaypointTextStyle#labelScale"))
            .setBoxPadding(checkRange(style.getBoxPadding(), 1.0F, 8.0F, "WaypointTextStyle#boxPadding"))
            .setBoxBorders(style.isBoxBorders())
            .setTextShadow(style.isTextShadow())
            .setShowDistance(style.isShowDistance())
            .build();
    }

    private static final class WaypointSerializer implements TypeSerializer<Waypoint> {
        @Override
        public Waypoint deserialize(Type type, ConfigurationNode node) throws SerializationException {
            Waypoint.WaypointBuilder builder = Waypoint.builder()
                .name(this.virtualNode(node, "name").getString())
                .location(ApolloBlockLocation.builder()
                    .world(this.virtualNode(node, "location", "world").getString())
                    .x(this.virtualNode(node, "location", "x").getInt())
                    .y(this.virtualNode(node, "location", "y").getInt())
                    .z(this.virtualNode(node, "location", "z").getInt())
                    .build())
                .color(Color.decode(node.node("color").getString("#FFFFFF")))
                .preventRemoval(node.node("prevent-removal").getBoolean())
                .hidden(node.node("hidden").getBoolean());

            if (node.hasChild("show-beam")) {
                builder.showBeam(node.node("show-beam").getBoolean(true));
            }

            if (node.hasChild("highlight-block")) {
                builder.highlightBlock(node.node("highlight-block").getBoolean(true));
            }

            if (node.hasChild("highlight-block-line-width")) {
                builder.highlightBlockLineWidth((float) node.node("highlight-block-line-width").getDouble(4.0D));
            }

            if (node.hasChild("style")) {
                builder.textStyle(this.readStyle(node.node("style")));
            }

            return builder.build();
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
            node.node("show-beam").set(waypoint.isShowBeam());
            node.node("highlight-block").set(waypoint.isHighlightBlock());
            node.node("highlight-block-line-width").set(waypoint.getHighlightBlockLineWidth());

            if (waypoint.getTextStyle() != null) {
                this.writeStyle(node.node("style"), waypoint.getTextStyle());
            }
        }

        private WaypointTextStyle readStyle(ConfigurationNode node) {
            WaypointTextStyle.WaypointTextStyleBuilder builder = WaypointTextStyle.builder();
            if (node.hasChild("show-text")) {
                builder.showText(node.node("show-text").getBoolean(true));
            }

            if (node.hasChild("only-show-text-when-looking-near")) {
                builder.onlyShowTextWhenLookingNear(node.node("only-show-text-when-looking-near").getBoolean(false));
            }

            if (node.hasChild("show-icons")) {
                builder.showIcons(node.node("show-icons").getBoolean(false));
            }

            if (node.hasChild("text-icon-scale")) {
                builder.textIconScale(node.node("text-icon-scale").getFloat(1.5F));
            }

            if (node.hasChild("label-scale")) {
                builder.labelScale(node.node("label-scale").getFloat(1.0F));
            }

            if (node.hasChild("box-padding")) {
                builder.boxPadding(node.node("box-padding").getFloat(4.0F));
            }

            if (node.hasChild("box-borders")) {
                builder.boxBorders(node.node("box-borders").getBoolean(true));
            }

            if (node.hasChild("text-shadow")) {
                builder.textShadow(node.node("text-shadow").getBoolean(false));
            }

            if (node.hasChild("show-distance")) {
                builder.showDistance(node.node("show-distance").getBoolean(true));
            }

            return builder.build();
        }

        private void writeStyle(ConfigurationNode node, WaypointTextStyle style) throws SerializationException {
            node.node("show-text").set(style.isShowText());
            node.node("only-show-text-when-looking-near").set(style.isOnlyShowTextWhenLookingNear());
            node.node("show-icons").set(style.isShowIcons());
            node.node("text-icon-scale").set(style.getTextIconScale());
            node.node("label-scale").set(style.getLabelScale());
            node.node("box-padding").set(style.getBoxPadding());
            node.node("box-borders").set(style.isBoxBorders());
            node.node("text-shadow").set(style.isTextShadow());
            node.node("show-distance").set(style.isShowDistance());
        }

        private ConfigurationNode virtualNode(ConfigurationNode source, Object... path) throws SerializationException {
            if (!source.hasChild(path)) {
                throw new SerializationException("Required field " + Arrays.toString(path) + " not found!");
            }

            return source.node(path);
        }

    }

}
