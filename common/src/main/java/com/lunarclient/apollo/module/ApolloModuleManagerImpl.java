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
package com.lunarclient.apollo.module;

import com.lunarclient.apollo.ApolloConfig;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.option.ConfigOptions;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.option.OptionsImpl;
import com.lunarclient.apollo.util.ConfigTarget;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.spongepowered.configurate.CommentedConfigurationNode;

/**
 * Provides the implementation for the {@link ApolloModuleManager}.
 *
 * @since 1.0.0
 */
@NoArgsConstructor
public final class ApolloModuleManagerImpl implements ApolloModuleManager {

    private final Map<Class<? extends ApolloModule>, ApolloModule> modules = new LinkedHashMap<>();

    @Override
    public boolean isEnabled(@NonNull Class<? extends ApolloModule> moduleClass) {
        return this.modules.containsKey(moduleClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ApolloModule> T getModule(@NonNull Class<T> moduleClass) {
        return (T) this.modules.get(moduleClass);
    }

    @Override
    public Collection<ApolloModule> getModules() {
        return Collections.unmodifiableCollection(this.modules.values());
    }

    /**
     * Enables all the added modules, if they are not already enabled.
     *
     * @throws Throwable if there is an error enabling the modules
     * @since 1.0.0
     */
    public void enableModules() throws Throwable {
        for (ApolloModule module : this.modules.values()) {
            // Load configuration options for the module.
            module.setOptions(new OptionsImpl(module));

            List<Option<?, ?, ?>> options = module.getOptionKeys();
            this.loadConfiguration(module, options);

            // Enable the module if it is able to.
            if (module.isEnabled() || module.getOptions().get(ApolloModule.ENABLE) == Boolean.FALSE) {
                continue;
            }

            EventBus.getBus().register(module);
            module.enable();
        }
    }

    /**
     * Reloads the configuration and enables/disables modules that have been
     * changed.
     *
     * @throws Throwable if there is an error reloading the modules
     * @since 1.0.5
     */
    public void reloadModules() throws Throwable {
        for (ApolloModule module : this.modules.values()) {
            List<Option<?, ?, ?>> options = module.getOptionKeys();
            this.loadConfiguration(module, options);

            // Enable or disable the module depending on the setting.
            Boolean enable;
            if ((enable = module.getOptions().get(ApolloModule.ENABLE)) != Boolean.valueOf(module.isEnabled())) {
                if (enable == Boolean.TRUE) {
                    EventBus.getBus().register(module);
                    module.enable();
                } else {
                    EventBus.getBus().unregister(module);
                    module.disable();
                }
            }
        }

        this.saveConfiguration();
    }

    /**
     * Disables all the added modules, if they are not already disabled.
     *
     * @since 1.0.0
     */
    public void disableModules() {
        for (ApolloModule module : this.modules.values()) {
            if (!module.isEnabled()) {
                continue;
            }

            EventBus.getBus().unregister(module);
            module.disable();
        }
    }

    /**
     * Adds a module to the module manager.
     *
     * @param moduleClass the module class
     * @param <T>         the module type
     * @return the module manager
     * @since 1.0.0
     */
    public <T extends ApolloModule> ApolloModuleManagerImpl addModule(@NonNull Class<T> moduleClass) {
        this.modules.computeIfAbsent(moduleClass, key -> {
            try {
                Constructor<T> constructor = moduleClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                return constructor.newInstance();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
        return this;
    }

    /**
     * Adds a module to the module manager.
     *
     * @param moduleClass the module class
     * @param module      the module
     * @param <T>         the module type
     * @return the module manager
     * @since 1.0.0
     */
    public <T extends ApolloModule> ApolloModuleManagerImpl addModule(Class<T> moduleClass, @NonNull T module) {
        this.modules.putIfAbsent(moduleClass, module);
        return this;
    }

    /**
     * Saves the configuration for all the loaded modules.
     *
     * @throws Throwable if there is an error saving the module configurations
     * @since 1.0.0
     */
    public void saveConfiguration() throws Throwable {
        for (ApolloModule module : this.modules.values()) {
            ConfigTarget configTarget = module.getConfigTarget();
            ApolloConfig config = ApolloConfig.get(configTarget);

            CommentedConfigurationNode node = config.node();
            CommentedConfigurationNode modules = node.node((Object[]) configTarget.getModulesNode());
            CommentedConfigurationNode moduleNode = modules.node(module.getId().toLowerCase(Locale.ROOT));

            Options optionsContainer = module.getOptions();
            ConfigOptions.saveOptions(optionsContainer, moduleNode, module.getOptionKeys());
        }
    }

    private void loadConfiguration(ApolloModule module, List<Option<?, ?, ?>> options) throws Throwable {
        ConfigTarget configTarget = module.getConfigTarget();
        ApolloConfig config = ApolloConfig.compute(ApolloManager.getConfigPath(), configTarget);
        CommentedConfigurationNode node = config.node();

        CommentedConfigurationNode modules = node.node((Object[]) configTarget.getModulesNode());
        CommentedConfigurationNode moduleNode = modules.node(module.getId().toLowerCase(Locale.ROOT));
        if (moduleNode.virtual()) {
            return;
        }

        Options optionsContainer = module.getOptions();
        ConfigOptions.loadOptions(optionsContainer, moduleNode, options);
    }

}
