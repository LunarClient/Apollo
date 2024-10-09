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
package com.lunarclient.apollo.example.api.examples;

import com.google.common.collect.Lists;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.example.common.modules.impl.TransferExample;
import com.lunarclient.apollo.module.transfer.PingResponse;
import com.lunarclient.apollo.module.transfer.TransferModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Optional;
import org.bukkit.entity.Player;

public class TransferApiExample extends TransferExample {

    private final TransferModule transferModule = Apollo.getModuleManager().getModule(TransferModule.class);

    @Override
    public void transferExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        if (!apolloPlayerOpt.isPresent()) {
            viewer.sendMessage("Join with Lunar Client to test this feature!");
            return;
        }

        // Sending the transfer request to the player, to transfer them to "mc.hypixel.net"
        this.transferModule.transfer(apolloPlayerOpt.get(), "mc.hypixel.net")
            .onSuccess(response -> {
                String message = "";

                switch (response.getStatus()) {
                    case ACCEPTED: {
                        message = "Transfer accepted! Goodbye!";
                        break;
                    }

                    case REJECTED: {
                        message = "Transfer rejected by client!";
                        break;
                    }
                }

                viewer.sendMessage(message);
            })
            .onFailure(exception -> {
                viewer.sendMessage("Internal error! Check console!");
                exception.printStackTrace();
            });
    }

    @Override
    public void pingExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        if (!apolloPlayerOpt.isPresent()) {
            viewer.sendMessage("Join with Lunar Client to test this feature!");
            return;
        }

        // Sending the ping request to the player, for the servers "mc.hypixel.net" & "minehut.com".
        this.transferModule.ping(apolloPlayerOpt.get(), Lists.newArrayList("mc.hypixel.net", "minehut.com"))
            .onSuccess(response -> {
                for (PingResponse.PingData pingData : response.getData()) {
                    String message = "";

                    switch (pingData.getStatus()) {
                        // Displays successful ping request to display the server IP and the players ping to that server.
                        case SUCCESS: {
                            message = String.format("Ping to %s is %d ms.", pingData.getServerIp(), pingData.getPingMillis());
                            break;
                        }

                        // If the ping request times-out
                        case TIMED_OUT: {
                            message = String.format("Failed to ping %s", pingData.getServerIp());
                            break;
                        }
                    }

                    viewer.sendMessage(message);
                }
            })
            .onFailure(exception -> {
                viewer.sendMessage("Internal error! Check console!");
                exception.printStackTrace();
            });
    }

}
