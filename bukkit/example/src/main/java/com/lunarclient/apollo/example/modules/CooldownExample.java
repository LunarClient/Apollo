package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.example.utilities.IconExample;
import com.lunarclient.apollo.module.cooldown.Cooldown;
import com.lunarclient.apollo.module.cooldown.CooldownModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Optional;

public class CooldownExample {

    private CooldownModule cooldownModule;

    public void displayCooldownExample(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        if (apolloPlayerOpt.isEmpty()) {
            player.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
            return;
        }

        cooldownModule.displayCooldown(apolloPlayerOpt.get(), Cooldown.builder()
            .name("enderpearl-cooldown")
            .duration(Duration.ofSeconds(15))
            .icon(IconExample.itemStackIconExample())
            .build()
        );
    }

    public void removeCooldownExample(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        if (apolloPlayerOpt.isEmpty()) {
            player.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
            return;
        }

        cooldownModule.removeCooldown(apolloPlayerOpt.get(), "enderpearl-cooldown");
    }

    public void resetCooldownsExample(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());

        if (apolloPlayerOpt.isEmpty()) {
            player.sendMessage(Component.text("Join with Lunar Client to test this feature!"));
            return;
        }


        cooldownModule.resetCooldowns(apolloPlayerOpt.get());
    }

}
