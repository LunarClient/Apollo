# Colors

## Overview

Apollo exclusively uses `java.awt.Color` for all configurable colors. This page provides an outline of the most common ways
to work with colors when using Apollo.

## `ApolloColors` Predefined Colors

The `ApolloColors` class statically exposes colors that correspond to Bukkit/Spigot's `ChatColor` enum.

### Exposed Colors
* `BLACK`
* `DARK_BLUE`
* `DARK_GREEN`
* `DARK_AQUA`
* `DARK_RED`
* `DARK_PURPLE`
* `GOLD`
* `GRAY`
* `DARK_GRAY`
* `BLUE`
* `GREEN`
* `AQUA`
* `RED`
* `LIGHT_PURPLE`
* `YELLOW`
* `WHITE`

### Sample Code

```java
Border yellowBorder = Border.builder().color(ApolloColors.YELLOW).build();
```

```java
Beam purpleBeam = Beam.builder().color(ApolloColors.LIGHT_PURPLE).build();
```

## `java.awt.Color` Predefined Colors

The `java.awt.Color` class statically exposes some colors, although they do not correspond to any existing colors used in Minecraft.

### Available Colors

Please see the [Javadocs](https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html) for a comprehensive list.

### Sample Code

```java
Beam magentaBeam = Beam.builder().color(Color.MAGENTA).build();
```

## Available Colors

Custom colors can be created from any RGB values using `new Color(int red, int green, int blue)`, or from any hex color using `Color.decode(String hex)`

### Sample Code

```java
Border customBorder = Border.builder().color(new Color(25, 43, 48)).build();
```

```java
Beam customBeam = Beam.builder().color(Color.decode("#FF00FF")).build();
```
