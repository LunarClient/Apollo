package com.moonsworth.apollo.module;

import com.moonsworth.apollo.event.EventBus;
import com.moonsworth.apollo.option.Option;
import com.moonsworth.apollo.option.Options;
import com.moonsworth.apollo.option.OptionsContainer;
import lombok.NoArgsConstructor;
import org.spongepowered.configurate.CommentedConfigurationNode;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * Provides the implementation for the {@link ApolloModuleManager}.
 *
 * @since 1.0.0
 */
@NoArgsConstructor
public final class ApolloModuleManagerImpl implements ApolloModuleManager {

    private final Map<Class<? extends ApolloModule>, ApolloModule> modules = new IdentityHashMap<>();

    @Override
    public boolean isEnabled(final Class<? extends ApolloModule> moduleClass) {
        requireNonNull(moduleClass, "moduleClass");
        return this.modules.containsKey(moduleClass);
    }

    @Override
    public <T extends ApolloModule> Optional<T> getModule(final Class<T> moduleClass) {
        requireNonNull(moduleClass, "moduleClass");
        return Optional.ofNullable(this.modules.get(moduleClass))
                .map(moduleClass::cast);
    }

    @Override
    public Collection<ApolloModule> getModules() {
        return Collections.unmodifiableCollection(this.modules.values());
    }

    public <T extends ApolloModule> ApolloModuleManagerImpl addModule(final Class<T> moduleClass) {
        requireNonNull(moduleClass, "moduleClass");
        this.modules.computeIfAbsent(moduleClass, key -> {
            try {
                final Constructor<T> constructor = moduleClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                final T module = constructor.newInstance();
                EventBus.getBus().register(module);
                module.enable();
                return module;
            } catch(final Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
        return this;
    }

    public <T extends ApolloModule> ApolloModuleManagerImpl addModule(final Class<T> moduleClass, final T module) {
        requireNonNull(module, "module");
        this.modules.computeIfAbsent(moduleClass, key -> {
            try {
                final Option<?, ?, ?>[] options = module.getOptionKeys();
                if(options.length > 0) {
                    module.setOptions(new OptionsContainer(module, Arrays.asList(options)));
                }
                EventBus.getBus().register(module);
                module.enable();
                return module;
            } catch(final Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
        return this;
    }

    public void loadConfiguration(final CommentedConfigurationNode node) {
        for(final ApolloModule module : this.modules.values()) {
            final CommentedConfigurationNode moduleNode = node.node(module.getName());
            if(moduleNode.virtual()) continue;

            final Options.Container optionsContainer = module.getOptions();
            for(final Option<?, ?, ?> option : optionsContainer) {
                final CommentedConfigurationNode optionNode = moduleNode.node((Object[]) option.getNode());
                if(optionNode.virtual()) continue;

                try {
                    final Object value = optionNode.get(option.getTypeToken());
                    optionsContainer.set(option, value);
                } catch(final Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }

    public void saveConfiguration(final CommentedConfigurationNode node) {
        for(final ApolloModule module : this.modules.values()) {
            final CommentedConfigurationNode moduleNode = node.node(module.getName());

            final Options.Container optionsContainer = module.getOptions();
            for(final Option<?, ?, ?> option : optionsContainer) {
                final CommentedConfigurationNode optionNode = moduleNode.node((Object[]) option.getNode());
                if(optionNode == null) continue;

                try {
                    optionNode.set(optionsContainer.get(option));
                } catch(final Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }

}
