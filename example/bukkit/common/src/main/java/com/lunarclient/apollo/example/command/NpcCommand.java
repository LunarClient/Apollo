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
import com.lunarclient.apollo.example.nms.NpcManager;
import com.lunarclient.apollo.example.nms.PlayerNpc;
import java.util.Optional;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NpcCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            this.sendUsage(player);
            return true;
        }

        NpcManager npcManager = ApolloExamplePlugin.getInstance().getNpcManager();

        switch (args[0].toLowerCase()) {
            case "spawn": {
                if (args.length < 2) {
                    this.sendUsage(player);
                    break;
                }

                PlayerNpc npc = npcManager.spawnNpc(args[1], player.getLocation());
                if (npc == null) {
                    player.sendMessage(ChatColor.RED + "Failed to spawn NPC.");
                    break;
                }

                player.sendMessage(ChatColor.GREEN + "Spawned NPC " + npc.getName() + " (" + npc.getUuid() + ")");
                break;
            }

            case "remove": {
                if (args.length < 2) {
                    this.sendUsage(player);
                    break;
                }

                Optional<PlayerNpc> byName = npcManager.findByName(args[1]);
                if (!byName.isPresent()) {
                    player.sendMessage(ChatColor.RED + "No NPC found with name: " + args[1]);
                    break;
                }

                npcManager.removeNpc(byName.get().getUuid());
                player.sendMessage(ChatColor.GREEN + "Removed NPC " + args[1]);
                break;
            }

            default: {
                this.sendUsage(player);
                break;
            }
        }

        return true;
    }

    private void sendUsage(Player player) {
        player.sendMessage("Usage: /npc spawn <name>");
        player.sendMessage("Usage: /npc remove <name>");
    }

}
