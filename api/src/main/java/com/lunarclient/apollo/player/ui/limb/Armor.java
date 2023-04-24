package com.lunarclient.apollo.player.ui.limb;

import java.util.Set;
import java.util.UUID;
import lombok.Value;

/**
 * Represents an armor which can be shown or hidden on the client.
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
     * Returns a {@link Set} of {@link ArmorPiece}s.
     *
     * @return the armor piece set
     * @since 1.0.0
     */
    Set<ArmorPiece> armorPiece;

    /**
     * Represents an armor part.
     *
     * @since 1.0.0
     */
    public enum ArmorPiece {

        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS;
    }
}
