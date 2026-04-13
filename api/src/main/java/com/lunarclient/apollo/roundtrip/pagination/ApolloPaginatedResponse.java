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
package com.lunarclient.apollo.roundtrip.pagination;

import com.lunarclient.apollo.roundtrip.ApolloResponse;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Represents a Paginated Apollo Response.
 *
 * @param <T> the type of the elements in the paginated payload
 * @since 1.2.5
 */
@Getter
@SuperBuilder
public abstract class ApolloPaginatedResponse<T> extends ApolloResponse {

    /**
     * The current page number.
     *
     * @since 1.2.5
     */
    @Getter(AccessLevel.PROTECTED)
    int page;

    /**
     * The total number of pages expected.
     *
     * @since 1.2.5
     */
    @Getter(AccessLevel.PROTECTED)
    int totalPages;

    /**
     * The elements contained in this specific page.
     *
     * @since 1.2.5
     */
    List<T> elements;

    /**
     * Checks if this is the final page in the sequence.
     *
     * @return true if it is the last page
     * @since 1.2.5
     */
    public boolean isLastPage() {
        return this.page == this.totalPages - 1;
    }

    /**
     * Creates a finalized response containing all paginated elements.
     *
     * @param packetId the response packet id
     * @param elements the complete list of elements across all pages
     * @return a new combined response
     * @since 1.2.5
     */
    public abstract ApolloPaginatedResponse<T> combine(UUID packetId, List<T> elements);

}
