package com.moonsworth.apollo.api.module;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ApolloModuleManager {

    @Getter
    private final Map<Class<? extends ApolloModule>, ApolloModule> moduleMap = new HashMap<>();

    /**
     * Registers the module for usage.
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
