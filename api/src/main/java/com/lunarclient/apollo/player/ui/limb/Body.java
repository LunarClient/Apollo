package com.lunarclient.apollo.player.ui.limb;

import java.util.UUID;
import lombok.Value;

/**
 * Represents a body part which can be shown or hidden on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Body {

    /**
     * Returns the body player {@link UUID} uuid.
     *
     * @return the player uuid
     * @since 1.0.0
     */
    UUID player;

    /**
     * Returns the {@link BodyPart}.
     *
     * @return the body part
     * @since 1.0.0
     */
    BodyPart bodyPart;

    /**
     * Returns the body part {@link Boolean} hidden state.
     *
     * @return the body hidden state
     * @since 1.0.0
     */
    boolean hidden;

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
        RIGHT_LEG,
        ALL
    }
}
