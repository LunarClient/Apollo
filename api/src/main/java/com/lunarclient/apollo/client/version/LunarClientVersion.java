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
package com.lunarclient.apollo.client.version;

import lombok.Builder;
import lombok.Getter;

/**
 * Represents the Lunar Client version.
 *
 * @since 1.0.6
 */
@Getter
@Builder
public final class LunarClientVersion {

    /**
     * Returns the version {@link String} git branch.
     *
     * @return the git branch
     * @since 1.0.6
     */
    String gitBranch;

    /**
     * Returns the version {@link String} git commit.
     *
     * @return the git commit
     * @since 1.0.6
     */
    String gitCommit;

    /**
     * Returns the version {@link String} semver encoding (e.g. '2.5.1).
     *
     * @return the mod display name
     * @since 1.0.6
     */
    String semVer;

}
