package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.StaffMods;
import com.lunarclient.apollo.staffmod.v1.DisableStaffModsMessage;
import com.lunarclient.apollo.staffmod.v1.EnableStaffModsMessage;
import com.lunarclient.apollo.staffmod.v1.StaffMod;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public final class StaffModsImpl extends com.lunarclient.apollo.module.type.StaffMods {

    public StaffModsImpl() {
        super();
    }

    @Override
    public void enableStaffMods(ApolloPlayer player, StaffMods mods) {
        requireNonNull(player, "player");
        requireNonNull(mods, "staffMods");

        Set<StaffMod> staffMods = mods.getMods().stream()
            .map(mod -> StaffMod.forNumber(mod.ordinal() + 1))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) player).sendPacket(EnableStaffModsMessage.newBuilder()
            .addAllStaffMods(staffMods)
            .build());
    }

    @Override
    public void disableStaffMods(ApolloPlayer player, StaffMods mods) {
        requireNonNull(player, "player");
        requireNonNull(mods, "staffMods");

        Set<StaffMod> staffMods = mods.getMods().stream()
            .map(mod -> StaffMod.forNumber(mod.ordinal() + 1))
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) player).sendPacket(DisableStaffModsMessage.newBuilder()
            .addAllStaffMods(staffMods)
            .build());
    }

}
