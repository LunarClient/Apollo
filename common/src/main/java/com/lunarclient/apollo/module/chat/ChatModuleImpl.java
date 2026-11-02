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
import com.lunarclient.apollo.chat.v1.ChatPopupResponseMessage;
import com.lunarclient.apollo.chat.v1.DisplayChatButtonsMessage;
import com.lunarclient.apollo.chat.v1.DisplayChatPopupMessage;
import com.lunarclient.apollo.chat.v1.DisplayChatTabMessageMessage;
import com.lunarclient.apollo.chat.v1.DisplayChatTabsMessage;
import com.lunarclient.apollo.chat.v1.DisplayLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.RemoveChatButtonMessage;
import com.lunarclient.apollo.chat.v1.RemoveChatPopupMessage;
import com.lunarclient.apollo.chat.v1.RemoveChatTabMessage;
import com.lunarclient.apollo.chat.v1.RemoveLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.ResetChatButtonsMessage;
import com.lunarclient.apollo.chat.v1.ResetChatPopupsMessage;
import com.lunarclient.apollo.chat.v1.ResetChatTabsMessage;
import com.lunarclient.apollo.chat.v1.UpdateChatButtonMessage;
import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.chat.ApolloChatPopupResponseEvent;
import com.lunarclient.apollo.event.packetenrichment.chat.ApolloPlayerChatCloseEvent;
import com.lunarclient.apollo.event.packetenrichment.chat.ApolloPlayerChatOpenEvent;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.event.player.ApolloUnregisterPlayerEvent;
import com.lunarclient.apollo.module.button.ButtonModuleSupport;
import com.lunarclient.apollo.module.button.ButtonSurface;
import com.lunarclient.apollo.module.packetenrichment.PacketEnrichmentModule;
import com.lunarclient.apollo.network.ButtonNetworkTypes;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.option.config.Serializer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

/**
 * Provides the chat module.
 *
 * @since 1.0.2
 */
public final class ChatModuleImpl extends ChatModule implements ButtonSurface<ChatButton, com.lunarclient.apollo.chat.v1.ChatButton>, Serializer {

    private final ButtonModuleSupport<ChatButton, com.lunarclient.apollo.chat.v1.ChatButton> support =
        new ButtonModuleSupport<>(this, this, ChatModule.BROADCAST_LIVE_BUTTONS);

