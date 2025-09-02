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

import com.lunarclient.apollo.command.type.ApolloCommand;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.ArgumentWord;
import net.minestom.server.entity.Player;

/**
 * The Minestom implementation of the {@link ApolloCommand}.
 *
 * @since 1.2.0
 */
public final class MinestomApolloCommand extends ApolloCommand<CommandSender> {

    /**
     * Returns a new instance of this command.
     *
     * @return a new command
     * @since 1.2.0
     */
    public static Command create() {
        MinestomApolloCommand apolloCommand = new MinestomApolloCommand();

        Command command = new Command("apollo");
        command.setDefaultExecutor((sender, context) -> {
            if (sender instanceof Player && ((Player) sender).getPermissionLevel() < 2) {
                sender.sendMessage(Component.text("You don't have permission to use this command.", NamedTextColor.RED));
                return;
            }

            apolloCommand.getCurrentVersion(sender);
        });

        ArgumentWord argument = ArgumentType.Word("action")
            .from("reload", "update");

        command.addSyntax((sender, context) -> {
            if (sender instanceof Player && ((Player) sender).getPermissionLevel() < 2) {
                sender.sendMessage(Component.text("You don't have permission to use this command.", NamedTextColor.RED));
                return;
            }

            String subArg = context.get(argument);

            if (subArg.equalsIgnoreCase("reload")) {
                apolloCommand.reloadConfiguration(sender);
            } else if (subArg.equalsIgnoreCase("update")) {
                Component message = Component.text("[Apollo] The update command is not available for the ", NamedTextColor.YELLOW)
                    .append(Component.text("Minestom ", NamedTextColor.GOLD))
                    .append(Component.text("platform!", NamedTextColor.YELLOW));

                apolloCommand.textConsumer.accept(sender, message);
            }
        }, argument);

        return command;
    }

    MinestomApolloCommand() {
        super(Audience::sendMessage);
    }

}
