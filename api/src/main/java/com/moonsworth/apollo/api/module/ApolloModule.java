package com.moonsworth.apollo.api.module;

import com.google.gson.JsonObject;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
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
     * Determines if we notify the players in a bulk packet when they login.
     * This is very useful for things like LegacyCombatModule where it directly determines gameplay
     * factors from the moment the user logs in, but less useful for things like NotificationModule
     * where the user only needs to know it's enabled once it takes an action.
     * @return The result of if this should contain a combat module
     */
    public abstract boolean notifyPlayers();

    /**
     * Called when a player logs in.
     * If this is a notifying module, the player will have already received the packet that
     * this module is enabled, and this will be overridden to add additional details around the
     * rules of the module.
     *
     * An example of this could be telling the client a duration for a cool down on login instead
     * of sending that value everytime an action to cause the cooldown is invoked.
     * @param player The player that has recently logged in.
     */
    public void playerLogin(ApolloPlayer player) {

    }

    /**
     * Loads the configuration from the specified YML file as a JsonObject.
     *
     * This is called
     */
    public void loadConfiguration(JsonObject object) {

    }


}
