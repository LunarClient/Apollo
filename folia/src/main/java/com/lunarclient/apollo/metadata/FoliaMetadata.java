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
package com.lunarclient.apollo.metadata;

import com.lunarclient.apollo.stats.metadata.PlatformMetadata;
import java.util.Map;
import java.util.Set;
import lombok.Builder;
import lombok.ToString;

/**
 * Represents the folia metadata implementation.
 *
 * @since 1.1.9
 */
@ToString
@Builder(toBuilder = true)
public class FoliaMetadata extends PlatformMetadata {

    /**
     * Tracks client brands sent by the players.
     *
     * <p>A {@link Set} of {@link String} client brands.</p>
     *
     * @since 1.1.9
     */
    private final Set<String> clientBrands;

    /**
     * Tracks the total number of resource pack status events received.
     *
     * <p>Represents a {@link Map} of {@link String} status enum name as a key
     * and {@link Integer} count of how many times that status has been reported.</p>
     *
     * @since 1.1.9
     */
    private final Map<String, Integer> resourcePackStatuses;

}
