package com.lunarclient.apollo.common.icon;

import lombok.Builder;
import lombok.Getter;

/**
 * Represents an item stack icon.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class ItemStackIcon extends Icon {

    /**
     * Returns the icon {@link Integer} item id.
     *
     * @return the icon item id
     * @since 1.0.0
     */
    int itemId;

}
