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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * The general Apollo command.
 *
 * @since 1.1.8
 */
public final class FoliaApolloCommand extends ApolloCommand<CommandSender> implements CommandExecutor {

    /**
     * Returns a new instance of this command.
     *
     * @since 1.1.8
     */
    public FoliaApolloCommand() {
        super((sender, component) -> sender.sendMessage(ApolloComponent.toLegacy(component)));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(args.length < 1) {
            this.getCurrentVersion(sender);
        } else if(args[0].equalsIgnoreCase("reload")) {
            this.reloadConfiguration(sender);
        } else if(args[0].equalsIgnoreCase("update")) {
            ApolloManager.getVersionManager().forceUpdate(
                ApolloPlatform.Platform.FOLIA,
                message -> this.textConsumer.accept(sender, message)
            );
        }

        return true;
    }

}
