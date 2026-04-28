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
import com.lunarclient.apollo.example.module.impl.CosmeticExample;
import com.lunarclient.apollo.example.nms.PlayerNpc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CosmeticCommand implements CommandExecutor {

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

        CosmeticExample example = ApolloExamplePlugin.getInstance().getCosmeticExample();

        if ("spray".equalsIgnoreCase(args[0])) {
            return this.handleSpray(player, example, args);
        }

        if (args.length < 2) {
            this.sendUsage(player);
            return true;
        }

        String npcName = args[1];
        boolean uuidParam = npcName.contains("-");

        Optional<PlayerNpc> npcOpt = ApolloExamplePlugin.getInstance().getNpcManager().findByName(npcName);
        if (!npcOpt.isPresent() && !uuidParam) {
            player.sendMessage(ChatColor.RED + "No NPC found with name: " + npcName);
            return true;
        }

        UUID uuid;
        if (uuidParam) {
            uuid = UUID.fromString(npcName);
        } else {
            uuid = npcOpt.get().getUuid();
        }

        switch (args[0].toLowerCase()) {
            case "equip": {
                List<Integer> cosmeticIds = this.parseCosmeticIds(args);
                example.equipNpcCosmeticsInternal(player, uuid, cosmeticIds);
                player.sendMessage(ChatColor.GREEN + "Equipped cosmetics " + cosmeticIds + " on NPC " + npcName);
                break;
            }

            case "unequip": {
                List<Integer> cosmeticIds = this.parseCosmeticIds(args);
                example.unequipNpcCosmeticsInternal(player, uuid, cosmeticIds);
                player.sendMessage(ChatColor.GREEN + "Unequipped cosmetics " + cosmeticIds + " from NPC " + npcName);
                break;
            }

            case "reset": {
                example.resetNpcCosmeticsExample(player, uuid);
                player.sendMessage(ChatColor.GREEN + "Reset all cosmetics on NPC " + npcName);
                break;
            }

            default: {
                this.sendUsage(player);
                break;
            }
        }

        return true;
    }

    private boolean handleSpray(Player player, CosmeticExample example, String[] args) {
        if (args.length < 2) {
            this.sendSprayUsage(player);
            return true;
        }

        switch (args[1].toLowerCase()) {
            case "display": {
                if (args.length < 3) {
                    this.sendSprayUsage(player);
                    return true;
                }

                int sprayId;
                try {
                    sprayId = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    player.sendMessage("Spray id must be an integer.");
                    return true;
                }

                example.displaySprayExample(player, sprayId);
                player.sendMessage(ChatColor.GREEN + "Displayed spray " + sprayId + " at your target block");
                break;
            }

            case "remove": {
                if (args.length < 3) {
                    this.sendSprayUsage(player);
                    return true;
                }

                int sprayId;
                try {
                    sprayId = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    player.sendMessage("Spray id must be an integer.");
                    return true;
                }

                example.removeSprayExample(sprayId);
                player.sendMessage(ChatColor.GREEN + "Removed all sprays with id " + sprayId);
                break;
            }

            case "reset": {
                example.resetSpraysExample();
                player.sendMessage(ChatColor.GREEN + "Reset all server sprays");
                break;
            }

            default: {
                this.sendSprayUsage(player);
                break;
            }
        }

        return true;
    }

    private List<Integer> parseCosmeticIds(String[] args) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 2; i < args.length; i++) {
            try {
                ids.add(Integer.parseInt(args[i]));
            } catch (NumberFormatException ignored) {
            }
        }
        return ids;
    }

    private void sendUsage(Player player) {
        player.sendMessage("Usage:");
        player.sendMessage(" - /cosmetic equip <npc_name> [cosmeticIds]");
        player.sendMessage(" - /cosmetic unequip <npc_name> [cosmeticIds]");
        player.sendMessage(" - /cosmetic reset <npc_name>");
        this.sendSprayUsage(player);
    }

    private void sendSprayUsage(Player player) {
        player.sendMessage(" - /cosmetic spray display <sprayId>");
        player.sendMessage(" - /cosmetic spray remove <sprayId>");
        player.sendMessage(" - /cosmetic spray reset");
    }

}
