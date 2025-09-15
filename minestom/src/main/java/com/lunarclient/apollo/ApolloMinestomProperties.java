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
package com.lunarclient.apollo;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.minestom.server.command.CommandSender;
import net.minestom.server.entity.Player;

/**
 * Configuration properties for initializing the Apollo Minestom platform.
 *
 * @since 1.2.0
 */
@Getter
@Builder
@Accessors
public class ApolloMinestomProperties {

    /**
     * An instance of {@link ApolloMinestomProperties} with default properties.
     */
    public static final ApolloMinestomProperties DEFAULT_PROPERTIES = ApolloMinestomProperties.builder().build();

    /**
     * Determines whether Apollo should skip sending the {@code minecraft:register}
     * packet for its plugin channels.
     *
     * <p>By default, this is {@code false}, and Apollo will automatically register its
     * {@code lunar:apollo} plugin channel to communicate with the client.</p>
     *
     * <p>Set this to {@code true} if another plugin or system is responsible
     * for sending the register packet, to avoid conflicts where
     * multiple plugins attempt registration at the same time.</p>
     *
     * <p>If you disable channel registration, you must ensure that your server
     * or another plugin sends the {@code lunar:apollo} registration packet
     * if you want Apollo to function correctly.</p>
     *
     * @return true if channel registration is disabled, false otherwise
     * @since 1.2.0
     */
    @Builder.Default
    private final boolean sendRegisterPacket = true;

    /**
     * The path where Apollo will store and load its configuration files.
     *
     * <p>By default, this path points to the {@code ./Apollo/} directory,
     * relative to the server's working directory. If this directory does not
     * exist, it will be created automatically.</p>
     *
     * <p>Override this path if you want to keep Apollo's configuration files in a different location</p>
     *
     * @return the path to Apollo's configuration directory
     * @since 1.2.0
     */
    @Builder.Default
    private final Path configPath = ApolloMinestomProperties.getDefaultConfigPath();

    /**
     * The command properties for Apollo Minestom.
     *
     * @return the command properties
     * @since 1.2.0
     */
    @Builder.Default
    private final CommandProperties commandProperties = CommandProperties.builder().build();

    /**
     * Computes the default configuration path ({@code ./Apollo/}).
     *
     * @return the resolved config path
     * @since 1.2.0
     */
    private static Path getDefaultConfigPath() {
        Path serverDir = Paths.get("").toAbsolutePath();
        Path apolloDir = serverDir.resolve("Apollo");
        File apolloFile = apolloDir.toFile();

        if (!apolloFile.exists()) {
            apolloFile.mkdirs();
        }

        return apolloDir;
    }

    /**
     * Configuration properties for Apollo commands on Minestom.
     *
     * @since 1.2.0
     */
    @Getter
    @Builder
    @Accessors
    public static class CommandProperties {

        private static final Predicate<CommandSender> DEFAULT_PERMISSION = sender ->
            !(sender instanceof Player) || ((Player) sender).getPermissionLevel() >= 2;

        /**
         * Determines whether Apollo should register the {@code /apollo} command.
         *
         * @return true if the command should be registered, false otherwise
         * @since 1.2.0
         */
        @Builder.Default
        private final boolean registerApolloCommand = true;

        /**
         * Determines whether Apollo should register the {@code /lunarclient} command.
         *
         * @return true if the command should be registered, false otherwise
         * @since 1.2.0
         */
        @Builder.Default
        private final boolean registerLunarClientCommand = true;

        /**
         * The predicate to check if a {@link CommandSender} has permission to use
         * the {@code /apollo} command.
         *
         * @return the permission predicate
         * @since 1.2.0
         */
        @Builder.Default
        private final Predicate<CommandSender> apolloCommandPermission = DEFAULT_PERMISSION;

        /**
         * The predicate to check if a {@link CommandSender} has permission to use
         * the {@code /lunarclient} command.
         *
         * @return the permission predicate
         * @since 1.2.0
         */
        @Builder.Default
        private final Predicate<CommandSender> lunarClientCommandPermission = DEFAULT_PERMISSION;

    }

}
