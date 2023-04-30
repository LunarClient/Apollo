package com.lunarclient.apollo.module.team;

import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.team.v1.UpdateTeamMembersMessage;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;

/**
 * Provides the teams module.
 *
 * @since 1.0.0
 */
public final class TeamModuleImpl extends TeamModule {

    @Override
    public void updateTeamMembers(@NonNull ApolloPlayer viewer, @NonNull List<TeamMember> teamMembers) {
        List<com.lunarclient.apollo.team.v1.TeamMember> teamMembersProto = teamMembers.stream()
            .map(teamMember -> com.lunarclient.apollo.team.v1.TeamMember.newBuilder()
                .setPlayerUuid(NetworkTypes.toProtobuf(teamMember.getPlayerUuid()))
                .setLocation(NetworkTypes.toProtobuf(teamMember.getLocation()))
                .setMarkerColor(NetworkTypes.toProtobuf(teamMember.getMarkerColor()))
                .build()
            )
            .collect(Collectors.toList());

        ((AbstractApolloPlayer) viewer).sendPacket(UpdateTeamMembersMessage.newBuilder()
            .addAllMembers(teamMembersProto)
            .build()
        );
    }

    @Override
    public void resetTeamMembers(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(UpdateTeamMembersMessage.getDefaultInstance());
    }

}
