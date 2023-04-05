package com.moonsworth.apollo.module;

import com.moonsworth.apollo.event.EventBus;
import com.moonsworth.apollo.option.Option;
import com.moonsworth.apollo.option.Options;
import com.moonsworth.apollo.option.OptionsContainer;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import lombok.NoArgsConstructor;
import org.spongepowered.configurate.CommentedConfigurationNode;

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
    public boolean isEnabled(Class<? extends ApolloModule> moduleClass) {
        requireNonNull(moduleClass, "moduleClass");
        return this.modules.containsKey(moduleClass);
    }

    @Override
    public <T extends ApolloModule> Optional<T> getModule(Class<T> moduleClass) {
        requireNonNull(moduleClass, "moduleClass");
        return Optional.ofNullable(this.modules.get(moduleClass))
                .map(moduleClass::cast);
    }

    @Override
    public Collection<ApolloModule> getModules() {
        return Collections.unmodifiableCollection(this.modules.values());
    }

    public <T extends ApolloModule> ApolloModuleManagerImpl addModule(Class<T> moduleClass) {
        requireNonNull(moduleClass, "moduleClass");
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

    public <T extends ApolloModule> ApolloModuleManagerImpl addModule(Class<T> moduleClass, T module) {
        requireNonNull(module, "module");
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

    public void loadConfiguration(CommentedConfigurationNode node) {
        for(ApolloModule module : this.modules.values()) {
            CommentedConfigurationNode moduleNode = node.node(module.getName().toLowerCase(Locale.ENGLISH));
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

    public void saveConfiguration(CommentedConfigurationNode node) {
        for(ApolloModule module : this.modules.values()) {
            CommentedConfigurationNode moduleNode = node.node(module.getName().toLowerCase(Locale.ENGLISH));

            Options.Container optionsContainer = module.getOptions();
            for(Option<?, ?, ?> option : optionsContainer) {
                CommentedConfigurationNode optionNode = moduleNode.node((Object[]) option.getNode());
                if(optionNode == null) continue;

                try {
                    if(option.getComment() != null) optionNode.comment(option.getComment());

                    optionNode.set(optionsContainer.get(option));
                } catch(Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }

}
