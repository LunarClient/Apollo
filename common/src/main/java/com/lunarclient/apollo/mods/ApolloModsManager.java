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
package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.StatusOptionsImpl;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Manages Apollo mods for players.
 *
 * @since 1.2.1
 */
@Getter
public final class ApolloModsManager {

    private final Container container;
    private final StatusOptionsImpl playerOptions;

    /**
     * Constructs the {@link ApolloModsManager}.
     *
     * @since 1.2.1
     */
    public ApolloModsManager() {
        this.container = ApolloModsManager.loadModOptions();
        this.playerOptions = new StatusOptionsImpl(this.container.getModStatusOptions());
    }

    /**
     * Load all options from auto-generated mod classes into a {@link Container} object.
     *
     * @return the container
     * @since 1.2.1
     */
    public static Container loadModOptions() {
        Map<String, Option<?, ?, ?>> modStatusOptions = new LinkedHashMap<>();
        List<Option<?, ?, ?>> modSettingsOptions = new ArrayList<>();

        try {
            Field defaultValueField = Option.class.getDeclaredField("defaultValue");
            defaultValueField.setAccessible(true);

            for (Class<?> mod : Mods.ALL_MODS) {
                Field[] fields = mod.getDeclaredFields();

                for (Field field : fields) {
                    field.setAccessible(true);
                    Option<?, ?, ?> option = (Option<?, ?, ?>) field.get(null);

                    if (option == null) {
                        continue;
                    }

                    modStatusOptions.put(option.getKey(), option);

                    Option<?, ?, ?> copy = option.clone();
                    defaultValueField.set(copy, null);
                    modSettingsOptions.add(copy);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Container(modStatusOptions, modSettingsOptions);
    }

    /**
     * Holds the loaded options.
     *
     * @since 1.2.1
     */
    @Getter
    @RequiredArgsConstructor
    public static class Container {

        /**
         * Returns all registered mod options with default values.
         *
         * @return the map of option key to option
         * @since 1.2.1
         */
        private final Map<String, Option<?, ?, ?>> modStatusOptions;

        /**
         * Returns all registered mod options with default values removed.
         *
         * @return the list of options without default values
         * @since 1.2.1
         */
        private final List<Option<?, ?, ?>> modSettingsOptions;

    }

}
