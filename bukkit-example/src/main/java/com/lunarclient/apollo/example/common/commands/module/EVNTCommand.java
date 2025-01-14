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
import com.lunarclient.apollo.example.common.modules.impl.EVNTExample;
import com.lunarclient.apollo.module.evnt.GuiType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class EVNTCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;
        EVNTExample evntExample = ApolloExamplePlugin.getPlugin().getEvntExample();

        if (args.length == 2 && args[0].equalsIgnoreCase("opengui")) {
            try {
                GuiType type = GuiType.valueOf(args[1].toUpperCase());
                evntExample.openGuiExample(player, type);
                player.sendMessage("Opening GUI...");
            } catch (IllegalArgumentException e) {
                player.sendMessage("Invalid GUI menu type!");
                player.sendMessage("Available GUI types: " + Arrays.toString(GuiType.values()));
            }

            return true;
        }

        if (args.length != 1) {
            this.sendUsage(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "overridehearttexture": {
                evntExample.overrideHeartTextureExample(player);
                player.sendMessage("Displaying heart texture...");
                break;
            }

            case "resethearttexture": {
                evntExample.resetHeartTextureExample(player);
                player.sendMessage("Resetting heart texture...");
                break;
            }

            case "opengui": {
                GuiType[] values = GuiType.values();
                int randomGuiType = ThreadLocalRandom.current().nextInt(values.length);

                evntExample.openGuiExample(player, values[randomGuiType]);
                player.sendMessage("Opening GUI...");
                break;
            }

            case "closegui": {
                evntExample.closeGuiExample(player);
                player.sendMessage("Closing GUI...");
                break;
            }

            case "overridecharacter": {
                evntExample.overrideCharacterExample(player);
                player.sendMessage("Overriding character...");
                break;
            }

            case "overridecharacterability": {
                evntExample.overrideCharacterAbilityExample(player);
                player.sendMessage("Overriding character ability...");
                break;
            }

            case "overridecharactercosmetic": {
                evntExample.overrideCharacterCosmeticExample(player);
                player.sendMessage("Overriding character cosmetic...");
                break;
            }

            case "overridecharacterresources": {
                evntExample.overrideCharacterResources(player);
                player.sendMessage("Overriding character resources...");
                break;
            }

            case "updategameoverview": {
                evntExample.updateGameOverviewExample();
                player.sendMessage("Updaing game overview...");
                break;
            }

            case "updatestatusoverview": {
                evntExample.updateStatusOverviewExample();
                player.sendMessage("Updaing status overview...");
                break;
            }

            default: {
                this.sendUsage(player);
                break;
            }
        }

        return true;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage("/evnt overrideHeartTexture");
        sender.sendMessage("/evnt resetHeartTexture");
        sender.sendMessage("/evnt openGui <type>");
        sender.sendMessage("/evnt closeGui");
        sender.sendMessage("/evnt overrideCharacter");
        sender.sendMessage("/evnt overrideCharacterAbility");
        sender.sendMessage("/evnt overrideCharacterCosmetic");
        sender.sendMessage("/evnt overrideCosmeticResource");
        sender.sendMessage("/evnt updateGameOverview");
        sender.sendMessage("/evnt updateStatusOverview");
    }

}
