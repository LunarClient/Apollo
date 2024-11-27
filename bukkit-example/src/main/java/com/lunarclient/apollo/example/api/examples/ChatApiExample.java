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
package com.lunarclient.apollo.example.api.examples;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.common.modules.impl.ChatExample;
import com.lunarclient.apollo.module.chat.ChatModule;
import com.lunarclient.apollo.recipients.Recipients;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatApiExample extends ChatExample {

    private final ChatModule chatModule = Apollo.getModuleManager().getModule(ChatModule.class);

    @Override
    public void displayLiveChatMessageExample() {
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

        runnable.runTaskTimer(ApolloExamplePlugin.getPlugin(), 0L, 20L);
    }

    @Override
    public void removeLiveChatMessageExample() {
        this.chatModule.removeLiveChatMessage(Recipients.ofEveryone(), 13);
    }

}
