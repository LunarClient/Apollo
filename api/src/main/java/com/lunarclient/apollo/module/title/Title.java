package com.lunarclient.apollo.module.title;

import com.lunarclient.apollo.common.Component;
import java.time.Duration;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a title which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public class Title {

    /**
     * Returns the title {@link TitleType} type.
     *
     * @return the title type
     * @since 1.0.0
     */
    TitleType type;

    /**
     * Returns the title {@link Component} message.
     *
     * @return the title message
     * @since 1.0.0
     */
    Component message;

    /**
     * Returns the title {@link Float} scale.
     *
     * @return the title scale
     * @since 1.0.0
     */
    float scale;

    /**
     * Returns the title {@link Duration} display time.
     *
     * @return the title display time
     * @since 1.0.0
     */
    Duration displayTime;

    /**
     * Returns the title {@link Duration} fade in time.
     *
     * @return the title fade in time
     * @since 1.0.0
     */
    Duration fadeInTime;

    /**
     * Returns the title {@link Duration} fade out time.
     *
     * @return the title fade out time
     * @since 1.0.0
     */
    Duration fadeOutTime;

}
