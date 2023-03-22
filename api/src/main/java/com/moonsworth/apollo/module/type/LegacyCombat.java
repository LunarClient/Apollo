package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;
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
     * Enables the armor durability.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE_PROJECTILE_DAMAGE = Option.<Boolean>builder()
            .comment("Set to 'true' to enable projectile damage, otherwise 'false'.")
            .node("enable-projectile-damage").type(TypeToken.get(Boolean.class))
            .defaultValue(true).build();

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
