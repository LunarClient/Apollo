/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.module.team;

import com.lunarclient.apollo.audience.Audience;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
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
    public void updateTeamMembers(@NonNull Audience audience, @NonNull List<TeamMember> teamMembers) {
        List<com.lunarclient.apollo.team.v1.TeamMember> teamMembersProto = teamMembers.stream()
            .map(teamMember -> com.lunarclient.apollo.team.v1.TeamMember.newBuilder()
                .setPlayerUuid(NetworkTypes.toProtobuf(teamMember.getPlayerUuid()))
                .setPlayerDisplayName(NetworkTypes.toProtobuf(teamMember.getDisplayName()))
                .setLocation(NetworkTypes.toProtobuf(teamMember.getLocation()))
                .setMarkerColor(NetworkTypes.toProtobuf(teamMember.getMarkerColor()))
                .build()
            )
            .collect(Collectors.toList());

        UpdateTeamMembersMessage message = UpdateTeamMembersMessage.newBuilder()
            .addAllMembers(teamMembersProto)
            .build();

        audience.forEachAudience(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void resetTeamMembers(@NonNull Audience audience) {
        UpdateTeamMembersMessage message = UpdateTeamMembersMessage.getDefaultInstance();
        audience.forEachAudience(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

}
