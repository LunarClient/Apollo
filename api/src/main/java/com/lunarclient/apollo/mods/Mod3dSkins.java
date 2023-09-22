package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Adds additional depth and detail to player and skull models.
 * 
 * @since %release_version%
 */
public final class Mod3dSkins {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> RENDER_DISTANCE_LOD = NumberOption.<Integer>number()
        .node("3d-skins", "render-distance-lod").type(TypeToken.get(Integer.class))
        .defaultValue(14).min(5).max(40)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLE_HAT = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-hat").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLE_JACKET = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-jacket").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLE_LEFT_SLEEVE = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-left-sleeve").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLE_RIGHT_SLEEVE = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-right-sleeve").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLE_LEFT_PANTS = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-left-pants").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLE_RIGHT_PANTS = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-right-pants").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> BASE_VOXEL_SIZE = NumberOption.<Float>number()
        .node("3d-skins", "base-voxel-size").type(TypeToken.get(Float.class))
        .defaultValue(1.15F).min(1.001F).max(1.4F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> BODY_VOXEL_WIDTH_SIZE = NumberOption.<Float>number()
        .node("3d-skins", "body-voxel-width-size").type(TypeToken.get(Float.class))
        .defaultValue(1.05F).min(1.001F).max(1.4F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> HEAD_VOXEL_SIZE = NumberOption.<Float>number()
        .node("3d-skins", "head-voxel-size").type(TypeToken.get(Float.class))
        .defaultValue(1.18F).min(1.001F).max(1.25F)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLE_SKULLS = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-skulls").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLE_SKULLS_ITEMS = SimpleOption.<Boolean>builder()
        .node("3d-skins", "enable-skulls-items").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Float> SKULL_VOXEL_SIZE = NumberOption.<Float>number()
        .node("3d-skins", "skull-voxel-size").type(TypeToken.get(Float.class))
        .defaultValue(1.1F).min(1.001F).max(1.2F)
        .build();

}