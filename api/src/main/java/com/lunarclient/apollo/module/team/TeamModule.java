package com.lunarclient.apollo.module.team;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the team module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "team", name = "Team")
public abstract class TeamModule extends ApolloModule {

    /**
     * Upserts all team members for the given {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @param teamMembers all current team members of the viewer
     * @since 1.0.0
     */
    public abstract void updateTeamMembers(ApolloPlayer viewer, List<TeamMember> teamMembers);

    /**
     * Resets all team members for the given {@link ApolloPlayer}.
     *
     * @param viewer the player who is receiving the packet
     * @since 1.0.0
     */
    public abstract void resetTeamMembers(ApolloPlayer viewer);

}
