package com.moonsworth.apollo.option.type;

import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.protocol.BlockLocationMessage;
import com.moonsworth.apollo.world.ApolloBlockLocation;

public final class BlockLocationImpl {

    public BlockLocationImpl() {
        NetworkOptions.register(ApolloBlockLocation.class, BlockLocationMessage.getDefaultInstance(), new OptionConverter<ApolloBlockLocation, BlockLocationMessage>() {
            @Override
            public BlockLocationMessage to(final ApolloBlockLocation object) throws IllegalArgumentException {
                return BlockLocationMessage.newBuilder()
                    .setWorld(object.getWorld())
                    .setX(object.getX())
                    .setY(object.getY())
                    .setZ(object.getZ())
                    .build();
            }

            @Override
            public ApolloBlockLocation from(final BlockLocationMessage message) throws IllegalArgumentException {
                return ApolloBlockLocation.of(
                    message.getWorld(),
                    message.getX(),
                    message.getY(),
                    message.getZ()
                );
            }
        });
    }
}
