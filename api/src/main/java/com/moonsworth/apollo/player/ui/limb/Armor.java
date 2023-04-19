package com.moonsworth.apollo.player.ui.limb;

import java.util.UUID;
import lombok.Value;

/**
 * Represents an armor part which can be shown or hidden on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Armor {

    /**
     * Returns the armor player {@link UUID} uuid.
     *
     * @return the player uuid
     * @since 1.0.0
     */
    UUID player;

    /**
     * Returns the {@link ArmorPart}.
     *
     * @return the armor part
     * @since 1.0.0
     */
    ArmorPart armorPart;

    /**
     * Returns the armor part {@link Boolean} hidden state.
     *
     * @return the armor hidden state
     * @since 1.0.0
     */
    boolean hidden;

    /**
     * Represents an armor part.
     *
     * @since 1.0.0
     */
    public enum ArmorPart {

        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS,
        ALL
    }
}
