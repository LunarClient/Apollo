package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.StaffMod;
import com.lunarclient.apollo.player.AbstractApolloPlayer;

import java.util.Set;
import java.util.stream.Collectors;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.StaffModsMessage;

import static java.util.Objects.requireNonNull;

public final class StaffModsImpl extends StaffMods {

    public StaffModsImpl() {
        super();
    }

    @Override
    public void giveModule(ApolloPlayer player, StaffMod mod) {
        requireNonNull(player, "player");
        requireNonNull(mod, "mod");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.ADD, this.to(mod));
    }

    @Override
    public void removeModule(ApolloPlayer player, StaffMod mod) {
        requireNonNull(player, "player");
        requireNonNull(mod, "staffMod");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.REMOVE, this.to(mod));
    }

    @Override
    public void giveAllModules(ApolloPlayer player) {
        requireNonNull(player, "player");

        StaffMod mods = StaffMod.of(StaffMod.Module.getModules());
        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.ADD, this.to(mods));
    }

    @Override
    public void removeAllModules(ApolloPlayer player) {
        requireNonNull(player, "player");

        StaffMod mods = StaffMod.of(StaffMod.Module.getModules());
        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.REMOVE, this.to(mods));
    }

    private StaffModsMessage to(StaffMod staffMod) {
        Set<StaffModsMessage.Module> modules = staffMod.getModules().stream()
                .map(module -> StaffModsMessage.Module.valueOf(module.name()))
                .collect(Collectors.toSet());

        return StaffModsMessage.newBuilder()
                .addAllModules(modules)
                .build();
    }

    private StaffMod from(StaffModsMessage message) {
        Set<StaffMod.Module> modules = message.getModulesList().stream()
                .map(module -> StaffMod.Module.valueOf(module.name()))
                .collect(Collectors.toSet());

        return StaffMod.of(modules);
    }
}
