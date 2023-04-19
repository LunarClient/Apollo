package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.ApolloPlatform;
import com.moonsworth.apollo.module.ApolloModule;
import com.moonsworth.apollo.option.ListOption;
import com.moonsworth.apollo.option.Option;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.ModSetting;
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

    private static final ModSetting SKYBLOCK_ADDONS_SETTING = ModSetting.of(
        "skyblockAddons",
        false,
        null
    );

    /**
     * A list of mod settings.
     *
     * @since 1.0.0
     */
    public static final ListOption<ModSetting> MOD_SETTINGS = Option.<ModSetting>list()
        .comment("A list of mod settings to send to the client.")
        .node("settings").type(new TypeToken<List<ModSetting>>() {})
        .defaultValue(new ArrayList<>(Collections.singletonList(SKYBLOCK_ADDONS_SETTING)))
        .notifyClient()
        .build();

    ModSettings() {
        super("ModSettings");

        this.registerOptions(MOD_SETTINGS);
    }

    /**
     * Sends the {@link ModSetting} to the {@link ApolloPlayer}.
     *
     * @param player the player
     * @param setting the mod setting
     * @since 1.0.0
     */
    public abstract void sendSetting(ApolloPlayer player, ModSetting setting);

    /**
     * Sends the {@link ModSetting} to all {@link ApolloPlayer}s.
     *
     * @param setting the mod setting
     * @since 1.0.0
     */
    public abstract void broadcastSetting(ModSetting setting);

    @Override
    public Collection<ApolloPlatform.Kind> getSupport() {
        return Arrays.asList(ApolloPlatform.Kind.SERVER, ApolloPlatform.Kind.PROXY);
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

}
