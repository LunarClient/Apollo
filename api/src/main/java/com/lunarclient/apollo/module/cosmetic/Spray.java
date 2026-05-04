/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.module.cosmetic;

import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.module.packetenrichment.raytrace.Direction;
import java.time.Duration;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Range;

/**
 * Represents a spray.
 *
 * <p>Sprays are client-local and validated against loaded chunks;
 * they're removed when their backing chunk unloads and won't reappear
 * unless the server resends the spray packet.</p>
 *
 * @since 1.2.6
 */
@Getter
@Builder
public final class Spray {

    /**
     * Returns the Lunar Client spray cosmetic id.
     *
     * <p>The value must be greater than 0.</p>
     *
     * @return the spray cosmetic id
     * @since 1.2.6
     */
    @Range(from = 1, to = Integer.MAX_VALUE) int sprayId;

    /**
     * Returns the {@link ApolloBlockLocation} of the block the spray is placed on.
     *
     * @return the block location
     * @since 1.2.6
     */
    ApolloBlockLocation location;

    /**
     * Returns the {@link Direction} indicating which side the spray faces.
     *
     * @return the facing direction
     * @since 1.2.6
     */
    Direction facing;

    /**
     * Returns the spray rotation in degrees on the client.
     *
     * @return the rotation in degrees
     * @since 1.2.6
     */
    @Builder.Default
    float rotation = 0f;

    /**
     * Returns the {@link Duration} for how long the spray remains visible on the client.
     *
     * @return the display duration
     * @since 1.2.6
     */
    @Builder.Default
    Duration duration = Duration.ofSeconds(30);

}
