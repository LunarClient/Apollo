package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.example.ApolloExamplePlugin;
import com.lunarclient.apollo.module.team.TeamModule;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamExample implements Listener {

    // Uncompleted

    private final TeamModule teamModule = Apollo.getModuleManager().getModule(TeamModule.class);

    public TeamExample() {
        Bukkit.getPluginManager().registerEvents(this, ApolloExamplePlugin.getPlugin());
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {

    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {

    }

    public class TeamUpdateTask extends BukkitRunnable {

        public TeamUpdateTask() {
            this.runTaskTimerAsynchronously(ApolloExamplePlugin.getPlugin(), 5L, 5L);
        }

        @Override
        public void run() {

        }
    }
}
