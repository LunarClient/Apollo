package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.StaffMod;
import com.moonsworth.apollo.protocol.StaffModsMessage;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public final class StaffModsImpl extends com.moonsworth.apollo.module.type.StaffMods {

    public StaffModsImpl() {
        super();

        NetworkOptions.register(StaffMod.class, StaffModsMessage.getDefaultInstance(), new OptionConverter<StaffMod, StaffModsMessage>() {
            @Override
            public StaffModsMessage to(final StaffMod object) throws IllegalArgumentException {
                Set<StaffModsMessage.Module> modules = object.getModules().stream()
                    .map(module -> StaffModsMessage.Module.valueOf(module.name()))
                    .collect(Collectors.toSet());

                return StaffModsMessage.newBuilder()
                    .addAllModules(modules)
                    .build();
            }

            @Override
            public StaffMod from(final StaffModsMessage message) throws IllegalArgumentException {
                Set<StaffMod.Module> modules = message.getModulesList().stream()
                    .map(module -> StaffMod.Module.valueOf(module.name()))
                    .collect(Collectors.toSet());

                return StaffMod.of(modules);
            }
        });
    }

    // TODO
    @Override
    public void giveModule(final ApolloPlayer player, final StaffMod.Module module) {
        requireNonNull(player, "player");
        requireNonNull(module, "module");
    }

    @Override
    public void removeModule(final ApolloPlayer player, final StaffMod.Module module) {
        requireNonNull(player, "player");
        requireNonNull(module, "module");
    }

    @Override
    public void giveAllModules(final ApolloPlayer player) {
        requireNonNull(player, "player");
    }

    @Override
    public void removeAllModules(final ApolloPlayer player) {
        requireNonNull(player, "player");
    }

}