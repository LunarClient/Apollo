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
package com.lunarclient.apollo.example.common.commands.module;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.common.modules.impl.TeamExample;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;
        TeamExample teamExample = ApolloExamplePlugin.getPlugin().getTeamExample();

        if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "create": {
                    teamExample.createTeam(player);
                    break;
                }

                case "delete": {
                    teamExample.deleteTeam(player);
                    break;
                }

                default: {
                    player.sendMessage("Usage: /team <create|delete>");
                    player.sendMessage("Usage: /team <addMember|removeMember> <player>");
                    break;
                }
            }

            return true;
        }

        if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                player.sendMessage("Player '" + args[1] + "' not found!");
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "addmember": {
                    teamExample.addMember(player, target);
                    break;
                }

                case "removemember": {
                    teamExample.removeMember(player, target);
                    break;
                }

                default: {
                    player.sendMessage("Usage: /team <create|delete>");
                    player.sendMessage("Usage: /team <addMember|removeMember> <player>");
                    break;
                }
            }

            return true;
        }

        player.sendMessage("Usage: /team <create|delete>");
        player.sendMessage("Usage: /team <addMember|removeMember> <player>");

        return true;
    }
}
