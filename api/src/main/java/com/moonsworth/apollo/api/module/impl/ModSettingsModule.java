package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.options.OptionProperty;
import com.moonsworth.apollo.api.options.StringListOption;
import com.moonsworth.apollo.api.protocol.ModSetting;
import com.moonsworth.apollo.api.protocol.ModSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModSettingsModule extends ApolloModule {
    private ModSettings modSettings;
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
        return false;
    }

    @Override
    public List<ApolloPlatform.Kind> runsOn() {
        return ImmutableList.of(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

    @Override
    protected void loadConfiguration(Map<String, Object> configuration) {
        if (!configuration.containsKey(getName() + ".force-disabled-mods")) {
            return;
        }
        ModSettings.Builder settings = ModSettings.newBuilder();
        List<String> modIds = (List<String>) configuration.get(getName() + ".force-disabled-mods");
        for (String modId : modIds) {
            settings.putModSettings(modId, ModSetting.newBuilder().setEnabled(false).build());
        }
        modSettings = settings.build();
    }
}
