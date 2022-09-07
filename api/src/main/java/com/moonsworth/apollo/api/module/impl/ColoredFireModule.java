package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ColoredFireModule extends ApolloModule {

    public ColoredFireModule() {
        super("ColoredFireModule");
    }

    @Override
    public List<ApolloOption> options() {
        return new ArrayList<>();
    }

    @Override
    public boolean notifyPlayers() {
        return true;
    }

    @Override
    public List<ApolloPlatform.Kind> runsOn() {
        return ImmutableList.of(ApolloPlatform.Kind.SERVER);
    }
}
