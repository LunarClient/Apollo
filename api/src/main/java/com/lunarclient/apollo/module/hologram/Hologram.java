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
package com.lunarclient.apollo.module.hologram;

import com.lunarclient.apollo.common.location.ApolloLocation;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import net.kyori.adventure.text.Component;

/**
 * Represents a hologram which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class Hologram {

    /**
     * Returns the hologram {@link String} id.
     *
     * @return the hologram id
     * @since 1.0.0
     */
    String id;

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

    /**
     * Returns the hologram {@link Boolean} show through walls state.
     *
     * @return the hologram show through walls state
     * @since 1.0.0
     */
    boolean showThroughWalls;

    /**
     * Returns the hologram {@link Boolean} show shadow state.
     *
     * @return the hologram show shadow state
     * @since 1.0.0
     */
    boolean showShadow;

    /**
     * Returns the hologram {@link Boolean} show background state.
     *
     * @return the hologram show background state
     * @since 1.0.0
     */
    boolean showBackground;

}
