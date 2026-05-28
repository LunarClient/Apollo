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
import com.lunarclient.apollo.example.nms.CommandCosmetic;
import com.lunarclient.apollo.example.nms.PlayerNpc;
import com.lunarclient.apollo.example.util.CommandUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
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

        if ("emote".equalsIgnoreCase(args[0])) {
            return this.handleEmote(player, example, args);
        }

        if (args.length < 2) {
            this.sendUsage(player);
            return true;
        }

        String npcName = args[1];
        UUID uuid = this.resolveNpcUuid(player, npcName);
        if (uuid == null) {
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "equip": {
                if (args.length >= 3 && this.isCosmeticType(args[2])) {
                    this.handleTypedEquip(player, example, uuid, npcName, args);
                    break;
                }

                List<Integer> cosmeticIds = this.parseCosmeticIds(args);
                example.equipNpcCosmeticsInternal(player, uuid, cosmeticIds);
                this.persistEquipped(uuid, cosmeticIds.stream()
                    .map(id -> CommandCosmetic.builder().id(id).build())
                    .collect(Collectors.toList()));
                player.sendMessage(ChatColor.GREEN + "Equipped cosmetics " + cosmeticIds + " on NPC " + npcName);
                break;
            }

            case "equiplocal": {
                example.equipNpcCosmeticsCopyLocalExample(player, uuid);
                player.sendMessage(ChatColor.GREEN + "Equip local cosmetics on NPC " + npcName);
                break;
            }

            case "unequip": {
                List<Integer> cosmeticIds = this.parseCosmeticIds(args);
                example.unequipNpcCosmeticsInternal(player, uuid, cosmeticIds);
                this.persistUnequipped(uuid, cosmeticIds);
                player.sendMessage(ChatColor.GREEN + "Unequipped cosmetics " + cosmeticIds + " from NPC " + npcName);
                break;
            }

            case "reset": {
                example.resetNpcCosmeticsExample(player, uuid);
                this.persistReset(uuid);
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

    private boolean handleEmote(Player player, CosmeticExample example, String[] args) {
        if (args.length < 2) {
            this.sendEmoteUsage(player);
            return true;
        }

        switch (args[1].toLowerCase()) {
            case "start": {
                if (args.length < 4) {
                    this.sendEmoteUsage(player);
                    return true;
                }

                UUID uuid = this.resolveNpcUuid(player, args[2]);
                if (uuid == null) {
                    return true;
                }

                int emoteId;
                try {
                    emoteId = Integer.parseInt(args[3]);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Emote id must be an integer.");
                    return true;
                }

                int metadata = 0;
                if (args.length >= 5) {
                    try {
                        metadata = Integer.parseInt(args[4]);
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "Emote metadata must be an integer.");
                        return true;
                    }
                }

                example.startNpcEmoteInternal(player, uuid, emoteId, metadata);
                player.sendMessage(ChatColor.GREEN + "Started emote " + emoteId + " on NPC " + args[2]);
                break;
            }

            case "stop": {
                if (args.length < 3) {
                    this.sendEmoteUsage(player);
                    return true;
                }

                UUID uuid = this.resolveNpcUuid(player, args[2]);
                if (uuid == null) {
                    return true;
                }

                example.stopNpcEmoteExample(player, uuid);
                player.sendMessage(ChatColor.GREEN + "Stopped emote on NPC " + args[2]);
                break;
            }

            case "reset": {
                example.resetNpcEmotesExample();
                player.sendMessage(ChatColor.GREEN + "Reset all NPC emotes");
                break;
            }

            default: {
                this.sendEmoteUsage(player);
                break;
            }
        }

        return true;
    }

    private UUID resolveNpcUuid(Player player, String npcName) {
        Optional<PlayerNpc> npcOpt = ApolloExamplePlugin.getInstance().getNpcManager().findByName(npcName);
        if (!npcOpt.isPresent()) {
            player.sendMessage(ChatColor.RED + "No NPC found with name: " + npcName);
            return null;
        }

        return npcOpt.get().getUuid();
    }

    private boolean isCosmeticType(String type) {
        String lower = type.toLowerCase();
        return "hat".equals(lower) || "cloak".equals(lower) || "pet".equals(lower) || "body".equals(lower);
    }

    private void handleTypedEquip(Player player, CosmeticExample example, UUID uuid, String npcName, String[] args) {
        if (args.length < 4) {
            this.sendUsage(player);
            return;
        }

        int cosmeticId;
        try {
            cosmeticId = Integer.parseInt(args[3]);
        } catch (NumberFormatException ex) {
            player.sendMessage(ChatColor.RED + "Cosmetic id must be an integer.");
            return;
        }

        Map<String, String> options = this.parseOptions(args, 4);
        String type = args[2].toLowerCase();

        CommandCosmetic.Options optionsSpec;
        switch (type) {
            case "hat": {
                optionsSpec = CommandCosmetic.Hat.builder()
                    .showOverHelmet(CommandUtil.parseBoolean(options.get("showoverhelmet"), true))
                    .showOverSkinLayer(CommandUtil.parseBoolean(options.get("showoverskinlayer"), true))
                    .heightOffset(CommandUtil.parseFloat(options.get("heightoffset"), 0f))
                    .build();
                break;
            }
            case "cloak": {
                optionsSpec = CommandCosmetic.Cloak.builder()
                    .useClothPhysics(CommandUtil.parseBoolean(options.get("useclothphysics"), false))
                    .build();
                break;
            }
            case "pet": {
                optionsSpec = CommandCosmetic.Pet.builder()
                    .flipShoulder(CommandUtil.parseBoolean(options.get("flipshoulder"), false))
                    .build();
                break;
            }
            case "body": {
                optionsSpec = CommandCosmetic.Body.builder()
                    .showOverChestplate(CommandUtil.parseBoolean(options.get("showoverchestplate"), true))
                    .showOverLeggings(CommandUtil.parseBoolean(options.get("showoverleggings"), true))
                    .showOverBoots(CommandUtil.parseBoolean(options.get("showoverboots"), true))
                    .build();
                break;
            }
            default: {
                this.sendUsage(player);
                return;
            }
        }

        CommandCosmetic spec = CommandCosmetic.builder()
            .id(cosmeticId)
            .options(optionsSpec)
            .build();

        example.equipNpcCosmeticInternal(player, uuid, spec);
        this.persistEquipped(uuid, Collections.singletonList(spec));
        player.sendMessage(ChatColor.GREEN + "Equipped " + type + " cosmetic " + cosmeticId + " on NPC " + npcName);
    }

    private void persistEquipped(UUID npcUuid, List<CommandCosmetic> equipped) {
        Optional<PlayerNpc> npcOpt = ApolloExamplePlugin.getInstance().getNpcManager().findByUuid(npcUuid);
        if (!npcOpt.isPresent()) {
            return;
        }

        List<CommandCosmetic> cosmetics = npcOpt.get().getCosmetics();
        for (CommandCosmetic cosmetic : equipped) {
            cosmetics.removeIf(existing -> existing.getId() == cosmetic.getId());
            cosmetics.add(cosmetic);
        }

        ApolloExamplePlugin.getInstance().getNpcManager().save();
    }

    private void persistUnequipped(UUID npcUuid, List<Integer> cosmeticIds) {
        Optional<PlayerNpc> npcOpt = ApolloExamplePlugin.getInstance().getNpcManager().findByUuid(npcUuid);
        if (!npcOpt.isPresent()) {
            return;
        }

        npcOpt.get().getCosmetics().removeIf(cosmetic -> cosmeticIds.contains(cosmetic.getId()));
        ApolloExamplePlugin.getInstance().getNpcManager().save();
    }

    private void persistReset(UUID npcUuid) {
        Optional<PlayerNpc> npcOpt = ApolloExamplePlugin.getInstance().getNpcManager().findByUuid(npcUuid);
        if (!npcOpt.isPresent()) {
            return;
        }

        npcOpt.get().getCosmetics().clear();
        ApolloExamplePlugin.getInstance().getNpcManager().save();
    }

    private Map<String, String> parseOptions(String[] args, int startIndex) {
        Map<String, String> options = new HashMap<>();
        for (int i = startIndex; i < args.length; i++) {
            String token = args[i];
            int index = token.indexOf('=');

            if (index <= 0 || index == token.length() - 1) {
                continue;
            }

            options.put(token.substring(0, index).toLowerCase(), token.substring(index + 1));
        }

        return options;
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
        this.sendCosmeticUsage(player);
        player.sendMessage("");
        this.sendEmoteUsage(player);
        player.sendMessage("");
        this.sendSprayUsage(player);
    }

    private void sendCosmeticUsage(Player player) {
        player.sendMessage(" - /cosmetic equip <npc_name> [cosmeticIds]");
        player.sendMessage(ChatColor.ITALIC + "   /cosmetic equip Apollo 434 3654 3977");
        player.sendMessage("");
        player.sendMessage(" - /cosmetic equip <npc_name> hat <id>");
        player.sendMessage(ChatColor.ITALIC + "   /cosmetic equip Apollo hat 434 showOverHelmet=true showOverSkinLayer=true heightOffset=0.0");
        player.sendMessage("");
        player.sendMessage(" - /cosmetic equip <npc_name> cloak <id>");
        player.sendMessage(ChatColor.ITALIC + "   /cosmetic equip Apollo cloak 3 useClothPhysics=true");
        player.sendMessage("");
        player.sendMessage(" - /cosmetic equip <npc_name> pet <id>");
        player.sendMessage(ChatColor.ITALIC + "   /cosmetic equip Apollo pet 5095 flipShoulder=true");
        player.sendMessage("");
        player.sendMessage(" - /cosmetic equip <npc_name> body <id>");
        player.sendMessage(ChatColor.ITALIC + "   /cosmetic equip Apollo body 3977 showOverChestplate=true showOverLeggings=true showOverBoots=true");
        player.sendMessage("");
        player.sendMessage(" - /cosmetic equiplocal <npc_name>");
        player.sendMessage(ChatColor.ITALIC + "   /cosmetic equiplocal Apollo");
        player.sendMessage("");
        player.sendMessage(" - /cosmetic unequip <npc_name> [cosmeticIds]");
        player.sendMessage(ChatColor.ITALIC + "   /cosmetic unequip Apollo 434 3654");
        player.sendMessage("");
        player.sendMessage(" - /cosmetic reset <npc_name>");
        player.sendMessage(ChatColor.ITALIC + "   /cosmetic reset Apollo");
    }

    private void sendEmoteUsage(Player player) {
        player.sendMessage(" - /cosmetic emote start <npc_name> <id> [metadata]");
        player.sendMessage(ChatColor.ITALIC + "   /cosmetic emote start Apollo 342 1 (Coin Flip, metadata 1 = heads)");
        player.sendMessage(" - /cosmetic emote stop <npc_name>");
        player.sendMessage(" - /cosmetic emote reset");
    }

    private void sendSprayUsage(Player player) {
        player.sendMessage(" - /cosmetic spray display <sprayId>");
        player.sendMessage(" - /cosmetic spray remove <sprayId>");
        player.sendMessage(" - /cosmetic spray reset");
    }

}
