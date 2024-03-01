package com.lunarclient.apollo.module.ping;

import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.recipients.Recipients;
import org.jetbrains.annotations.ApiStatus;

import java.util.UUID;

/**
 * Represents the Player Ping module.
 *
 * @since 1.1.1
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "ping", name = "Ping")
public abstract class PingModule extends ApolloModule {

    /**
     * Send a team ping to all the given Recipients.
     *
     * @param recipients the recipients to send the packet to
     * @param playerUuid the player UUID who has originally sent the ping
     * @param location  the in-world location of the ping
     * @param isDoublePing true if the ping is a double-click ping, false otherwise
     * @since 1.1.1
     */
    public abstract void pingTeamMembers(Recipients recipients, UUID playerUuid, ApolloLocation location, boolean isDoublePing);

}
