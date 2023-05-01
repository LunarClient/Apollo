# Cooldown Module

## Overview

Apollo's cooldown module, allows servers to interact with the cooldown mod found within Luanr Client.

* Create your own cooldowns for the Lunar Client cooldown mod.
  * Add your own custom icon, visible within the cooldown.
  * Set your own duration for each cooldown.

## Integration

### Sample Code

```java
public void displayCooldownExample(Player viewer) {
    Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

    apolloPlayerOpt.ifPresentOrElse(apolloPlayer -> {
        this.cooldownModule.displayCooldown(apolloPlayer, Cooldown.builder()
            .name("enderpearl-cooldown")
            .duration(Duration.ofSeconds(15))
            .icon(IconExample.itemStackIconExample())
            .build()
         );
    }, () -> viewer.sendMessage(Component.text("Join with Lunar Client to test this feature!")));
}
```

### `Cooldown` Options

`.name(String)` should include a unique identifier for the each cooldown.
```java
.name("enderpearl-cooldown")
```

`.duration(java.time.Duration)` the duration the cooldown should last for. See the [java.time.Duration Javadocs](https://docs.oracle.com/javase/8/docs/api/java/time/Duration.html) for more.
```java
.duration(Duration.ofSeconds(15))
```

`.icon(itemStackIcon)` is how you display a custom icon. Read the [icons utilities page](/apollo/developers/utilities/icons) to learn more about icons.
```java
.icon(ItemStackIcon.builder().itemId(Material.ENDER_PEARL.getId()).build())
```

### Removing a specific cooldown for a player

```java
public void removeCooldownExample(Player viewer) {
    Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
    
    // Removing the cooldown with the name "enderpearl-cooldown" from the player
    apolloPlayerOpt.ifPresent(apolloPlayer -> this.cooldownModule.removeCooldown(apolloPlayer, "enderpearl-cooldown"));
}
```

### Resetting all cooldowns for a player

```java
public void resetCooldownsExample(Player viewer) {
    Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

    if (apolloPlayerOpt.isEmpty()) {
        viewer.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
        return;
    }

    // Resetting all cooldowns for the player
    this.cooldownModule.resetCooldowns(apolloPlayerOpt.get());
}
```
