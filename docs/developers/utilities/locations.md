# Locations

## Overview

Apollo adds a user-friendly location builders that seamlessly integrate with all Apollo modules requiring location information.

## `ApolloLocation` Builder

```java
public class ApolloLocation {

    /**
     * Returns the world name for this location.
     *
     * @return the world name
     * @since 1.0.0
     */
    String world;

    /**
     * Returns the {@code double} X coordinate for this location.
     *
     * @return the x coordinate
     * @since 1.0.0
     */
    double x;

    /**
     * Returns the {@code double} Y coordinate for this location.
     *
     * @return the y coordinate
     * @since 1.0.0
     */
    double y;

    /**
     * Returns the {@code double} Z coordinate for this location.
     *
     * @return the z coordinate
     * @since 1.0.0
     */
    double z;

}
```

### Sample Code

```java
public static ApolloLocation locationExample() {
    return ApolloLocation.builder()
        .world("world")
        .x(50.5D)
        .y(100)
        .z(50.0D)
        .build();
}
```

## `ApolloBlockLocation` Builder

```java
public class ApolloBlockLocation {

    /**
     * Returns the world name for this location.
     *
     * @return the world name
     * @since 1.0.0
     */
    String world;

    /**
     * Returns the {@code int} X coordinate for this location.
     *
     * @return the x coordinate
     * @since 1.0.0
     */
    int x;

    /**
     * Returns the {@code int} Y coordinate for this location.
     *
     * @return the y coordinate
     * @since 1.0.0
     */
    int y;

    /**
     * Returns the {@code int} Z coordinate for this location.
     *
     * @return the z coordinate
     * @since 1.0.0
     */
    int z;

}
```

### Sample Code

```java
public static ApolloBlockLocation blockLocationExample() {
    return ApolloBlockLocation.builder()
        .world("world")
        .x(0)
        .y(100)
        .z(0)
        .build();
}
```