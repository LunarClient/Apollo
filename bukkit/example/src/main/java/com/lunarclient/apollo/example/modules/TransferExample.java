package com.lunarclient.apollo.example.modules;

import com.google.common.collect.Lists;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.transfer.PingResponse;
import com.lunarclient.apollo.module.transfer.TransferModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.util.Optional;

public class TransferExample {

    private final TransferModule transferModule = Apollo.getModuleManager().getModule(TransferModule.class);

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
