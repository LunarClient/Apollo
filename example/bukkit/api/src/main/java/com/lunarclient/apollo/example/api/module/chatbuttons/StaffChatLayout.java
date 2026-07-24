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
package com.lunarclient.apollo.example.api.module.chatbuttons;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.button.ApolloButtonShape;
import com.lunarclient.apollo.common.button.ApolloButtonSize;
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.action.ApolloButtonAction;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.location.HudPosition;
import com.lunarclient.apollo.module.chat.ChatButton;
import com.lunarclient.apollo.module.chat.ChatModule;
import java.util.Arrays;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public final class StaffChatLayout {

    public static void display(ChatModule chatModule, Player viewer) {
        Apollo.getPlayerManager().getPlayer(viewer.getUniqueId()).ifPresent(apolloPlayer -> {
            ChatButton staffChat = ChatButton.builder()
                .id("staff-chat")
                .position(HudPosition.of(0, 2))
                .size(ApolloButtonSize.of(70, 16))
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(ChatButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(ChatButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("COMMAND_BLOCK")
                        .build())
                    .append(Component.text("Staff Chat", NamedTextColor.AQUA))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Click to switch!", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/channel staff"))
                .build();

            ChatButton publicChat = ChatButton.builder()
                .id("public-chat")
                .position(HudPosition.of(76, 2))
                .size(ApolloButtonSize.of(78, 16))
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(ChatButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(ChatButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("OAK_SIGN")
                        .build())
                    .append(Component.text("Public Chat"))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Click to switch!", NamedTextColor.YELLOW)))
                .onClick(ApolloButtonAction.runCommand("/channel public"))
                .build();

            ChatButton clearChat = ChatButton.builder()
                .id("clear-chat")
                .position(HudPosition.of(160, 2))
                .size(ApolloButtonSize.of(44, 16))
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(ChatButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(ChatButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("SPONGE")
                        .build())
                    .append(Component.text("Clear", NamedTextColor.YELLOW))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Clears the public chat", NamedTextColor.GRAY)))
                .onClick(ApolloButtonAction.runCommand("/clearchat"))
                .build();

            ChatButton muteChat = ChatButton.builder()
                .id("mute-chat")
                .position(HudPosition.of(210, 2))
                .size(ApolloButtonSize.of(44, 16))
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(ChatButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(ChatButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("BARRIER")
                        .build())
                    .append(Component.text("Mute", NamedTextColor.RED))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Mutes the public chat", NamedTextColor.GRAY)))
                .onClick(ApolloButtonAction.runCommand("/mutechat"))
                .build();

            ChatButton unmuteChat = ChatButton.builder()
                .id("unmute-chat")
                .position(HudPosition.of(260, 2))
                .size(ApolloButtonSize.of(56, 16))
                .shape(ApolloButtonShape.ROUNDED_SQUARE)
                .backgroundColor(ChatButton.DEFAULT_BACKGROUND_COLOR)
                .borderColor(ChatButton.DEFAULT_BORDER_COLOR)
                .content(ApolloButtonContent.builder()
                    .append(ItemStackIcon.builder()
                        .itemName("BELL")
                        .build())
                    .append(Component.text("Unmute", NamedTextColor.GREEN))
                    .scale(1.0F)
                    .build())
                .tooltip(ApolloButtonTooltip.of(Component.text("Unmutes the public chat", NamedTextColor.GRAY)))
                .onClick(ApolloButtonAction.runCommand("/unmutechat"))
                .build();

            chatModule.displayChatButtons(apolloPlayer, Arrays.asList(staffChat, publicChat,
                clearChat, muteChat, unmuteChat));
        });
    }

    private StaffChatLayout() {
    }

}
