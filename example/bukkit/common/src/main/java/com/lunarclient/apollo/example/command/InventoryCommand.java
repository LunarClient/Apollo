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
import com.lunarclient.apollo.example.module.impl.InventoryExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class InventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            this.sendUsage(player);
            return true;
        }

        InventoryExample inventoryExample = ApolloExamplePlugin.getInstance().getInventoryExample();

        switch (args[0].toLowerCase()) {
            case "giveitems": {
                if (inventoryExample.inventoryModuleExample(player)) {
                    player.sendMessage("Giving items...");
                } else {
                    player.sendMessage("Displaying menu...");
                }

                break;
            }

            case "displaymenu": {
                inventoryExample.displayMenuLayoutExample(player);
                player.sendMessage("Displaying menu layout buttons...");
                break;
            }

            case "displayhub": {
                inventoryExample.displayHubLayoutExample(player);
                player.sendMessage("Displaying hub layout buttons...");
                break;
            }

            case "displayminigame": {
                inventoryExample.displayMinigameLayoutExample(player);
                player.sendMessage("Displaying minigame layout buttons...");
                break;
            }

            case "displaystaff": {
                inventoryExample.displayStaffLayoutExample(player);
                player.sendMessage("Displaying staff layout buttons...");
                break;
            }

            case "removebutton": {
                inventoryExample.removeInventoryButtonExample(player);
                player.sendMessage("Removing buttons...");
                break;
            }

            case "updatebutton": {
                inventoryExample.updateInventoryButtonExample(player);
                player.sendMessage("Updating the vote button...");
                break;
            }

            case "resetbuttons": {
                inventoryExample.resetInventoryButtonsExample(player);
                player.sendMessage("Resetting buttons...");
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
        player.sendMessage("Usage: /inventory giveItems");
        player.sendMessage("Usage: /inventory displayMenu");
        player.sendMessage("Usage: /inventory displayHub");
        player.sendMessage("Usage: /inventory displayMinigame");
        player.sendMessage("Usage: /inventory displayStaff");
        player.sendMessage("Usage: /inventory removeButton");
        player.sendMessage("Usage: /inventory updateButton");
        player.sendMessage("Usage: /inventory resetButtons");
    }
}
