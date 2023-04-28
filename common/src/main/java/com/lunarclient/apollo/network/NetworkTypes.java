package com.lunarclient.apollo.network;

import com.lunarclient.apollo.common.v1.Uuid;
import com.lunarclient.apollo.option.type.Component;
import com.lunarclient.apollo.option.type.bounds.Cuboid2D;
import com.lunarclient.apollo.option.type.bounds.Cuboid3D;
import com.lunarclient.apollo.option.type.icon.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.option.type.icon.Icon;
import com.lunarclient.apollo.option.type.icon.ItemStackIcon;
import com.lunarclient.apollo.option.type.icon.SimpleResourceLocationIcon;
import com.lunarclient.apollo.world.ApolloBlockLocation;
import com.lunarclient.apollo.world.ApolloLocation;
import java.awt.Color;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class NetworkTypes {

    private NetworkTypes() {

    }

    public static Uuid toProtobuf(UUID object) {
        return Uuid.newBuilder()
            .setHigh64(object.getMostSignificantBits())
            .setLow64(object.getLeastSignificantBits())
            .build();
    }

    public static UUID fromProtobuf(Uuid message) {
        return new UUID(message.getHigh64(), message.getLow64());
    }

    public static com.lunarclient.apollo.common.v1.Color toProtobuf(Color object) {
        return com.lunarclient.apollo.common.v1.Color.newBuilder()
            .setColor(object.getRGB())
            .build();
    }

    public static Color fromProtobuf(com.lunarclient.apollo.common.v1.Color message) {
        return new Color(message.getColor());
    }

    public static com.google.protobuf.Duration toProtobuf(Duration object) {
        return com.google.protobuf.Duration.newBuilder()
            .setSeconds(object.getSeconds())
            .setNanos(object.getNano())
            .build();
    }

    public static Duration fromProtobuf(com.google.protobuf.Duration message) {
        return Duration.ofSeconds(message.getSeconds()).withNanos(message.getNanos());
    }

    public static com.lunarclient.apollo.common.v1.Location toProtobuf(ApolloLocation object) {
        return com.lunarclient.apollo.common.v1.Location.newBuilder()
            .setWorld(object.getWorld())
            .setX(object.getX())
            .setY(object.getY())
            .setZ(object.getZ())
            .build();
    }

    public static ApolloLocation fromProtobuf(com.lunarclient.apollo.common.v1.Location message) {
        return ApolloLocation.builder()
            .withWorld(message.getWorld())
            .withX(message.getX())
            .withY(message.getY())
            .withZ(message.getZ())
            .build();
    }

    public static com.lunarclient.apollo.common.v1.BlockLocation toProtobuf(ApolloBlockLocation object) {
        return com.lunarclient.apollo.common.v1.BlockLocation.newBuilder()
            .setWorld(object.getWorld())
            .setX(object.getX())
            .setY(object.getY())
            .setZ(object.getZ())
            .build();
    }

    public static ApolloBlockLocation fromProtobuf(com.lunarclient.apollo.common.v1.BlockLocation message) {
        return ApolloBlockLocation.builder()
            .withWorld(message.getWorld())
            .withX(message.getX())
            .withY(message.getY())
            .withZ(message.getZ())
            .build();
    }

    public static com.lunarclient.apollo.common.v1.Cuboid2D toProtobuf(Cuboid2D object) {
        return com.lunarclient.apollo.common.v1.Cuboid2D.newBuilder()
            .setMinX(object.getMinX())
            .setMinZ(object.getMinZ())
            .setMaxX(object.getMaxX())
            .setMaxZ(object.getMaxZ())
            .build();
    }

    public static Cuboid2D fromProtobuf(com.lunarclient.apollo.common.v1.Cuboid2D message) {
        return Cuboid2D.builder()
            .withMinX(message.getMinX())
            .withMinZ(message.getMinZ())
            .withMaxX(message.getMaxX())
            .withMaxZ(message.getMaxZ())
            .build();
    }

    public static com.lunarclient.apollo.common.v1.Cuboid3D toProtobuf(Cuboid3D object) {
        return com.lunarclient.apollo.common.v1.Cuboid3D.newBuilder()
            .setMinX(object.getMinX())
            .setMinY(object.getMinY())
            .setMinZ(object.getMinZ())
            .setMaxX(object.getMaxX())
            .setMaxY(object.getMaxY())
            .setMaxZ(object.getMaxZ())
            .build();
    }

    public static Cuboid3D fromProtobuf(com.lunarclient.apollo.common.v1.Cuboid3D message) {
        return Cuboid3D.builder()
            .withMinX(message.getMinX())
            .withMinY(message.getMinY())
            .withMinZ(message.getMinZ())
            .withMaxX(message.getMaxX())
            .withMaxY(message.getMaxY())
            .withMaxZ(message.getMaxZ())
            .build();
    }

    public static com.lunarclient.apollo.common.v1.Component toProtobuf(Component object) {
        List<com.lunarclient.apollo.common.v1.Component.TextDecorator> decorators = object.getDecorators()
            .stream().map(decorator -> com.lunarclient.apollo.common.v1.Component.TextDecorator.forNumber(decorator.ordinal() + 1))
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

    public static Component fromProtobuf(com.lunarclient.apollo.common.v1.Component message) {
        List<Component.TextDecorators> decorators = message.getDecoratorsList()
            .stream().map(decorator -> Component.TextDecorators.values()[decorator.ordinal() - 1])
            .collect(Collectors.toList());

        List<Component> children = message.getChildrenList()
            .stream().map(NetworkTypes::fromProtobuf)
            .collect(Collectors.toList());

        return Component.builder()
            .withContent(message.getContent())
            .withColor(NetworkTypes.fromProtobuf(message.getColor()))
            .withDecorators(decorators)
            .withChildren(children)
            .build();
    }

    public static com.lunarclient.apollo.common.v1.Icon toProtobuf(Icon icon) {
        com.lunarclient.apollo.common.v1.Icon.Builder builder = com.lunarclient.apollo.common.v1.Icon.newBuilder();

        if(icon instanceof ItemStackIcon) {
            ItemStackIcon item = (ItemStackIcon) icon;

            builder.setItemStack(com.lunarclient.apollo.common.v1.ItemStackIcon.newBuilder()
                .setItemId(item.getItemId())
                .build());
        } else if(icon instanceof SimpleResourceLocationIcon) {
            SimpleResourceLocationIcon simple = (SimpleResourceLocationIcon) icon;

            builder.setSimpleResourceLocation(com.lunarclient.apollo.common.v1.SimpleResourceLocationIcon.newBuilder()
                .setResourceLocation(simple.getResourceLocation())
                .setSize(simple.getSize())
                .build());
        } else if(icon instanceof AdvancedResourceLocationIcon) {
            AdvancedResourceLocationIcon advanced = (AdvancedResourceLocationIcon) icon;

            builder.setAdvancedResourceLocation(com.lunarclient.apollo.common.v1.AdvancedResourceLocationIcon.newBuilder()
                .setResourceLocation(advanced.getResourceLocation())
                .setWidth(advanced.getWidth())
                .setHeight(advanced.getHeight())
                .setMinU(advanced.getMinU())
                .setMaxU(advanced.getMaxU())
                .setMinV(advanced.getMinV())
                .setMaxV(advanced.getMaxV())
                .build());
        }

        return builder.build();
    }

    public static Icon fromProtobuf(com.lunarclient.apollo.common.v1.Icon icon) {
        if(icon.hasItemStack()) {
            com.lunarclient.apollo.common.v1.ItemStackIcon item = icon.getItemStack();

            return ItemStackIcon.builder()
                .withItemId(item.getItemId())
                .build();
        } else if(icon.hasSimpleResourceLocation()) {
            com.lunarclient.apollo.common.v1.SimpleResourceLocationIcon simple = icon.getSimpleResourceLocation();

            return SimpleResourceLocationIcon.builder()
                .withResourceLocation(simple.getResourceLocation())
                .withSize(simple.getSize())
                .build();
        } else if(icon.hasAdvancedResourceLocation()) {
            com.lunarclient.apollo.common.v1.AdvancedResourceLocationIcon advanced = icon.getAdvancedResourceLocation();

            return AdvancedResourceLocationIcon.builder()
                .withResourceLocation(advanced.getResourceLocation())
                .withWidth(advanced.getWidth())
                .withHeight(advanced.getHeight())
                .withMinU(advanced.getMinU())
                .withMaxU(advanced.getMaxU())
                .withMinV(advanced.getMinV())
                .withMaxV(advanced.getMaxV())
                .build();
        }

        return null;
    }
}
