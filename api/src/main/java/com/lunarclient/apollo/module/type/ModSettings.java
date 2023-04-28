package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.option.ListOption;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.type.configurable.Configurable;
import com.lunarclient.apollo.option.type.configurable.ConfigurableSettings;
import com.lunarclient.apollo.player.ApolloPlayer;
import io.leangen.geantyref.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the mod settings module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class ModSettings extends ApolloModule {

    private static final Configurable SKYBLOCK_ADDONS_SETTING = Configurable.builder()
            .withTarget("skyblockAddons")
            .withEnable(false)
            .withProperties(null)
            .build();

    /**
     * A list of mod settings.
     *
     * @since 1.0.0
     */
    public static final ListOption<Configurable> MOD_SETTINGS = Option.<Configurable>list()
        .comment("A list of mod settings to send to the client.")
        .node("settings").type(new TypeToken<List<Configurable>>() {})
        .defaultValue(new ArrayList<>(Collections.singletonList(SKYBLOCK_ADDONS_SETTING)))
        .notifyClient()
        .build();

    ModSettings() {
        super("ModSettings");

        this.registerOptions(MOD_SETTINGS);
    }

    @Override
    public Collection<ApolloPlatform.Kind> getSupport() {
        return Arrays.asList(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Sends the {@link ConfigurableSettings} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param settings the settings
     * @since 1.0.0
     */
    public abstract void sendSettings(ApolloPlayer player, ConfigurableSettings settings);

    /**
     * Resets all {@link ConfigurableSettings}s for the {@link ApolloPlayer}.
     *
     * @param player the player
     * @since 1.0.0
     */
    public abstract void resetSettings(ApolloPlayer player);

    /**
     * Sends the {@link ConfigurableSettings} to all {@link ApolloPlayer}s.
     *
     * @param settings the settings
     * @since 1.0.0
     */
    public abstract void broadcastSettings(ConfigurableSettings settings);

}
