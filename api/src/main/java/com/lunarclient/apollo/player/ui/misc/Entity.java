package com.lunarclient.apollo.player.ui.misc;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents an entity.
 *
 * @since 1.0.0
 */
@Getter
@Builder(setterPrefix = "with")
public class Entity {

    /**
     * Returns the entity {@link UUID} uuid.
     *
     * @return the entity uuid
     * @since 1.0.0
     */
    UUID id;

}
