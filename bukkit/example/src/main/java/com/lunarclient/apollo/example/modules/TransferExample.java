package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.transfer.PingResponse;
import com.lunarclient.apollo.module.transfer.TransferModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class TransferExample {

    private TransferModule transferModule;

    public void transferExample(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        if (apolloPlayerOpt.isEmpty()) {
            player.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
            return;
        }

        this.transferModule.transfer(apolloPlayerOpt.get(), "mc.hypixel.net")
            .onSuccess(response -> {
                String message = switch (response.getStatus()) {
                    case ACCEPTED -> "Transfer accepted! Goodbye!";
                    case REJECTED -> "Transfer rejected by client!";
                };

                player.sendMessage(Component.text(message, NamedTextColor.YELLOW));
            })
            .onFailure(exception -> {
                player.sendMessage(Component.text("Internal error! Check console!"));
                exception.printStackTrace();
            });
    }

    public void pingExample(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        if (apolloPlayerOpt.isEmpty()) {
            player.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
            return;
        }

        this.transferModule.ping(apolloPlayerOpt.get(), List.of("mc.hypixel.net", "minehut.com"))
            .onSuccess(response -> {
                for (PingResponse.PingData pingData : response.getData()) {
                    String message = switch (pingData.getStatus()) {
                        case SUCCESS -> String.format("Ping to %s is %d ms.", pingData.getServerIp(), pingData.getPingMillis());
                        case TIMED_OUT -> String.format("Failed to ping %s", pingData.getServerIp());
                    };

                    player.sendMessage(Component.text(message, NamedTextColor.YELLOW));
                }
            })
            .onFailure(exception -> {
                player.sendMessage(Component.text("Internal error! Check console!"));
                exception.printStackTrace();
            });
    }

}
