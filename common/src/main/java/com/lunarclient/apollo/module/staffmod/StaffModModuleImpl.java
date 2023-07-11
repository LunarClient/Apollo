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
package com.lunarclient.apollo.module.staffmod;

import com.lunarclient.apollo.audience.Audience;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.staffmod.v1.DisableStaffModsMessage;
import com.lunarclient.apollo.staffmod.v1.EnableStaffModsMessage;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.NonNull;

/**
 * Provides the staff mod module.
 *
 * @since 1.0.0
 */
public final class StaffModModuleImpl extends StaffModModule {

    @Override
    public void enableStaffMods(@NonNull Audience audience, @NonNull List<StaffMod> mods) {
        Set<com.lunarclient.apollo.staffmod.v1.StaffMod> staffModsProto = mods.stream()
            .map(this::toProtobuf)
            .collect(Collectors.toSet());

        EnableStaffModsMessage message = EnableStaffModsMessage.newBuilder()
            .addAllStaffMods(staffModsProto)
            .build();

        audience.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    @Override
    public void disableStaffMods(@NonNull Audience audience, @NonNull List<StaffMod> mods) {
        Set<com.lunarclient.apollo.staffmod.v1.StaffMod> staffModsProto = mods.stream()
            .map(this::toProtobuf)
            .collect(Collectors.toSet());

        DisableStaffModsMessage message = DisableStaffModsMessage.newBuilder()
            .addAllStaffMods(staffModsProto)
            .build();

        audience.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

    private com.lunarclient.apollo.staffmod.v1.StaffMod toProtobuf(StaffMod staffMod) {
        return com.lunarclient.apollo.staffmod.v1.StaffMod.forNumber(staffMod.ordinal() + 1);
    }

}
