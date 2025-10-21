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
package com.lunarclient.apollo.example.api.mods;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Map;
import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ModStatusCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("/modstatus <player>");
            sender.sendMessage("/modstatus <player> <option>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage("Player '" + args[0] + "' not found!");
            return true;
        }

        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(target.getUniqueId());
        if (!apolloPlayerOpt.isPresent()) {
            sender.sendMessage("Player '" + args[0] + "' not running Lunar Client!");
            return true;
        }

        if (args.length == 1) {
            this.sendCurrent(sender, apolloPlayerOpt.get());
            return true;
        }

        if (args.length == 2) {
            Option<?, ?, ?> option = ApolloManager.getModsManager().getPlayerOptions().getOptionsByKey().get(args[1]);

            if (option == null) {
                sender.sendMessage("Option not found!");
                return true;
            }

            Object value = apolloPlayerOpt.get().getModStatus().get(option);
            sender.sendMessage("Current value for \"" + option.getKey() + "\" is \"" + value + "\"");
            return true;
        }

        sender.sendMessage("/modstatus <player>");
        sender.sendMessage("/modstatus <player> <option>");
        return true;
    }

    private void sendCurrent(CommandSender sender, ApolloPlayer target) {
        Map<Option<?, ?, ?>, Object> playerOptions = ApolloManager.getModsManager().getPlayerOptions().getPlayerOptions().get(target.getUniqueId());

        sender.sendMessage("-------------------------------------");
        sender.sendMessage("Target: " + target.getName());
        sender.sendMessage("");

        for (Map.Entry<Option<?, ?, ?>, Object> entry : playerOptions.entrySet()) {
            sender.sendMessage(" - " + entry.getKey().getKey() + "=" + entry.getValue());
        }

        sender.sendMessage("-------------------------------------");
    }
}
