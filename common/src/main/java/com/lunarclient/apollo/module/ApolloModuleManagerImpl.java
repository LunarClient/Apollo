package com.lunarclient.apollo.module;

import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.option.OptionsContainer;
import io.leangen.geantyref.TypeToken;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
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
                T module = constructor.newInstance();

                Option<?, ?, ?>[] options = module.getOptionKeys();
                if(options.length > 0) {
                    module.setOptions(new OptionsContainer(module, Arrays.asList(options)));
                }

                EventBus.getBus().register(module);
                module.enable();
                return module;
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
        this.modules.computeIfAbsent(moduleClass, key -> {
            try {
                Option<?, ?, ?>[] options = module.getOptionKeys();
                if(options.length > 0) {
                    module.setOptions(new OptionsContainer(module, Arrays.asList(options)));
                }

                EventBus.getBus().register(module);
                module.enable();
                return module;
            } catch(Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
        return this;
    }

    /**
     * Loads the configuration for all the loaded modules.
     *
     * @param node the configuration node
     * @since 1.0.0
     */
    public void loadConfiguration(CommentedConfigurationNode node) {
        for(ApolloModule module : this.modules.values()) {
            CommentedConfigurationNode moduleNode = node.node(module.getId().toLowerCase(Locale.ENGLISH));
            if(moduleNode.virtual()) continue;

            Options.Container optionsContainer = module.getOptions();
            for(Option<?, ?, ?> option : optionsContainer) {
                CommentedConfigurationNode optionNode = moduleNode.node((Object[]) option.getNode());
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

    /**
     * Saves the configuration for all the loaded modules.
     *
     * @param node the configuration node
     * @since 1.0.0
     */
    public void saveConfiguration(CommentedConfigurationNode node) {
        for(ApolloModule module : this.modules.values()) {
            CommentedConfigurationNode moduleNode = node.node(module.getId().toLowerCase(Locale.ENGLISH));

            Options.Container optionsContainer = module.getOptions();
            for(Option<?, ?, ?> option : optionsContainer) {
                CommentedConfigurationNode optionNode = moduleNode.node((Object[]) option.getNode());
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

}
