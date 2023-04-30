package com.lunarclient.apollo.module.nametag;

import com.lunarclient.apollo.common.Component;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a nametag which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public class Nametag {

    /**
     * Returns a {@link List} of {@link Component} nametag.
     *
     * @return the nametag
     * @since 1.0.0
     */
    List<Component> lines;

}
