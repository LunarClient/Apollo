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
package com.lunarclient.apollo.module.serverlink;

import lombok.Builder;
import lombok.Getter;
import net.kyori.adventure.text.Component;

/**
 * Represents a link entry displayed in the Server Links menu.
 *
 * @since 1.2.5
 */
@Getter
@Builder
public final class ServerLink {

    /**
     * Returns the server link {@link String} id.
     *
     * @return the server link id
     * @since 1.2.5
     */
    String id;

    /**
     * Returns the server link {@link Component} display name.
     *
     * @return the server link display name
     * @since 1.2.5
     */
    Component displayName;

    /**
     * Returns the server link {@link String} url.
     *
     * @return the server link url
     * @since 1.2.5
     */
    String url;

}
