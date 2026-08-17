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
package com.lunarclient.apollo.common.icon;

import com.lunarclient.apollo.common.profile.Profile;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an item stack icon.
 *
 * @since 1.0.0
 */
@Getter
@Builder
public final class ItemStackIcon extends Icon {

    /**
     * Returns the icon {@link String} item name.
     *
     * @return the icon item name
     * @since 1.0.0
     */
    String itemName;

    /**
     * Returns the icon {@link Integer} item id.
     *
     * @return the icon item id
     * @since 1.0.0
     */
    int itemId;

    /**
     * Returns the icon {@link Integer} custom model data.
     *
     * @return the icon custom model data
     * @deprecated for removal since 1.2.7, use {@link ItemStackIcon#customModelDataObject} instead.
     * @since 1.0.7
     */
    @Deprecated
    int customModelData;

    /**
     * Returns the icon {@link CustomModelData} object.
     *
     * @return the icon custom model data object
     * @since 1.2.7
     */
    @Nullable CustomModelData customModelDataObject;

    /**
     * Returns the icon {@link Profile}.
     *
     * @return the icon profile
     * @since 1.2.6
     */
    @Nullable Profile profile;

    /**
     * Returns the icon {@link String} potion id (e.g. {@code "healing"}).
     *
     * @return the icon potion id
     * @since 1.2.9
     */
    @Nullable String potion;

}
