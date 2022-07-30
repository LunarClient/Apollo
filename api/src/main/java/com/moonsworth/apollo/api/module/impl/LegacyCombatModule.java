package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.module.Configureable;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.options.BooleanOption;
import com.moonsworth.apollo.api.options.NumberOption;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
public class LegacyCombatModule extends ApolloModule implements Configureable {

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

    @Override
    public void enable() {
    }

    @Override
    public List<ApolloOption> options() {
        return ImmutableList.of(
                disableEntityCramming = new BooleanOption("disableEntityCramming", true),
                disableEnderpearlCooldown = new BooleanOption("disableEnderpearlCooldown", true),
                disableSwingSounds = new BooleanOption("disableSwingSounds", true),
                disableSweep = new BooleanOption("disableSweep", true),
                legacyCrits = new BooleanOption("legacyCrits", true),
                legacyAttackSpeed = new BooleanOption("legacyAttackSpeed", true),
                armorDurability = new BooleanOption("armorDurability", true),
                projectileDamage = new BooleanOption("projectileDamage", true),
                armorDurabilityReduction = new NumberOption<>("armorDurabilityReduction", 1, 0, 5),
                noDamageTicks = new NumberOption<>("noDamageTicks", 19, 20, 1),
                attackSpeed = new NumberOption<>("attackSpeed", 16F, 32F, 8F),
                projectileRandomnessValue = new NumberOption<>("projectileRandomnessValue", 0.1D, 0.001D, 1D),
                regenInterval = new NumberOption<>("regenInterval", 3990, 1000, 5000),
                regenHealAmount = new NumberOption<>("regenHealAmount", 1, 0, 5),
                regenExhaustionHealAmount = new NumberOption<>("regenExhaustionHealAmount", 3, 0, 5)
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

    @Override
    public String name() {
        return "LegacyCombatModule";
    }

    @Override
    public void load(Map<String, Object> configuration) {
        for (ApolloOption option : getOptions()) {
            if (!configuration.containsKey(name() + "." + option.getId())) {
                continue;
            }
            try {
                option.load(configuration.get(name() + "." + option.getId()).toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
