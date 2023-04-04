package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.network.NetworkTypes;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Waypoint;
import java.awt.Color;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.WaypointMessage;

import static java.util.Objects.requireNonNull;

/**
 * Provides the waypoints module.
 *
 * @since 1.0.0
 */
public final class WaypointsImpl extends Waypoints {

    public WaypointsImpl() {
        super();
    }

    @Override
    public void addWaypoint(final ApolloPlayer player, final Waypoint waypoint) {
        requireNonNull(player, "player");
        requireNonNull(waypoint, "waypoint");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.ADD, this.to(waypoint));
    }

    @Override
    public void removeWaypoint(final ApolloPlayer player, final Waypoint waypoint) {
        requireNonNull(player, "player");
        requireNonNull(waypoint, "waypoint");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.REMOVE, this.to(waypoint));
    }

    @Override
    public void clearWaypoints(final ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.CLEAR);
    }

    private WaypointMessage to(final Waypoint waypoint) {
        return WaypointMessage.newBuilder()
                .setName(waypoint.getName())
                .setLocation(NetworkTypes.toBlockLocation(waypoint.getLocation()))
                .setColor(waypoint.getColor().getRGB())
                .setForced(waypoint.isForced())
                .setVisible(waypoint.isVisible())
                .build();
    }

    private Waypoint from(final WaypointMessage message) {
        return Waypoint.of(
                message.getName(),
                NetworkTypes.fromBlockLocation(message.getLocation()),
                new Color(message.getColor()),
                message.getForced(),
                message.getVisible()
        );
    }
}
