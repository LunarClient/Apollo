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

<!-- Add sample code of using ender pearl as an icon -->

```java

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

<!-- Add sample code of using a resource location to make an icon -->

```java

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

<!-- Add sample code of using a sprite to create an icon -->

```java

```
