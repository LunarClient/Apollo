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
import com.lunarclient.apollo.command.FoliaApolloCommand;
import com.lunarclient.apollo.common.ApolloComponent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * The general Lunar Client command.
 *
 * @since 1.1.8
 */
public final class LunarClientCommand extends FoliaApolloCommand<CommandSender> implements CommandExecutor {

    /**
     * Returns a new instance of this command.
     *
     * @since 1.1.8
     */
    public LunarClientCommand() {
        super((sender, component) -> sender.sendMessage(ApolloComponent.toLegacy(component)));

        this.setUsage("/lunarclient <player>");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(args.length != 1) {
            this.sendCommandUsage(commandSender);
            return true;
        }

        this.handlePlayerArgument(commandSender, args[0], player -> {
            Component message = Component.text("Player ", NamedTextColor.GRAY)
                .append(Component.text(player.getName(), NamedTextColor.AQUA))
                .append(Component.text(" is ", NamedTextColor.GRAY));

            if (Apollo.getPlayerManager().hasSupport(player.getUniqueId())) {
                message = message.append(Component.text("using ", NamedTextColor.GREEN));
            } else {
                message = message.append(Component.text("not using ", NamedTextColor.RED));
            }

            message = message.append(Component.text("Lunar Client!", NamedTextColor.GRAY));
            this.textConsumer.accept(commandSender, message);
        });

        return true;
    }

}
