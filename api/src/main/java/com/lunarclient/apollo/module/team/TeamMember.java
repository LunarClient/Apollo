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
package com.lunarclient.apollo.module.team;

import com.lunarclient.apollo.common.location.ApolloLocation;
import java.awt.Color;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import net.kyori.adventure.text.Component;

/**
 * Represents a team which can be shown on the client.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class TeamMember {

    /**
     * The UUID of this team member.
     *
     * @since 1.0.0
     */
    UUID playerUuid;

    /**
     * Returns the team member's {@link Component}.
     *
     * <p>The display name is only used when the player
     * is out of render distance for the observer and when the
     * observer hovers over the marker.</p>
     *
     * @return the team member's display name
     * @since 1.0.0
     */
    Component displayName;

    /**
     * Returns the team member's assigned {@link Color} - this will be used
     * for any markers (such as on duration HUD, above head markers, etc).
     *
     * @return the team member's marker color
     * @since 1.0.0
     */
    Color markerColor;

    /**
     * Returns the team member's {@link ApolloLocation}.
     *
     * <p>The provided location is only used when the player is out of
     * render distance for the observer. If you know that the players
     * are close, you don't need to send the location.</p>
     *
     * @return the team member location
     * @since 1.0.0
     */
    ApolloLocation location;
}
