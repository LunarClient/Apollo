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
import com.lunarclient.apollo.example.modules.EVNTExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EVNTCommand implements CommandExecutor {

    private final EVNTExample evntExample = ApolloExamplePlugin.getPlugin().getEvntExample();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Usage: /evnt <overrideHeartTexture" +
                "|resetHeartTexture" +
                "|openGui" +
                "|closeGui" +
                "|overrideCharacter" +
                "|overrideCharacterAbility" +
                "|overrideCharacterCosmetic" +
                "|updateGameOverview" +
                "|updateStatusOverview>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "overridehearttexture": {
                this.evntExample.overrideHeartTextureExample(player);
                player.sendMessage("Displaying heart texture...");
                break;
            }

            case "resethearttexture": {
                this.evntExample.resetHeartTextureExample(player);
                player.sendMessage("Resetting heart texture...");
                break;
            }

            case "opengui": {
                this.evntExample.openGuiExample(player);
                player.sendMessage("Opening GUI...");
                break;
            }

            case "closegui": {
                this.evntExample.closeGuiExample(player);
                player.sendMessage("Closing GUI...");
                break;
            }

            case "overridecharacter": {
                this.evntExample.overrideCharacterExample(player);
                player.sendMessage("Overriding character...");
                break;
            }

            case "overridecharacterability": {
                this.evntExample.overrideCharacterAbilityExample(player);
                player.sendMessage("Overriding character ability...");
                break;
            }

            case "overridecharactercosmetic": {
                this.evntExample.overrideCharacterCosmeticExample(player);
                player.sendMessage("Overriding character cosmetic...");
                break;
            }

            case "updategameoverview": {
                this.evntExample.updateGameOverviewExample();
                player.sendMessage("Updaing game overview...");
                break;
            }

            case "updatestatusoverview": {
                this.evntExample.updateStatusOverviewExample();
                player.sendMessage("Updaing status overview...");
                break;
            }

            default: {
                player.sendMessage("Usage: /evnt <overrideHeartTexture" +
                    "|resetHeartTexture" +
                    "|openGui" +
                    "|closeGui" +
                    "|overrideCharacter" +
                    "|overrideCharacterAbility" +
                    "|overrideCharacterCosmetic" +
                    "|updateGameOverview" +
                    "|updateStatusOverview>");
                break;
            }
        }

        return true;
    }

}
