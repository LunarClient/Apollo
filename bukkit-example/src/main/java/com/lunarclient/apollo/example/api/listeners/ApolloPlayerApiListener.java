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
package com.lunarclient.apollo.example.api.listeners;

import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.example.api.examples.TeamApiExample;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

public class ApolloPlayerApiListener implements ApolloListener {

    private final ApolloExamplePlugin plugin;
    private final TeamApiExample.Team defaultTeam;

    public ApolloPlayerApiListener(ApolloExamplePlugin plugin) {
        this.plugin = plugin;
        this.defaultTeam = ((TeamApiExample) this.plugin.getTeamExample()).createTeam();

        EventBus.getBus().register(this);
    }

    public void disable() {
        EventBus.getBus().unregister(this);
    }

    @Listen
    private void onApolloRegister(ApolloRegisterPlayerEvent event) {
        ApolloPlayer apolloPlayer = event.getPlayer();
        Player player = (Player) apolloPlayer.getPlayer();

        // Default team view markers
        this.defaultTeam.addMember(player);

        this.plugin.getBeamExample().displayBeamExample(player);
        this.plugin.getBorderExample().displayBorderExample(player);
        this.plugin.getCooldownExample().displayCooldownItemExample(player);
        this.plugin.getNametagExample().overrideNametagExample(player);
        this.plugin.getWaypointExample().displayWaypointExample(player);
    }

}
