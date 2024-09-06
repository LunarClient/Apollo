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
package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.impl.LimbExample;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LimbCommand implements CommandExecutor {

    private final LimbExample limbExample = ApolloExamplePlugin.getPlugin().getLimbExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 2) {
            player.sendMessage("Usage: /limb <hideArmor|resetArmor|hideBody|resetBody> <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            player.sendMessage("Player '" + args[1] + "' not found!");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "hidearmor": {
                this.limbExample.hideArmorExample(player, target);
                player.sendMessage("Hiding armor....");
                break;
            }

            case "resetarmor": {
                this.limbExample.resetArmorExample(player, target);
                player.sendMessage("Resetting armor....");
                break;
            }

            case "hidebody": {
                this.limbExample.hideBodyExample(player, target);
                player.sendMessage("Hiding body....");
                break;
            }

            case "resetbody": {
                this.limbExample.resetBodyExample(player, target);
                player.sendMessage("Resetting body....");
                break;
            }

            default: {
                player.sendMessage("Usage: /limb <hideArmor|resetArmor|hideBody|resetBody>");
                break;
            }
        }

        return true;
    }
}
