package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Change how particles look and work.
 * 
 * @since %release_version%
 */
public final class ModParticleChanger {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("particle-changer", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ALWAYS_ENCHANT_STRIKES = SimpleOption.<Boolean>builder()
        .node("particle-changer", "always-enchant-strikes").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> AFFECT_ENCHANTED_WEAPONS = SimpleOption.<Boolean>builder()
        .node("particle-changer", "affect-enchanted-weapons").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * Hide your players potion effect particles when you're in first person
     * 
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_FIRST_PERSON_PARTICLES = SimpleOption.<Boolean>builder()
        .comment("Hide your players potion effect particles when you're in first person")
        .node("particle-changer", "hide-first-person-particles").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> COLOR = NumberOption.<Integer>number()
        .node("particle-changer", "color").type(TypeToken.get(Integer.class))
        .defaultValue(0xFFFFFFFF).min(0x80000000).max(0x7FFFFFFF)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SCALE = NumberOption.<Float>number()
        .node("particle-changer", "scale").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.5F).max(2.0F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> PARTICLE_MULTIPLIER = NumberOption.<Integer>number()
        .node("particle-changer", "particle-multiplier").type(TypeToken.get(Integer.class))
        .defaultValue(1).min(1).max(10)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_PARTICLE = SimpleOption.<Boolean>builder()
        .node("particle-changer", "hide-particle").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> OVERLAY_COLOR = SimpleOption.<Boolean>builder()
        .node("particle-changer", "overlay-color").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}