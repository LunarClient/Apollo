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

import com.lunarclient.apollo.common.button.ApolloButtonShape;
import com.lunarclient.apollo.common.button.ApolloButtonSize;
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.action.ApolloButtonAction;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.location.HudPosition;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.ListOption;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.recipients.Recipients;
import io.leangen.geantyref.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the chat module.
 *
 * @since 1.0.2
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "chat", name = "Chat")
public abstract class ChatModule extends ApolloModule {

    /**
     * Controls whether live {@link ChatButton} content is automatically
     * re-resolved and re-sent to viewers.
     *
     * <p>When enabled without further setup, updates are always sent to
     * every player holding live buttons, even while their chat is closed.
     * Also enable the packet enrichment module and its player chat
     * open/close packets and events to only send updates to players who
     * currently have their chat open.</p>
     *
     * @since 1.2.9
     */
    public static final SimpleOption<Boolean> BROADCAST_LIVE_BUTTONS = Option.<Boolean>builder()
        .comment(
            "Set to 'true' to automatically re-send resolved live chat button content, otherwise 'false'.",
            "When enabled, updates are always sent, even to players whose chat is closed; also enable",
            "the packet enrichment module and its player chat open/close packets and events to only",
            "send updates to players who currently have their chat open."
        )
        .node("buttons", "live-broadcast").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    /**
     * Controls whether the {@link #DEFAULT_BUTTONS} are displayed to
     * players when they join.
     *
     * @since 1.2.9
     */
    public static final SimpleOption<Boolean> SEND_DEFAULT_BUTTONS = Option.<Boolean>builder()
        .comment("Set to 'true' to display the default buttons to players when they join, otherwise 'false'.")
        .node("buttons", "send-defaults").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    /**
     * The default {@link ChatButton}s displayed to joining players while
     * {@link #SEND_DEFAULT_BUTTONS} is enabled.
     *
     * @since 1.2.9
     */
    public static final ListOption<ChatButton> DEFAULT_BUTTONS = Option.<ChatButton>list()
        .comment(
            "Sets the default buttons to display to players when they join, while send-defaults is enabled.",
            "Text is read as legacy strings ('&'-color codes) and icons as icon definitions; live values",
            "are only available through the API."
        )
        .node("buttons", "defaults").type(new TypeToken<List<ChatButton>>() {})
        .defaultValue(ChatModule.createDefaultButtons())
        .build();

    /**
     * Controls whether the server's {@link ChatTab}s replace the client tabs,
     * the chat tabs that Lunar Client adds itself, like the party and private
     * message tabs. The "All" tab always stays.
     *
     * @since 1.3.1
     */
    public static final SimpleOption<Boolean> REPLACE_CLIENT_TABS = Option.<Boolean>builder()
        .comment(
            "Set to 'true' to hide the client tabs, the chat tabs that Lunar Client adds itself,",
            "except the 'All' tab, otherwise 'false'."
        )
        .node("tabs", "replace-client-tabs").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    /**
     * Controls whether the {@link #SERVER_TABS} are displayed to players when
     * they join. It doesn't affect the tabs that the API displays.
     *
     * @since 1.3.1
     */
    public static final SimpleOption<Boolean> SEND_SERVER_TABS = Option.<Boolean>builder()
        .comment(
            "Set to 'true' to display the tabs listed under server-tabs to players when they join, otherwise",
            "'false'. Tabs that a plugin displays through the API are sent either way."
        )
        .node("tabs", "send-server-tabs").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    /**
     * The {@link ChatTab}s displayed to joining players while
     * {@link #SEND_SERVER_TABS} is enabled.
     *
     * @since 1.3.1
     */
    public static final ListOption<ChatTab> SERVER_TABS = Option.<ChatTab>list()
        .comment(
            "Sets the chat tabs to display to players when they join, while send-server-tabs is enabled.",
            "Display names are read as legacy strings ('&'-color codes), and patterns as Java regular",
            "expressions that are matched against the plain text of each chat message."
        )
        .node("tabs", "server-tabs").type(new TypeToken<List<ChatTab>>() {})
        .defaultValue(ChatModule.createServerTabs())
        .build();

