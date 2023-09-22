package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Manage the hurt camera shaking animation.
 * 
 * @since %release_version%
 */
public final class ModHurtCam {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("hurt-cam", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> DISABLE_HURT_CAM = SimpleOption.<Boolean>builder()
        .node("hurt-cam", "disable-hurt-cam").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> HURT_SHAKING_INTENSITY = NumberOption.<Float>number()
        .node("hurt-cam", "hurt-shaking-intensity").type(TypeToken.get(Float.class))
        .defaultValue(1.0F).min(0.0F).max(2.0F)
        .build();

}