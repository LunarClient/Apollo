/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.module.notification;

import java.time.Duration;
import lombok.Builder;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a notification which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class Notification {

    /**
     * Returns the notification {@link String} title.
     *
     * @deprecated This field is deprecated since version 1.0.6,
     *             use {@link Notification#titleComponent} instead
     * @return the notification title
     * @since 1.0.0
     */
    @Deprecated String title;

    /**
     * Returns the notification {@link String} description.
     *
     * @deprecated This field is deprecated since version 1.0.6,
     *             use {@link Notification#descriptionComponent} instead
     * @return the notification description
     * @since 1.0.0
     */
    @Deprecated String description;

    /**
     * Returns the notification {@link String} title component.
     *
     * @return the notification title component
     * @since 1.0.6
     */
    Component titleComponent;

    /**
     * Returns the notification {@link String} description component.
     *
     * @return the notification description component
     * @since 1.0.6
     */
    Component descriptionComponent;

    /**
     * Returns the notification {@link String} resource location.
     *
     * <p>Represents an icon that will appear for the player
     * if empty (null) it'll display a generic info message</p>
     *
     * @return the notification resource location
     * @since 1.0.0
     */
    @Nullable String resourceLocation;

    /**
     * Returns the notification {@link Duration} display time.
     *
     * @return the notification display time
     * @since 1.0.0
     */
    Duration displayTime;
}
