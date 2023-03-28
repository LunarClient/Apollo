package com.moonsworth.apollo.player.ui;

import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.module.type.Teams;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.world.ApolloLocation;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.awt.*;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a team which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Team {

    UUID teamId;

    Map<UUID, Teammate> teammates;

    public boolean addMember(final Team.Teammate teammate, final ApolloPlayer player) {
        return Apollo.getModuleManager().getModule(Teams.class)
            .map(module -> module.addMember(player, this, teammate)).orElse(false);
    }

    public boolean removeMember(final ApolloPlayer player) {
        return Apollo.getModuleManager().getModule(Teams.class)
            .map(module -> module.removeMember(player, this)).orElse(false);
    }

    public void refreshTeam() {
        Apollo.getModuleManager().getModule(Teams.class).ifPresent(module -> module.refreshTeam(this));
    }

    /**
     * Represents a teammate which can be shown on the client.
     *
     * @since 1.0.0
     */
    @Value(staticConstructor = "of")
    public static class Teammate {

        /**
         * Returns the teammate {@link Color}.
         *
         * @return the teammate color
         * @since 1.0.0
         */
        @NonFinal @Setter Color color;

        /**
         * Returns the teammate {@link ApolloLocation}.
         * <p>
         * The provided location is only used when the player is out of
         * render distance for the observer. If you know that the players
         * are close, you don't need to send the location.
         *
         * @return the teammate location
         * @since 1.0.0
         */
        @NonFinal @Setter ApolloLocation location;
    }

    @Override
    public int hashCode() {
        return this.teamId.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof UUID)) {
            return false;
        }

        UUID compareTo = (UUID) object;
        return this.teamId.equals(compareTo);
    }
}
