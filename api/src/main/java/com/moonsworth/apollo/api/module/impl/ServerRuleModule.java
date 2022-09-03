package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.options.BooleanOption;
import com.moonsworth.apollo.api.options.NumberOption;
import com.moonsworth.apollo.api.options.OptionProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ServerRuleModule extends ApolloModule {

    private BooleanOption competitiveGame;
    private BooleanOption shadersDisabled;
    private BooleanOption disableF6Broadcasting;
    private BooleanOption antiPortalTraps;
    private BooleanOption cpsLimiter;
    private NumberOption<Integer> cpsLimit;
    private BooleanOption affectBrightness;
    private NumberOption<Integer> brightness;

    public ServerRuleModule() {
        super("ServerRuleModule");
    }

    @Override
    public List<ApolloOption> options() {
        return ImmutableList.of(
                competitiveGame = new BooleanOption("competitiveGame", OptionProperty.CLIENT, false),
                shadersDisabled = new BooleanOption("shadersDisabled", OptionProperty.CLIENT, false),
                disableF6Broadcasting = new BooleanOption("disableF6Broadcasting", OptionProperty.CLIENT, false),
                antiPortalTraps = new BooleanOption("antiPortalTraps", OptionProperty.CLIENT, false),
                cpsLimiter = new BooleanOption("cpsLimiter", OptionProperty.CLIENT, false),
                cpsLimit = new NumberOption<>("cpsLimit", OptionProperty.CLIENT, 20, 1, 100),
                affectBrightness = new BooleanOption("affectBrightness", OptionProperty.CLIENT, false),
                brightness = new NumberOption<>("brightness", OptionProperty.CLIENT, 50, 1, 10000)
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
