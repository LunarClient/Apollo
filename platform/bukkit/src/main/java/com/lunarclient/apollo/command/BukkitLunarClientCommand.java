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
package com.lunarclient.apollo.command;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.command.type.LunarClientCommand;
import com.lunarclient.apollo.common.ApolloComponent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The general Lunar Client command.
 *
 * @since 1.0.9
 */
public final class BukkitLunarClientCommand extends LunarClientCommand<CommandSender> implements CommandExecutor {

    /**
     * Returns a new instance of this command.
     *
     * @since 1.0.9
     */
    public BukkitLunarClientCommand() {
        super((sender, component) -> sender.sendMessage(ApolloComponent.toLegacy(component)));

        this.setUsage("/lunarclient <player>");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1) {
            this.sendCommandUsage(sender);
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if (player == null) {
            this.textConsumer.accept(sender, Component.text("Player '", NamedTextColor.RED)
                .append(Component.text(args[0], NamedTextColor.RED))
                .append(Component.text("' not found!", NamedTextColor.RED)));
            return true;
        }

        Component message = Component.text("Player ", NamedTextColor.GRAY)
            .append(Component.text(player.getName(), NamedTextColor.AQUA))
            .append(Component.text(" is ", NamedTextColor.GRAY));

        if (Apollo.getPlayerManager().hasSupport(player.getUniqueId())) {
            message = message.append(Component.text("using ", NamedTextColor.GREEN));
        } else {
            message = message.append(Component.text("not using ", NamedTextColor.RED));
        }

        message = message.append(Component.text("Lunar Client!", NamedTextColor.GRAY));
        this.textConsumer.accept(sender, message);

        return true;
    }

}
