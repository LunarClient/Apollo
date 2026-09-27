/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.example.command;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.module.impl.TotemCounterExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TotemCounterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;
        TotemCounterExample totemCounterExample = ApolloExamplePlugin.getInstance().getTotemCounterExample();

        if (args.length == 2 && args[0].equalsIgnoreCase("disableLocalTracking")) {
            boolean value = Boolean.parseBoolean(args[1]);
            totemCounterExample.setDisableLocalTracking(value);

            player.sendMessage("Disable local tracking has been set to " + value);
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /totemcounter <override|overrideAll|reset|clear>");
            player.sendMessage("Usage: /totemcounter <disableLocalTracking> <value>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "override": {
                totemCounterExample.overrideTotemCounterExample(player);
                player.sendMessage("Overriding totem counter....");
                break;
            }

            case "overrideall": {
                totemCounterExample.overrideTotemCountersExample(player);
                player.sendMessage("Overriding totem counters....");
                break;
            }

            case "reset": {
                totemCounterExample.resetTotemCounterExample(player);
                player.sendMessage("Resetting totem counter....");
                break;
            }

            case "clear": {
                totemCounterExample.resetTotemCountersExample(player);
                player.sendMessage("Clearing totem counters...");
                break;
            }

            default: {
                player.sendMessage("Usage: /totemcounter <override|overrideAll|reset|clear>");
                player.sendMessage("Usage: /totemcounter <disableLocalTracking> <value>");
                break;
            }
        }

        return true;
    }
}