    protected ChatModule() {
        this.registerOptions(
            ChatModule.BROADCAST_LIVE_BUTTONS,
            ChatModule.SEND_DEFAULT_BUTTONS,
            ChatModule.DEFAULT_BUTTONS,
            ChatModule.REPLACE_CLIENT_TABS,
            ChatModule.SEND_SERVER_TABS,
            ChatModule.SERVER_TABS
        );
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Displays the message to the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param text       the text to display
     * @param messageId  the message id to update
     * @since 1.0.2
     */
    public abstract void displayLiveChatMessage(Recipients recipients, Component text, int messageId);

    /**
     * Removes the message from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param messageId  the message id to update
     * @since 1.0.2
     */
    public abstract void removeLiveChatMessage(Recipients recipients, int messageId);

    /**
     * Displays the {@link ChatButton}s to the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttons    the chat buttons
     * @since 1.2.9
     */
    public abstract void displayChatButtons(Recipients recipients, Collection<ChatButton> buttons);

    /**
     * Displays the {@link ChatButton} to the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param button     the chat button
     * @since 1.2.9
     */
    public abstract void displayChatButton(Recipients recipients, ChatButton button);

    /**
     * Removes the {@link ChatButton} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttonId   the chat button id
     * @since 1.2.9
     */
    public abstract void removeChatButton(Recipients recipients, String buttonId);

    /**
     * Removes the {@link ChatButton} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param button     the chat button
     * @since 1.2.9
     */
    public abstract void removeChatButton(Recipients recipients, ChatButton button);

    /**
     * Resets all {@link ChatButton}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.2.9
     */
    public abstract void resetChatButtons(Recipients recipients);

    /**
     * Updates the content and tooltip of a previously displayed
     * {@link ChatButton} for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttonId   the chat button id
     * @param content    the new button content
     * @param tooltip    the new tooltip, or {@code null} to clear the tooltip
     * @since 1.2.9
     */
    public abstract void updateChatButton(Recipients recipients, String buttonId,
                                          ApolloButtonContent content, @Nullable ApolloButtonTooltip tooltip);

    /**
     * Updates only the content of a previously displayed
     * {@link ChatButton} for the {@link Recipients}, keeping the
     * previous tooltip.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttonId   the chat button id
     * @param content    the new button content
     * @since 1.2.9
     */
    public abstract void updateChatButtonContent(Recipients recipients, String buttonId,
                                                 ApolloButtonContent content);

    /**
     * Updates only the tooltip of a previously displayed
     * {@link ChatButton} for the {@link Recipients}, keeping the
     * previous content.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttonId   the chat button id
     * @param tooltip    the new tooltip, or {@code null} to clear the tooltip
     * @since 1.2.9
     */
    public abstract void updateChatButtonTooltip(Recipients recipients, String buttonId,
                                                 @Nullable ApolloButtonTooltip tooltip);

    /**
     * Displays the {@link ChatTab}s to the {@link Recipients}, replacing a
     * displayed tab with the same id.
     *
     * @param recipients the recipients that are receiving the packet
     * @param tabs       the chat tabs
     * @since 1.3.1
     */
    public abstract void displayChatTabs(Recipients recipients, Collection<ChatTab> tabs);

    /**
     * Displays the {@link ChatTab} to the {@link Recipients}, replacing a
     * displayed tab with the same id.
     *
     * @param recipients the recipients that are receiving the packet
     * @param tab        the chat tab
     * @since 1.3.1
     */
    public abstract void displayChatTab(Recipients recipients, ChatTab tab);

    /**
     * Removes the {@link ChatTab} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param tabId      the chat tab id
     * @since 1.3.1
     */
    public abstract void removeChatTab(Recipients recipients, String tabId);

    /**
     * Removes the {@link ChatTab} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param tab        the chat tab
     * @since 1.3.1
     */
    public abstract void removeChatTab(Recipients recipients, ChatTab tab);

    /**
     * Resets all {@link ChatTab}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.3.1
     */
    public abstract void resetChatTabs(Recipients recipients);

    /**
     * Displays the message in a {@link ChatTab} of the {@link Recipients},
     * without matching it against the tab patterns. The message doesn't show
     * in the client's "All" tab.
     *
     * <p>The message id works like the one of
     * {@link #displayLiveChatMessage(Recipients, Component, int)}, so the same
     * id updates the message and {@link #removeLiveChatMessage(Recipients, int)}
     * removes it. A client without the tab, or with chat tabs turned off,
     * shows the message in the normal chat.
     * Lunar Client versions from before chat tabs don't show the message at
     * all.</p>
     *
     * @param recipients the recipients that are receiving the packet
     * @param tabId      the chat tab id
     * @param text       the text to display
     * @param messageId  the message id to update
     * @since 1.3.1
     */
    public abstract void displayChatTabMessage(Recipients recipients, String tabId, Component text, int messageId);

    /**
     * Displays the {@link ChatPopup} to the {@link Recipients}, replacing a
     * displayed popup with the same id.
     *
     * @param recipients the recipients that are receiving the packet
     * @param popup      the chat popup
     * @since 1.3.1
     */
    public abstract void displayChatPopup(Recipients recipients, ChatPopup popup);

    /**
     * Removes the {@link ChatPopup} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param popupId    the chat popup id
     * @since 1.3.1
     */
    public abstract void removeChatPopup(Recipients recipients, String popupId);

    /**
     * Removes the {@link ChatPopup} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param popup      the chat popup
     * @since 1.3.1
     */
    public abstract void removeChatPopup(Recipients recipients, ChatPopup popup);

    /**
     * Resets all {@link ChatPopup}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.3.1
     */
    public abstract void resetChatPopups(Recipients recipients);

    private static List<ChatTab> createServerTabs() {
        ChatTab teamChat = ChatTab.builder()
            .id("team")
            .displayName(Component.text("Team", NamedTextColor.GREEN))
            .pattern("^\\[Team\\] ")
            .inputPrefix("/tc ")
            .build();

        return new ArrayList<>(Collections.singletonList(teamChat));
    }

    private static List<ChatButton> createDefaultButtons() {
        ChatButton teamChat = ChatButton.builder()
            .id("team-chat")
            .position(HudPosition.of(0, 2))
            .size(ApolloButtonSize.of(70, 16))
            .shape(ApolloButtonShape.ROUNDED_SQUARE)
            .content(ApolloButtonContent.builder()
                .append(ItemStackIcon.builder().itemName("SHIELD").build())
                .append(Component.text("Team Chat", NamedTextColor.GREEN))
                .build())
            .tooltip(ApolloButtonTooltip.of(Component.text("Click to switch!", NamedTextColor.YELLOW)))
            .onClick(ApolloButtonAction.runCommand("/channel team"))
            .build();

        ChatButton publicChat = ChatButton.builder()
            .id("public-chat")
            .position(HudPosition.of(76, 2))
            .size(ApolloButtonSize.of(78, 16))
            .shape(ApolloButtonShape.ROUNDED_SQUARE)
            .content(ApolloButtonContent.builder()
                .append(ItemStackIcon.builder().itemName("OAK_SIGN").build())
                .append(Component.text("Public Chat"))
                .build())
            .tooltip(ApolloButtonTooltip.of(Component.text("Click to switch!", NamedTextColor.YELLOW)))
            .onClick(ApolloButtonAction.runCommand("/channel public"))
            .build();

        ChatButton partyChat = ChatButton.builder()
            .id("party-chat")
            .position(HudPosition.of(160, 2))
            .size(ApolloButtonSize.of(76, 16))
            .shape(ApolloButtonShape.ROUNDED_SQUARE)
            .content(ApolloButtonContent.builder()
                .append(ItemStackIcon.builder().itemName("FIREWORK_ROCKET").build())
                .append(Component.text("Party Chat", NamedTextColor.LIGHT_PURPLE))
                .build())
            .tooltip(ApolloButtonTooltip.of(Component.text("Click to switch!", NamedTextColor.YELLOW)))
            .onClick(ApolloButtonAction.runCommand("/channel party"))
            .build();

        return new ArrayList<>(Arrays.asList(teamChat, publicChat, partyChat));
    }

}
