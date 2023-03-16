package com.moonsworth.apollo.module;

import com.moonsworth.apollo.option.Option;
import com.moonsworth.apollo.option.Options;
import lombok.NoArgsConstructor;
import org.spongepowered.configurate.CommentedConfigurationNode;

import java.lang.reflect.Constructor;
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

    public <T extends ApolloModule> ApolloModuleManagerImpl addModule(final Class<T> moduleClass) {
        requireNonNull(moduleClass, "moduleClass");
        this.modules.computeIfAbsent(moduleClass, key -> {
            try {
                final Constructor<T> constructor = moduleClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                final T module = constructor.newInstance();
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
            if(moduleNode == null || moduleNode.virtual()) continue;

            final Options options = module.getOptions();
            for(final Option<?, ?, ?> option : options) {
                final CommentedConfigurationNode optionNode = moduleNode.node((Object[]) option.getNode());
                if(optionNode == null || optionNode.virtual()) continue;

                try {
                    final Object value = optionNode.get(option.getDefaultValue().getClass());
                    options.set(option, value);
                } catch(final Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }

    public CommentedConfigurationNode saveConfiguration() {
        final CommentedConfigurationNode node = CommentedConfigurationNode.root();
        for(final ApolloModule module : this.modules.values()) {
            final CommentedConfigurationNode moduleNode = node.node(module.getName());
            if(moduleNode == null) continue;

            final Options options = module.getOptions();
            for(final Option<?, ?, ?> option : options) {
                final CommentedConfigurationNode optionNode = moduleNode.node((Object[]) option.getNode());
                if(optionNode == null) continue;

                try {
                    optionNode.set(option.getDefaultValue());
                } catch(final Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }

        return node;
    }

}
