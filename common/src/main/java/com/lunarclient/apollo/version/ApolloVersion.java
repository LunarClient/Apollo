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
package com.lunarclient.apollo.version;

import lombok.Getter;

/**
 * Represents an Apollo version.
 *
 * @since 1.0.0
 */
@Getter
public class ApolloVersion {

    private final int major;
    private final int minor;
    private final int patch;

    /**
     * Constructs the {@link ApolloVersion} by the
     * provided version string.
     *
     * <p>Divides the version string by the major, minor & patch version.</p>
     *
     * @param version the version
     * @since 1.0.0
     */
    public ApolloVersion(String version) {
        String[] versions = version
            .replaceAll("[^0-9.]", "")
            .split("\\.");

        if (versions.length != 3) {
            throw new RuntimeException("Failed to parse Apollo version.");
        }

        try {
            this.major = Integer.parseInt(versions[0]);
            this.minor = Integer.parseInt(versions[1]);
            this.patch = Integer.parseInt(versions[2]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse Apollo version.");
        }
    }

    /**
     * Checks the current and the latest available for updates.
     *
     * @param version the latest available version
     * @return whether the update is available
     * @see ApolloVersionManager
     * @since 1.0.0
     */
    public boolean isUpdateAvailable(ApolloVersion version) {
        if (version.getMajor() > this.major) {
            return true;
        } else if (version.getMinor() > this.minor) {
            return true;
        }

        return version.getPatch() > this.patch;
    }
}
