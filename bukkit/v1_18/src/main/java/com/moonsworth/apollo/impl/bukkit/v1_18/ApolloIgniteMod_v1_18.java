package com.moonsworth.apollo.impl.bukkit.v1_18;

import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;
import space.vectrix.ignite.api.Platform;
import space.vectrix.ignite.api.event.Subscribe;
import space.vectrix.ignite.api.event.platform.PlatformInitializeEvent;

/**
 * Entry point for Ignite
 *
 * @author jado
 */
public class ApolloIgniteMod_v1_18 {

    private final Logger logger;
    private final Platform platform;

    @Inject
    public ApolloIgniteMod_v1_18(Logger logger, Platform platform) {
        this.logger = logger;
        this.platform = platform;
    }

    @Subscribe
    public void onInitialize(PlatformInitializeEvent event) {
        this.logger.info("Hello Example!");
    }
}
