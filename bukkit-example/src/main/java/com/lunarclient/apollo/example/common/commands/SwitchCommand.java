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
package com.lunarclient.apollo.example.common.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.common.modules.ApolloExampleType;
import java.util.NoSuchElementException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class SwitchCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Usage: /switch <API|PROTO|JSON|>");
            sender.sendMessage("Current implementation: " + ApolloExamplePlugin.TYPE.name());
            return true;
        }

        ApolloExampleType type;
        try {
            type = ApolloExampleType.valueOf(args[0].toUpperCase());
        } catch (NoSuchElementException e) {
            sender.sendMessage("No implementation '" + args[0] + "' found!");
            return false;
        }

        if (type == ApolloExamplePlugin.TYPE) {
            sender.sendMessage("The implementation type is already set to " + ApolloExamplePlugin.TYPE.name());
            return true;
        }

        Plugin plugin = Bukkit.getPluginManager().getPlugin("Apollo-Bukkit");
        if (type == ApolloExampleType.API && plugin == null) {
            sender.sendMessage("Can't switch implementation to API, the Apollo-Bukkit plugin must be running.");
            return true;
        }

        if (type != ApolloExampleType.API && plugin != null) {
            sender.sendMessage("Can't switch implementation to " + ApolloExamplePlugin.TYPE.name() + ", the Apollo-Bukkit must be deleted.");
            return true;
        }

        ApolloExamplePlugin.getPlugin().changeImplementationType(type);
        sender.sendMessage("Successfully switched example implementation to " + type.name() + "!");
        sender.sendMessage("Re-join the server to apply the changes!");
        return true;
    }

}
