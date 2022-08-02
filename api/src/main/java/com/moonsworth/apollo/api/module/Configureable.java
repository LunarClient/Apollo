package com.moonsworth.apollo.api.module;

import java.util.Map;

public interface Configureable {

    /**
     * Gets the name for the Configuration Section
     * @return The name of the item
     */
    String getName();

    /**
     * Loads the items from a config file.
     * @param configuration The data from the config section in YAML
     */
    void load(Map<String, Object> configuration);
}
