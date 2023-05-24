package com.lunarclient.apollo.common;

import java.awt.Color;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a component which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class Component {

    /**
     * Returns the renderable string {@link String} content.
     *
     * @return the renderable string content
     * @since 1.0.0
     */
    String content;

    /**
     * Returns the renderable string {@link Color}.
     *
     * @return the renderable string color
     * @since 1.0.0
     */
    @Builder.Default
    Color color = Color.WHITE;

    /**
     * Returns a {@link List} of {@link TextDecorators}.
     *
     * @return the renderable string decorators
     * @since 1.0.0
     */
    @Builder.Default
    List<TextDecorators> decorators = Collections.emptyList();

    /**
     * Returns a {@link List} of {@link Component}.
     *
     * @return the renderable string children
     * @since 1.0.0
     */
    @Builder.Default
    List<Component> children = Collections.emptyList();

    /**
     * Represents the text decorator.
     *
     * @since 1.0.0
     */
    public enum TextDecorators {
        OBFUSCATED,
        BOLD,
        STRIKETHROUGH,
        UNDERLINED,
        ITALIC
    }

}
