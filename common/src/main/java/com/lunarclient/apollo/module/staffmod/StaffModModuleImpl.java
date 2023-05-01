package com.lunarclient.apollo.module.staffmod;

import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
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
    public void enableStaffMods(@NonNull ApolloPlayer viewer, @NonNull List<StaffMod> mods) {
        Set<com.lunarclient.apollo.staffmod.v1.StaffMod> staffModsProto = mods.stream()
            .map(this::toProtobuf)
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(EnableStaffModsMessage.newBuilder()
            .addAllStaffMods(staffModsProto)
            .build());
    }

    @Override
    public void disableStaffMods(@NonNull ApolloPlayer viewer, @NonNull List<StaffMod> mods) {
        Set<com.lunarclient.apollo.staffmod.v1.StaffMod> staffModsProto = mods.stream()
            .map(this::toProtobuf)
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(DisableStaffModsMessage.newBuilder()
            .addAllStaffMods(staffModsProto)
            .build());
    }

    private com.lunarclient.apollo.staffmod.v1.StaffMod toProtobuf(StaffMod staffMod) {
        return com.lunarclient.apollo.staffmod.v1.StaffMod.forNumber(staffMod.ordinal() + 1);
    }

}
