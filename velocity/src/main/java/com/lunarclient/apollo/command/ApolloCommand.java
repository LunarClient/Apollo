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

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import lombok.Getter;
import net.kyori.adventure.audience.Audience;

/**
 * The general Apollo command.
 *
 * @since 1.0.5
 */
@Getter
public final class ApolloCommand extends AbstractApolloCommand<CommandSource> {

    /**
     * Returns a new instance of this command.
     *
     * @return a new command
     * @since 1.0.5
     */
    public static BrigadierCommand create() {
        ApolloCommand command = new ApolloCommand();

        return new BrigadierCommand(LiteralArgumentBuilder.<CommandSource>literal("apollo")
            .requires(source -> source.hasPermission("apollo.command"))
            .executes(command.getBaseCommand())
            .then(LiteralArgumentBuilder.<CommandSource>literal("reload")
                .executes(command.getReloadCommand())
                .build()
            )
            .build()
        );
    }

    private final Command<CommandSource> baseCommand = context -> {
        CommandSource source = context.getSource();
        ApolloCommand.this.getCurrentVersion(source);
        return Command.SINGLE_SUCCESS;
    };

    private final Command<CommandSource> reloadCommand = context -> {
        CommandSource source = context.getSource();
        this.reloadConfiguration(source);
        return Command.SINGLE_SUCCESS;
    };

    ApolloCommand() {
        super(Audience::sendMessage);
    }

}
