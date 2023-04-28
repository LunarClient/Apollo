package com.lunarclient.apollo.option.type.icon;

import lombok.Builder;
import lombok.Getter;

/**
 * Represents an item stack icon.
 *
 * @since 1.0.0
 */
@Getter
@Builder(setterPrefix = "with")
public class ItemStackIcon extends Icon {

    /**
     * Returns the icon {@link Integer} item id.
     *
     * @return the icon item id
     * @since 1.0.0
     */
    int itemId;

}
