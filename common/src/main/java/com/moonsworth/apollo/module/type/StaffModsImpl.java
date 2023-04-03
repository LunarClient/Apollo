package com.moonsworth.apollo.module.type;

import com.google.protobuf.Any;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.StaffMod;
import java.util.Set;
import java.util.stream.Collectors;
import lunarclient.apollo.common.MessageOperation;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.StaffModsMessage;

import static java.util.Objects.requireNonNull;

public final class StaffModsImpl extends com.moonsworth.apollo.module.type.StaffMods {

    public StaffModsImpl() {
        super();
    }

    @Override
    public void giveModule(final ApolloPlayer player, final StaffMod mod) {
        requireNonNull(player, "player");
        requireNonNull(mod, "mod");

        ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                .setModule(this.getName())
                .setOperation(OptionOperation.ADD)
                .setValue(Any.pack(this.to(mod)))
                .build());
    }

    @Override
    public void removeModule(final ApolloPlayer player, final StaffMod mod) {
        requireNonNull(player, "player");
        requireNonNull(mod, "staffMod");

        ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                .setModule(this.getName())
                .setOperation(OptionOperation.REMOVE)
                .setValue(Any.pack(this.to(mod)))
                .build());
    }

    @Override
    public void giveAllModules(final ApolloPlayer player) {
        requireNonNull(player, "player");

        final StaffMod mods = StaffMod.of(StaffMod.Module.getModules());
        ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                .setModule(this.getName())
                .setOperation(OptionOperation.ADD)
                .setValue(Any.pack(this.to(mods)))
                .build());
    }

    @Override
    public void removeAllModules(final ApolloPlayer player) {
        requireNonNull(player, "player");

        final StaffMod mods = StaffMod.of(StaffMod.Module.getModules());
        ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                .setModule(this.getName())
                .setOperation(OptionOperation.REMOVE)
                .setValue(Any.pack(this.to(mods)))
                .build());
    }

    private StaffModsMessage to(final StaffMod staffMod) {
        final Set<StaffModsMessage.Module> modules = staffMod.getModules().stream()
                .map(module -> StaffModsMessage.Module.valueOf(module.name()))
                .collect(Collectors.toSet());

        return StaffModsMessage.newBuilder()
                .addAllModules(modules)
                .build();
    }

    private StaffMod from(final StaffModsMessage message) {
        final Set<StaffMod.Module> modules = message.getModulesList().stream()
                .map(module -> StaffMod.Module.valueOf(module.name()))
                .collect(Collectors.toSet());

        return StaffMod.of(modules);
    }
}
