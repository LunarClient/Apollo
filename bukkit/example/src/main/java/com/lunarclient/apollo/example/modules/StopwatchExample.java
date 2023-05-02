package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.stopwatch.StopwatchModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.util.Optional;

public class StopwatchExample {

    private final StopwatchModule stopwatchModule = Apollo.getModuleManager().getModule(StopwatchModule.class);

    public void startStopwatchExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.stopwatchModule::startStopwatch);
    }

    public void stopStopwatchExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.stopwatchModule::stopStopwatch);
    }

    public void resetStopwatchExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.stopwatchModule::resetStopwatch);
    }

}
