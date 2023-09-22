package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.option.NumberOption;
import io.leangen.geantyref.TypeToken;

/**
 * Record your game sessions and replay them from any perspective.
 * 
 * @since %release_version%
 */
public final class ModReplaymod {

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ENABLED = SimpleOption.<Boolean>builder()
        .node("replaymod", "enabled").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> INDICATOR = SimpleOption.<Boolean>builder()
        .node("replaymod", "indicator").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> AUTO_POST_PROCESS = SimpleOption.<Boolean>builder()
        .node("replaymod", "auto-post-process").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_SERVER_IP = SimpleOption.<Boolean>builder()
        .node("replaymod", "show-server-ip").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> AUTO_SYNC = SimpleOption.<Boolean>builder()
        .node("replaymod", "auto-sync").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Integer> TIMELINE_LENGTH = NumberOption.<Integer>number()
        .node("replaymod", "timeline-length").type(TypeToken.get(Integer.class))
        .defaultValue(1800).min(1500).max(2100)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> ASK_FOR_OPEN_EYE = SimpleOption.<Boolean>builder()
        .node("replaymod", "ask-for-open-eye").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SKIP_POST_RENDER_GUI = SimpleOption.<Boolean>builder()
        .node("replaymod", "skip-post-render-gui").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SKIP_POST_RENDER_GUI = SimpleOption.<Boolean>builder()
        .node("replaymod", "skip-post-render-gui").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SKIP_POST_SCREENSHOT_GUI = SimpleOption.<Boolean>builder()
        .node("replaymod", "skip-post-screenshot-gui").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> PATH_PREVIEW = SimpleOption.<Boolean>builder()
        .node("replaymod", "path-preview").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> LUNAR_U_I = SimpleOption.<Boolean>builder()
        .node("replaymod", "lunar-ui").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> NOTIFICATIONS = SimpleOption.<Boolean>builder()
        .node("replaymod", "notifications").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> RECORD_SINGLEPLAYER = SimpleOption.<Boolean>builder()
        .node("replaymod", "record-singleplayer").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> RECORD_SERVER = SimpleOption.<Boolean>builder()
        .node("replaymod", "record-server").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> AUTO_START_RECORDING = SimpleOption.<Boolean>builder()
        .node("replaymod", "auto-start-recording").type(TypeToken.get(Boolean.class))
        .defaultValue(false)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> RENAME_DIALOG = SimpleOption.<Boolean>builder()
        .node("replaymod", "rename-dialog").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

    /**
     * @since %release_version%
     */
    public static final SimpleOption<Boolean> SHOW_CHAT = SimpleOption.<Boolean>builder()
        .node("replaymod", "show-chat").type(TypeToken.get(Boolean.class))
        .defaultValue(true)
        .build();

}