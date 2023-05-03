package com.lunarclient.apollo.module.tntcountdown;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.NumberOption;
import com.lunarclient.apollo.player.ApolloPlayer;
import io.leangen.geantyref.TypeToken;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the tnt countdown module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "tnt_countdown", name = "TNT Countdown")
public abstract class TntCountdownModule extends ApolloModule {

    /**
     * Set the amount of ticks before the TNT explodes.
     *
     * @since 1.0.0
     */
    public static final NumberOption<Integer> TNT_TICKS = NumberOption.<Integer>number()
            .comment("Set the amount of ticks before the TNT explodes.")
            .node("tnt-ticks").type(TypeToken.get(Integer.class))
            .defaultValue(80).min(1).max(Integer.MAX_VALUE).build();

    TntCountdownModule() {
        this.registerOptions(
            TntCountdownModule.TNT_TICKS
        );
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Set the amount of ticks before the specified TNT explodes.
     *
     * @param viewer the player who is receiving the packet
     * @param tntUuid the UUID of the TNT entity
     * @param ticks the ticks until explosion
     * @since 1.0.0
     */
    public abstract void setTntCountdown(ApolloPlayer viewer, UUID tntUuid, int ticks);

}
