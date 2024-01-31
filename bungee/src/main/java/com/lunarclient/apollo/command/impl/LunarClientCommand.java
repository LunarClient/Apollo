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
package com.lunarclient.apollo.command.impl;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.command.BungeeApolloCommand;
import com.lunarclient.apollo.common.ApolloComponent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

/**
 * The general Lunar Client command.
 *
 * @since 1.0.9
 */
public final class LunarClientCommand extends BungeeApolloCommand<CommandSender> {

    /**
     * Returns a new instance of this command.
     *
     * @return a new command
     * @since 1.0.9
     */
    public static Command create() {
        return new Command("lunarclient", "apollo.lunarclient") {
            private final LunarClientCommand command = new LunarClientCommand();

            @Override
            public void execute(CommandSender sender, String[] args) {
                this.command.execute(sender, args);
            }
        };
    }

    LunarClientCommand() {
        super((sender, component) -> sender.sendMessage(ApolloComponent.toLegacy(component)));

        this.setUsage("/lunarclient <player>");
    }

    void execute(CommandSender sender, String[] args) {
        if(args.length != 1) {
            this.sendCommandUsage(sender);
            return;
        }

        this.handlePlayerArgument(sender, args[0], player -> {
            Component message = Component.text("Player ", NamedTextColor.GRAY)
                .append(Component.text(player.getName(), NamedTextColor.AQUA))
                .append(Component.text(" is ", NamedTextColor.GRAY));

            if (Apollo.getPlayerManager().hasSupport(player.getUniqueId())) {
                message = message.append(Component.text("using ", NamedTextColor.GREEN));
            } else {
                message = message.append(Component.text("not using ", NamedTextColor.RED));
            }

            message = message.append(Component.text("Lunar Client!", NamedTextColor.GRAY));
            this.textConsumer.accept(sender, message);
        });
    }

}
