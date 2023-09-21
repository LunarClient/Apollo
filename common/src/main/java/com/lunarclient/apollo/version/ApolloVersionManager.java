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
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Getter;

/**
 * Manages Apollo versioning.
 *
 * @since 1.0.0
 */
@Getter
public final class ApolloVersionManager {

    public static final SimpleOption<Boolean> SEND_UPDATE_MESSAGE = Option.<Boolean>builder()
        .comment("Set to 'true' to send opped players available update message, otherwise 'false'.")
        .node("send-updater-message").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    private static final String APOLLO_UPDATES_URL = "https://api.lunarclientprod.com/apollo/updates";
    private static final String DOWNLOAD_URL = "https://lunarclient.dev/apollo/downloads";
    public static final String UPDATE_MESSAGE = "[Apollo] You’re running an outdated version, update to the latest version here: " + DOWNLOAD_URL;

    /**
     * Returns whether the server needs to update Apollo.
     *
     * @return the needs update value
     * @since 1.0.0
     */
    private boolean needsUpdate;

    /**
     * The executor for http requests.
     *
     * @since 1.0.0
     */
    private final ExecutorService requestExecutor;

    /**
     * Constructs the {@link ApolloVersionManager}.
     *
     * @since 1.0.0
     */
    public ApolloVersionManager() {
        ApolloManager.registerOptions(SEND_UPDATE_MESSAGE);

        this.requestExecutor = Executors.newSingleThreadExecutor();
        this.checkForUpdates();
    }

    private void checkForUpdates() {
        ApolloPlatform platform = Apollo.getPlatform();

        if (!platform.getOptions().get(SEND_UPDATE_MESSAGE)) {
            return;
        }

        this.requestExecutor.submit(() -> {
            try {
                ApolloVersion currentVersion = new ApolloVersion(platform.getApolloVersion());
                ApolloVersion latestVersion = this.fetchLatestApolloVersion();

                if (latestVersion != null && currentVersion.isUpdateAvailable(latestVersion)) {
                    this.needsUpdate = true;
                    platform.getPlatformLogger().warning(UPDATE_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private ApolloVersion fetchLatestApolloVersion() {
        try {
            URL url = new URL(APOLLO_UPDATES_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String version = in.readLine();
                return new ApolloVersion(version);
            }
        } catch (IOException e) {
            return null;
        }
    }
}
