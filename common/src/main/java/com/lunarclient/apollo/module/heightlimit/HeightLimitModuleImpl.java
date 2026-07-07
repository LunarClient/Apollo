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
package com.lunarclient.apollo.module.heightlimit;

import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.heightlimit.v1.OverrideHeightLimitMessage;
import com.lunarclient.apollo.heightlimit.v1.RemoveHeightLimitMessage;
import com.lunarclient.apollo.heightlimit.v1.ResetHeightLimitsMessage;
import com.lunarclient.apollo.option.config.Serializer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import static com.lunarclient.apollo.util.Ranges.checkStrictlyPositive;

/**
 * Provides the height limit module.
 *
 * @since 1.2.9
 */
public final class HeightLimitModuleImpl extends HeightLimitModule implements Serializer {

    /**
     * Creates a new instance of {@link HeightLimitModuleImpl}.
     *
     * @since 1.2.9
     */
    public HeightLimitModuleImpl() {
        super();
        this.serializer(HeightLimit.class, new HeightLimitSerializer());
        this.handle(ApolloRegisterPlayerEvent.class, this::onPlayerRegister);
    }

    @Override
    public void overrideHeightLimit(@NonNull Recipients recipients, @NonNull HeightLimit heightLimit) {
        OverrideHeightLimitMessage.Builder builder = OverrideHeightLimitMessage.newBuilder()
            .setWorld(heightLimit.getWorld())
            .setLimit(checkStrictlyPositive(heightLimit.getLimit(), "HeightLimit#limit"));

        Component displayName = heightLimit.getDisplayName();
        if (displayName != null) {
            builder.setDisplayNameAdventureJsonLines(ApolloComponent.toJson(displayName));
        }

        OverrideHeightLimitMessage message = builder.build();
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void removeHeightLimit(@NonNull Recipients recipients, @NonNull String world) {
        RemoveHeightLimitMessage message = RemoveHeightLimitMessage.newBuilder()
            .setWorld(world)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void removeHeightLimit(@NonNull Recipients recipients, @NonNull HeightLimit heightLimit) {
        this.removeHeightLimit(recipients, heightLimit.getWorld());
    }

    @Override
    public void resetHeightLimits(@NonNull Recipients recipients) {
        ResetHeightLimitsMessage message = ResetHeightLimitsMessage.getDefaultInstance();
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    private void onPlayerRegister(ApolloRegisterPlayerEvent event) {
        ApolloPlayer player = event.getPlayer();
        List<HeightLimit> heightLimits = this.getOptions().get(player, HeightLimitModule.DEFAULT_HEIGHT_LIMITS);

        if (heightLimits != null) {
            for (HeightLimit heightLimit : heightLimits) {
                this.overrideHeightLimit(player, heightLimit);
            }
        }
    }

    private static final class HeightLimitSerializer implements TypeSerializer<HeightLimit> {

        @Override
        public HeightLimit deserialize(Type type, ConfigurationNode node) throws SerializationException {
            HeightLimit.HeightLimitBuilder builder = HeightLimit.builder()
                .world(this.virtualNode(node, "world").getString())
                .limit(this.virtualNode(node, "limit").getInt());

            String displayName = node.node("display-name").getString();
            if (displayName != null) {
                builder.displayName(ApolloComponent.fromLegacy(displayName));
            }

            return builder.build();
        }

        @Override
        public void serialize(Type type, @Nullable HeightLimit heightLimit, ConfigurationNode node) throws SerializationException {
            if (heightLimit == null) {
                node.raw(null);
                return;
            }

            node.node("world").set(heightLimit.getWorld());
            node.node("limit").set(heightLimit.getLimit());

            Component displayName = heightLimit.getDisplayName();
            if (displayName != null) {
                node.node("display-name").set(LegacyComponentSerializer.legacyAmpersand().serialize(displayName));
            }
        }

        private ConfigurationNode virtualNode(ConfigurationNode source, Object... path) throws SerializationException {
            if (!source.hasChild(path)) {
                throw new SerializationException("Required field " + Arrays.toString(path) + " not found!");
            }

            return source.node(path);
        }
    }

}
