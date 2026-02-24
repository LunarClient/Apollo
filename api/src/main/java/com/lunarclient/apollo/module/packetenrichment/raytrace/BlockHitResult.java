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
package com.lunarclient.apollo.module.packetenrichment.raytrace;

import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.common.location.ApolloLocation;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents the block hit ray trace result.
 *
 * @since 1.2.2
 */
@Getter
@Builder
public class BlockHitResult extends RayTraceResult {

    /**
     * The exact hit {@link ApolloLocation} of the ray on the block.
     *
     * @return the hit location
     * @since 1.2.2
     */
    ApolloLocation hitLocation;

    /**
     * The {@link ApolloBlockLocation} of the block that was hit.
     *
     * @return the block location
     * @since 1.2.2
     */
    ApolloBlockLocation blockLocation;

    /**
     * The {@link Direction} from which the block was hit.
     *
     * @return the direction
     * @since 1.2.2
     */
    Direction direction;

}
