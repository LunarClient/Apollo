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
import com.lunarclient.apollo.api.request.VersionRequest;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Manages Apollo versioning.
 *
 * @since 1.0.0
 */
public final class ApolloVersionManager {

    public static final SimpleOption<Boolean> SEND_UPDATE_MESSAGE = Option.<Boolean>builder()
        .comment("Set to 'true' to send opped players available update message, otherwise 'false'.")
        .node("send-updater-message").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    private static final String DOWNLOAD_URL = "https://lunarclient.dev/apollo/downloads";
    public static final String UPDATE_MESSAGE = "[Apollo] You’re running an outdated version, update to the latest version here: " + DOWNLOAD_URL;

    /**
     * Returns whether the server needs to update Apollo.
     *
     * @return the needs update value
     * @since 1.0.0
     */
    public static boolean NEEDS_UPDATE;

    /**
     * Constructs the {@link ApolloVersionManager}.
     *
     * @since 1.0.0
     */
    public ApolloVersionManager() {
        ApolloManager.registerOptions(ApolloVersionManager.SEND_UPDATE_MESSAGE);

        this.checkForUpdates();
    }

    private void checkForUpdates() {
        ApolloPlatform platform = Apollo.getPlatform();

        if (!platform.getOptions().get(ApolloVersionManager.SEND_UPDATE_MESSAGE)) {
            return;
        }

        ApolloManager.getHttpManager().request(VersionRequest.builder().build())
            .onSuccess(response -> {
                ApolloVersion currentVersion = new ApolloVersion(platform.getApolloVersion());
                ApolloVersion latestVersion = new ApolloVersion(response.getVersion());

                if (currentVersion.isUpdateAvailable(latestVersion)) {
                    ApolloVersionManager.NEEDS_UPDATE = true;
                    platform.getPlatformLogger().warning(UPDATE_MESSAGE);
                }
            })
            .onFailure(Throwable::printStackTrace);
    }

}
