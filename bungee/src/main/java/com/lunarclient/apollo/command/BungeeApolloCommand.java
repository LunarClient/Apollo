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
package com.lunarclient.apollo.command;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Provides common command functions for Bungee.
 *
 * @param <T> the sender type
 * @since 1.0.9
 */
public abstract class BungeeApolloCommand<T> extends AbstractApolloCommand<T> {

    /**
     * Returns a new instance of a Bungee command.
     *
     * @param textConsumer the consumer for sending messages to the sender
     * @since 1.0.9
     */
    public BungeeApolloCommand(BiConsumer<T, Component> textConsumer) {
        super(textConsumer);
    }

    /**
     * Handles a player argument; if the provided player doesn't exist, a not found message
     * is sent to the sender. Otherwise, the player is passed to the provided player consumer.
     *
     * @param sender the command sender
     * @param argument the argument passed from the command execution
     * @param playerConsumer a consumer used for processing a desired action if the player is found
     * @since 1.0.9
     */
    protected void handlePlayerArgument(@NonNull T sender, @NonNull String argument, @NonNull Consumer<ProxiedPlayer> playerConsumer) {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(argument);

        if (player == null) {
            this.textConsumer.accept(sender, Component.text("Player '", NamedTextColor.RED)
                .append(Component.text(argument, NamedTextColor.RED))
                .append(Component.text("' not found!", NamedTextColor.RED)));
            return;
        }

        playerConsumer.accept(player);
    }
}
