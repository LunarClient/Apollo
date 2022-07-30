package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.options.BooleanOption;

import java.util.List;

public class LegacyCombatModule extends ApolloModule {
    @Override
    public void enable() {

    }

    @Override
    public List<ApolloOption> options() {
        return ImmutableList.of(
            new BooleanOption("t", false)
        );
    }

    @Override
    public boolean notifyPlayers() {
        return true;
    }

}
