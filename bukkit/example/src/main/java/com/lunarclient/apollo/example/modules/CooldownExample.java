package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.module.cooldown.Cooldown;
import com.lunarclient.apollo.module.cooldown.CooldownModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Optional;

public class CooldownExample {

    private final CooldownModule cooldownModule = Apollo.getModuleManager().getModule(CooldownModule.class);

    public void displayCooldownExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresentOrElse(apolloPlayer -> {
            this.cooldownModule.displayCooldown(apolloPlayer, Cooldown.builder()
                .name("enderpearl-cooldown")
                .duration(Duration.ofSeconds(15))
                .icon(ItemStackIcon.builder()
                    .itemId(Material.ENDER_PEARL.getId())
                    .build()
                )
                .build()
            );
        }, () -> viewer.sendMessage(Component.text("Join with Lunar Client to test this feature!")));
    }

    public void removeCooldownExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.cooldownModule.removeCooldown(apolloPlayer, "enderpearl-cooldown"));
    }

    public void resetCooldownsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.cooldownModule::resetCooldowns);
    }

}
