package com.lunarclient.apollo.module.team;

import com.lunarclient.apollo.common.location.ApolloLocation;
import java.awt.Color;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a team which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class TeamMember {

    /**
     * The UUID of this team member.
     *
     * @since 1.0.0
     */
    UUID playerUuid;

    /**
     * Returns the team member's assigned {@link Color} - this will be used
     * for any markers (such as on duration HUD, above head markers, etc).
     *
     * @return the team member's marker color
     * @since 1.0.0
     */
    Color markerColor;

    /**
     * Returns the team member's {@link ApolloLocation}.
     *
     * <p>The provided location is only used when the player is out of
     * render distance for the observer. If you know that the players
     * are close, you don't need to send the location.</p>
     *
     * @return the team member location
     * @since 1.0.0
     */
    ApolloLocation location;

}
