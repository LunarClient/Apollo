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

import com.lunarclient.apollo.command.BukkitApolloCommand;
import com.lunarclient.apollo.common.ApolloComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * The general Apollo command.
 *
 * @since 1.0.5
 */
public final class ApolloCommand extends BukkitApolloCommand<CommandSender> implements CommandExecutor {

    /**
     * Returns a new instance of this command.
     *
     * @since 1.0.5
     */
    public ApolloCommand() {
        super((sender, component) -> sender.sendMessage(ApolloComponent.toLegacy(component)));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(args.length < 1) {
            this.getCurrentVersion(commandSender);
        } else if(args[0].equalsIgnoreCase("reload")) {
            this.reloadConfiguration(commandSender);
        } else if(args[0].equalsIgnoreCase("update")) {

        }

        return true;
    }

}
