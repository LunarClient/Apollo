package com.lunarclient.apollo.network;

import com.lunarclient.apollo.common.v1.Uuid;
import com.lunarclient.apollo.option.type.Component;
import com.lunarclient.apollo.world.ApolloBlockLocation;
import com.lunarclient.apollo.world.ApolloLocation;
import java.awt.Color;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NetworkTypes {

    public Uuid toProtobuf(UUID object) {
        return Uuid.newBuilder()
            .setHigh64(object.getMostSignificantBits())
            .setLow64(object.getLeastSignificantBits())
            .build();
    }

    public UUID fromProtobuf(Uuid message) {
        return new UUID(message.getHigh64(), message.getLow64());
    }

    public com.lunarclient.apollo.common.v1.Color toProtobuf(Color object) {
        return com.lunarclient.apollo.common.v1.Color.newBuilder()
            .setColor(object.getRGB())
            .build();
    }

    public Color fromProtobuf(com.lunarclient.apollo.common.v1.Color message) {
        return new Color(message.getColor());
    }

    public com.google.protobuf.Duration toProtobuf(Duration object) {
        return com.google.protobuf.Duration.newBuilder()
            .setSeconds(object.getSeconds())
            .setNanos(object.getNano())
            .build();
    }

    public Duration fromProtobuf(com.google.protobuf.Duration message) {
        return Duration.ofSeconds(message.getSeconds()).withNanos(message.getNanos());
    }

    public com.lunarclient.apollo.common.v1.Location toProtobuf(ApolloLocation object) {
        return com.lunarclient.apollo.common.v1.Location.newBuilder()
            .setWorld(object.getWorld())
            .setX(object.getX())
            .setY(object.getY())
            .setZ(object.getZ())
            .build();
    }

    public ApolloLocation fromProtobuf(com.lunarclient.apollo.common.v1.Location message) {
        return ApolloLocation.of(
            message.getWorld(),
            message.getX(),
            message.getY(),
            message.getZ()
        );
    }

    public com.lunarclient.apollo.common.v1.BlockLocation toProtobuf(ApolloBlockLocation object) {
        return com.lunarclient.apollo.common.v1.BlockLocation.newBuilder()
            .setWorld(object.getWorld())
            .setX(object.getX())
            .setY(object.getY())
            .setZ(object.getZ())
            .build();
    }

    public ApolloBlockLocation fromProtobuf(com.lunarclient.apollo.common.v1.BlockLocation message) {
        return ApolloBlockLocation.of(
            message.getWorld(),
            message.getX(),
            message.getY(),
            message.getZ()
        );
    }

    public com.lunarclient.apollo.common.v1.Component toProtobuf(Component object) {
        List<com.lunarclient.apollo.common.v1.Component.TextDecorator> decorators = object.getDecorators()
            .stream().map(decorator -> com.lunarclient.apollo.common.v1.Component.TextDecorator.valueOf("TEXT_DECORATOR_" + decorator.name()))
            .collect(Collectors.toList());

        List<com.lunarclient.apollo.common.v1.Component> children = object.getChildren()
            .stream().map(NetworkTypes::toProtobuf)
            .collect(Collectors.toList());

        return com.lunarclient.apollo.common.v1.Component.newBuilder()
            .setContent(object.getContent())
            .setColor(NetworkTypes.toProtobuf(object.getColor()))
            .addAllDecorators(decorators)
            .addAllChildren(children)
            .build();
    }

    public Component fromProtobuf(com.lunarclient.apollo.common.v1.Component message) {
        List<Component.TextDecorators> decorators = message.getDecoratorsList()
            .stream().map(decorator -> Component.TextDecorators.valueOf(decorator.name()))
            .collect(Collectors.toList());

        List<Component> children = message.getChildrenList()
            .stream().map(NetworkTypes::fromProtobuf)
            .collect(Collectors.toList());

        return Component.of(
            message.getContent(),
            NetworkTypes.fromProtobuf(message.getColor()),
            decorators,
            children
        );
    }
}
