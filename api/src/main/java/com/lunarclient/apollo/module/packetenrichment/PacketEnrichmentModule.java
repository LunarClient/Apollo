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
package com.lunarclient.apollo.module.packetenrichment;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the packet enrichment module.
 *
 * @since 1.0.7
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "packet_enrichment", name = "PacketEnrichment")
public abstract class PacketEnrichmentModule extends ApolloModule {

    /**
     * Controls whether the client sends an additional player attack packet to the server.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> PLAYER_ATTACK_PACKET = Option.<Boolean>builder()
        .comment("Set to 'true' to have the client send an additional player attack packet to the server, otherwise 'false'.")
        .node("player-attack", "send-packet").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    /**
     * Controls whether Apollo fires {@link com.lunarclient.apollo.event.packetenrichment.melee.ApolloPlayerAttackEvent}
     * when the packet is received.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> PLAYER_ATTACK_EVENT = Option.<Boolean>builder()
        .comment("If 'true', Apollo fires the player attack event on the main thread. Disable this and handle the packet yourself if you require asynchronous or off-thread processing.")
        .node("player-attack", "fire-apollo-event").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    /**
     * Controls whether the client sends an additional player chat open packet to the server.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> PLAYER_CHAT_OPEN_PACKET = Option.<Boolean>builder()
        .comment("Set to 'true' to have the client send an additional player chat open packet to the server, otherwise 'false'.")
        .node("player-chat-open", "send-packet").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    /**
     * Controls whether Apollo fires {@link com.lunarclient.apollo.event.packetenrichment.chat.ApolloPlayerChatOpenEvent}
     * when the packet is received.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> PLAYER_CHAT_OPEN_EVENT = Option.<Boolean>builder()
        .comment("If 'true', Apollo fires the player chat open event on the main thread. Disable this and handle the packet yourself if you require asynchronous or off-thread processing.")
        .node("player-chat-open", "fire-apollo-event").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    /**
     * Controls whether the client sends an additional player chat close packet to the server.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> PLAYER_CHAT_CLOSE_PACKET = Option.<Boolean>builder()
        .comment("Set to 'true' to have the client send an additional player chat close packet to the server, otherwise 'false'.")
        .node("player-chat-close", "send-packet").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    /**
     * Controls whether Apollo fires {@link com.lunarclient.apollo.event.packetenrichment.chat.ApolloPlayerChatCloseEvent}
     * when the packet is received.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> PLAYER_CHAT_CLOSE_EVENT = Option.<Boolean>builder()
        .comment("If 'true', Apollo fires the player chat close event on the main thread. Disable this and handle the packet yourself if you require asynchronous or off-thread processing.")
        .node("player-chat-close", "fire-apollo-event").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    /**
     * Controls whether the client sends an additional player use item packet to the server.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> PLAYER_USE_ITEM_PACKET = Option.<Boolean>builder()
        .comment("Set to 'true' to have the client send an additional player use item packet to the server, otherwise 'false'.")
        .node("player-use-item", "send-packet").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    /**
     * Controls whether Apollo fires {@link com.lunarclient.apollo.event.packetenrichment.world.ApolloPlayerUseItemEvent}
     * when the packet is received.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> PLAYER_USE_ITEM_EVENT = Option.<Boolean>builder()
        .comment("If 'true', Apollo fires the player use item event on the main thread. Disable this and handle the packet yourself if you require asynchronous or off-thread processing.")
        .node("player-use-item", "fire-apollo-event").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    /**
     * Controls whether the client sends an additional player use item bucket packet to the server.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> PLAYER_USE_ITEM_BUCKET_PACKET = Option.<Boolean>builder()
        .comment("Set to 'true' to have the client send an additional player use item bucket packet to the server, otherwise 'false'.")
        .node("player-use-item-bucket", "send-packet").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    /**
     * Controls whether Apollo fires {@link com.lunarclient.apollo.event.packetenrichment.world.ApolloPlayerUseItemBucketEvent}
     * when the packet is received.
     *
     * @since 1.2.2
     */
    public static final SimpleOption<Boolean> PLAYER_USE_ITEM_BUCKET_EVENT = Option.<Boolean>builder()
        .comment("If 'true', Apollo fires the player use item bucket event on the main thread. Disable this and handle the packet yourself if you require asynchronous or off-thread processing.")
        .node("player-use-item-bucket", "fire-apollo-event").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    protected PacketEnrichmentModule() {
        this.registerOptions(
            ApolloModule.ENABLE_OPTION_OFF,
            PacketEnrichmentModule.PLAYER_ATTACK_PACKET,
            PacketEnrichmentModule.PLAYER_ATTACK_EVENT,
            PacketEnrichmentModule.PLAYER_CHAT_OPEN_PACKET,
            PacketEnrichmentModule.PLAYER_CHAT_OPEN_EVENT,
            PacketEnrichmentModule.PLAYER_CHAT_CLOSE_PACKET,
            PacketEnrichmentModule.PLAYER_CHAT_CLOSE_EVENT,
            PacketEnrichmentModule.PLAYER_USE_ITEM_PACKET,
            PacketEnrichmentModule.PLAYER_USE_ITEM_EVENT,
            PacketEnrichmentModule.PLAYER_USE_ITEM_BUCKET_PACKET,
            PacketEnrichmentModule.PLAYER_USE_ITEM_BUCKET_EVENT
        );
    }

}
