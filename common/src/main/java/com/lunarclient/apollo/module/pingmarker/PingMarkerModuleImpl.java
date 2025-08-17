/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
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
package com.lunarclient.apollo.module.pingmarker;

import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.pingmarker.ApolloPlayerRequestMarkerEvent;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.pingmarker.v1.DisplayPlayerPingMessage;
import com.lunarclient.apollo.pingmarker.v1.RemovePingMarkerTypeMessage;
import com.lunarclient.apollo.pingmarker.v1.RemovePlayerPingMessage;
import com.lunarclient.apollo.pingmarker.v1.RequestPlayerPingMessage;
import com.lunarclient.apollo.pingmarker.v1.ResetPingMarkerTypesMessage;
import com.lunarclient.apollo.pingmarker.v1.ResetPlayerPingMessage;
import com.lunarclient.apollo.pingmarker.v1.SetPingMarkerTypeMessage;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.NonNull;

/**
 * Provides the ping marker module.
 *
 * @since 1.1.9
 */
public final class PingMarkerModuleImpl extends PingMarkerModule {

    private final Map<String, PingMarkerType> markerTypes = new ConcurrentHashMap<>();

    /**
     * Creates a new instance of {@link PingMarkerModuleImpl}.
     *
     * @since 1.1.9
     */
    public PingMarkerModuleImpl() {
        super();

        this.handle(ApolloReceivePacketEvent.class, this::onReceivePacket);
    }

    @Override
    public void setMarkerType(@NonNull Recipients recipients, @NonNull PingMarkerType type) {
        SetPingMarkerTypeMessage message = SetPingMarkerTypeMessage.newBuilder()
            .setId(type.getName())
            .setIcon(NetworkTypes.toProtobuf(type.getIcon()))
            .build();

        this.markerTypes.put(type.getName(), type);

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removeMarkerType(@NonNull Recipients recipients, @NonNull PingMarkerType type) {
        this.removeMarkerType(recipients, type.getName());
    }

    @Override
    public void removeMarkerType(@NonNull Recipients recipients, @NonNull String name) {
        RemovePingMarkerTypeMessage message = RemovePingMarkerTypeMessage.newBuilder()
            .setId(name)
            .build();

        this.markerTypes.remove(name);

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetMarkerType(@NonNull Recipients recipients) {
        ResetPingMarkerTypesMessage message = ResetPingMarkerTypesMessage.getDefaultInstance();

        this.markerTypes.clear();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void displayMarker(@NonNull Recipients recipients, @NonNull PingMarker marker) {
        PingMarkerType type = marker.getType();

        DisplayPlayerPingMessage.Builder messageBuilder = DisplayPlayerPingMessage.newBuilder()
            .setId(NetworkTypes.toProtobuf(marker.getId()))
            .setLocation(NetworkTypes.toProtobuf(marker.getLocation()))
            .setColor(NetworkTypes.toProtobuf(marker.getColor()))
            .setIcon(NetworkTypes.toProtobuf(marker.getIcon()))
            .setDuration(NetworkTypes.toProtobuf(marker.getDuration()))
            .setFocus(marker.isFocus());

        if (type != null) {
            messageBuilder.setType(type.getName());
        }

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(messageBuilder.build()));
    }

    @Override
    public void removeMarker(@NonNull Recipients recipients, @NonNull PingMarker marker) {
        this.removeMarker(recipients, marker.getId());
    }

    @Override
    public void removeMarker(@NonNull Recipients recipients, @NonNull UUID markerId) {
        RemovePlayerPingMessage message = RemovePlayerPingMessage.newBuilder()
            .setId(NetworkTypes.toProtobuf(markerId))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetMarkers(@NonNull Recipients recipients) {
        ResetPlayerPingMessage message = ResetPlayerPingMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    private void onReceivePacket(ApolloReceivePacketEvent event) {
        event.unpack(RequestPlayerPingMessage.class).ifPresent(packet -> {
            ApolloPlayerRequestMarkerEvent playerRequestPingEvent = new ApolloPlayerRequestMarkerEvent(
                event.getPlayer(),
                this.markerTypes.get(packet.getType()),
                NetworkTypes.fromProtobuf(packet.getSourceLocation()),
                NetworkTypes.fromProtobuf(packet.getTargetLocation())
            );

            EventBus.EventResult<ApolloPlayerRequestMarkerEvent> result = EventBus.getBus().post(playerRequestPingEvent);

            if (!result.getEvent().isCancelled()) {
                // TODO
            }

            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        });
    }

}
