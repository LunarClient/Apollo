package com.moonsworth.apollo.network;

import com.moonsworth.apollo.option.type.IconSpecifications;
import com.moonsworth.apollo.option.type.RenderableIcon;
import com.moonsworth.apollo.option.type.RenderableString;
import com.moonsworth.apollo.world.ApolloBlockLocation;
import com.moonsworth.apollo.world.ApolloLocation;
import java.awt.Color;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import lunarclient.apollo.utility.BlockLocation;
import lunarclient.apollo.utility.IconSpecification;
import lunarclient.apollo.utility.Location;
import lunarclient.apollo.utility.Uuid;

// TODO: temp solution?

@UtilityClass
public class NetworkTypes {

    public Uuid toUuid(final UUID object) {
        return Uuid.newBuilder()
            .setHigh64(object.getMostSignificantBits())
            .setLow64(object.getLeastSignificantBits())
            .build();
    }

    public UUID fromUuid(final Uuid message) {
        return new UUID(message.getHigh64(), message.getLow64());
    }

    public com.google.protobuf.Duration toDuration(final Duration object) {
        return com.google.protobuf.Duration.newBuilder()
            .setSeconds(object.getSeconds())
            .setNanos(object.getNano())
            .build();
    }

    public Duration fromDuration(final com.google.protobuf.Duration message) {
        return Duration.ofSeconds(message.getSeconds()).withNanos(message.getNanos());
    }

    public Location toLocation(final ApolloLocation object) {
        return Location.newBuilder()
            .setWorld(object.getWorld())
            .setX(object.getX())
            .setY(object.getY())
            .setZ(object.getZ())
            .build();
    }

    public ApolloLocation fromLocation(final Location message) {
        return ApolloLocation.of(
            message.getWorld(),
            message.getX(),
            message.getY(),
            message.getZ()
        );
    }

    public BlockLocation toBlockLocation(final ApolloBlockLocation object) {
        return BlockLocation.newBuilder()
            .setWorld(object.getWorld())
            .setX(object.getX())
            .setY(object.getY())
            .setZ(object.getZ())
            .build();
    }

    public ApolloBlockLocation fromBlockLocation(final BlockLocation message) {
        return ApolloBlockLocation.of(
            message.getWorld(),
            message.getX(),
            message.getY(),
            message.getZ()
        );
    }

    public IconSpecification toIconSpecifications(final IconSpecifications object) {
        return IconSpecification.newBuilder()
            .setWidth(object.getWidth())
            .setHeight(object.getHeight())
            .setMinU(object.getMinU())
            .setMaxU(object.getMaxU())
            .setMinV(object.getMinV())
            .setMaxV(object.getMaxV())
            .build();
    }

    public IconSpecifications fromIconSpecifications(final IconSpecification message) {
        return IconSpecifications.of(
            message.getWidth(),
            message.getHeight(),
            message.getMinU(),
            message.getMaxU(),
            message.getMinV(),
            message.getMaxV()
        );
    }

    public lunarclient.apollo.utility.RenderableIcon toRenderableIcon(final RenderableIcon icon) {
        return lunarclient.apollo.utility.RenderableIcon.newBuilder()
            .setResourceLocation(icon.getResourceLocation())
            .setSize(icon.getSize())
            .setSpecification(NetworkTypes.toIconSpecifications(icon.getSpecifications()))
            .build();
    }

    public RenderableIcon fromRenderableIcon(final lunarclient.apollo.utility.RenderableIcon icon) {
        return RenderableIcon.of(
            icon.getResourceLocation(),
            icon.getSize(),
            NetworkTypes.fromIconSpecifications(icon.getSpecification())
        );
    }

    public lunarclient.apollo.utility.RenderableString toRenderableString(final RenderableString object) {
        final List<lunarclient.apollo.utility.RenderableString.TextDecorators> decorators = object.getDecorators()
            .stream().map(decorator -> lunarclient.apollo.utility.RenderableString.TextDecorators.valueOf(decorator.name()))
            .collect(Collectors.toList());

        final List<lunarclient.apollo.utility.RenderableString> children = object.getChildren()
            .stream().map(NetworkTypes::toRenderableString)
            .collect(Collectors.toList());

        return lunarclient.apollo.utility.RenderableString.newBuilder()
            .setContent(object.getContent())
            .setColor(object.getColor().getRGB())
            .addAllDecoration(decorators)
            .addAllChildren(children)
            .build();
    }

    public RenderableString fromRenderableString(final lunarclient.apollo.utility.RenderableString message) {
        final List<RenderableString.TextDecorators> decorators = message.getDecorationList()
            .stream().map(decorator -> RenderableString.TextDecorators.valueOf(decorator.name()))
            .collect(Collectors.toList());

        final List<RenderableString> children = message.getChildrenList()
            .stream().map(NetworkTypes::fromRenderableString)
            .collect(Collectors.toList());

        return RenderableString.of(
            message.getContent(),
            new Color(message.getColor()),
            decorators,
            children
        );
    }
}
