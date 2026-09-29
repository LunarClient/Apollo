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
package com.lunarclient.apollo.module.modsetting;

import com.lunarclient.apollo.module.RestrictedAccess;
import com.lunarclient.apollo.roundtrip.pagination.ApolloPaginatedResponse;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Represents the mod status response.
 *
 * <p>The elements are the mod options the player has changed from their default
 * value. Any option that is not listed is at its default value.</p>
 *
 * @since 1.3.0
 */
@Getter
@SuperBuilder
@RestrictedAccess
public final class ModStatusResponse extends ApolloPaginatedResponse<ModOptionStatus> {

    @Override
    public ApolloPaginatedResponse<ModOptionStatus> combine(UUID packetId, List<ModOptionStatus> elements) {
        return ModStatusResponse.builder()
            .packetId(packetId)
            .elements(elements)
            .build();
    }

}
