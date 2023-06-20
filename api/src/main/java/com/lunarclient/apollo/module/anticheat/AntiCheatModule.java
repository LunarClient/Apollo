package com.lunarclient.apollo.module.anticheat;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the anti cheat module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "anti_cheat", name = "AntiCheat")
public abstract class AntiCheatModule extends ApolloModule {

}
