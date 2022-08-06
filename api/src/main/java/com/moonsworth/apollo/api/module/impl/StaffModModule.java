package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.events.impl.player.EventApolloPlayerJoin;
import com.moonsworth.apollo.api.events.impl.player.EventApolloPlayerUnregister;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.options.PermissionNodeOption;
import com.moonsworth.apollo.api.protocol.StaffMods;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaffModModule extends ApolloModule {

    private PermissionNodeOption xrayPermission;

    private List<UUID> playersWithStaffMods = new ArrayList<>();

    public StaffModModule() {
        super("StaffModModule");
        handle(EventApolloPlayerJoin.class, this::handleJoin);
        handle(EventApolloPlayerUnregister.class, this::handleQuit);
    }

    @Override
    public List<ApolloOption> options() {
        return ImmutableList.of(
                xrayPermission = new PermissionNodeOption("xray", "apollo.staffmods.xray")
        );
    }

    @Override
    public boolean notifyPlayers() {
        return false;
    }

    @Override
    public List<ApolloPlatform.Kind> runsOn() {
        return ImmutableList.of(ApolloPlatform.Kind.SERVER);
    }

    public void handleJoin(EventApolloPlayerJoin event) {
        if (event.getPlayer().hasPermission(xrayPermission.get())) {
            playersWithStaffMods.add(event.getPlayer().getUniqueId());
            event.getPlayer().sendPacket(StaffMods.newBuilder().setModule(StaffMods.StaffModule.XRAY).setValue(true).build());
        }
    }

    public void handleQuit(EventApolloPlayerUnregister apolloPlayer) {
        playersWithStaffMods.remove(apolloPlayer.getPlayer().getUniqueId());
    }
}
