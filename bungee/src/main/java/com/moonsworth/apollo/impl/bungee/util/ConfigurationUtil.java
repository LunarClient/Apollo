package com.moonsworth.apollo.impl.bungee.util;

import lombok.experimental.UtilityClass;
import net.md_5.bungee.config.Configuration;

import java.util.Map;

@UtilityClass
public class ConfigurationUtil {

    /**
     * Flattens the configuration map to mimic Bukkit implementation of the ConfigurationSection.
     *
     * @param values The values that are in this {@link Configuration} thus far
     * @param path The Bukkit-like path where each Configuration is separated by a period "."
     * @param config The current map to extract values from
     */
    public void flattenConfigurationDeep(Map<String, Object> values, String path, Configuration config) {
        for (String key : config.getKeys()) {
            if (config.contains(key)) {
                var value = config.get(key);
                if (value instanceof Configuration) {
                    flattenConfigurationDeep(values, path + "." + key, ((Configuration) value));
                } else {
                    values.put(path + "." + key, config.get(key));
                }
            }
        }
    }
}
