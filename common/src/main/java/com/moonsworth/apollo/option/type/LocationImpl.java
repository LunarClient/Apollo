package com.moonsworth.apollo.option.type;

import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.protocol.LocationMessage;
import com.moonsworth.apollo.world.ApolloLocation;

public final class LocationImpl {

    public LocationImpl() {
        NetworkOptions.register(ApolloLocation.class, LocationMessage.getDefaultInstance(), new OptionConverter<ApolloLocation, LocationMessage>() {
            @Override
            public LocationMessage to(final ApolloLocation object) throws IllegalArgumentException {
                return LocationMessage.newBuilder()
                    .setWorld(object.getWorld())
                    .setX(object.getX())
                    .setY(object.getY())
                    .setZ(object.getZ())
                    .build();
            }

            @Override
            public ApolloLocation from(final LocationMessage message) throws IllegalArgumentException {
                return ApolloLocation.of(
                    message.getWorld(), message.getX(), message.getY(), message.getZ()
                );
            }
        });
    }
}
