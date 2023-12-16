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
package com.lunarclient.apollo.command;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.module.ApolloModuleManagerImpl;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * Provides common command functions.
 *
 * @param <T> the sender type
 * @since 1.0.0
 */
public abstract class AbstractApolloCommand<T> {

    private final BiConsumer<T, Component> textConsumer;

    protected AbstractApolloCommand(BiConsumer<T, Component> textConsumer) {
        this.textConsumer = textConsumer;
    }

    /**
     * Sends the current version message to the sender.
     *
     * @param sender the command sender
     * @since 1.0.0
     */
    protected void getCurrentVersion(@NonNull T sender) {
        this.textConsumer.accept(sender, Component.text("Apollo is running version ", NamedTextColor.GREEN)
            .append(Component.text(Apollo.getPlatform().getApolloVersion(), NamedTextColor.WHITE))
            .append(Component.text(".", NamedTextColor.GREEN))
        );
    }

    /**
     * Reloads the configuration and messages the result to the sender.
     *
     * @param sender the command sender
     * @since 1.0.0
     */
    protected void reloadConfiguration(@NonNull T sender) {
        try {
            ApolloManager.loadConfiguration();
            ((ApolloModuleManagerImpl) Apollo.getModuleManager()).reloadModules();
            ApolloManager.saveConfiguration();
        } catch (Throwable throwable) {
            Apollo.getPlatform().getPlatformLogger().log(Level.SEVERE, "Unable to save Apollo configuration!", throwable);

            this.textConsumer.accept(sender, Component.text(
                "An error occurred attempting to save the configuration!",
                NamedTextColor.RED
            ));

            return;
        }

        this.textConsumer.accept(sender, Component.text(
            "Reloaded the Apollo configuration!",
            NamedTextColor.GREEN
        ));
    }

}
