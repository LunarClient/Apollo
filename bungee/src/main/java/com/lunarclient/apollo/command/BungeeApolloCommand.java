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

import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.command.type.ApolloCommand;
import com.lunarclient.apollo.common.ApolloComponent;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

/**
 * The general Apollo command.
 *
 * @since 1.0.5
 */
public final class BungeeApolloCommand extends ApolloCommand<CommandSender> {

    /**
     * Returns a new instance of this command.
     *
     * @return a new command
     * @since 1.0.5
     */
    public static Command create() {
        return new Command("apollo", "apollo.command") {
            private final BungeeApolloCommand command = new BungeeApolloCommand();

            @Override
            public void execute(CommandSender sender, String[] args) {
                this.command.execute(sender, args);
            }
        };
    }

    BungeeApolloCommand() {
        super((sender, component) -> sender.sendMessage(ApolloComponent.toLegacy(component)));
    }

    void execute(CommandSender sender, String[] args) {
        if(args.length < 1) {
            this.getCurrentVersion(sender);
        } else if(args[0].equalsIgnoreCase("reload")) {
            this.reloadConfiguration(sender);
        } else if(args[0].equalsIgnoreCase("update")) {
            ApolloManager.getVersionManager().forceUpdate(
                ApolloPlatform.Platform.BUNGEE,
                message -> this.textConsumer.accept(sender, message)
            );
        }
    }

}
