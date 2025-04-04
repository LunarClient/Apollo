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
package com.lunarclient.apollo.example.common.modules.impl;

import com.lunarclient.apollo.example.common.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryExample extends NMSExample {

    public boolean inventoryModuleExample(Player player) {
        if (this.isOneEight()) {
            this.inventoryModuleNMSExample(player);
            return false;
        } else {
            this.inventoryModuleCommandExample(player);
            return true;
        }
    }

    public void inventoryModuleCommandExample(Player player) {
        player.performCommand("summon item ~ ~1 ~ {Item:{id:\"minecraft:stone\",Count:1b,tag:{display:{Name:\"\\\"§c§lUNCLICKABLE\\\"\"},lunar:{unclickable:true}}}}");
        player.performCommand("summon item ~ ~1 ~ {Item:{id:\"minecraft:paper\",Count:1b,tag:{display:{Name:\"\\\"§9§lCOPY TO CLIPBOARD\\\"\"},lunar:{unclickable:true,copyToClipboard:\"lunarclient.com\"}}}}");
        player.performCommand("summon item ~ ~1 ~ {Item:{id:\"minecraft:torch\",Count:1b,tag:{display:{Name:\"\\\"§6§lOPEN URL\\\"\"},lunar:{unclickable:true,openUrl:\"https://lunarclient.com\"}}}}");
        player.performCommand("summon item ~ ~1 ~ {Item:{id:\"minecraft:book\",Count:1b,tag:{display:{Name:\"\\\"§2§lSUGGEST COMMAND\\\"\"},lunar:{unclickable:true,suggestCommand:\"/apollo\"}}}}");
        player.performCommand("summon item ~ ~1 ~ {Item:{id:\"minecraft:writable_book\",Count:1b,tag:{display:{Name:\"\\\"§d§lRUN COMMAND\\\"\"},lunar:{unclickable:true,runCommand:\"/apollo\"}}}}");
    }

    public void inventoryModuleNMSExample(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 5 * 9);

        ItemStack unclickableItem = ItemUtil.itemWithName(
            Material.STONE,
            ChatColor.RED.toString() + ChatColor.BOLD + "UNCLICKABLE"
        );

        inventory.setItem(11, ItemUtil.addTag(unclickableItem, "unclickable", true));

        ItemStack copyToClipboardItem = ItemUtil.itemWithName(
            Material.PAPER,
            ChatColor.BLUE.toString() + ChatColor.BOLD + "COPY TO CLIPBOARD"
        );

        copyToClipboardItem = ItemUtil.addTag(copyToClipboardItem, "unclickable", true);
        inventory.setItem(14, ItemUtil.addTag(copyToClipboardItem, "copyToClipboard", "lunarclient.com"));

        ItemStack openUrlItem = ItemUtil.itemWithName(
            Material.TORCH,
            ChatColor.GOLD.toString() + ChatColor.BOLD + "OPEN URL"
        );

        openUrlItem = ItemUtil.addTag(openUrlItem, "unclickable", true);
        inventory.setItem(17, ItemUtil.addTag(openUrlItem, "openUrl", "https://lunarclient.com"));

        ItemStack suggestCommandItem = ItemUtil.itemWithName(
            Material.BOOK,
            ChatColor.GREEN.toString() + ChatColor.BOLD + "SUGGEST COMMAND"
        );

        suggestCommandItem = ItemUtil.addTag(suggestCommandItem, "unclickable", true);
        inventory.setItem(29, ItemUtil.addTag(suggestCommandItem, "suggestCommand", "/apollo"));

        ItemStack runCommandItem = ItemUtil.itemWithName(
            Material.BOOK_AND_QUILL,
            ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "RUN COMMAND"
        );

        runCommandItem = ItemUtil.addTag(runCommandItem, "unclickable", true);
        inventory.setItem(33, ItemUtil.addTag(runCommandItem, "runCommand", "/apollo"));

        player.openInventory(inventory);
    }

}
