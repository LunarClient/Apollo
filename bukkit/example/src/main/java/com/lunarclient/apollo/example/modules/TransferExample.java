package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.transfer.PingResponse;
import com.lunarclient.apollo.module.transfer.TransferModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.util.List;

public final class TransferExample {

    public void transferExample(Player target, String serverIp) {
        // Normally we'd handle Optional being empty better
        TransferModule transferModule = Apollo.getModuleManager().getModule(TransferModule.class).orElseThrow();
        ApolloPlayer apolloPlayer = Apollo.getPlayerManager().getPlayer(target.getUniqueId()).orElseThrow();

        transferModule.transfer(apolloPlayer, serverIp)
            .onSuccess(response -> {
                String message = switch (response.getStatus()) {
                    case ACCEPTED -> "Transfer accepted! Goodbye!";
                    case REJECTED -> "Transfer rejected by client!";
                };

                target.sendMessage(Component.text(message, NamedTextColor.YELLOW));
            })
            .onFailure(exception -> {
                target.sendMessage(Component.text("Internal error! Check console!"));
                exception.printStackTrace();
            });
    }

    public void pingExample(Player target, List<String> serverIps) {
        // Normally we'd handle Optional being empty better
        TransferModule transferModule = Apollo.getModuleManager().getModule(TransferModule.class).orElseThrow();
        ApolloPlayer apolloPlayer = Apollo.getPlayerManager().getPlayer(target.getUniqueId()).orElseThrow();

        transferModule.ping(apolloPlayer, serverIps)
            .onSuccess(response -> {
                for (PingResponse.PingData pingData : response.getData()) {
                    String message = switch (pingData.getStatus()) {
                        case SUCCESS -> String.format("Ping to %s is %d ms.", pingData.getServerIp(), pingData.getPingMillis());
                        case TIMED_OUT -> String.format("Failed to ping %s", pingData.getServerIp());
                    };

                    target.sendMessage(Component.text(message, NamedTextColor.YELLOW));
                }
            })
            .onFailure(exception -> {
                target.sendMessage(Component.text("Internal error! Check console!"));
                exception.printStackTrace();
            });
    }

}
