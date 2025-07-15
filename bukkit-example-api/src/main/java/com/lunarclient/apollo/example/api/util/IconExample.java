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
package com.lunarclient.apollo.example.api.util;

import com.lunarclient.apollo.common.icon.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.icon.SimpleResourceLocationIcon;
import org.bukkit.Material;

public final class IconExample {

    public static ItemStackIcon itemStackIdIconExample() {
        return ItemStackIcon.builder()
            .itemId(Material.ENDER_PEARL.getId())
            .build();
    }

    public static ItemStackIcon itemStackNameIconExample() {
        return ItemStackIcon.builder()
            .itemName("ENDER_PEARL")
            .build();
    }

    public static SimpleResourceLocationIcon simpleResourceLocationIconExample() {
        return SimpleResourceLocationIcon.builder()
            .resourceLocation("icons/server-logo.png") // Resource path location
            .size(16) // Represents both height and width
            .build();
    }

    public static AdvancedResourceLocationIcon advancedResourceLocationIconExample() {
        return AdvancedResourceLocationIcon.builder()
            .resourceLocation("icons/server-sprite.png")
            .width(512)
            .height(512)
            .minU(1)
            .maxU(1)
            .minV(1)
            .maxV(1)
            .build();
    }

    private IconExample() {
    }

}
