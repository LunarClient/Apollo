package com.lunarclient.apollo.player.ui.limb;

import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a body part which can be shown or hidden on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder(setterPrefix = "with")
public class Body {

    /**
     * Returns the body player {@link UUID} uuid.
     *
     * @return the player uuid
     * @since 1.0.0
     */
    UUID player;

    /**
     * Returns a {@link Set} of {@link BodyPart}s.
     *
     * @return the body part set
     * @since 1.0.0
     */
    Set<BodyPart> bodyPart;

    /**
     * Represents a body part.
     *
     * @since 1.0.0
     */
    public enum BodyPart {

        HEAD,
        TORSO,
        LEFT_ARM,
        RIGHT_ARM,
        LEFT_LEG,
        RIGHT_LEG
    }
}
