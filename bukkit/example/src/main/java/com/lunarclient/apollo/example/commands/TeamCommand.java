package com.lunarclient.apollo.example.commands;

import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.modules.TeamExample;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

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
