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
import com.lunarclient.apollo.button.v1.ButtonTooltip;
import com.lunarclient.apollo.button.v1.ButtonUpdate;
import com.lunarclient.apollo.chat.v1.DisplayChatTabsMessage;
import com.lunarclient.apollo.chat.v1.RemoveChatTabMessage;
import com.lunarclient.apollo.chat.v1.ResetChatTabsMessage;
import com.lunarclient.apollo.chat.v1.UpdateChatTabMessage;
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.action.SelectChatTabAction;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.common.pattern.TextPattern;
import com.lunarclient.apollo.common.pattern.TextPatternType;
import com.lunarclient.apollo.module.button.ButtonModuleSupport;
import com.lunarclient.apollo.module.button.ButtonSurface;
import com.lunarclient.apollo.network.ButtonNetworkTypes;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.awt.Color;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.jetbrains.annotations.Nullable;

/**
 * The {@link ButtonSurface} of the {@link ChatTab}s, so that their live
 * display names and tooltips are re-sent like the ones of chat buttons.
 *
 * @since 1.3.1
 */
public final class ChatTabSurface implements ButtonSurface<ChatTab, com.lunarclient.apollo.chat.v1.ChatTab> {

    @Override
    public String getId(ChatTab tab) {
        return tab.getId();
    }

    @Override
    public ApolloButtonContent getContent(ChatTab tab) {
        return tab.getDisplayName();
    }

    @Override
    public @Nullable ApolloButtonTooltip getTooltip(ChatTab tab) {
        return tab.getTooltip();
    }

    @Override
    public void validate(ChatTab tab) {
        ButtonModuleSupport.requireSet(tab.getId(), "ChatTab#id");
        ButtonModuleSupport.requireSet(tab.getDisplayName(), "ChatTab#displayName");
        ButtonModuleSupport.requireSet(tab.getPatterns(), "ChatTab#patterns");
        ButtonModuleSupport.requireSet(tab.getInputPrefix(), "ChatTab#inputPrefix");

        if (tab.getId().isEmpty()) {
            throw new IllegalArgumentException("ChatTab#id must not be empty");
        }

        if (tab.getId().equals(SelectChatTabAction.ALL_TAB_ID)) {
            throw new IllegalArgumentException("ChatTab#id must not be '" + SelectChatTabAction.ALL_TAB_ID + "'");
        }

        if (tab.getPatterns().size() > ChatTab.MAX_PATTERNS) {
            throw new IllegalArgumentException("ChatTab#patterns supports at most " + ChatTab.MAX_PATTERNS + " patterns");
        }

        for (TextPattern pattern : tab.getPatterns()) {
            ButtonModuleSupport.requireSet(pattern, "ChatTab#patterns entry");

            String value = pattern.getValue();

            // an empty pattern matches every message, and with hideFromAll it would empty the "All" tab
            if (value.isEmpty()) {
                throw new IllegalArgumentException("TextPattern#value must not be empty");
            }

            if (value.length() > ChatTab.MAX_PATTERN_LENGTH) {
                throw new IllegalArgumentException("TextPattern#value must be at most " + ChatTab.MAX_PATTERN_LENGTH + " characters");
            }

            if (pattern.getType() == TextPatternType.REGEX) {
                try {
                    Pattern.compile(value);
                } catch (PatternSyntaxException exception) {
                    throw new IllegalArgumentException("TextPattern#value is not a valid regex: " + exception.getMessage());
                }
            }
        }
    }

    @Override
    public com.lunarclient.apollo.chat.v1.ChatTab toDisplayElement(ChatTab tab, @Nullable ApolloPlayer viewer) {
        com.lunarclient.apollo.chat.v1.ChatTab.Builder builder = com.lunarclient.apollo.chat.v1.ChatTab.newBuilder()
            .setId(tab.getId())
            .setDisplayName(ButtonNetworkTypes.toContentProtobuf(tab.getDisplayName(), viewer))
            .setInputPrefix(tab.getInputPrefix())
            .setHideFromAll(tab.isHideFromAll());

        for (TextPattern pattern : tab.getPatterns()) {
            builder.addPatterns(NetworkTypes.toProtobuf(pattern));
        }

        ButtonTooltip tooltip = ButtonNetworkTypes.toTooltipProtobuf(tab.getTooltip(), viewer);
        if (tooltip != null) {
            builder.setTooltip(tooltip);
        }

        Color backgroundColor = tab.getBackgroundColor();
        if (backgroundColor != null) {
            builder.setBackgroundColor(NetworkTypes.toProtobuf(backgroundColor));
        }

        return builder.build();
    }

    @Override
    public Message createDisplay(List<com.lunarclient.apollo.chat.v1.ChatTab> elements) {
        return DisplayChatTabsMessage.newBuilder()
            .addAllChatTabs(elements)
            .build();
    }

    @Override
    public Message createUpdate(String tabId, ButtonUpdate update) {
        return UpdateChatTabMessage.newBuilder()
            .setId(tabId)
            .setUpdate(update)
            .build();
    }

    @Override
    public Message createRemove(String tabId) {
        return RemoveChatTabMessage.newBuilder()
            .setId(tabId)
            .build();
    }

    @Override
    public Message createReset() {
        return ResetChatTabsMessage.getDefaultInstance();
    }

    @Override
    public boolean isOpenTrackingActive() {
        // the client also shows the selected tab's name while the chat is closed, so live tabs always get updates
        return false;
    }

}
