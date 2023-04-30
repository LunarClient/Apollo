# Border Module

## Overview

The border module not only enhances Minecraft's current world border system, but also backports all the improvements and vanilla features to Minecraft 1.7 players.

* Backports all vanilla minecraft world border functionality, found in 1.8+ to the 1.7 version of Lunar Client.
  * All vanilla features such as preventing entry/exit and border expansion/shrinkage are supported.
* Adds improvements to further customize the vanilla world border, in addition to being able to create and customize Lunar Client world borders.
  * Custom border colors can be provided.
  * Ability to generate and display multiple world borders at once.

## Integration

### Sample Code

```java
public void displayBorderExample(Player player) {
    Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

    if (apolloPlayerOpt.isEmpty()) {
        player.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
        return;
    }

    borderModule.displayBorder(apolloPlayerOpt.get(), Border.builder()
        .id("pvp-tagged-spawn")
        .world("world")
        .cancelEntry(true)
        .cancelExit(false)
        .canShrinkOrExpand(false)
        .color(Color.RED)
        .bounds(Cuboid2D.builder()
            .minX(-50)
            .minZ(-50)
            .maxX(50)
            .maxZ(50)
            .build()
        )
        .durationTicks(0)
        .build()
    );
}
```

### `Border` Options

`.id(String)` should include a unique identifier for the border. It's important when you have multiple borders in a single world.
```java
.id("pvp-tagged-spawn")
```

`.world(String)` is the world, by name, that you wish to add the border to.
```java
.world("world")
```

`.cancelEntry(boolean)` is a boolean option to prevent players from entering the border, if they're outside the border bounds.
```java
.cancelEntry(true)
```

`.cancelExit(boolean)` is a boolean option to prevent players from exiting the border, if they're currently inside the border bounds.
```java
.cancelExit(false)
```

`.canShrinkOrExpand(boolean)` is a boolean option to control if the border has the ability to expand or shrink.
```java
.canShrinkOrExpand(false)
```

`.color(java.awt.Color)` is how you dictate the color of the border. See the [colors page](/apollo/developers/colors) for more.
```java
.color(ApolloColors.RED)
```
<!-- insert screenshot of red border -->

`.bounds(Cuboid2D)` is used to determine the bounds of the border, using a 2D cuboid.
```java
.bounds(Cuboid2D.builder() // Calling the 2D cuboid builder inside of Apollo
    .minX(-50) // The X value of your lowest point
    .minZ(-50) // The Z value of your lowest point
    .maxX(50) // The X value of your highest point
    .maxZ(50) // The Z value of your highest point
    .build() // Calls to the builder inside of Apollo to build the cuboid
)
```

`.durationTicks(Integer)` is used to determine the speed of expansion or shrinkage.
```java
.durationTicks(0) // 20 ticks = 1 second
```

### Removing a specific border for a player

```java
public void removeBorderExample(Player player) {
    Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

    // Removing the border with the ID "pvp-tagged-spawn" for the UUID
    borderModule.removeBorder(apolloPlayerOpt.get(), "pvp-tagged-spawn");
}
```

### Resetting all borders for a player

```java
public void resetBordersExample(Player player) {
    Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());
        
    // Resetting all borders for the UUID
    borderModule.resetBorders(apolloPlayerOpt.get());
}
```

## Public Use Cases
Some servers known to be using the border module are:
* ```play.vipermc.net```
* ```play.minecadia.com```
