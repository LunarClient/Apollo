package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.player.ui.Team;
import com.moonsworth.apollo.protocol.LocationMessage;
import com.moonsworth.apollo.protocol.TeamMessage;
import com.moonsworth.apollo.world.ApolloLocation;

import java.awt.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Provides the teams module.
 *
 * @since 1.0.0
 */
public final class TeamsImpl extends Teams {

    public TeamsImpl() {
        super();

        NetworkOptions.register(Team.class, TeamMessage.getDefaultInstance(), new OptionConverter<Team, TeamMessage>() {
            @Override
            public TeamMessage to(final Team object) throws IllegalArgumentException {
                final OptionConverter<ApolloLocation, LocationMessage> locationConverter = NetworkOptions.get(ApolloLocation.class);

                final List<TeamMessage.Teammate> teammates = object.getTeammates().stream()
                    .map(teammate -> TeamMessage.Teammate.newBuilder()
                        .setPlayer(teammate.getPlayer().toString())
                        .setColor(teammate.getColor().getRGB())
                        .setLocation(locationConverter.to(teammate.getLocation()))
                        .build()
                    )
                    .collect(Collectors.toList());

                return TeamMessage.newBuilder()
                    .addAllMembers(teammates)
                    .build();
            }

            @Override
            public Team from(final TeamMessage message) throws IllegalArgumentException {
                final OptionConverter<ApolloLocation, LocationMessage> locationConverter = NetworkOptions.get(ApolloLocation.class);

                final List<Team.Teammate> teammates = message.getMembersList().stream()
                    .map(teammate -> Team.Teammate.of(
                        UUID.fromString(teammate.getPlayer()),
                        new Color(teammate.getColor()),
                        locationConverter.from(teammate.getLocation())
                    ))
                    .collect(Collectors.toList());

                return Team.of(teammates);
            }
        });
    }

}
