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
import com.lunarclient.apollo.example.modules.TeamExample;
import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeamCommand implements CommandExecutor {

    private final TeamExample teamExample = ApolloExamplePlugin.getPlugin().getTeamExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "create": {
                    TeamExample.Team team = this.teamExample.createTeam();
                    team.addMember(player);

                    player.sendMessage("Creating team...");
                    break;
                }

                case "delete": {
                    Optional<TeamExample.Team> teamOpt = this.teamExample.getByPlayerUuid(player.getUniqueId());

                    if (teamOpt.isPresent()) {
                        this.teamExample.deleteTeam(teamOpt.get().getTeamId());
                        player.sendMessage("Deleting team...");
                    } else {
                        player.sendMessage("No team found...");
                    }

                    break;
                }

                default: {
                    player.sendMessage("Usage: /team <create|delete>");
                    player.sendMessage("Usage: /team <addMember|removeMember> <player>");
                }
            }

            return true;
        }

        if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[1]);

            if(target == null) {
                player.sendMessage("Player '" + args[1] + "' not found!");
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "addmember": {
                    Optional<TeamExample.Team> teamOpt = this.teamExample.getByPlayerUuid(player.getUniqueId());

                    if (teamOpt.isPresent()) {
                        teamOpt.get().addMember(target);
                        player.sendMessage("Added " + target.getName() + " to your team...");
                    } else {
                        player.sendMessage("No team found...");
                    }

                    break;
                }

                case "removemember": {
                    Optional<TeamExample.Team> teamOpt = this.teamExample.getByPlayerUuid(player.getUniqueId());

                    if (teamOpt.isPresent()) {
                        teamOpt.get().removeMember(target);
                        player.sendMessage("Removed " + target.getName() + " from your team...");
                    } else {
                        player.sendMessage("No team found...");
                    }

                    break;
                }

                default: {
                    player.sendMessage("Usage: /team <create|delete>");
                    player.sendMessage("Usage: /team <addMember|removeMember> <player>");
                }
            }

            return true;
        }

        player.sendMessage("Usage: /team <create|delete>");
        player.sendMessage("Usage: /team <addMember|removeMember> <player>");

        return true;
    }
}