    /**
     * Creates a new instance of {@link ChatModuleImpl}.
     *
     * @since 1.2.9
     */
    public ChatModuleImpl() {
        super();
        this.serializer(ChatButton.class, new ChatButtonSerializer());
        this.serializer(ChatTab.class, new ChatTabSerializer());
        this.handle(ApolloRegisterPlayerEvent.class, this::onPlayerRegister);
        this.handle(ApolloRegisterPlayerEvent.class, this::sendServerTabs);
        this.handle(ApolloReceivePacketEvent.class, this::onChatPopupResponse);
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
    public void displayChatTabs(@NonNull Recipients recipients, @NonNull Collection<ChatTab> tabs) {
        if (tabs.size() > ChatTab.MAX_TABS) {
            throw new IllegalArgumentException("ChatTab batches support at most " + ChatTab.MAX_TABS + " tabs");
        }

        DisplayChatTabsMessage.Builder message = DisplayChatTabsMessage.newBuilder();
        for (ChatTab tab : tabs) {
            message.addChatTabs(this.toProtobuf(tab));
        }

        ApolloManager.getNetworkManager().sendPacket(recipients, message.build());
    }

    @Override
    public void displayChatTab(@NonNull Recipients recipients, @NonNull ChatTab tab) {
        this.displayChatTabs(recipients, Collections.singleton(tab));
    }

    @Override
    public void removeChatTab(@NonNull Recipients recipients, @NonNull String tabId) {
        RemoveChatTabMessage message = RemoveChatTabMessage.newBuilder()
            .setId(tabId)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void removeChatTab(@NonNull Recipients recipients, @NonNull ChatTab tab) {
        this.removeChatTab(recipients, tab.getId());
    }

    @Override
    public void resetChatTabs(@NonNull Recipients recipients) {
        ApolloManager.getNetworkManager().sendPacket(recipients, ResetChatTabsMessage.getDefaultInstance());
    }

    @Override
    public void displayChatTabMessage(@NonNull Recipients recipients, @NonNull String tabId,
                                      @NonNull Component text, int messageId) {
        DisplayChatTabMessageMessage message = DisplayChatTabMessageMessage.newBuilder()
            .setTabId(tabId)
            .setMessageId(messageId)
            .setAdventureJsonLines(ApolloComponent.toJson(text))
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void displayChatPopup(@NonNull Recipients recipients, @NonNull ChatPopup popup) {
        DisplayChatPopupMessage message = DisplayChatPopupMessage.newBuilder()
            .setChatPopup(this.toProtobuf(popup))
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void removeChatPopup(@NonNull Recipients recipients, @NonNull String popupId) {
        RemoveChatPopupMessage message = RemoveChatPopupMessage.newBuilder()
            .setId(popupId)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void removeChatPopup(@NonNull Recipients recipients, @NonNull ChatPopup popup) {
        this.removeChatPopup(recipients, popup.getId());
    }

    @Override
    public void resetChatPopups(@NonNull Recipients recipients) {
        ApolloManager.getNetworkManager().sendPacket(recipients, ResetChatPopupsMessage.getDefaultInstance());
    }

    private com.lunarclient.apollo.chat.v1.ChatTab toProtobuf(ChatTab tab) {
        if (tab.getId() == null || tab.getId().isEmpty()) {
            throw new IllegalArgumentException("ChatTab id must not be empty");
        }

        if (tab.getDisplayName() == null) {
            throw new IllegalArgumentException("ChatTab " + tab.getId() + " needs a display name");
        }

        if (tab.getPatterns().size() > ChatTab.MAX_PATTERNS) {
            throw new IllegalArgumentException("ChatTab " + tab.getId() + " has more than "
                + ChatTab.MAX_PATTERNS + " patterns");
        }

        for (String pattern : tab.getPatterns()) {
            if (pattern.length() > ChatTab.MAX_PATTERN_LENGTH) {
                throw new IllegalArgumentException("ChatTab " + tab.getId() + " has a pattern longer than "
                    + ChatTab.MAX_PATTERN_LENGTH + " characters");
            }

            try {
                Pattern.compile(pattern);
            } catch (PatternSyntaxException exception) {
                throw new IllegalArgumentException("ChatTab " + tab.getId() + " has an invalid pattern: "
                    + exception.getMessage());
            }
        }

        return com.lunarclient.apollo.chat.v1.ChatTab.newBuilder()
            .setId(tab.getId())
            .setDisplayNameAdventureJson(ApolloComponent.toJson(tab.getDisplayName()))
            .addAllPatterns(tab.getPatterns())
            .setInputPrefix(tab.getInputPrefix() == null ? "" : tab.getInputPrefix())
            .setHideFromAll(tab.isHideFromAll())
            .build();
    }

    private void sendServerTabs(ApolloRegisterPlayerEvent event) {
        if (!this.isEnabled() || !this.getOptions().get(ChatModule.SEND_SERVER_TABS)) {
            return;
        }

        ApolloPlayer player = event.getPlayer();
        List<ChatTab> tabs = this.getOptions().get(player, ChatModule.SERVER_TABS);
        if (tabs == null || tabs.isEmpty()) {
            return;
        }

        try {
            this.displayChatTabs(player, tabs);
        } catch (IllegalArgumentException exception) {
            Apollo.getPlatform().getPlatformLogger()
                .warning("Skipping the server tabs: " + exception.getMessage());
        }
    }

    private com.lunarclient.apollo.chat.v1.ChatPopup toProtobuf(ChatPopup popup) {
        if (popup.getId() == null || popup.getId().isEmpty()) {
            throw new IllegalArgumentException("ChatPopup id must not be empty");
        }

        if (popup.getTitle() == null) {
            throw new IllegalArgumentException("ChatPopup " + popup.getId() + " needs a title");
        }

        if (popup.getOptions().size() > ChatPopup.MAX_OPTIONS) {
            throw new IllegalArgumentException("ChatPopup " + popup.getId() + " has more than "
                + ChatPopup.MAX_OPTIONS + " options");
        }

        com.lunarclient.apollo.chat.v1.ChatPopup.Builder builder = com.lunarclient.apollo.chat.v1.ChatPopup.newBuilder()
            .setId(popup.getId())
            .setTitleAdventureJson(ApolloComponent.toJson(popup.getTitle()));

        Component description = popup.getDescription();
        if (description != null) {
            builder.setDescriptionAdventureJson(ApolloComponent.toJson(description));
        }

        Duration displayTime = popup.getDisplayTime();
        if (displayTime != null) {
            if (displayTime.isNegative() || displayTime.isZero()) {
                throw new IllegalArgumentException("ChatPopup " + popup.getId() + " needs a positive display time");
            }

            builder.setDisplayTime(NetworkTypes.toProtobuf(displayTime));
        }

        Set<String> optionIds = new HashSet<>();
        for (ChatPopupOption option : popup.getOptions()) {
            if (!optionIds.add(option.getId())) {
                throw new IllegalArgumentException("ChatPopup " + popup.getId()
                    + " has more than one option with the id " + option.getId());
            }

            builder.addOptions(this.toProtobuf(option, popup.getId()));
        }

        return builder.build();
    }

    private com.lunarclient.apollo.chat.v1.ChatPopupOption toProtobuf(ChatPopupOption option, String popupId) {
        if (option.getId() == null || option.getId().isEmpty()) {
            throw new IllegalArgumentException("ChatPopup " + popupId + " has an option without an id");
        }

        if (option.getLabel() == null) {
            throw new IllegalArgumentException("ChatPopup " + popupId + " needs a label on option " + option.getId());
        }

        return com.lunarclient.apollo.chat.v1.ChatPopupOption.newBuilder()
            .setId(option.getId())
            .setLabelAdventureJson(ApolloComponent.toJson(option.getLabel()))
            .build();
    }

    private void onChatPopupResponse(ApolloReceivePacketEvent event) {
        if (!this.isEnabled()) {
            return;
        }

        event.unpack(ChatPopupResponseMessage.class).ifPresent(packet -> {
            ApolloChatPopupResponseEvent chatPopupResponseEvent = new ApolloChatPopupResponseEvent(
                event.getPlayer(),
                packet.getPopupId(),
                packet.getOptionId().isEmpty() ? null : packet.getOptionId()
            );

            EventBus.EventResult<ApolloChatPopupResponseEvent> result = EventBus.getBus().post(chatPopupResponseEvent);

            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        });
    }

    private void onPlayerRegister(ApolloRegisterPlayerEvent event) {
        if (!this.isEnabled() || !this.getOptions().get(ChatModule.SEND_DEFAULT_BUTTONS)) {
            return;
        }

        ApolloPlayer player = event.getPlayer();
        List<ChatButton> buttons = this.getOptions().get(player, ChatModule.DEFAULT_BUTTONS);
        if (buttons == null || buttons.isEmpty()) {
            return;
        }

        try {
            this.support.displayButtons(player, buttons);
        } catch (IllegalArgumentException exception) {
            Apollo.getPlatform().getPlatformLogger()
                .warning("Skipping the default chat buttons: " + exception.getMessage());
        }
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
