package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.events.impl.player.EventApolloPlayerRegister;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.options.OptionProperty;
import com.moonsworth.apollo.api.options.StringListOption;
import com.moonsworth.apollo.api.protocol.AddWaypointMessage;
import com.moonsworth.apollo.api.protocol.ModSetting;
import com.moonsworth.apollo.api.protocol.ModSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModSettingsModule extends ApolloModule {

    private StringListOption settings;
    public ModSettingsModule() {
        super("ModSettingsModule");
    }

    @Override
    public List<ApolloOption> options() {
        return ImmutableList.of(settings = new StringListOption("settings", new ArrayList<>(), OptionProperty.CLIENT));
    }

    @Override
    public boolean notifyPlayers() {
        return true;
    }

    @Override
    public List<ApolloPlatform.Kind> runsOn() {
        return ImmutableList.of(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

}
