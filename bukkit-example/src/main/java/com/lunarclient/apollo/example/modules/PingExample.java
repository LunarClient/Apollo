package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.ping.ApolloPlayerPingEvent;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.module.ping.PingModule;
import com.lunarclient.apollo.player.ApolloPlayerManager;
import com.lunarclient.apollo.recipients.Recipients;
import org.bukkit.entity.Entity;

import java.util.Optional;
import java.util.stream.Collectors;

public class PingExample {

    private final PingModule pingModule = Apollo.getModuleManager().getModule(PingModule.class);

    public PingExample() {
        EventBus.getBus().register(ApolloPlayerPingEvent.class, this::onApolloPlayerPing);
    }

    private void onApolloPlayerPing(ApolloPlayerPingEvent apolloPlayerPingEvent) {
        TeamExample teamExample = ApolloExamplePlugin.getPlugin().getTeamExample();

        // Forward the event to everyone on the team. You may want to limit by distance or other factors.
        teamExample.getByPlayerUuid(apolloPlayerPingEvent.getPlayerUuid()).ifPresent(team -> {
            ApolloPlayerManager apolloPlayerManager = Apollo.getPlayerManager();
            Recipients recipients = Recipients.of(
                team.getMembers().stream()
                    .map(Entity::getUniqueId)
                    .map(apolloPlayerManager::getPlayer)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList())
            );

            team.getMembers().forEach(member -> {
                this.pingModule.pingTeamMembers(recipients, apolloPlayerPingEvent.getPlayerUuid(), apolloPlayerPingEvent.getLocation(), apolloPlayerPingEvent.isDoublePing());
            });
        });
    }
}
