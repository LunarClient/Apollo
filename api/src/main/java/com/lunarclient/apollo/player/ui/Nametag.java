package com.lunarclient.apollo.player.ui;

import com.lunarclient.apollo.option.type.Component;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a nametag which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder(setterPrefix = "with")
public class Nametag {

    /**
     * Returns the nametag player {@link UUID} uuid.
     *
     * @return the player uuid
     * @since 1.0.0
     */
    UUID player;

    /**
     * Returns a {@link List} of {@link Component} nametag.
     *
     * @return the nametag
     * @since 1.0.0
     */
    List<Component> nametag;
}
