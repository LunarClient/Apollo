package com.lunarclient.apollo.player.ui;

import com.lunarclient.apollo.option.type.RenderableString;
import com.lunarclient.apollo.world.ApolloLocation;
import java.util.List;
import java.util.UUID;
import lombok.Value;

/**
 * Represents a hologram which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
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
     * Returns a {@link List} of {@link RenderableString} lines.
     *
     * @return the lines
     * @since 1.0.0
     */
    List<RenderableString> lines;
}
