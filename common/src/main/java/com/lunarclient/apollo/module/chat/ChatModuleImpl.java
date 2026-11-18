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
import com.lunarclient.apollo.chat.v1.DisplayLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.RemoveChatButtonMessage;
import com.lunarclient.apollo.chat.v1.RemoveChatPopupMessage;
import com.lunarclient.apollo.chat.v1.RemoveLiveChatMessageMessage;
import com.lunarclient.apollo.chat.v1.ResetChatButtonsMessage;
import com.lunarclient.apollo.chat.v1.ResetChatPopupsMessage;
import com.lunarclient.apollo.chat.v1.UpdateChatButtonMessage;
import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.action.ApolloButtonAction;
import com.lunarclient.apollo.common.button.action.ClientAction;
import com.lunarclient.apollo.common.button.action.OpenUrlAction;
import com.lunarclient.apollo.common.button.action.RunCommandAction;
import com.lunarclient.apollo.common.button.action.SelectChatTabAction;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
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

    private final ChatTabSurface tabSurface = new ChatTabSurface();
    private final ButtonModuleSupport<ChatTab, com.lunarclient.apollo.chat.v1.ChatTab> tabSupport =
        new ButtonModuleSupport<>(this, this.tabSurface, ChatModule.BROADCAST_LIVE_TABS);

    private final Map<UUID, Set<String>> displayedTabIds = new ConcurrentHashMap<>();
    private final Map<UUID, Map<String, ChatPopup>> displayedPopups = new ConcurrentHashMap<>();

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
        this.handle(ApolloReceivePacketEvent.class, this::onChatPopupResponse);
        this.handle(ApolloPlayerChatOpenEvent.class, this::onPlayerChatOpen);
        this.handle(ApolloPlayerChatCloseEvent.class, this::onPlayerChatClose);
        this.handle(ApolloUnregisterPlayerEvent.class, this::onPlayerUnregister);
    }

    @Override
    protected void onEnable() {
        this.support.startBroadcast();
        this.tabSupport.startBroadcast();
    }

    @Override
    protected void onDisable() {
        // the client drops its tabs and popups when the module is disabled
        this.displayedTabIds.clear();
        this.displayedPopups.clear();
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
        if (tabs.isEmpty()) {
            throw new IllegalArgumentException("ChatTab collection must not be empty");
        }

        if (tabs.size() > ChatTab.MAX_TABS) {
            throw new IllegalArgumentException("ChatTab batches support at most " + ChatTab.MAX_TABS + " tabs");
        }

        Set<String> ids = new HashSet<>(tabs.size());
        for (ChatTab tab : tabs) {
            this.tabSurface.validate(tab);

            if (!ids.add(tab.getId())) {
                throw new IllegalArgumentException("Duplicate chat tab id '" + tab.getId() + "' in display batch");
            }
        }

        List<ApolloPlayer> accepted = new ArrayList<>();
        List<String> skipped = new ArrayList<>();
        recipients.forEach(recipient -> {
            ApolloPlayer player = (ApolloPlayer) recipient;
            if (this.trackTabIds(player, ids)) {
                accepted.add(player);
            } else {
                skipped.add(player.getName());
            }
        });

        if (!skipped.isEmpty()) {
            String message = "Players " + skipped + " would have more than " + ChatTab.MAX_TABS + " chat tabs";

            // a call for one player gets the error, because it has no other player to send to
            if (recipients instanceof ApolloPlayer) {
                throw new IllegalArgumentException(message);
            }

            Apollo.getPlatform().getPlatformLogger().warning("Skipping chat tabs: " + message);
        }

        if (!accepted.isEmpty()) {
            this.tabSupport.displayButtons(Recipients.of(accepted), tabs);
        }
    }

    private boolean trackTabIds(ApolloPlayer player, Set<String> ids) {
        boolean[] tracked = {false};

        // compute runs atomically for the player's entry, so the check and the add can't interleave with another
        // display call, or with a reset or remove that would otherwise leave the ids in a set that's no longer tracked
        this.displayedTabIds.compute(player.getUniqueId(), (uuid, displayed) -> {
            Set<String> tabIds = displayed != null ? displayed : ConcurrentHashMap.newKeySet();
            int total = ids.size();
            for (String id : tabIds) {
                if (!ids.contains(id)) {
                    total++;
                }
            }

            if (total > ChatTab.MAX_TABS) {
                return displayed;
            }

            tabIds.addAll(ids);
            tracked[0] = true;
            return tabIds;
        });
        return tracked[0];
    }

    @Override
    public void displayChatTab(@NonNull Recipients recipients, @NonNull ChatTab tab) {
        this.displayChatTabs(recipients, Collections.singleton(tab));
    }

    @Override
    public void removeChatTab(@NonNull Recipients recipients, @NonNull String tabId) {
        this.tabSupport.removeButton(recipients, tabId);

        recipients.forEach(recipient -> this.displayedTabIds.computeIfPresent(
            ((ApolloPlayer) recipient).getUniqueId(),
            (uuid, displayed) -> {
                displayed.remove(tabId);
                return displayed;
            }
        ));
    }

    @Override
    public void removeChatTab(@NonNull Recipients recipients, @NonNull ChatTab tab) {
        this.removeChatTab(recipients, tab.getId());
    }

    @Override
    public void resetChatTabs(@NonNull Recipients recipients) {
        this.tabSupport.resetButtons(recipients);
        recipients.forEach(recipient -> this.displayedTabIds.remove(((ApolloPlayer) recipient).getUniqueId()));
    }

    @Override
    public void updateChatTab(@NonNull Recipients recipients, @NonNull String tabId,
                              @NonNull ApolloButtonContent content, @Nullable ApolloButtonTooltip tooltip) {
        this.pushTabUpdate(recipients, tabId, content, true, tooltip);
    }

    @Override
    public void updateChatTabContent(@NonNull Recipients recipients, @NonNull String tabId, @NonNull ApolloButtonContent content) {
        this.pushTabUpdate(recipients, tabId, content, false, null);
    }

    @Override
    public void updateChatTabTooltip(@NonNull Recipients recipients, @NonNull String tabId, @Nullable ApolloButtonTooltip tooltip) {
        this.pushTabUpdate(recipients, tabId, null, true, tooltip);
    }

    private void pushTabUpdate(Recipients recipients, String tabId, @Nullable ApolloButtonContent content,
                               boolean updateTooltip, @Nullable ApolloButtonTooltip tooltip) {
        if (tabId.isEmpty()) {
            throw new IllegalArgumentException("ChatTab#id must not be empty");
        }

        this.tabSupport.pushUpdate(recipients, tabId, content, updateTooltip, tooltip);
    }

    @Override
    public void displayChatTabMessage(@NonNull Recipients recipients, @NonNull String tabId,
                                      @NonNull Component text, int messageId) {
        if (tabId.isEmpty()) {
            throw new IllegalArgumentException("ChatTab#id must not be empty");
        }

        DisplayLiveChatMessageMessage message = DisplayLiveChatMessageMessage.newBuilder()
            .setMessageId(messageId)
            .setAdventureJsonLines(ApolloComponent.toJson(text))
            .setTabId(tabId)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    @Override
    public void displayChatPopup(@NonNull Recipients recipients, @NonNull ChatPopup popup) {
        this.validatePopup(popup);

        DisplayChatPopupMessage message = DisplayChatPopupMessage.newBuilder()
            .setChatPopup(this.toProtobuf(popup))
            .build();

        // the recipients are resolved once, so that only the players who get the popup are tracked,
        // and the tracking comes first, because a response that arrives before it would be ignored
        List<ApolloPlayer> players = new ArrayList<>();
        recipients.forEach(recipient -> {
            ApolloPlayer player = (ApolloPlayer) recipient;
            this.displayedPopups
                .computeIfAbsent(player.getUniqueId(), uuid -> this.createPopupTracking())
                .put(popup.getId(), popup);
            players.add(player);
        });

        ApolloManager.getNetworkManager().sendPacket(Recipients.of(players), message);
    }

    private Map<String, ChatPopup> createPopupTracking() {
        // the client sends a STATUS_DROPPED response for every popup it drops over ChatPopup.MAX_POPUPS,
        // which keeps the tracking in step with it.
        // This looser cap only bounds the tracking for clients that never respond, like versions from before chat
        // popups, because a cap of MAX_POPUPS would forget the oldest popup while the player can still answer it
        return Collections.synchronizedMap(new LinkedHashMap<String, ChatPopup>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, ChatPopup> eldest) {
                return this.size() > 64;
            }
        });
    }

    @Override
    public void removeChatPopup(@NonNull Recipients recipients, @NonNull String popupId) {
        RemoveChatPopupMessage message = RemoveChatPopupMessage.newBuilder()
            .setId(popupId)
            .build();

        ApolloManager.getNetworkManager().sendPacket(recipients, message);

        recipients.forEach(recipient -> {
            Map<String, ChatPopup> displayed = this.displayedPopups.get(((ApolloPlayer) recipient).getUniqueId());
            if (displayed != null) {
                displayed.remove(popupId);
            }
        });
    }

    @Override
    public void removeChatPopup(@NonNull Recipients recipients, @NonNull ChatPopup popup) {
        this.removeChatPopup(recipients, popup.getId());
    }

    @Override
    public void resetChatPopups(@NonNull Recipients recipients) {
        ApolloManager.getNetworkManager().sendPacket(recipients, ResetChatPopupsMessage.getDefaultInstance());
        recipients.forEach(recipient -> this.displayedPopups.remove(((ApolloPlayer) recipient).getUniqueId()));
    }

    private void sendDefaultTabs(ApolloRegisterPlayerEvent event) {
        if (!this.isEnabled() || !this.getOptions().get(ChatModule.SEND_DEFAULT_TABS)) {
            return;
        }

        ApolloPlayer player = event.getPlayer();
        List<ChatTab> tabs = this.getOptions().get(player, ChatModule.DEFAULT_TABS);
        if (tabs == null || tabs.isEmpty()) {
            return;
        }

        try {
            this.displayChatTabs(player, tabs);
        } catch (IllegalArgumentException exception) {
            Apollo.getPlatform().getPlatformLogger()
                .warning("Skipping the default chat tabs: " + exception.getMessage());
        }
    }

    private void validatePopup(ChatPopup popup) {
        ButtonModuleSupport.requireSet(popup.getId(), "ChatPopup#id");
        ButtonModuleSupport.requireSet(popup.getTitle(), "ChatPopup#title");
        ButtonModuleSupport.requireSet(popup.getOptions(), "ChatPopup#options");

        if (popup.getId().isEmpty()) {
            throw new IllegalArgumentException("ChatPopup#id must not be empty");
        }

        if (popup.getOptions().size() > ChatPopup.MAX_OPTIONS) {
            throw new IllegalArgumentException("ChatPopup#options supports at most " + ChatPopup.MAX_OPTIONS + " options");
        }

        Duration displayTime = popup.getDisplayTime();
        if (displayTime != null && (displayTime.isNegative() || displayTime.isZero())) {
            throw new IllegalArgumentException("ChatPopup#displayTime must be greater than 0");
        }

        // the client counts the display time in milliseconds, and treats a longer one as no display time
        if (displayTime != null && displayTime.getSeconds() >= Long.MAX_VALUE / 1000) {
            throw new IllegalArgumentException("ChatPopup#displayTime is too long, leave it out for no display time");
        }

        Set<String> optionIds = new HashSet<>(popup.getOptions().size());
        for (ChatPopupOption option : popup.getOptions()) {
            ButtonModuleSupport.requireSet(option, "ChatPopup#options entry");
            ButtonModuleSupport.requireSet(option.getId(), "ChatPopupOption#id");
            ButtonModuleSupport.requireSet(option.getLabel(), "ChatPopupOption#label");

            if (option.getId().isEmpty()) {
                throw new IllegalArgumentException("ChatPopupOption#id must not be empty");
            }

            if (!optionIds.add(option.getId())) {
                throw new IllegalArgumentException("Duplicate chat popup option id '" + option.getId() + "' in popup");
            }

            ButtonModuleSupport.validateAction(option.getOnClick());
        }
    }

    private com.lunarclient.apollo.chat.v1.ChatPopup toProtobuf(ChatPopup popup) {
        com.lunarclient.apollo.chat.v1.ChatPopup.Builder builder = com.lunarclient.apollo.chat.v1.ChatPopup.newBuilder()
            .setId(popup.getId())
            .setTitleAdventureJsonLines(ApolloComponent.toJson(popup.getTitle()));

        Component description = popup.getDescription();
        if (description != null) {
            builder.setDescriptionAdventureJsonLines(ApolloComponent.toJson(description));
        }

        Duration displayTime = popup.getDisplayTime();
        if (displayTime != null) {
            builder.setDisplayTime(NetworkTypes.toProtobuf(displayTime));
        }

        for (ChatPopupOption option : popup.getOptions()) {
            builder.addOptions(this.toProtobuf(option));
        }

        return builder.build();
    }

    private com.lunarclient.apollo.chat.v1.ChatPopupOption toProtobuf(ChatPopupOption option) {
        com.lunarclient.apollo.chat.v1.ChatPopupOption.Builder builder = com.lunarclient.apollo.chat.v1.ChatPopupOption.newBuilder()
            .setId(option.getId())
            .setLabelAdventureJsonLines(ApolloComponent.toJson(option.getLabel()));

        ApolloButtonAction onClick = option.getOnClick();
        if (onClick instanceof RunCommandAction) {
            builder.setRunCommand(((RunCommandAction) onClick).getCommand());
        } else if (onClick instanceof OpenUrlAction) {
            builder.setOpenUrl(((OpenUrlAction) onClick).getUrl());
        } else if (onClick instanceof ClientAction) {
            builder.setClientAction(ButtonNetworkTypes.toClientActionProtobuf((ClientAction) onClick));
        } else if (onClick instanceof SelectChatTabAction) {
            builder.setSelectChatTab(ButtonNetworkTypes.toSelectChatTabProtobuf((SelectChatTabAction) onClick));
        } else if (onClick != null) {
            throw new IllegalArgumentException("Unknown button action type: " + onClick.getClass().getName());
        }

        return builder.build();
    }

    private void onChatPopupResponse(ApolloReceivePacketEvent event) {
        if (!this.isEnabled()) {
            return;
        }

        event.unpack(ChatPopupResponseMessage.class).ifPresent(packet -> {
            ApolloPlayer player = event.getPlayer();

            // the client already closed the popup, so it stays untracked even when the response is invalid
            Map<String, ChatPopup> displayed = this.displayedPopups.get(player.getUniqueId());
            ChatPopup popup = displayed != null ? displayed.remove(packet.getPopupId()) : null;
            if (popup == null) {
                return;
            }

            ApolloChatPopupResponseEvent.Status[] statuses = ApolloChatPopupResponseEvent.Status.values();
            int statusValue = packet.getStatusValue();
            if (statusValue < 1 || statusValue > statuses.length) {
                return;
            }

            ApolloChatPopupResponseEvent.Status status = statuses[statusValue - 1];
            String optionId = null;
            if (status == ApolloChatPopupResponseEvent.Status.PICKED) {
                optionId = packet.getOptionId();
                if (!this.hasOption(popup, optionId)) {
                    return;
                }
            }

            ApolloChatPopupResponseEvent chatPopupResponseEvent = new ApolloChatPopupResponseEvent(
                player,
                popup.getId(),
                popup,
                status,
                optionId
            );

            EventBus.EventResult<ApolloChatPopupResponseEvent> result = EventBus.getBus().post(chatPopupResponseEvent);

            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        });
    }

    private boolean hasOption(ChatPopup popup, String optionId) {
        for (ChatPopupOption option : popup.getOptions()) {
            if (option.getId().equals(optionId)) {
                return true;
            }
        }

        return false;
    }

    private void onPlayerChatOpen(ApolloPlayerChatOpenEvent event) {
        this.support.handleOpen(event.getPlayer());
        this.tabSupport.handleOpen(event.getPlayer());
    }

    private void onPlayerChatClose(ApolloPlayerChatCloseEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();
        this.support.handleClose(playerId);
        this.tabSupport.handleClose(playerId);
    }

    private void onPlayerUnregister(ApolloUnregisterPlayerEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();
        this.support.handleUnregister(playerId);
        this.tabSupport.handleUnregister(playerId);
        this.displayedTabIds.remove(playerId);
        this.displayedPopups.remove(playerId);
    }

    private void onPlayerRegister(ApolloRegisterPlayerEvent event) {
        // a display that runs after the player quit tracks them again, and a new client starts without tabs or popups
        UUID playerId = event.getPlayer().getUniqueId();
        this.displayedTabIds.remove(playerId);
        this.displayedPopups.remove(playerId);
        // after the clear, because the default tabs are tracked too
        this.sendDefaultTabs(event);

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
    public String getId(ChatButton button) {
        return button.getId();
    }

    @Override
    public ApolloButtonContent getContent(ChatButton button) {
        return button.getContent();
    }

    @Override
    public @Nullable ApolloButtonTooltip getTooltip(ChatButton button) {
        return button.getTooltip();
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
