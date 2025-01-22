/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.module.evnt;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.module.evnt.event.EventGame;
import com.lunarclient.apollo.module.evnt.event.EventPlayerStatus;
import com.lunarclient.apollo.module.evnt.event.EventStatus;
import com.lunarclient.apollo.option.NumberOption;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.recipients.Recipients;
import io.leangen.geantyref.TypeToken;
import org.jetbrains.annotations.ApiStatus;
import java.util.List;
import java.util.UUID;

/**
 * Represents the EVNT module.
 *
 * @since 1.0.0 // TODO
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "evnt", name = "EVNT")
public abstract class EVNTModule extends ApolloModule {

    public static final SimpleOption<Boolean> DISABLE_ENDERPEARL_COOLDOWN = Option.<Boolean>builder()
        .comment("Set to 'true' to disable enderpearl cooldown, otherwise 'false'.")
        .node("disable-enderpearl-cooldown").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    public static final SimpleOption<Boolean> DISABLE_PROJECTILE_DAMAGE = Option.<Boolean>builder()
        .comment("Set to 'true' to disable projectile damage, otherwise 'false'.")
        .node("override-projectile-damage").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    public static final SimpleOption<Boolean> LEGACY_ATTACK_SPEED = Option.<Boolean>builder()
        .comment("Set to 'true' to enable legacy attack speed, otherwise 'false'.")
        .node("legacy-attack-speed").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    public static final SimpleOption<Boolean> LEGACY_ENCHANTING = Option.<Boolean>builder()
        .comment("Set to 'true' to enable legacy enchanting, otherwise 'false'.")
        .node("legacy-enchanting").type(TypeToken.get(Boolean.class))
        .defaultValue(false).notifyClient().build();

    public static final SimpleOption<Boolean> OVERRIDE_ARMOR_DURABILITY_REDUCTION = Option.<Boolean>builder()
        .comment("Set to 'true' to override armor durability reduction, otherwise 'false'.")
        .node("override-armor-durability-reduction").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    public static final NumberOption<Integer> ARMOR_DURABILITY_REDUCTION = Option.<Integer>number()
        .comment("Set the armor durability reduction amount.")
        .node("armor-durability-reduction").type(TypeToken.get(Integer.class))
        .defaultValue(1).min(0).max(5).build();

    public static final SimpleOption<Boolean> OVERRIDE_NO_DAMAGE_TICKS = Option.<Boolean>builder()
        .comment("Set to 'true' to override no damage ticks, otherwise 'false'.")
        .node("override-no-damage-ticks").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    public static final NumberOption<Integer> NO_DAMAGE_TICKS = Option.<Integer>number()
        .comment("Set the no damage ticks amount.")
        .node("no-damage-ticks").type(TypeToken.get(Integer.class))
        .defaultValue(19).min(1).max(20).build();

    public static final SimpleOption<Boolean> OVERRIDE_ATTACK_SPEED = Option.<Boolean>builder()
        .comment("Set to 'true' to override attack speed, otherwise 'false'.")
        .node("override-attack-speed").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    public static final NumberOption<Float> ATTACK_SPEED = Option.<Float>number()
        .comment("Set the attack speed amount.")
        .node("attack-speed").type(TypeToken.get(Float.class))
        .defaultValue(16.0F).min(8.0F).max(32.0F).build();

    public static final SimpleOption<Boolean> OVERRIDE_PROJECTILE_RANDOMNESS = Option.<Boolean>builder()
        .comment("Set to 'true' to override projectile randomness, otherwise 'false'.")
        .node("override-projectile-randomness").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    public static final NumberOption<Double> PROJECTILE_RANDOMNESS = Option.<Double>number()
        .comment("Set the projectile randomness amount.")
        .node("projectile-randomness").type(TypeToken.get(Double.class))
        .defaultValue(0.1D).min(0.001D).max(1.0D).build();

    public static final SimpleOption<Boolean> OVERRIDE_REGEN = Option.<Boolean>builder()
        .comment("Set to 'true' to override regen, otherwise 'false'.")
        .node("override-regen").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    public static final NumberOption<Integer> REGEN_INTERVAL = Option.<Integer>number()
        .comment("Set the regen interval.")
        .node("regen-interval").type(TypeToken.get(Integer.class))
        .defaultValue(3900).min(1000).max(5000).build();

    public static final NumberOption<Integer> REGEN_HEAL_AMOUNT = Option.<Integer>number()
        .comment("Set the regen heal amount.")
        .node("regen-heal-amount").type(TypeToken.get(Integer.class))
        .defaultValue(1).min(0).max(5).build();

    public static final NumberOption<Integer> REGEN_EXHAUSTION_HEAL_AMOUNT = Option.<Integer>number()
        .comment("Set the regen exhaustion heal amount.")
        .node("regen-exhaustion-heal-amount").type(TypeToken.get(Integer.class))
        .defaultValue(3).min(0).max(5).build();

    public static final SimpleOption<Boolean> DISABLE_OPTIFINE_CAPES = Option.<Boolean>builder()
        .comment("Set to 'true' to disable optifine capes, otherwise 'false'.")
        .node("disable-optifine-capes").type(TypeToken.get(Boolean.class))
        .defaultValue(true).notifyClient().build();

    public static final SimpleOption<Boolean> DISABLE_NOTIFY_MISMATCH = Option.<Boolean>builder()
        .comment("Set to 'true' to disable notifications for players using the wrong branch or version, otherwise 'false'.")
        .node("disable-notify-mismatch").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    public static final SimpleOption<Boolean> DEBUG = Option.<Boolean>builder()
        .comment("Used for internal testing only.")
        .node("debug").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    EVNTModule() {
        this.registerOptions(
            EVNTModule.DISABLE_ENDERPEARL_COOLDOWN,
            EVNTModule.DISABLE_PROJECTILE_DAMAGE,
            EVNTModule.LEGACY_ATTACK_SPEED,
            EVNTModule.LEGACY_ENCHANTING,
            EVNTModule.OVERRIDE_ARMOR_DURABILITY_REDUCTION,
            EVNTModule.ARMOR_DURABILITY_REDUCTION,
            EVNTModule.OVERRIDE_NO_DAMAGE_TICKS,
            EVNTModule.NO_DAMAGE_TICKS,
            EVNTModule.OVERRIDE_ATTACK_SPEED,
            EVNTModule.ATTACK_SPEED,
            EVNTModule.OVERRIDE_PROJECTILE_RANDOMNESS,
            EVNTModule.PROJECTILE_RANDOMNESS,
            EVNTModule.OVERRIDE_REGEN,
            EVNTModule.REGEN_INTERVAL,
            EVNTModule.REGEN_HEAL_AMOUNT,
            EVNTModule.REGEN_EXHAUSTION_HEAL_AMOUNT,
            EVNTModule.DISABLE_OPTIFINE_CAPES,
            EVNTModule.DISABLE_NOTIFY_MISMATCH,
            EVNTModule.DEBUG
        );
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    public abstract void overrideHeartTexture(Recipients recipients, int x, boolean hardcore);

    public abstract void resetHeartTexture(Recipients recipients);

    public abstract void openGui(Recipients recipients, GuiType type);

    public abstract void closeGui(Recipients recipients);

    public abstract void overrideCosmeticResources(Recipients recipients, CharacterResource resource);

    public abstract void updateCosmetics(Recipients recipients, List<String> models, List<String> animations);

    public abstract void overrideCharacterSuitAccess(Recipients recipients, CharacterType type, List<String> suitNames);

    public abstract void overrideCharacterCosmetic(Recipients recipients, UUID playerUuid, CharacterType type);

    public abstract void overrideCharacterCosmetic(Recipients recipients, UUID playerUuid, CharacterType type, String suitName);

    public abstract void overrideCharacterAbility(Recipients recipients, List<CharacterAbility> abilities);

    public abstract void overrideCharacter(Recipients recipients, Character character);

    public abstract void updateGameOverview(Recipients recipients, EventGame game);

    public abstract void updateStatusOverview(Recipients recipients, EventStatus status);

    public abstract void updateEventOverview(Recipients recipients, List<EventPlayerStatus> playerStatuses);

}
