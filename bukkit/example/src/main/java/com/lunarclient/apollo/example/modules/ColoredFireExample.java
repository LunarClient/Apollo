package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.coloredfire.ColoredFireModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.Collection;
import java.util.UUID;

public class ColoredFireExample {

    private ColoredFireModule coloredFireModule;

    public void overrideColoredFireExample(UUID burningPlayer) {
        Collection<ApolloPlayer> viewers = Apollo.getPlayerManager().getPlayers();

        this.coloredFireModule.overrideColoredFire(viewers,
            burningPlayer, 
            Color.BLUE
        );
    }

    public void resetColoredFireExample(UUID burningPlayer) {
        Collection<ApolloPlayer> viewers = Apollo.getPlayerManager().getPlayers();

        this.coloredFireModule.resetColoredFire(viewers, burningPlayer);
    }

    public void resetColoredFiresExample(Player player) {
        Collection<ApolloPlayer> viewers = Apollo.getPlayerManager().getPlayers();

        for(ApolloPlayer viewer : viewers) {
            this.coloredFireModule.resetColoredFires(viewer);
        }
    }

}
