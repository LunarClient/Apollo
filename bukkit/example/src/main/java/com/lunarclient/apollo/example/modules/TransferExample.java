package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.type.Transfer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.transfer.ServerPing;
import com.lunarclient.apollo.player.ui.transfer.ServerTransfer;
import com.lunarclient.apollo.roundtrip.async.Handler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class TransferExample implements CommandExecutor {

    public void transferPlayer(Player target, String address) {
        Optional<Transfer> transferModule = Apollo.getModuleManager().getModule(Transfer.class);
        Optional<ApolloPlayer> apolloPlayer = Apollo.getPlayerManager().getPlayer(target.getUniqueId());

        transferModule.ifPresent(module -> apolloPlayer.ifPresent(player -> {
            ServerTransfer.Request transferRequest = ServerTransfer.Request.builder()
                .withAddress(address)
                .build();

            Handler<ServerTransfer.Response> responseHandler = response -> {
                switch (response.getStatus()) {
                    case ACCEPTED -> target.sendMessage(Component.text("Transfer completed!", NamedTextColor.GREEN));
                    case REJECTED -> target.sendMessage(Component.text("Transfer failed!", NamedTextColor.RED));
                }
            };

            module.transfer(player, transferRequest, responseHandler);
        }));
    }

    public void pingServers(Player target, List<String> addresses, boolean transfer) {
        Optional<Transfer> transferModule = Apollo.getModuleManager().getModule(Transfer.class);
        Optional<ApolloPlayer> apolloPlayer = Apollo.getPlayerManager().getPlayer(target.getUniqueId());

        transferModule.ifPresent(module -> apolloPlayer.ifPresent(player -> {
            ServerPing.Request pingRequest = ServerPing.Request.builder()
                .withAddresses(addresses)
                .build();

            Handler<ServerPing.Response> responseHandler = response -> {
                for (ServerPing.Response.PingData pingData : response.getData()) {
                    switch (pingData.getStatus()) {
                        case SUCCESS -> {
                            target.sendMessage(Component.text(String.format("Your ping for %s is %d.",
                                pingData.getAddress(), pingData.getPing()), NamedTextColor.GREEN));

                            if(transfer) {
                                this.transferPlayer(target, pingData.getAddress());
                                return;
                            }
                        }

                        case TIMED_OUT -> target.sendMessage(Component.text(String.format("Ping request timed out for %s",
                            pingData.getAddress()), NamedTextColor.RED));
                    }
                }
            };

            module.ping(player, pingRequest, responseHandler);
        }));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Player only...", NamedTextColor.RED));
            return false;
        }

        if(args.length != 2) {
            player.sendMessage(Component.text("Usage: /ping <address> <transfer>"));
            return false;
        }

        String address = args[0];
        boolean transfer = Boolean.parseBoolean(args[1]);

        this.pingServers(player, List.of(address), transfer);

        return true;
    }
}
