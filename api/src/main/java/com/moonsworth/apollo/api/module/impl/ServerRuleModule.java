package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.options.BooleanOption;
import com.moonsworth.apollo.api.options.OptionProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ServerRuleModule extends ApolloModule {

    private BooleanOption competitiveGame;
    private BooleanOption shadersDisabled;

    public ServerRuleModule() {
        super("ServerRuleModule");
    }

    @Override
    public List<ApolloOption> options() {
        return ImmutableList.of(
                competitiveGame = new BooleanOption("competitiveGame", OptionProperty.CLIENT, false),
                shadersDisabled = new BooleanOption("shadersDisabled", OptionProperty.CLIENT, false)
        );
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
