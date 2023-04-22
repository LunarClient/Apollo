package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.StaffMods;
import com.lunarclient.apollo.staffmod.v1.DisableStaffModsMessage;
import com.lunarclient.apollo.staffmod.v1.EnableStaffModsMessage;
import com.lunarclient.apollo.staffmod.v1.StaffMod;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public final class StaffModsImpl extends com.lunarclient.apollo.module.type.StaffMods {

    public StaffModsImpl() {
        super();
    }

    @Override
    public void enableStaffMods(ApolloPlayer player, StaffMods mods) {
        requireNonNull(player, "player");
        requireNonNull(mods, "staffMods");

        // TODO:
        Set<StaffMod> staffMods = new HashSet<>();
//        Set<StaffMod> staffMods = mods.getMods().stream()
//            .map()
//            .collect(Collectors.toList())

        ((AbstractApolloPlayer) player).sendPacket(EnableStaffModsMessage.newBuilder()
            .addAllStaffMods(staffMods)
            .build());
    }

    @Override
    public void disableStaffMods(ApolloPlayer player, StaffMods mods) {
        requireNonNull(player, "player");
        requireNonNull(mods, "staffMods");

        // TODO:
        Set<StaffMod> staffMods = new HashSet<>();
//        Set<StaffMod> staffMods = mods.getMods().stream()
//            .map()
//            .collect(Collectors.toList())

        ((AbstractApolloPlayer) player).sendPacket(DisableStaffModsMessage.newBuilder()
            .addAllStaffMods(staffMods)
            .build());
    }
}
