package com.lunarclient.apollo.option.type;

import java.awt.Color;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a component which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder(setterPrefix = "with")
public class Component {

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
    Color color;

    /**
     * Returns a {@link List} of {@link TextDecorators}.
     *
     * @return the renderable string decorators
     * @since 1.0.0
     */
    List<TextDecorators> decorators;

    /**
     * Returns a {@link List} of {@link Component}.
     *
     * @return the renderable string children
     * @since 1.0.0
     */
    List<Component> children;

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
