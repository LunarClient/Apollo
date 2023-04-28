# Transfer Module

## Overview
Our transfer module contains both a transfer packet and a ping packet.
If used correctly, both these packets can be very powerful.
The transfer packet will allow you to send Lunar Client players to different servers, using the IP address.
The ping packet will allow you to ping different IP addresses to get the players current ping to that server.
When you combine the two packets, you can do connection optimizing for your players, so they're always connected to the best server available.

You can use these features to create multi-server networks, without the requirement of additional proxy setups.
Being fully transparent, there will be a pop-up notification to the players that they're about to be transferred from one server to another.
The players do have the option to "trust" your server, to prevent the pop-up from being displayed on future request.

<!-- insert screenshot of transfer-packet warning -->

## Integration
We're going to give you a brief overview of both the ping packet and transfer packet, respectively.

### Ping Packet
<!-- insert code example of ping packet -->
```java
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
```

### Transfer Packet
<!-- insert code example of trasnfer packet -->
```java
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
```
