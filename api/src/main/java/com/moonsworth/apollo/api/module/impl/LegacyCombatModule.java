package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.options.BooleanOption;
import com.moonsworth.apollo.api.options.NumberOption;
import com.moonsworth.apollo.api.options.OptionProperty;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
public class LegacyCombatModule extends ApolloModule {

    private BooleanOption legacyCrits;
    private BooleanOption legacyAttackSpeed; // TODO send to client
    private BooleanOption disableEntityCramming; // TODO send to client
    private BooleanOption disableEnderpearlCooldown; // TODO send to client
    private BooleanOption disableSwingSounds; // TODO send to client
    private BooleanOption disableSweep;
    private BooleanOption armorDurability;
    private NumberOption<Integer> armorDurabilityReduction;
    private NumberOption<Integer> noDamageTicks;
    private NumberOption<Float> attackSpeed;
    private NumberOption<Double> projectileRandomnessValue;
    private BooleanOption projectileDamage;
    private NumberOption<Integer> regenInterval;
    private NumberOption<Integer> regenHealAmount;
    private NumberOption<Integer> regenExhaustionHealAmount;

    public LegacyCombatModule() {
        super("LegacyCombatModule");
    }

    @Override
    public List<ApolloOption> options() {
        return ImmutableList.of(
                disableEntityCramming = new BooleanOption("disableEntityCramming", OptionProperty.CLIENT,true),
                disableEnderpearlCooldown = new BooleanOption("disableEnderpearlCooldown", OptionProperty.CLIENT,true),
                disableSwingSounds = new BooleanOption("disableSwingSounds", OptionProperty.SERVER,true),
                disableSweep = new BooleanOption("disableSweep", OptionProperty.SERVER,true),
                legacyCrits = new BooleanOption("legacyCrits", OptionProperty.SERVER, true),
                legacyAttackSpeed = new BooleanOption("legacyAttackSpeed", OptionProperty.CLIENT, true),
                armorDurability = new BooleanOption("armorDurability", OptionProperty.SERVER, true),
                projectileDamage = new BooleanOption("projectileDamage", OptionProperty.SERVER,true),
                armorDurabilityReduction = new NumberOption<>("armorDurabilityReduction", OptionProperty.SERVER,1, 0, 5),
                noDamageTicks = new NumberOption<>("noDamageTicks", OptionProperty.SERVER, 19, 1, 20),
                attackSpeed = new NumberOption<>("attackSpeed", OptionProperty.SERVER,16F, 8F, 32F),
                projectileRandomnessValue = new NumberOption<>("projectileRandomnessValue", OptionProperty.SERVER,0.1D, 0.001D, 1D),
                regenInterval = new NumberOption<>("regenInterval", OptionProperty.SERVER,3990, 1000, 5000),
                regenHealAmount = new NumberOption<>("regenHealAmount", OptionProperty.SERVER, 1, 0, 5),
                regenExhaustionHealAmount = new NumberOption<>("regenExhaustionHealAmount", OptionProperty.SERVER,3, 0, 5)
        );
    }

    @Override
    public boolean notifyPlayers() {
        return true;
    }

    @Override
    public List<ApolloPlatform.Kind> runsOn() {
        return Collections.singletonList(ApolloPlatform.Kind.SERVER);
    }
}
