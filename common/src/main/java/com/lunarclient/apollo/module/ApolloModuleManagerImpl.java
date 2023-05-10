package com.lunarclient.apollo.module;

import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.option.OptionsImpl;
import io.leangen.geantyref.TypeToken;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
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

    private final Map<Class<? extends ApolloModule>, ApolloModule> modules = new IdentityHashMap<>();

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
     * @since 1.0.0
     */
    public void enableModules() {
        for(ApolloModule module : this.modules.values()) {
            // Load configuration options for the module.
            module.setOptions(new OptionsImpl(module));

            List<Option<?, ?, ?>> options = module.getOptionKeys();
            this.loadConfiguration(module, ApolloManager.getConfigurationNode(), options);

            // Enable the module if it is able to.
            if(module.isEnabled() || !module.getOptions().get(ApolloModule.ENABLE)) continue;

            EventBus.getBus().register(module);
            module.enable();
        }
    }

    /**
     * Disables all the added modules, if they are not already disabled.
     *
     * @since 1.0.0
     */
    public void disableModules() {
        for(ApolloModule module : this.modules.values()) {
            if(!module.isEnabled()) continue;
            EventBus.getBus().unregister(module);
            module.disable();
        }
    }

    /**
     * Adds a module to the module manager.
     *
     * @param moduleClass the module class
     * @param <T> the module type
     * @return the module manager
     * @since 1.0.0
     */
    public <T extends ApolloModule> ApolloModuleManagerImpl addModule(@NonNull Class<T> moduleClass) {
        this.modules.computeIfAbsent(moduleClass, key -> {
            try {
                Constructor<T> constructor = moduleClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                return constructor.newInstance();
            } catch(Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
        return this;
    }

    /**
     * Adds a module to the module manager.
     *
     * @param moduleClass the module class
     * @param module the module
     * @param <T> the module type
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
     * @param node the configuration node
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public void saveConfiguration(CommentedConfigurationNode node) {
        for(ApolloModule module : this.modules.values()) {
            CommentedConfigurationNode moduleNode = node.node(module.getId().toLowerCase(Locale.ENGLISH));

            Options optionsContainer = module.getOptions();
            for(Option<?, ?, ?> option : module.getOptionKeys()) {
                CommentedConfigurationNode optionNode = moduleNode.node((Object[]) option.getPath());
                if(optionNode == null) continue;

                try {
                    if(option.getComment() != null) optionNode.comment(option.getComment());

                    optionNode.set((TypeToken<Object>) option.getTypeToken(), optionsContainer.get(option));
                } catch(Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }

    private void loadConfiguration(ApolloModule module, CommentedConfigurationNode node, List<Option<?, ?, ?>> options) {
        CommentedConfigurationNode modules = node.node("modules");
        CommentedConfigurationNode moduleNode = modules.node(module.getId().toLowerCase(Locale.ENGLISH));
        if(moduleNode.virtual()) return;

        Options optionsContainer = module.getOptions();
        for(Option<?, ?, ?> option : options) {
            CommentedConfigurationNode optionNode = moduleNode.node((Object[]) option.getPath());
            if(optionNode.virtual()) continue;

            try {
                Object value = optionNode.get(option.getTypeToken());
                optionsContainer.set(option, value);
            } catch(Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

}
