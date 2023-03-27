package com.moonsworth.apollo.option.type;

import lombok.Value;

import java.awt.*;
import java.util.List;

/**
 * Represents a renderable string which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class RenderableString {

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
     * Returns a {@link List} of {@link RenderableString}.
     *
     * @return the renderable string children
     * @since 1.0.0
     */
    List<RenderableString> children;

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
