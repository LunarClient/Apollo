# Beam Module

## Overview

The beam module allows you to create custom beams resembling beacon beams, which can be placed on your server to highlight specific points of interest.

* Backports all vanilla minecraft beacon beam functionality, found on 1.8+ to the 1.7 version of Lunar Client.
* Adds improvements to beacon beams for Lunar Client users.
  * Customizable colors for beams, different from the vanilla minecraft colors.
  * Exact location pinpointing for the beams to be shown on.
  * Beams appear through blocks, unlike in vanilla minecraft.

## Integration

### Sample Code

```java
public void displayBeamExample(Player player) {
    Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

    if (apolloPlayerOpt.isEmpty()) {
        player.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
        return;
    }

    beamModule.displayBeam(apolloPlayerOpt.get(), Beam.builder()
        .id("spawn-beacon")
        .color(Color.CYAN)
        .location(ApolloBlockLocation.builder()
            .world("world")
            .x(0)
            .y(60)
            .z(0)
            .build()
        )
        .build()
    );
}
```

### `Beam` Options

`.id(String)` should include a unique identifier for the beam.
```java
.id("spawn-beacon")
```

`.color(java.awt.Color)` is how you dictate the color of the beam. See the [colors page](/apollo/developers/colors) for more.
```java
.color(ApolloColors.LIGHT_PURPLE)
```

<!-- insert screenshot of light purple beam -->

`.location(ApolloBlockLocation)` used to determine the exact block you want the beam to be displayed on.
```java
.location(ApolloBlockLocation.builder() // Calling to ApolloBlockLocation
    .world("world") // The world name the beam should be displayed in
    .x(0) // The X value of the block the beam should be displayed on
    .y(60) // The Y value of the block the beam should start at
    .z(0) // The Z value of the block the beam should be displayed on
    .build() // Calls the builder inside of Apollo to build the block location
)
```

### Removing a specific beam for a player

```java
public void removeBeamExample(Player player) {
    Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

    // Removing the beam with the ID "spawn-beacon" for the player
    beamModule.removeBeam(apolloPlayerOpt.get(), "spawn-beacon");
}
```

### Resetting all beams for a player

```java
public void resetBeamsExample(Player player) {
    Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

    // Resetting all beams for the player
    beamModule.resetBeams(apolloPlayerOpt.get());
}
```

## Public Use Cases

<!-- add public use cases -->
