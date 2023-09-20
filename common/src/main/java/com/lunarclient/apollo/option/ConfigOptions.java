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
package com.lunarclient.apollo.option;

import io.leangen.geantyref.TypeToken;
import java.util.List;
import org.spongepowered.configurate.CommentedConfigurationNode;

/**
 * Utility class for storing options into configuration.
 *
 * @since 1.0.0
 */
public final class ConfigOptions {

    /**
     * Loads the list of options from the configuration node into the option
     * container.
     *
     * @param options the options container
     * @param node the configuration node
     * @param optionKeys the option keys
     * @since 1.0.0
     */
    public static void loadOptions(Options options, CommentedConfigurationNode node, List<Option<?, ?, ?>> optionKeys) {
        for (Option<?, ?, ?> option : optionKeys) {
            CommentedConfigurationNode optionNode = node.node((Object[]) option.getPath());
            if (optionNode.virtual()) {
                continue;
            }

            try {
                Object value = optionNode.get(option.getTypeToken());
                options.set(option, value);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Saves the options to the configuration.
     *
     * @param options the options container
     * @param node the configuration node
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public static void saveOptions(Options options, CommentedConfigurationNode node) {
        for (Option<?, ?, ?> option : options) {
            CommentedConfigurationNode optionNode = node.node((Object[]) option.getPath());
            if (optionNode == null) {
                continue;
            }

            try {
                if (option.getComment() != null) {
                    optionNode.comment(option.getComment());
                }

                optionNode.set((TypeToken<Object>) option.getTypeToken(), options.get(option));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    private ConfigOptions() {
    }

}
