package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.events.impl.player.EventApolloPlayerRegister;
import com.moonsworth.apollo.api.events.impl.player.EventApolloPlayerUnregister;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.module.Configureable;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.options.PermissionNodeOption;
import com.moonsworth.apollo.api.protocol.StaffMods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class StaffModModule extends ApolloModule implements Configureable {

    private PermissionNodeOption xrayPermission;

    private List<UUID> playersWithStaffMods = new ArrayList<>();

    public StaffModModule() {
        super("StaffModModule");
        handle(EventApolloPlayerRegister.class, this::handleJoin);
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

    public void handleJoin(EventApolloPlayerRegister event) {
        if (event.getPlayer().hasPermission(xrayPermission.get())) {
            playersWithStaffMods.add(event.getPlayer().getUniqueId());
            event.getPlayer().sendPacket(StaffMods.newBuilder().setModule(StaffMods.StaffModule.XRAY).setValue(true).build());
        }
    }

    public void handleQuit(EventApolloPlayerUnregister apolloPlayer) {
        playersWithStaffMods.remove(apolloPlayer.getPlayer().getUniqueId());
    }

    @Override
    public void load(Map<String, Object> configuration) {
        for (ApolloOption option : getOptions()) {
            if (!configuration.containsKey(getName() + "." + option.getId())) {
                continue;
            }
            try {
                option.load(configuration.get(getName() + "." + option.getId()).toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
