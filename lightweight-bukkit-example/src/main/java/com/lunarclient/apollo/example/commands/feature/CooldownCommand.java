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
package com.lunarclient.apollo.example.commands.feature;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.impl.CooldownExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CooldownCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Usage: /cooldown <displayItem|displayResource|remove|reset>");
            return true;
        }

        CooldownExample cooldownExample = ApolloExamplePlugin.getPlugin().getCooldownExample();

        switch (args[0].toLowerCase()) {
            case "displayitem": {
                cooldownExample.displayCooldownItemExample(player);
                player.sendMessage("Displaying cooldown item....");
                break;
            }

            case "displayresource": {
                cooldownExample.displayCooldownResourceExample(player);
                player.sendMessage("Displaying cooldown resource....");
                break;
            }

            case "remove": {
                cooldownExample.removeCooldownExample(player);
                player.sendMessage("Removing cooldown....");
                break;
            }

            case "reset": {
                cooldownExample.resetCooldownsExample(player);
                player.sendMessage("Resetting cooldowns...");
                break;
            }

            default: {
                player.sendMessage("Usage: /cooldown <displayItem|displayResource|remove|reset>");
                break;
            }
        }

        return true;
    }
}
