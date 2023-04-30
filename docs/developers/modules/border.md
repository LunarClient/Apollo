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
@Override
public void displayBorder(ApolloPlayer player, Border border) {
    ((AbstractApolloPlayer) player).sendPacket(DisplayBorderMessage.newBuilder()
    .setId(ByteString.copyFromUtf8(border.getId()))
    .setWorld(border.getWorld())
    .setCancelEntry(border.isCancelEntry())
    .setCancelExit(border.isCancelExit())
    .setCanShrinkOrExpand(border.isCanShrinkOrExpand())
    .setColor(NetworkTypes.toProtobuf(border.getColor()))
    .setBounds(NetworkTypes.toProtobuf(border.getBounds()))
    .setDurationTicks(border.getDuration())
    .build()
    );
}
```

### `Border` Options

**"setId()"** should include a unique identifier for the border.
```java
.setId(ByteString.copyFromUtf8(border.getId()))
```

**"setWorld()"** is the world, by name, that you wish to add the border to.
```java
.setWorld(border.getWorld())
```

**"setCancelEntry()"** is a boolean option to prevent players from entering the border, if they're outside the border bounds.
```java
.setCancelExit(false)
```

**"setCancelExit"** is a boolean option to prevent players from exiting the border, if they're currently inside the border bounds.
```java
.setCancelExit(false)
```

**"setCanShrinkOrExpand()"** is another boolean option to determine if the border can shrink or expand.
```java
.setCanShrinkOrExpand(true)
```

**"setColor()"** is how you dictate the color of the border, using hex colors.
```java
.setColor(#22000)
```

**"setBounds()"** is used to determine the bounds of the border.
```java
.setBounds(??)
```

**"setDurationTicks()"** is used to determine the speed of expansion or shrinkage.
```java
.setDurationTicks(10)
```

## Public Use Cases
Some servers known to be using the border module are:
* ```play.vipermc.net```
* ```play.minecadia.com```
