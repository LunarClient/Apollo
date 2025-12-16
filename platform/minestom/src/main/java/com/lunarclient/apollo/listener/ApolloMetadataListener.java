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
package com.lunarclient.apollo.listener;

import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.metadata.MinestomMetadataManager;
import com.lunarclient.apollo.util.ByteBufUtil;
import java.nio.ByteBuffer;
import java.util.Map;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.PlayerPluginMessageEvent;
import net.minestom.server.event.player.PlayerResourcePackStatusEvent;

/**
 * Handles Apollo metadata listeners.
 *
 * @since 1.2.0
 */
public final class ApolloMetadataListener {

    /**
     * Constructs the {@link ApolloMetadataListener}.
     *
     * @param node the node
     * @since 1.2.0
     */
    public ApolloMetadataListener(EventNode<Event> node) {
        node.addListener(PlayerResourcePackStatusEvent.class, this::onResourcePackStatus);
        node.addListener(PlayerPluginMessageEvent.class, this::onPlayerPluginMessage);
    }

    private void onResourcePackStatus(PlayerResourcePackStatusEvent event) {
        String status = event.getStatus().name();
        MinestomMetadataManager manager = (MinestomMetadataManager) ApolloManager.getMetadataManager();
        Map<String, Integer> statuses = manager.getResourcePackStatuses();

        statuses.put(status, statuses.getOrDefault(status, 0) + 1);
    }

    private void onPlayerPluginMessage(PlayerPluginMessageEvent event) {
        String identifier = event.getIdentifier();

        if (!identifier.equals("minecraft:brand")) {
            return;
        }

        ByteBuffer buffer = ByteBuffer.wrap(event.getMessage());
        String brand = ByteBufUtil.readString(buffer);
        MinestomMetadataManager manager = (MinestomMetadataManager) ApolloManager.getMetadataManager();
        Map<String, Integer> brands = manager.getClientBrands();

        brands.put(brand, brands.getOrDefault(brand, 0) + 1);
    }

}
