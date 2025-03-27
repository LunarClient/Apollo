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

public class SaturationExample extends NMSExample {

    public boolean saturationModuleExample(Player player) {
        if (this.isOneEight()) {
            this.saturationModuleNMSExample(player);
            return false;
        } else {
            this.saturationModuleCommandExample(player);
            return true;
        }
    }

    public void saturationModuleCommandExample(Player player) {
        player.performCommand("summon item ~ ~1 ~ {Item:{id:\"minecraft:apple\",Count:1b,tag:{lunar:{hunger:22,saturation:3}}}}");
        player.performCommand("summon item ~ ~1 ~ {Item:{id:\"minecraft:apple\",Count:1b,tag:{lunar:{hunger:7,saturation:7}}}}");
        player.performCommand("summon item ~ ~1 ~ {Item:{id:\"minecraft:apple\",Count:1b,tag:{lunar:{hunger:30,saturation:26}}}}");
    }

    public void saturationModuleNMSExample(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 3 * 9);

        ItemStack apple = new ItemStack(Material.APPLE);
        ItemUtil.addTag(apple, "hunger", 22);
        ItemUtil.addTag(apple, "saturation", 3);

        ItemStack apple2 = new ItemStack(Material.APPLE);
        ItemUtil.addTag(apple, "hunger", 7);
        ItemUtil.addTag(apple, "saturation", 7);

        ItemStack apple3 = new ItemStack(Material.APPLE);
        ItemUtil.addTag(apple, "hunger", 30);
        ItemUtil.addTag(apple, "saturation", 26);

        inventory.setItem(11, apple);
        inventory.setItem(14, apple2);
        inventory.setItem(17, apple3);

        player.openInventory(inventory);
    }

}
