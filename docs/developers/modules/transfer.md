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
@Override
public void {
    
    }
```

### Client Response (Ping Packet)
After sending a ping packet to the player, you'll get a response from the user with their current ping to the provided server address.

<!-- insert example of a response -->
```
ping response
```

### Transfer Packet
<!-- insert code example of trasnfer packet -->
```java
@Override
public void {
    
    }
```

### Client Response (Transfer Packet)
After transferring a player, you'll get a response from the user that contains the following;

```
ping response
```
