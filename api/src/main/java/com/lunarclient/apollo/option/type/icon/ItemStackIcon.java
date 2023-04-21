package com.lunarclient.apollo.option.type.icon;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Represents an item stack icon.
 *
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = false)
@Value(staticConstructor = "of")
public class ItemStackIcon extends Icon {

    /**
     * Returns the icon {@link Integer} item id.
     *
     * @return the icon item id
     * @since 1.0.0
     */
    int itemId;

}
