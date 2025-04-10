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
package com.lunarclient.apollo.example.module.impl;

import com.lunarclient.apollo.example.module.NMSExample;
import com.lunarclient.apollo.example.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GlintExample extends NMSExample {

    public boolean glintModuleExample(Player player) {
        if (this.isOneEight()) {
            this.glintModuleNMSExample(player);
            return false;
        } else {
            this.glintModuleCommandExample(player);
            return true;
        }
    }

    public void glintModuleCommandExample(Player player) {
        player.performCommand("summon item ~ ~1 ~ {Item:{id:\"minecraft:diamond_helmet\",Count:1b,tag:{lunar:{glint:\"#FF5733\"}}}}");
        player.performCommand("summon item ~ ~1 ~ {Item:{id:\"minecraft:diamond_helmet\",Count:1b,tag:{lunar:{glint:\"#33FF57\"}}}}");
        player.performCommand("summon item ~ ~1 ~ {Item:{id:\"minecraft:diamond_helmet\",Count:1b,tag:{lunar:{glint:\"#3357FF\"}}}}");
        player.performCommand("summon item ~ ~1 ~ {Item:{id:\"minecraft:diamond_helmet\",Count:1b,tag:{lunar:{glint:\"#FFD700\"}}}}");
        player.performCommand("summon item ~ ~1 ~ {Item:{id:\"minecraft:diamond_helmet\",Count:1b,tag:{lunar:{glint:\"-16711936\"}}}}");
    }

    public void glintModuleNMSExample(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 5 * 9);

        inventory.setItem(11, ItemUtil.addTag(new ItemStack(Material.DIAMOND_HELMET), "glint", "#FF5733"));
        inventory.setItem(14, ItemUtil.addTag(new ItemStack(Material.DIAMOND_HELMET), "glint", "#33FF57"));
        inventory.setItem(17, ItemUtil.addTag(new ItemStack(Material.DIAMOND_HELMET), "glint", "#3357FF"));
        inventory.setItem(29, ItemUtil.addTag(new ItemStack(Material.DIAMOND_HELMET), "glint", "#FFD700"));
        inventory.setItem(33, ItemUtil.addTag(new ItemStack(Material.DIAMOND_HELMET), "glint", -16711936));

        player.openInventory(inventory);
    }

}
