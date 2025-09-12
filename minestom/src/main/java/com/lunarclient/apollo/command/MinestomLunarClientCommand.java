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

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.command.type.LunarClientCommand;
import java.util.function.Predicate;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

/**
 * The general Lunar Client command.
 *
 * @since 1.2.0
 */
public final class MinestomLunarClientCommand extends LunarClientCommand<CommandSender> {

    /**
     * Returns a new instance of this command.
     *
     * @param permission the permission predicate
     * @return a new command
     * @since 1.2.0
     */
    public static Command create(Predicate<CommandSender> permission) {
        MinestomLunarClientCommand lunarClientCommand = new MinestomLunarClientCommand();

        Command command = new Command("lunarclient");
        command.setDefaultExecutor((sender, context) -> {
            if (!permission.test(sender)) {
                sender.sendMessage(Component.text("You don't have permission to use this command.", NamedTextColor.RED));
                return;
            }

            lunarClientCommand.sendCommandUsage(sender);
        });

        ArgumentString argument = ArgumentType.String("player");

        command.addSyntax((sender, context) -> {
            if (!permission.test(sender)) {
                sender.sendMessage(Component.text("You don't have permission to use this command.", NamedTextColor.RED));
                return;
            }

            String playerName = context.get(argument);
            Player player = MinecraftServer.getConnectionManager().getOnlinePlayerByUsername(playerName);

            if (player == null) {
                lunarClientCommand.textConsumer.accept(sender, Component.text("Player '", NamedTextColor.RED)
                    .append(Component.text(playerName, NamedTextColor.RED))
                    .append(Component.text("' not found!", NamedTextColor.RED)));
                return;
            }

            Component message = Component.text("Player ", NamedTextColor.GRAY)
                .append(Component.text(player.getUsername(), NamedTextColor.AQUA))
                .append(Component.text(" is ", NamedTextColor.GRAY));

            if (Apollo.getPlayerManager().hasSupport(player.getUuid())) {
                message = message.append(Component.text("using ", NamedTextColor.GREEN));
            } else {
                message = message.append(Component.text("not using ", NamedTextColor.RED));
            }

            message = message.append(Component.text("Lunar Client!", NamedTextColor.GRAY));
            lunarClientCommand.textConsumer.accept(sender, message);
        }, argument);

        return command;
    }

    MinestomLunarClientCommand() {
        super(Audience::sendMessage);
    }

}
