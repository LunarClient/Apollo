package com.moonsworth.apollo.api.module;

import lombok.Getter;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class ApolloModuleManager {

    private final Map<Class<? extends ApolloModule>, List<Consumer<ApolloModule>>> listeners = new HashMap<>();
    @Getter
    private final Map<Class<? extends ApolloModule>, ApolloModule> moduleMap = new HashMap<>();

    /**
     * Registers a listener to be called when a module has been enabled.
     * If the module is currently enabled it will be called immediately.
     * @param clazz The apollo module
     * @param moduleConsumer The callback
     */
    public <T> void registerModuleListener(Class<T> clazz, Consumer<T> moduleConsumer) {
        getModule(clazz).ifPresentOrElse(moduleConsumer, () -> listeners.computeIfAbsent((Class<? extends ApolloModule>) clazz, aClass -> new ArrayList<>()).add((Consumer<ApolloModule>) moduleConsumer));
    }

    /**
     * Determine if a module is enabled without grabbing an instance of the module
     *
     * @param clazz The module class
     * @return the module enabled value
     */
    public boolean isModuleEnabled(Class<? extends ApolloModule> clazz) {
        return moduleMap.containsKey(clazz);
    }

    /**
     * Gets a module by the class specified.
     * If the module does not exist, the optional will be empty
     * @param clazz The module to grab
     * @return An optional value containing the module.
     */
    public <T> Optional<T> getModule(Class<T> clazz){
        return (Optional<T>) Optional.ofNullable(moduleMap.get(clazz));
    }

    /**
     * Registers the module for usage.
     * This will also invoke the module listeners after the module has been initialized.
     * @param clazz The instance of the module
     */
    public void register(Class<? extends ApolloModule> clazz) {
        ApolloModule module;
        try {
            module = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return;
        }
        moduleMap.put(clazz, module);
        module.enable();
        List<Consumer<ApolloModule>> consumers = listeners.remove(clazz);
        if (consumers != null) {
            for (Consumer<ApolloModule> consumer : consumers) {
                consumer.accept(module);
            }
        }
    }

    /**
     * Loads all the configuration from the YML file.
     *
     * Needs to be called after registering all the modules
     */
    public void loadConfiguration() {

        for (ApolloModule value : moduleMap.values()) {
//            value.loadConfiguration();
        }
    }

}
