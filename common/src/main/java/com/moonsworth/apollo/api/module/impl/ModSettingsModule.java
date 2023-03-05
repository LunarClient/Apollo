package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.options.OptionProperty;
import com.moonsworth.apollo.api.options.StringListOption;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ModSettingsModule extends ApolloModule {

    private StringListOption settings;
    public ModSettingsModule() {
        super("ModSettingsModule");
    }

    @Override
    public List<ApolloOption<?>> options() {
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
