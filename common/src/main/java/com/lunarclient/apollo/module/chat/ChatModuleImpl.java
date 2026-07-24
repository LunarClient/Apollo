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
package com.lunarclient.apollo.module.chat;

import com.google.protobuf.Message;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.button.v1.ButtonUpdate;
import com.lunarclient.apollo.chat.v1.DisplayChatButtonsMessage;
import com.lunarclient.apollo.chat.v1.DisplayLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.RemoveChatButtonMessage;
import com.lunarclient.apollo.chat.v1.RemoveLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.ResetChatButtonsMessage;
import com.lunarclient.apollo.chat.v1.UpdateChatButtonMessage;
import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.event.packetenrichment.chat.ApolloPlayerChatCloseEvent;
import com.lunarclient.apollo.event.packetenrichment.chat.ApolloPlayerChatOpenEvent;
import com.lunarclient.apollo.event.player.ApolloUnregisterPlayerEvent;
import com.lunarclient.apollo.module.button.ButtonModuleSupport;
import com.lunarclient.apollo.module.button.ButtonSurface;
import com.lunarclient.apollo.module.packetenrichment.PacketEnrichmentModule;
import com.lunarclient.apollo.network.ButtonNetworkTypes;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

/**
 * Provides the chat module.
 *
 * @since 1.0.2
 */
public final class ChatModuleImpl extends ChatModule implements ButtonSurface<ChatButton, com.lunarclient.apollo.chat.v1.ChatButton> {

    private final ButtonModuleSupport<ChatButton, com.lunarclient.apollo.chat.v1.ChatButton> support =
        new ButtonModuleSupport<>(this, this, ChatModule.BROADCAST_LIVE_BUTTONS);

    /**
     * Creates a new instance of {@link ChatModuleImpl}.
     *
     * @since 1.2.9
     */
    public ChatModuleImpl() {
        super();
        this.handle(ApolloPlayerChatOpenEvent.class, event -> this.support.handleOpen(event.getPlayer()));
        this.handle(ApolloPlayerChatCloseEvent.class, event -> this.support.handleClose(event.getPlayer().getUniqueId()));
        this.handle(ApolloUnregisterPlayerEvent.class, event -> this.support.handleUnregister(event.getPlayer().getUniqueId()));
    }

    @Override
    protected void onEnable() {
        this.support.startBroadcast();
    }

    @Override
    public void displayLiveChatMessage(@NonNull Recipients recipients, @NonNull Component text, int messageId) {
        DisplayLiveChatMessageMessage message = DisplayLiveChatMessageMessage.newBuilder()
            .setAdventureJsonLines(ApolloComponent.toJson(text))
            .setMessageId(messageId)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void removeLiveChatMessage(@NonNull Recipients recipients, int messageId) {
        RemoveLiveChatMessageMessage message = RemoveLiveChatMessageMessage.newBuilder()
            .setMessageId(messageId)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void displayChatButtons(@NonNull Recipients recipients, @NonNull Collection<ChatButton> buttons) {
        if (buttons.size() > ChatButton.MAX_BUTTONS) {
            throw new IllegalArgumentException("ChatButton batches support at most " + ChatButton.MAX_BUTTONS + " buttons");
        }

        this.support.displayButtons(recipients, buttons);
    }

    @Override
    public void displayChatButton(@NonNull Recipients recipients, @NonNull ChatButton button) {
        this.support.displayButtons(recipients, Collections.singleton(button));
    }

    @Override
    public void removeChatButton(@NonNull Recipients recipients, @NonNull String buttonId) {
        this.support.removeButton(recipients, buttonId);
    }

    @Override
    public void removeChatButton(@NonNull Recipients recipients, @NonNull ChatButton button) {
        this.support.removeButton(recipients, button.getId());
    }

    @Override
    public void resetChatButtons(@NonNull Recipients recipients) {
        this.support.resetButtons(recipients);
    }

    @Override
    public void updateChatButton(@NonNull Recipients recipients, @NonNull String buttonId,
                                 @NonNull ApolloButtonContent content, @Nullable ApolloButtonTooltip tooltip) {
        this.support.pushUpdate(recipients, buttonId, content, true, tooltip);
    }

    @Override
    public void updateChatButtonContent(@NonNull Recipients recipients, @NonNull String buttonId, @NonNull ApolloButtonContent content) {
        this.support.pushUpdate(recipients, buttonId, content, false, null);
    }

    @Override
    public void updateChatButtonTooltip(@NonNull Recipients recipients, @NonNull String buttonId, @Nullable ApolloButtonTooltip tooltip) {
        this.support.pushUpdate(recipients, buttonId, null, true, tooltip);
    }

    @Override
    public void validate(ChatButton button) {
        ButtonModuleSupport.validateCommon(button, ChatButton.BOX_WIDTH, ChatButton.BOX_HEIGHT);
    }

    @Override
    public com.lunarclient.apollo.chat.v1.ChatButton toDisplayElement(ChatButton button, @Nullable ApolloPlayer viewer) {
        return com.lunarclient.apollo.chat.v1.ChatButton.newBuilder()
            .setButton(ButtonNetworkTypes.toProtobuf(button, viewer))
            .build();
    }

    @Override
    public Message createDisplay(List<com.lunarclient.apollo.chat.v1.ChatButton> elements) {
        return DisplayChatButtonsMessage.newBuilder()
            .addAllChatButtons(elements)
            .build();
    }

    @Override
    public Message createUpdate(String buttonId, ButtonUpdate update) {
        return UpdateChatButtonMessage.newBuilder()
            .setId(buttonId)
            .setUpdate(update)
            .build();
    }

    @Override
    public Message createRemove(String buttonId) {
        return RemoveChatButtonMessage.newBuilder()
            .setId(buttonId)
            .build();
    }

    @Override
    public Message createReset() {
        return ResetChatButtonsMessage.getDefaultInstance();
    }

    /**
     * Returns whether open-chat tracking is available.
     *
     * @return whether open-chat tracking is active
     */
    @Override
    public boolean isOpenTrackingActive() {
        PacketEnrichmentModule packetEnrichment = Apollo.getModuleManager().getModule(PacketEnrichmentModule.class);
        if (packetEnrichment == null || !packetEnrichment.isEnabled()) {
            return false;
        }

        Options options = packetEnrichment.getOptions();
        return options.get(PacketEnrichmentModule.PLAYER_CHAT_OPEN_PACKET)
            && options.get(PacketEnrichmentModule.PLAYER_CHAT_CLOSE_PACKET)
            && options.get(PacketEnrichmentModule.PLAYER_CHAT_OPEN_EVENT)
            && options.get(PacketEnrichmentModule.PLAYER_CHAT_CLOSE_EVENT);
    }

}
