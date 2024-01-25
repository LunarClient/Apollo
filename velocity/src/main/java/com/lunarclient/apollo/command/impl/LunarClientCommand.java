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
import com.lunarclient.apollo.command.VelocityApolloCommand;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import lombok.Getter;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * The general Lunar Client command.
 *
 * @since 1.0.9
 */
@Getter
public final class LunarClientCommand extends VelocityApolloCommand<CommandSource> {

    /**
     * Returns a new instance of this command.
     *
     * @return a new command
     * @since 1.0.9
     */
    public static BrigadierCommand create() {
        LunarClientCommand command = new LunarClientCommand();

        return new BrigadierCommand(LiteralArgumentBuilder.<CommandSource>literal("lunarclient")
            .executes(source -> {
                command.sendCommandUsage(source.getSource());
                return Command.SINGLE_SUCCESS;
            })
            .requires(source -> source.hasPermission("apollo.lunarclient"))
            .then(RequiredArgumentBuilder.<CommandSource, String>argument("player", StringArgumentType.word())
                .executes(command.getPlayerCommand())
                .build()
            )
            .build()
        );
    }

    private final Command<CommandSource> playerCommand = context -> {
        String argument = context.getArgument("player", String.class);
        CommandSource source = context.getSource();

        this.handlePlayerArgument(source, argument, player -> {
            Component message = Component.text("Player ", NamedTextColor.GRAY)
                .append(Component.text(player.getUsername(), NamedTextColor.AQUA))
                .append(Component.text(" is ", NamedTextColor.GRAY));

            if (Apollo.getPlayerManager().hasSupport(player.getUniqueId())) {
                message = message.append(Component.text("using ", NamedTextColor.GREEN));
            } else {
                message = message.append(Component.text("not using ", NamedTextColor.RED));
            }

            message = message.append(Component.text("Lunar Client!", NamedTextColor.GRAY));
            this.textConsumer.accept(source, message);
        });

        return Command.SINGLE_SUCCESS;
    };

    LunarClientCommand() {
        super(Audience::sendMessage);

        this.setUsage("/lunarclient <player>");
    }

}
