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
package com.lunarclient.apollo.example.api.module;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.chat.ApolloChatPopupResponseEvent;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.api.module.chatbuttons.ChannelsLayout;
import com.lunarclient.apollo.example.api.module.chatbuttons.StaffChatLayout;
import com.lunarclient.apollo.example.module.impl.ChatExample;
import com.lunarclient.apollo.example.util.ServerUtil;
import com.lunarclient.apollo.module.chat.ChatModule;
import com.lunarclient.apollo.module.chat.ChatPopup;
import com.lunarclient.apollo.module.chat.ChatPopupOption;
import com.lunarclient.apollo.module.chat.ChatTab;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatApiExample extends ChatExample implements ApolloListener {

    private final ChatModule chatModule = Apollo.getModuleManager().getModule(ChatModule.class);

    public ChatApiExample() {
        EventBus.getBus().register(this);
    }

    @Override
    public void displayLiveChatMessageExample() {
        if (ServerUtil.isFolia()) {
            this.runFoliaChatMessageTask();
        } else {
            this.runBukkitChatMessageTask();
        }
    }

    private void runBukkitChatMessageTask() {
        BukkitRunnable runnable = new BukkitRunnable() {

            private int countdown = 5;

            @Override
            public void run() {
                if (this.countdown > 0) {
                    ChatApiExample.this.chatModule.displayLiveChatMessage(Recipients.ofEveryone(),
                        Component.text("Game starting in ", NamedTextColor.GREEN)
                            .append(Component.text(this.countdown, NamedTextColor.BLUE)),
                        13
                    );

                    this.countdown--;
                } else {
                    ChatApiExample.this.chatModule.displayLiveChatMessage(Recipients.ofEveryone(),
                        Component.text("Game started! ", NamedTextColor.GREEN),
                        13
                    );

                    this.cancel();
                }
            }
        };

        runnable.runTaskTimer(ApolloExamplePlugin.getInstance(), 1L, 20L);
    }

    private void runFoliaChatMessageTask() {
        AtomicInteger countdown = new AtomicInteger(5);

        Bukkit.getGlobalRegionScheduler().runAtFixedRate(ApolloExamplePlugin.getInstance(), task -> {
            int seconds = countdown.getAndDecrement();
            if (seconds > 0) {
                ChatApiExample.this.chatModule.displayLiveChatMessage(Recipients.ofEveryone(),
                    Component.text("Game starting in ", NamedTextColor.GREEN)
                        .append(Component.text(seconds, NamedTextColor.BLUE)),
                    13
                );
            } else {
                ChatApiExample.this.chatModule.displayLiveChatMessage(Recipients.ofEveryone(),
                    Component.text("Game started! ", NamedTextColor.GREEN),
                    13
                );

                task.cancel();
            }
        }, 1L, 20L);
    }

    @Override
    public void displayChannelsLayoutExample(Player viewer) {
        ChannelsLayout.display(this.chatModule, viewer);
    }

    @Override
    public void displayStaffChatLayoutExample(Player viewer) {
        StaffChatLayout.display(this.chatModule, viewer);
    }

    @Override
    public void resetChatButtonsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.chatModule::resetChatButtons);
    }

    @Override
    public void updateChatButtonExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> this.chatModule.updateChatButtonContent(apolloPlayer, "public-chat",
            ApolloButtonContent.builder()
                .append(ItemStackIcon.builder()
                    .itemName("PAPER")
                    .build())
                .append(Component.text("Public Chat", NamedTextColor.AQUA))
                .scale(1.0F)
                .build()));
    }

    @Override
    public void removeChatButtonExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.chatModule.removeChatButton(apolloPlayer, "party-chat"));
    }

    @Override
    public void removeLiveChatMessageExample() {
        this.chatModule.removeLiveChatMessage(Recipients.ofEveryone(), 13);
    }

    @Override
    public void displayChatTabsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> this.chatModule.displayChatTabs(apolloPlayer, Arrays.asList(
            ChatTab.builder()
                .id("staff")
                .displayName(Component.text("Staff", NamedTextColor.RED))
                .pattern("^\\[Staff\\] ")
                .inputPrefix("/staffchat ")
                .hideFromAll(true)
                .build(),
            ChatTab.builder()
                .id("events")
                .displayName(Component.text("Events", NamedTextColor.GOLD))
                .build()
        )));
    }

    @Override
    public void displayChatTabMessageExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> this.chatModule.displayChatTabMessage(apolloPlayer, "events",
            Component.text("A KOTH event starts in 5 minutes!", NamedTextColor.GOLD),
            14
        ));
    }

    @Override
    public void removeChatTabExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.chatModule.removeChatTab(apolloPlayer, "staff"));
    }

    @Override
    public void resetChatTabsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.chatModule::resetChatTabs);
    }

    @Override
    public void displayChatPopupExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> this.chatModule.displayChatPopup(apolloPlayer, ChatPopup.builder()
            .id("duel-request")
            .title(Component.text("Duel Request"))
            .description(Component.text("Steve wants to duel you."))
            .displayTime(Duration.ofSeconds(60))
            .option(ChatPopupOption.builder()
                .id("accept")
                .label(Component.text("Accept", NamedTextColor.GREEN))
                .build())
            .option(ChatPopupOption.builder()
                .id("deny")
                .label(Component.text("Deny", NamedTextColor.RED))
                .build())
            .build()
        ));
    }

    @Override
    public void removeChatPopupExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.chatModule.removeChatPopup(apolloPlayer, "duel-request"));
    }

    @Override
    public void resetChatPopupsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.chatModule::resetChatPopups);
    }

    @Listen
    private void onChatPopupResponse(ApolloChatPopupResponseEvent event) {
        Player player = (Player) event.getPlayer().getPlayer();
        String optionId = event.getOptionId();

        if (optionId == null) {
            player.sendMessage("You closed the " + event.getPopupId() + " popup.");
        } else {
            player.sendMessage("You picked " + optionId + " on the " + event.getPopupId() + " popup.");
        }
    }

}
