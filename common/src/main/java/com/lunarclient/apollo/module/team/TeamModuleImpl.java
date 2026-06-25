/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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

import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.recipients.Recipients;
import com.lunarclient.apollo.team.v1.ResetTeamMembersMessage;
import com.lunarclient.apollo.team.v1.UpdateTeamMembersMessage;
import java.awt.Color;
import java.util.List;
import lombok.NonNull;
import net.kyori.adventure.text.Component;

/**
 * Provides the teams module.
 *
 * @since 1.0.0
 */
public final class TeamModuleImpl extends TeamModule {

    @Override
    public void updateTeamMembers(@NonNull Recipients recipients, @NonNull List<TeamMember> teamMembers) {
        UpdateTeamMembersMessage.Builder builder = UpdateTeamMembersMessage.newBuilder();

        for (TeamMember teamMember : teamMembers) {
            builder.addMembers(this.toProtobuf(teamMember));
        }

        ApolloManager.getNetworkManager().sendPacket(recipients, builder.build());
    }

    @Override
    public void resetTeamMembers(@NonNull Recipients recipients) {
        ResetTeamMembersMessage message = ResetTeamMembersMessage.getDefaultInstance();
        ApolloManager.getNetworkManager().sendPacket(recipients, message);
    }

    private com.lunarclient.apollo.team.v1.TeamMember toProtobuf(TeamMember member) {
        com.lunarclient.apollo.team.v1.TeamMember.Builder builder = com.lunarclient.apollo.team.v1.TeamMember.newBuilder()
            .setPlayerUuid(NetworkTypes.toProtobuf(member.getPlayerUuid()));

        Component displayName = member.getDisplayName();
        if (displayName != null) {
            builder.setAdventureJsonPlayerName(ApolloComponent.toJson(displayName));
        }

        Color markerColor = member.getMarkerColor();
        if (markerColor != null) {
            builder.setMarkerColor(NetworkTypes.toProtobuf(markerColor));
        }

        ApolloLocation location = member.getLocation();
        if (location != null) {
            builder.setLocation(NetworkTypes.toProtobuf(location));
        }

        return builder.build();
    }

}
