package com.lunarclient.apollo.module.ping;

import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.recipients.Recipients;
import org.jetbrains.annotations.ApiStatus;

import java.util.UUID;

@ApiStatus.NonExtendable
@ModuleDefinition(id = "ping", name = "Ping")
public abstract class PingModule extends ApolloModule {
    public abstract void pingTeamMembers(Recipients recipients, UUID playerUuid, ApolloLocation location, boolean isDoublePing);
}
