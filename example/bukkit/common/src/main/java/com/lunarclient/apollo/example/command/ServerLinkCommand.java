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
import com.lunarclient.apollo.example.module.impl.ServerLinkExample;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ServerLinkCommand implements CommandExecutor {

    private static final List<String> LEGACY_PLACEMENTS = Arrays.asList("NEW_ROW", "REPLACE_ACHIEVEMENTS", "REPLACE_STATISTICS");
    private static final List<String> MODERN_PLACEMENTS = Arrays.asList("REPLACE_REPORT_BUGS", "REPLACE_ACHIEVEMENTS", "REPLACE_STATISTICS");

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

        ServerLinkExample serverLinkExample = ApolloExamplePlugin.getInstance().getServerLinkExample();

        switch (args[0].toLowerCase()) {
            case "overrideresource": {
                serverLinkExample.overrideServerLinkResourceExample(player);
                player.sendMessage("Overriding server link resource...");
                break;
            }

            case "resetresource": {
                serverLinkExample.resetServerLinkResourceExample(player);
                player.sendMessage("Resetting server link resource...");
                break;
            }

            case "addserverlink": {
                serverLinkExample.addServerLinkExample(player);
                player.sendMessage("Adding server link...");
                break;
            }

            case "removeserverlink": {
                serverLinkExample.removeServerLinkExample(player);
                player.sendMessage("Removing server link...");
                break;
            }

            case "resetserverlinks": {
                serverLinkExample.resetServerLinksExample(player);
                player.sendMessage("Resetting server links...");
                break;
            }

            case "legacyplacement": {
                if (args.length != 2) {
                    player.sendMessage("Usage: /serverlink legacyPlacement <" + String.join("|", LEGACY_PLACEMENTS) + ">");
                    return true;
                }

                String placement = args[1].toUpperCase();

                if (!LEGACY_PLACEMENTS.contains(placement)) {
                    player.sendMessage("Invalid placement, must be one of: " + String.join("|", LEGACY_PLACEMENTS));
                    return true;
                }

                serverLinkExample.setLegacyButtonPlacementExample(placement);
                player.sendMessage("Legacy server link button placement has been set to " + placement);
                break;
            }

            case "modernplacement": {
                if (args.length != 2) {
                    player.sendMessage("Usage: /serverlink modernPlacement <" + String.join("|", MODERN_PLACEMENTS) + ">");
                    return true;
                }

                String placement = args[1].toUpperCase();

                if (!MODERN_PLACEMENTS.contains(placement)) {
                    player.sendMessage("Invalid placement, must be one of: " + String.join("|", MODERN_PLACEMENTS));
                    return true;
                }

                serverLinkExample.setModernButtonPlacementExample(placement);
                player.sendMessage("Modern server link button placement has been set to " + placement);
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
        player.sendMessage("Usage:");
        player.sendMessage("/serverlink <overrideResource|resetResource|addServerLink|removeServerLink|resetServerLinks>");
        player.sendMessage("/serverlink legacyPlacement <" + String.join("|", LEGACY_PLACEMENTS) + ">");
        player.sendMessage("/serverlink modernPlacement <" + String.join("|", MODERN_PLACEMENTS) + ">");
    }
}
