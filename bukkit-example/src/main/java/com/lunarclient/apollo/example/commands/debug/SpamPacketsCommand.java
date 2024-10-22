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
package com.lunarclient.apollo.example.commands.debug;

import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.debug.SpamPacketDebug;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpamPacketsCommand implements CommandExecutor {

    private final SpamPacketDebug spamPacketDebug = ApolloExamplePlugin.getPlugin().getSpamPacketDebug();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 2) {
            this.sendUsage(player);
        } else {
            switch (args[1].toLowerCase()) {
                case "start": {
                    int amount = args.length > 2 ? Integer.parseInt(args[2]) : Integer.MAX_VALUE;
                    int delay = args.length > 3 ? Integer.parseInt(args[3]) : 50;

                    this.spamPacketDebug.start(player, amount, delay, () -> player.sendMessage("Debug completed!"));

                    player.sendMessage("Debug started. (Amount: " + amount + ", Delay: " + delay + "ms)");
                    break;
                }

                case "stop": {
                    this.spamPacketDebug.stop(player);
                    player.sendMessage("Debug stopped.");
                    break;
                }

                case "stopall": {
                    this.spamPacketDebug.stopAll();
                    player.sendMessage("Debug stopped for all debuggers.");
                    break;
                }

                default: {
                    this.sendUsage(player);
                    break;
                }
            }
        }

        return true;
    }

    private void sendUsage(Player player) {
        player.sendMessage(ApolloComponent.toLegacy(Component.text()
            .append(Component.text("-------------------------------------", NamedTextColor.GRAY, TextDecoration.STRIKETHROUGH))
            .appendNewline()
            .append(Component.text("/apollodebug spampackets start <amount> <delay> ", NamedTextColor.WHITE))
            .append(Component.text("# Starts spamming modsetting update packets to the client, delay is in milliseconds.", NamedTextColor.GREEN))
            .appendNewline()
            .append(Component.text("/apollodebug spampackets stop ", NamedTextColor.WHITE))
            .append(Component.text("# Stop spamming modsetting update packets to the client.", NamedTextColor.GREEN))
            .appendNewline()
            .append(Component.text("/apollodebug spampackets stopall ", NamedTextColor.WHITE))
            .append(Component.text("# Stops spamming modsetting update packets to the client, for all debuggers.", NamedTextColor.GREEN))
            .appendNewline()
            .append(Component.text("-------------------------------------", NamedTextColor.GRAY, TextDecoration.STRIKETHROUGH))
            .build()
        ));
    }
}
