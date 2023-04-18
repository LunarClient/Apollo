package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.option.Option;
import com.moonsworth.apollo.option.SimpleOption;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Team;
import io.leangen.geantyref.TypeToken;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the team module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class Teams extends ApolloModule {

    /**
     * Disbands team if empty.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISBAND_TEAMS_IF_EMPTY = Option.<Boolean>builder()
        .comment("Set to 'true' to automatically disband teams if empty, otherwise 'false'.")
        .node("disband-teams-if-empty").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    /**
     * Automatically sends updates to the players whenever
     * someone joins or gets removed from the team.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> AUTOMATICALLY_REFRESH_ON_CHANGE = Option.<Boolean>builder()
        .comment("Set to 'true' to automatically send updates on team change, otherwise 'false'.")
        .node("automatically-refresh-on-change").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

    Teams() {
        super("Teams");

        this.registerOptions(
            Teams.DISBAND_TEAMS_IF_EMPTY,
            Teams.AUTOMATICALLY_REFRESH_ON_CHANGE
        );
    }

    /**
     * Creates a {@link Team}.
     *
     * @return the team
     * @since 1.0.0
     */
    public abstract Team createTeam();

    /**
     * Creates an {@link Team} with a {@link Map} that contains {@link UUID}
     * player uuid as key and {@link Team.Teammate} as value.
     *
     * @param teammates the teammates
     * @return the team
     * @since 1.0.0
     */
    public abstract Team createTeam(Map<UUID, Team.Teammate> teammates);

    /**
     * Deletes the provided {@link Team}.
     *
     * @param team the team
     * @return true if the team was deleted, otherwise false
     * @since 1.0.0
     */
    public abstract boolean deleteTeam(Team team);

    /**
     * Adds a new teammate to the team for the provided {@link ApolloPlayer}.
     *
     * @param player the player
     * @param team the team
     * @param teammate the teammate
     * @return true if the teammate was added, otherwise false
     * @since 1.0.0
     */
    public abstract boolean addMember(ApolloPlayer player, Team team, Team.Teammate teammate);

    /**
     * Removes the teammate from the team for the provided {@link ApolloPlayer}.
     *
     * @param player the player
     * @param team the team
     * @return true if the teammate was removed, otherwise false
     * @since 1.0.0
     */
    public abstract boolean removeMember(ApolloPlayer player, Team team);

    /**
     * Returns the {@link Team} from the provided player {@link UUID}.
     *
     * @param uuid the player uuid
     * @return the team
     * @since 1.0.0
     */
    public abstract @Nullable Team fromPlayer(UUID uuid);

    /**
     * Returns the {@link Team} from the provided team {@link UUID}.
     *
     * @param uuid the team uuid
     * @return the team
     * @since 1.0.0
     */
    public abstract @Nullable Team fromTeam(UUID uuid);

    /**
     * Refreshes the team for all players.
     *
     * @param team the team
     * @since 1.0.0
     */
    public abstract void refreshTeam(Team team);

    /**
     * Refreshes all teams for all players.
     *
     * @since 1.0.0
     */
    public abstract void refreshTeams();
}
