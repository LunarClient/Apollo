package com.lunarclient.apollo.player.ui;

import com.lunarclient.apollo.option.type.RenderableString;

import java.util.List;
import java.util.UUID;
import lombok.Value;

/**
 * Represents a nametag which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Nametag {

    /**
     * Returns the nametag player {@link UUID} uuid.
     *
     * @return the player uuid
     * @since 1.0.0
     */
    UUID player;

    /**
     * Returns the nametag {@link Boolean} hide state.
     *
     * @return the hide state
     * @since 1.0.0
     */
    boolean hide;

    /**
     * Returns a {@link List} of {@link RenderableString} nametag.
     *
     * @return the nametag
     * @since 1.0.0
     */
    List<RenderableString> nametag;

    /**
     * Returns the nametag {@link Integer} player name index.
     *
     * <p>Used to attach the lunar logo.</p>
     *
     * @return the player name index
     * @since 1.0.0
     */
    int playerNameIndex;
}
