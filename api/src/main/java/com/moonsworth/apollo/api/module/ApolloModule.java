package com.moonsworth.apollo.api.module;

import com.google.gson.JsonObject;
import com.moonsworth.apollo.api.options.ApolloOption;

import java.util.List;

public abstract class ApolloModule {

    /**
     * Enables this ApolloModule.
     * NOTE: Modules are enabled as they're created. Some modules will load before others!
     *
     * Called then the module is set to be enabled.
     * This happens before enable, but after init in ApolloConsumer.
     */
    public abstract void enable();

    public abstract List<ApolloOption> options();

    /**
     * Loads the configuration from the specified YML file as a JsonObject.
     *
     * This is called
     */
    public void loadConfiguration(JsonObject object) {

    }


}
