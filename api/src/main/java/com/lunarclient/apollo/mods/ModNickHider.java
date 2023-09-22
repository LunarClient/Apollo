package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Allows you to hide skins and usernames for yourself or others.
 * 
 * @since %release_version%
 */
public final class ModNickHider {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("nick-hider", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_NAME = SimpleOption.<Boolean>builder()
        .node("nick-hider", "hide-name").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_REAL_NAME = SimpleOption.<Boolean>builder()
        .node("nick-hider", "hide-real-name").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_OTHERS_NAMES = SimpleOption.<Boolean>builder()
        .node("nick-hider", "hide-others-names").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_OWN_SKIN = SimpleOption.<Boolean>builder()
        .node("nick-hider", "hide-own-skin").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> USE_REAL_SKIN = SimpleOption.<Boolean>builder()
        .node("nick-hider", "use-real-skin").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> HIDE_OTHERS_SKIN = SimpleOption.<Boolean>builder()
        .node("nick-hider", "hide-others-skin").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

}