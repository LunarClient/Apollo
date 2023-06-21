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
package com.lunarclient.apollo.module.modsetting;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a mods settings.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class ModSettings {

    /**
     * Returns the configurable {@link String} target.
     *
     * @return the configurable target
     * @since 1.0.0
     */
    String target;

    /**
     * Returns the configurable {@link Boolean} enabled state.
     *
     * @return the enabled state
     * @since 1.0.0
     */
    boolean enable;

    /**
     * Returns the configurable properties {@link Map} that contains {@link String}
     * option id as key and {@link Object} forced value as value.
     *
     * @return the properties map
     * @since 1.0.0
     */
    Map<String, Object> properties;

}
