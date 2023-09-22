package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import io.leangen.geantyref.TypeToken;

/**
 * Updates the screenshot behaviour allowing you to instantly open, upload or tweet the image.
 * 
 * @since %release_version%
 */
public final class ModScreenshot {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("screenshot", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> COPY_AUTOMATICALLY = SimpleOption.<Boolean>builder()
        .node("screenshot", "copy-automatically").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> UPLOAD_OPTION = SimpleOption.<Boolean>builder()
        .node("screenshot", "upload-option").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> TWEET_OPTION = SimpleOption.<Boolean>builder()
        .node("screenshot", "tweet-option").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> COPY_OPTION = SimpleOption.<Boolean>builder()
        .node("screenshot", "copy-option").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

}