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
package com.lunarclient.apollo.module.serverlink;

import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.common.icon.ResourceLocationIcon;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import com.lunarclient.apollo.serverlink.v1.AddServerLinkMessage;
import com.lunarclient.apollo.serverlink.v1.OverrideServerLinkResourceMessage;
import com.lunarclient.apollo.serverlink.v1.RemoveServerLinkMessage;
import com.lunarclient.apollo.serverlink.v1.ResetServerLinkResourceMessage;
import com.lunarclient.apollo.serverlink.v1.ResetServerLinksMessage;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;

/**
 * Provides the server link module.
 *
 * @since 1.2.5
 */
public final class ServerLinkModuleImpl extends ServerLinkModule {

    @Override
    public void overrideServerLinkResource(@NonNull Recipients recipients, @NonNull ResourceLocationIcon icon) {
        OverrideServerLinkResourceMessage message = OverrideServerLinkResourceMessage.newBuilder()
            .setIcon(NetworkTypes.toProtobuf(icon))
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetServerLinkResource(@NonNull Recipients recipients) {
        ResetServerLinkResourceMessage message = ResetServerLinkResourceMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void addServerLink(@NonNull Recipients recipients, @NonNull ServerLink serverLink) {
        this.addServerLink(recipients, Collections.singletonList(serverLink));
    }

    @Override
    public void addServerLink(@NonNull Recipients recipients, @NonNull List<ServerLink> serverLinks) {
        List<com.lunarclient.apollo.serverlink.v1.ServerLink> serverLinksProto = serverLinks.stream()
            .map(this::toProtobuf)
            .collect(Collectors.toList());

        AddServerLinkMessage message = AddServerLinkMessage.newBuilder()
            .addAllServerLinks(serverLinksProto)
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void removeServerLink(@NonNull Recipients recipients, @NonNull String serverLinkId) {
        this.removeServerLink(recipients, Collections.singletonList(serverLinkId));
    }

    @Override
    public void removeServerLink(@NonNull Recipients recipients, @NonNull ServerLink serverLink) {
        this.removeServerLink(recipients, Collections.singletonList(serverLink.getId()));
    }

    @Override
    public void removeServerLink(@NonNull Recipients recipients, @NonNull List<String> serverLinkIds) {
        RemoveServerLinkMessage message = RemoveServerLinkMessage.newBuilder()
            .addAllServerLinkIds(serverLinkIds)
            .build();

        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetServerLinks(@NonNull Recipients recipients) {
        ResetServerLinksMessage message = ResetServerLinksMessage.getDefaultInstance();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    private com.lunarclient.apollo.serverlink.v1.ServerLink toProtobuf(ServerLink serverLink) {
        return com.lunarclient.apollo.serverlink.v1.ServerLink.newBuilder()
            .setId(serverLink.getId())
            .setDisplayNameAdventureJsonLines(ApolloComponent.toJson(serverLink.getDisplayName()))
            .setUrl(serverLink.getUrl())
            .build();
    }

}
