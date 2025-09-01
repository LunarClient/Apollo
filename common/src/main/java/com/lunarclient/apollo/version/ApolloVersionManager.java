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
import com.lunarclient.apollo.api.request.DownloadFileRequest;
import com.lunarclient.apollo.api.request.VersionRequest;
import com.lunarclient.apollo.api.response.VersionResponse;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Logger;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

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

    @Getter
    private String latestVersion;
    private VersionResponse.Assets assets;

    private final AtomicBoolean updated = new AtomicBoolean(false);

    /**
     * Constructs the {@link ApolloVersionManager}.
     *
     * @since 1.0.0
     */
    public ApolloVersionManager() {
        ApolloManager.registerOptions(ApolloVersionManager.SEND_UPDATE_MESSAGE);
    }

    /**
     * Runs the update checker.
     *
     * @since 1.0.0
     */
    public void checkForUpdates() {
        ApolloManager.getHttpManager().request(VersionRequest.builder().build())
            .onSuccess(response -> {
                this.assets = response.getAssets();

                ApolloPlatform platform = Apollo.getPlatform();
                String version = response.getVersion();

                ApolloVersion currentVersion = new ApolloVersion(platform.getApolloVersion());
                ApolloVersion latestVersion = new ApolloVersion(version);

                if (!currentVersion.isUpdateAvailable(latestVersion)) {
                    return;
                }

                this.latestVersion = version;

                if (!platform.getOptions().get(ApolloVersionManager.SEND_UPDATE_MESSAGE)) {
                    return;
                }

                Logger logger = platform.getPlatformLogger();
                logger.warning(String.format("A new version of Apollo is available! Latest release: %s", version));

                if (platform.getPlatform() != ApolloPlatform.Platform.MINESTOM) {
                    logger.warning("Please update by running \"/apollo update\" or by downloading the " +
                        "latest build from https://lunarclient.dev/apollo/downloads");
                }
            })
            .onFailure(Throwable::printStackTrace);
    }

    /**
     * Runs a force update.
     *
     * @param platform the platform to run the update on
     * @param message the message to send to the command sender
     * @since 1.0.9
     */
    public void forceUpdate(ApolloPlatform.Platform platform, Consumer<Component> message) {
        if (this.updated.get()) {
            message.accept(Component.text(
                "Apollo is already updated, please restart your server!",
                NamedTextColor.RED)
            );
            return;
        }

        if (this.latestVersion == null) {
            message.accept(Component.text(
                "This server is already running the latest version of Apollo.",
                NamedTextColor.RED)
            );
            return;
        }

        if (this.assets == null) {
            message.accept(Component.text(
                "Unable to find assets to update from.",
                NamedTextColor.RED)
            );
            return;
        }

        String platformUrl = this.getPlatformUrl(platform);
        if (platformUrl == null) {
            message.accept(Component.text(
                "Unable to find platform url.",
                NamedTextColor.RED)
            );
            return;
        }

        // Find the current running Apollo jar
        URL url = Apollo.getPlatform().getPlugin()
            .getClass()
            .getProtectionDomain()
            .getCodeSource()
            .getLocation();

        File file;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        // Find name of the new Apollo jar
        String[] urlArgs = platformUrl.split("/");
        String fileName = urlArgs[urlArgs.length - 1];

        // Create a path for the downloaded Apollo jar
        Path updatedJarPath = Paths.get(file.getParent() + File.separator + fileName);

        DownloadFileRequest request = DownloadFileRequest.builder()
            .url(platformUrl)
            .target(updatedJarPath)
            .build();

        ApolloManager.getHttpManager().download(request)
            .onSuccess(response -> {
                message.accept(Component.text(
                    "Successfully updated Apollo, please restart your server!",
                    NamedTextColor.RED)
                );

                // Delete old Apollo jar
                file.deleteOnExit();
                this.updated.set(true);
            })
            .onFailure(throwable -> {
                message.accept(Component.text(
                    "Failed to update Apollo, please check your console for more information!",
                    NamedTextColor.RED)
                );

                throwable.printStackTrace();
            });
    }

    private String getPlatformUrl(ApolloPlatform.Platform platform) {
        switch (platform) {
            case BUKKIT: {
                return this.assets.getBukkit();
            }

            case BUNGEE: {
                return this.assets.getBungee();
            }

            case VELOCITY: {
                return this.assets.getVelocity();
            }

            case FOLIA: {
                return this.assets.getFolia();
            }
        }

        return null;
    }

}
