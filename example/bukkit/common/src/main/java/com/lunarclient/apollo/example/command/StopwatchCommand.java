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
import com.lunarclient.apollo.example.module.impl.StopwatchExample;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StopwatchCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage("Usage: /stopwatch stopwatch <add|remove|start|stop|reset|resetall>");
            player.sendMessage("Usage: /stopwatch timer <add|remove|start|stop|reset|resetall>");
            return true;
        }

        StopwatchExample stopwatchExample = ApolloExamplePlugin.getInstance().getStopwatchExample();
        String type = args[0].toLowerCase();
        String action = args[1].toLowerCase();

        switch (type) {
            case "stopwatch": {
                this.handleStopwatch(player, stopwatchExample, action);
                break;
            }

            case "timer": {
                this.handleTimer(player, stopwatchExample, action);
                break;
            }

            default: {
                player.sendMessage("Usage: /stopwatch stopwatch <add|remove|start|stop|reset|resetall>");
                player.sendMessage("Usage: /stopwatch timer <add|remove|start|stop|reset|resetall>");
                break;
            }
        }

        return true;
    }

    private void handleStopwatch(Player player, StopwatchExample example, String action) {
        switch (action) {
            case "add": {
                example.addStopwatchExample(player);
                player.sendMessage("Adding stopwatch...");
                break;
            }

            case "remove": {
                example.removeStopwatchExample(player);
                player.sendMessage("Removing stopwatch...");
                break;
            }

            case "start": {
                example.startStopwatchExample(player);
                player.sendMessage("Starting stopwatch...");
                break;
            }

            case "stop": {
                example.stopStopwatchExample(player);
                player.sendMessage("Stopping stopwatch...");
                break;
            }

            case "reset": {
                example.resetStopwatchExample(player);
                player.sendMessage("Resetting stopwatch...");
                break;
            }

            case "resetall": {
                example.resetStopwatchesExample(player);
                player.sendMessage("Resetting all stopwatches...");
                break;
            }

            default: {
                player.sendMessage("Usage: /stopwatch stopwatch <add|remove|start|stop|reset|resetall>");
                break;
            }
        }
    }

    private void handleTimer(Player player, StopwatchExample example, String action) {
        switch (action) {
            case "add": {
                example.addTimerExample(player);
                player.sendMessage("Adding timer...");
                break;
            }

            case "remove": {
                example.removeTimerExample(player);
                player.sendMessage("Removing timer...");
                break;
            }

            case "start": {
                example.startTimerExample(player);
                player.sendMessage("Starting timer...");
                break;
            }

            case "stop": {
                example.stopTimerExample(player);
                player.sendMessage("Stopping timer...");
                break;
            }

            case "reset": {
                example.resetTimerExample(player);
                player.sendMessage("Resetting timer...");
                break;
            }

            case "resetall": {
                example.resetTimersExample(player);
                player.sendMessage("Resetting all timers...");
                break;
            }

            default: {
                player.sendMessage("Usage: /stopwatch timer <add|remove|start|stop|reset|resetall>");
                break;
            }
        }
    }
}
