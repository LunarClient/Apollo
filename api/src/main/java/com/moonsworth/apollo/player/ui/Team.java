package com.moonsworth.apollo.player.ui;

import com.moonsworth.apollo.Apollo;
import com.moonsworth.apollo.module.type.Teams;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.world.ApolloLocation;
import java.awt.Color;
import java.util.Map;
import java.util.UUID;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

/**
 * Represents a team which can be shown on the client.
 *
 * @since 1.0.0
 */
@Value(staticConstructor = "of")
public class Team {

    /**
     * Returns the team {@link UUID}.
     *
     * @return the team uuid
     * @since 1.0.0
     */
    UUID teamId;

    /**
     * Returns the team {@link Map} that contains {@link UUID}
     * player uuid as key and {@link Team.Teammate} as value.
     *
     * @return the teammates map
     * @since 1.0.0
     */
    Map<UUID, Teammate> teammates;

    /**
     * Adds a new teammate to the team for the provided {@link ApolloPlayer}.
     *
     * @param teammate the teammate
     * @param player the player
     * @return true if the teammate was added, otherwise false
     * @since 1.0.0
     */
    public boolean addMember(Team.Teammate teammate, ApolloPlayer player) {
        return Apollo.getModuleManager().getModule(Teams.class)
            .map(module -> module.addMember(player, this, teammate)).orElse(false);
    }

    /**
     * Removes the teammate from the team for the provided {@link ApolloPlayer}.
     *
     * @param player the player
     * @return true if the teammate was removed, otherwise false
     * @since 1.0.0
     */
    public boolean removeMember(ApolloPlayer player) {
        return Apollo.getModuleManager().getModule(Teams.class)
            .map(module -> module.removeMember(player, this)).orElse(false);
    }

    /**
     * Refreshes the team for all players.
     *
     * @since 1.0.0
     */
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
         *
         * <p>The provided location is only used when the player is out of
         * render distance for the observer. If you know that the players
         * are close, you don't need to send the location.</p>
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
