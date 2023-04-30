# Colors

## Overview

Apollo exclusively uses `java.awt.Color`s for all configurable colors. This page provides an outline of the most common ways
to work with `java.awt.Color`s when using Apollo;

## `ApolloColors` Predefined Colors

The `ApolloColors` class statically exposes colors that correspond to Bukkit/Spigot's `ChatColor` enum.

### Sample Code

```java
Border yellowBorder = Border.builder().color(ApolloColors.YELLOW).build();
```

### Exposed Colors
* <p style="color: red">█</p> `RED`

## `java.awt.Color` Predefined Colors

The `java.awt.Color` class statically exposes colors, although they do not correspond to any existing colors used in Minecraft.

Please see the [Javadocs](https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html) for a comprehensive list.

## Custom Colors

Custom colors can be created from any R/G/B combination using `new java.awt.Color(red, green, blue)`.
