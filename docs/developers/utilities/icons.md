# Icons

Apollo adds builders for creating various types of icon entries.

## `ItemStackIcon` Builder

```java
public class ItemStackIcon extends Icon {

    /**
     * Returns the icon {@link Integer} item id.
     *
     * @return the icon item id
     * @since 1.0.0
     */
    int itemId;

}
```

### Sample Code

```java
public static ItemStackIcon itemStackIconExample() {
    return ItemStackIcon.builder()
        .itemId(Material.ENDER_PEARL.getId())
        .build();
}
```

## `SimpleResourceLocation` Builder

```java
public class SimpleResourceLocationIcon extends Icon {

    /**
     * Returns the icon {@link String} resource location.
     *
     * <p>Represents an icon that will appear for the player.</p>
     *
     * @return the icon resource location
     * @since 1.0.0
     */
    String resourceLocation;

    /**
     * Returns the icon {@link Integer} size.
     *
     * @return the icon size
     * @since 1.0.0
     */
    int size;

}
```

### Sample Code

```java
public static SimpleResourceLocationIcon simpleResourceLocationIconExample() {
    return SimpleResourceLocationIcon.builder()
        .resourceLocation("icons/server-logo.png")
        .size(1)
        .build();
}
```

## `AdvancedResourceLocationIcon` Builder

```java
public class AdvancedResourceLocationIcon extends Icon {

    /**
     * Returns the icon {@link String} resource location.
     *
     * <p>Represents an icon that will appear for the player.</p>
     *
     * @return the icon resource location
     * @since 1.0.0
     */
    String resourceLocation;

    /**
     * Returns the icon width {@link Float}.
     *
     * <p>Size of the image width (in pixels).</p>
     *
     * @return the icon width
     * @since 1.0.0
     */
    float width;

    /**
     * Returns the icon height {@link Float}.
     *
     * <p>Size of the image height (in pixels).</p>
     *
     * @return the icon height
     * @since 1.0.0
     */
    float height;

    /**
     * Returns the icon min u {@link Float}.
     *
     * <p>Range of 0-1 (the x location on a TextureAtlas).</p>
     *
     * @return the icon min u
     * @since 1.0.0
     */
    float minU;

    /**
     * Returns the icon max u {@link Float}.
     *
     * <p>Range of 0-1 (the x location on a TextureAtlas).</p>
     *
     * @return the icon max u
     * @since 1.0.0
     */
    float maxU;

    /**
     * Returns the icon min v {@link Float}.
     *
     * <p>Range of 0-1 (the y location on a TextureAtlas).</p>
     *
     * @return the icon min v
     * @since 1.0.0
     */
    float minV;

    /**
     * Returns the icon max v {@link Float}.
     *
     * <p>Range of 0-1 (the y location on a TextureAtlas).</p>
     *
     * @return the icon max v
     * @since 1.0.0
     */
    float maxV;

}
```

### Sample Code

```java
public static AdvancedResourceLocationIcon advancedResourceLocationIconExample() {
    return AdvancedResourceLocationIcon.builder()
        .resourceLocation("icons/server-sprite.png")
        .width(512)
        .height(512)
        .minU(1)
        .maxU(1)
        .minV(1)
        .maxV(1)
        .build();
}
```
