package com.moonsworth.apollo.module.type;

import com.google.common.collect.Lists;
import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Waypoint;
import com.moonsworth.apollo.protocol.BlockLocationMessage;
import com.moonsworth.apollo.protocol.WaypointMessage;
import com.moonsworth.apollo.world.ApolloBlockLocation;

import java.awt.*;

import static java.util.Objects.requireNonNull;

/**
 * Provides the waypoints module.
 *
 * @since 1.0.0
 */
public final class WaypointsImpl extends Waypoints {

    public WaypointsImpl() {
        super();

        NetworkOptions.register(Waypoint.class, WaypointMessage.getDefaultInstance(), new OptionConverter<Waypoint, WaypointMessage>() {
            @Override
            public WaypointMessage to(final Waypoint object) throws IllegalArgumentException {
                final OptionConverter<ApolloBlockLocation, BlockLocationMessage> locationConverter = NetworkOptions.get(ApolloBlockLocation.class);

                return WaypointMessage.newBuilder()
                    .setName(object.getName())
                    .setLocation(locationConverter.to(object.getLocation()))
                    .setColor(object.getColor().getRGB())
                    .setForced(object.isForced())
                    .setVisible(object.isVisible())
                    .build();
            }

            @Override
            public Waypoint from(final WaypointMessage message) throws IllegalArgumentException {
                final OptionConverter<ApolloBlockLocation, BlockLocationMessage> locationConverter = NetworkOptions.get(ApolloBlockLocation.class);

                return Waypoint.of(
                        message.getName(),
                        locationConverter.from(message.getLocation()),
                        new Color(message.getColor()),
                        message.getForced(),
                        message.getVisible()
                );
            }
        });
    }

    @Override
    public void addWaypoint(ApolloPlayer player, Waypoint waypoint) {
        requireNonNull(player, "player");
        requireNonNull(waypoint, "waypoint");
        this.getOptions().set(player, Waypoints.WAYPOINTS, Lists.newArrayList(waypoint));
    }

    @Override
    public void removeWaypoint(ApolloPlayer player, Waypoint waypoint) {
        requireNonNull(player, "player");
        requireNonNull(waypoint, "waypoint");
        this.getOptions().remove(player, Waypoints.WAYPOINTS, Lists.newArrayList(waypoint));
    }

    @Override
    public void clearWaypoints(ApolloPlayer player) {
        requireNonNull(player, "player");
        this.getOptions().set(player, Waypoints.WAYPOINTS, Lists.newArrayList());
    }

}
