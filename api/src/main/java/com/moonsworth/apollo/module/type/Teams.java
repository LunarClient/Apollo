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

    public abstract Team createTeam();

    public abstract Team createTeam(final Map<UUID, Team.Teammate> teammates);

    public abstract boolean deleteTeam(final Team team);

    public abstract boolean addMember(final ApolloPlayer player, final Team team, final Team.Teammate teammate);

    public abstract boolean removeMember(final ApolloPlayer player, final Team team);

    public abstract @Nullable Team fromPlayer(final UUID uuid);

    public abstract @Nullable Team fromTeam(final UUID uuid);

    public abstract void refreshTeam(final Team team);

    public abstract void refreshTeams();
}

