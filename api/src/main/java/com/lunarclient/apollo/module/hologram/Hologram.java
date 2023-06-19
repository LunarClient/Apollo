package com.lunarclient.apollo.module.hologram;

import com.lunarclient.apollo.common.Component;
import com.lunarclient.apollo.common.location.ApolloLocation;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a hologram which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class Hologram {

    /**
     * Returns the hologram {@link String} id.
     *
     * @return the hologram id
     * @since 1.0.0
     */
    String id;

    /**
     * Returns the hologram {@link ApolloLocation}.
     *
     * @return the hologram location
     * @since 1.0.0
     */
    ApolloLocation location;

    /**
     * Returns a {@link List} of {@link Component} lines.
     *
     * @return the lines
     * @since 1.0.0
     */
    List<Component> lines;

    /**
     * Returns the hologram {@link Boolean} show through walls state.
     *
     * @return the hologram show through walls state
     * @since 1.0.0
     */
    boolean showThroughWalls;

    /**
     * Returns the hologram {@link Boolean} show shadow state.
     *
     * @return the hologram show shadow state
     * @since 1.0.0
     */
    boolean showShadow;

    /**
     * Returns the hologram {@link Boolean} show background state.
     *
     * @return the hologram show background state
     * @since 1.0.0
     */
    boolean showBackground;

}
