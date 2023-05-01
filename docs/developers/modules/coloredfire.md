# Colored Fire Module

## Overview

The colored fire module adds the ability to change the color of flame that appears to be on a burning entity.

## Integration

### Sample Code

```java
public void overrideColoredFireExample(UUID burningPlayer) {
    Collection<ApolloPlayer> viewers = Apollo.getPlayerManager().getPlayers();

    this.coloredFireModule.overrideColoredFire(viewers,
        burningPlayer, 
        Color.BLUE
    );
}
```
