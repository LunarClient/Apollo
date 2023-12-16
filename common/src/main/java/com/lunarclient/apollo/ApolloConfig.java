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

import com.lunarclient.apollo.option.config.Serializers;
import com.lunarclient.apollo.util.ConfigTarget;
import java.nio.file.Path;
import java.util.Collection;
import java.util.EnumMap;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.loader.HeaderMode;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

/**
 * Provides the configurations for {@link ApolloManager}.
 *
 * @since 1.0.0
 */
public final class ApolloConfig {

    private static final EnumMap<ConfigTarget, ApolloConfig> CONFIGS = new EnumMap<>(ConfigTarget.class);

    /**
     * Computes the {@link ApolloConfig} for the given path and target.
     *
     * @param path the path to the configuration directory
     * @param target the target file name
     * @return the configuration
     * @since 1.0.0
     */
    public static ApolloConfig compute(Path path, ConfigTarget target) {
        return ApolloConfig.CONFIGS.computeIfAbsent(target, key -> new ApolloConfig(path, key));
    }

    /**
     * Gets the {@link ApolloConfig} for the given target.
     *
     * @param target the configuration target
     * @return the configuration
     * @since 1.0.0
     */
    public static ApolloConfig get(ConfigTarget target) {
        return ApolloConfig.CONFIGS.get(target);
    }

    /**
     * Gets all the {@link ApolloConfig}s.
     *
     * @return a collection of configurations
     * @since 1.0.0
     */
    public static Collection<ApolloConfig> configs() {
        return ApolloConfig.CONFIGS.values();
    }

    private final YamlConfigurationLoader loader;

    private CommentedConfigurationNode node;

    /**
     * Constructs a new {@link ApolloConfig} with the given path and name.
     *
     * @param path the path to the configuration directory
     * @param target the config target
     * @since 1.0.0
     */
    ApolloConfig(Path path, ConfigTarget target) {
        this.loader = YamlConfigurationLoader.builder()
            .nodeStyle(NodeStyle.BLOCK)
            .path(path.resolve(target.getFileName()))
            .headerMode(HeaderMode.PRESET)
            .defaultOptions(options -> options
                .serializers(builder -> builder.registerAll(Serializers.serializers()))
                .header(target.getHeaderComment())
            )
            .build();
    }

    /**
     * Gets the {@link CommentedConfigurationNode} for this configuration.
     *
     * @return the configuration node
     * @throws Throwable if an error occurs while loading the configuration
     * @since 1.0.0
     */
    public CommentedConfigurationNode node() throws Throwable {
        if (this.node != null) {
            return this.node;
        }

        return this.node = this.loader.load();
    }

    /**
     * Saves the {@link CommentedConfigurationNode} for this configuration.
     *
     * @throws Throwable if an error occurs while saving the configuration
     * @since 1.0.0
     */
    public void save() throws Throwable {
        this.loader.save(this.node);
    }

    /**
     * Removes the node in-memory so the next time it is needed, it is loaded
     * from disk.
     *
     * @since 1.0.0
     */
    public void reset() {
        this.node = null;
    }

}
