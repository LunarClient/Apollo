package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.option.NumberOption;
import com.moonsworth.apollo.option.Option;
import com.moonsworth.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

public final class LegacyCombat extends ApolloModule {

    /**
     * Disables entity cramming.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_ENTITY_CRAMMING = Option.<Boolean>builder()
            .comment("Set to 'true' to disable entity cramming, otherwise 'false'.")
            .node("disable-entity-cramming").type(TypeToken.get(Boolean.class))
            .defaultValue(true).notifyClient().build();

    /**
     * Disables the enderpearl cooldown.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_ENDERPEARL_COOLDOWN = Option.<Boolean>builder()
            .comment("Set to 'true' to disable the enderpearl cooldown, otherwise 'false'.")
            .node("disable-enderpearl-cooldown").type(TypeToken.get(Boolean.class))
            .defaultValue(true).notifyClient().build();

    /**
     * Disables the swing sounds.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_SWING_SOUNDS = Option.<Boolean>builder()
            .comment("Set to 'true' to disable the swing sounds, otherwise 'false'.")
            .node("disable-swing-sounds").type(TypeToken.get(Boolean.class))
            .defaultValue(true).build();

    /**
     * Disables the swing sounds.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> DISABLE_SWEEP = Option.<Boolean>builder()
            .comment("Set to 'true' to disable the sweeping attack, otherwise 'false'.")
            .node("disable-sweep-attack").type(TypeToken.get(Boolean.class))
            .defaultValue(true).build();

    /**
     * Enables the legacy crits.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_LEGACY_CRITS = Option.<Boolean>builder()
            .comment("Set to 'true' to enable the legacy crits, otherwise 'false'.")
            .node("enable-legacy-crits").type(TypeToken.get(Boolean.class))
            .defaultValue(true).build();

    /**
     * Enables the legacy attack speed.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_LEGACY_ATTACK_SPEED = Option.<Boolean>builder()
            .comment("Set to 'true' to enable the legacy attack speed, otherwise 'false'.")
            .node("enable-legacy-attack-speed").type(TypeToken.get(Boolean.class))
            .defaultValue(true).notifyClient().build();

    /**
     * Enables the armor durability.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_ARMOR_DURABILITY = Option.<Boolean>builder()
            .comment("Set to 'true' to enable armor durability, otherwise 'false'.")
            .node("enable-armor-durability").type(TypeToken.get(Boolean.class))
            .defaultValue(true).build();

    /**
     * Enables the projectile damage.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_PROJECTILE_DAMAGE = Option.<Boolean>builder()
            .comment("Set to 'true' to enable projectile damage, otherwise 'false'.")
            .node("enable-projectile-damage").type(TypeToken.get(Boolean.class))
            .defaultValue(true).build();

    /**
     * Sets armor durability reduction amount.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> ARMOR_DURABILITY_REDUCTION = Option.<Integer>number()
            .comment("Set the armor durability reduction amount.")
            .node("armor-durability-reduction").type(TypeToken.get(Integer.class))
            .defaultValue(1).min(1).max(5).build();

    /**
     * Sets no damage ticks amount.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> NO_DAMAGE_TICKS = Option.<Integer>number()
            .comment("Sets the no damage ticks duration.")
            .node("no-damage-ticks").type(TypeToken.get(Integer.class))
            .defaultValue(19).min(1).max(20).build();

    public static final NumberOption<Float> ATTACK_SPEED = Option.<Float>number()
            .comment("Sets the attack speed duration.")
            .node("attack-speed").type(TypeToken.get(Float.class))
            .defaultValue(16F).min(8F).max(32F).build();

    public static final NumberOption<Double> PROJECTILE_RANDOMNESS = Option.<Double>number()
            .comment("Sets the projectile randomness offset.")
            .node("projectile-randomness").type(TypeToken.get(Double.class))
            .defaultValue(0.1D).min(0.001D).max(1.0D).build();

    public static final NumberOption<Integer> REGEN_INTERVAL = Option.<Integer>number()
            .comment("Sets the regeneration interval.")
            .node("regen-interval").type(TypeToken.get(Integer.class))
            .defaultValue(3990).min(1000).max(5000).build();

    public static final NumberOption<Integer> REGEN_AMOUNT = Option.<Integer>number()
            .comment("Sets the regeneration amount.")
            .node("regen-amount").type(TypeToken.get(Integer.class))
            .defaultValue(1).min(0).max(5).build();

    public static final NumberOption<Integer> REGEN_EXHAUSTION = Option.<Integer>number()
            .comment("Sets the regeneration exhaustion amount.")
            .node("regen-exhaustion").type(TypeToken.get(Integer.class))
            .defaultValue(3).min(0).max(5).build();

    LegacyCombat() {
        super("LegacyCombat");

        this.registerOptions(
            LegacyCombat.DISABLE_ENTITY_CRAMMING,
            LegacyCombat.DISABLE_ENDERPEARL_COOLDOWN,
            LegacyCombat.DISABLE_SWING_SOUNDS,
            LegacyCombat.DISABLE_SWEEP,
            LegacyCombat.ENABLE_LEGACY_CRITS,
            LegacyCombat.ENABLE_LEGACY_ATTACK_SPEED,
            LegacyCombat.ENABLE_ARMOR_DURABILITY,
            LegacyCombat.ENABLE_PROJECTILE_DAMAGE
        );
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

}
