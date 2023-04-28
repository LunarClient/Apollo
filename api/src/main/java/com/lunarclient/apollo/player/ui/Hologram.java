package com.lunarclient.apollo.player.ui;

import com.lunarclient.apollo.option.type.Component;
import com.lunarclient.apollo.world.ApolloLocation;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a hologram which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder(setterPrefix = "with")
public class Hologram {

    /**
     * Returns the hologram {@link UUID} id.
     *
     * @return the hologram id
     * @since 1.0.0
     */
    UUID id;

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
}
