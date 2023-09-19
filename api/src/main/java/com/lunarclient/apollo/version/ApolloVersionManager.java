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

import com.lunarclient.apollo.Apollo;

/**
 * Manages Apollo versioning.
 *
 * @since 1.0.0
 */
public class ApolloVersionManager {

    // TODO: add message toggle to config

    private static final String DOWNLOAD_URL = "https://lunarclient.dev/apollo/downloads";
    private static final String UPDATE_MESSAGE = "[Apollo] You’re running an outdated version, update to the latest version here: " + DOWNLOAD_URL;

    /**
     * Constructs the {@link ApolloVersionManager}.
     *
     * @since 1.0.0
     */
    public ApolloVersionManager() {
        this.checkForUpdates();
    }

    private void checkForUpdates() {
        ApolloVersion currentVersion = new ApolloVersion(Apollo.getPlatform().getApolloVersion());
        ApolloVersion latestVersion = new ApolloVersion("v1.0.1"); // TODO: fetch

        if (currentVersion.isUpdateAvailable(latestVersion)) {
            Apollo.getPlatform().getPlatformLogger().warning(UPDATE_MESSAGE);
        }
    }

}
