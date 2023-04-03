package com.moonsworth.apollo.util;

import com.moonsworth.apollo.option.type.IconSpecifications;
import com.moonsworth.apollo.option.type.RenderableIcon;
import com.moonsworth.apollo.option.type.RenderableString;
import com.moonsworth.apollo.world.ApolloBlockLocation;
import com.moonsworth.apollo.world.ApolloLocation;
import lombok.experimental.UtilityClass;
import lunarclient.apollo.utility.BlockLocation;
import lunarclient.apollo.utility.IconSpecification;
import lunarclient.apollo.utility.Location;
import lunarclient.apollo.utility.Uuid;

import java.awt.*;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// TODO: temp solution?

@UtilityClass
public class ProtoUtils {

    public Uuid toProtoUuid(UUID object) {
        return Uuid.newBuilder()
            .setHigh64(object.getMostSignificantBits())
            .setLow64(object.getLeastSignificantBits())
            .build();
    }

    public UUID fromProtoUuid(Uuid message) {
        return new UUID(message.getHigh64(), message.getLow64());
    }

    public com.google.protobuf.Duration toProtoDuration(Duration object) {
        return com.google.protobuf.Duration.newBuilder()
            .setSeconds(object.getSeconds())
            .setNanos(object.getNano())
            .build();
    }

    public Duration fromProtoDuration(com.google.protobuf.Duration message) {
        return Duration.ofSeconds(message.getSeconds()).withNanos(message.getNanos());
    }

    public Location toProtoLocation(ApolloLocation object) {
        return Location.newBuilder()
            .setWorld(object.getWorld())
            .setX(object.getX())
            .setY(object.getY())
            .setZ(object.getZ())
            .build();
    }

    public ApolloLocation fromProtoLocation(Location message) {
        return ApolloLocation.of(
            message.getWorld(),
            message.getX(),
            message.getY(),
            message.getZ()
        );
    }

    public BlockLocation toProtoBlockLocation(ApolloBlockLocation object) {
        return BlockLocation.newBuilder()
            .setWorld(object.getWorld())
            .setX(object.getX())
            .setY(object.getY())
            .setZ(object.getZ())
            .build();
    }

    public ApolloBlockLocation fromProtoBlockLocation(BlockLocation message) {
        return ApolloBlockLocation.of(
            message.getWorld(),
            message.getX(),
            message.getY(),
            message.getZ()
        );
    }

    public IconSpecification toProtoIconSpecifications(IconSpecifications object) {
        return IconSpecification.newBuilder()
            .setWidth(object.getWidth())
            .setHeight(object.getHeight())
            .setMinU(object.getMinU())
            .setMaxU(object.getMaxU())
            .setMinV(object.getMinV())
            .setMaxV(object.getMaxV())
            .build();
    }

    public IconSpecifications fromProtoIconSpecifications(IconSpecification message) {
        return IconSpecifications.of(
            message.getWidth(),
            message.getHeight(),
            message.getMinU(),
            message.getMaxU(),
            message.getMinV(),
            message.getMaxV()
        );
    }

    public lunarclient.apollo.utility.RenderableIcon toProtoRenderableIcon(RenderableIcon icon) {
        return lunarclient.apollo.utility.RenderableIcon.newBuilder()
            .setResourceLocation(icon.getResourceLocation())
            .setSize(icon.getSize())
            .setSpecification(ProtoUtils.toProtoIconSpecifications(icon.getSpecifications()))
            .build();
    }

    public RenderableIcon fromProtoRenderableIcon(lunarclient.apollo.utility.RenderableIcon icon) {
        return RenderableIcon.of(
            icon.getResourceLocation(),
            icon.getSize(),
            ProtoUtils.fromProtoIconSpecifications(icon.getSpecification())
        );
    }

    public lunarclient.apollo.utility.RenderableString toProtoRenderableString(RenderableString object) {
        final List<lunarclient.apollo.utility.RenderableString.TextDecorators> decorators = object.getDecorators()
            .stream().map(decorator -> lunarclient.apollo.utility.RenderableString.TextDecorators.valueOf(decorator.name()))
            .collect(Collectors.toList());

        final List<lunarclient.apollo.utility.RenderableString> children = object.getChildren()
            .stream().map(ProtoUtils::toProtoRenderableString)
            .collect(Collectors.toList());

        return lunarclient.apollo.utility.RenderableString.newBuilder()
            .setContent(object.getContent())
            .setColor(object.getColor().getRGB())
            .addAllDecoration(decorators)
            .addAllChildren(children)
            .build();
    }

    public RenderableString fromProtoRenderableString(lunarclient.apollo.utility.RenderableString message) {
        final List<RenderableString.TextDecorators> decorators = message.getDecorationList()
            .stream().map(decorator -> RenderableString.TextDecorators.valueOf(decorator.name()))
            .collect(Collectors.toList());

        final List<RenderableString> children = message.getChildrenList()
            .stream().map(ProtoUtils::fromProtoRenderableString)
            .collect(Collectors.toList());

        return RenderableString.of(
            message.getContent(),
            new Color(message.getColor()),
            decorators,
            children
        );
    }
}
