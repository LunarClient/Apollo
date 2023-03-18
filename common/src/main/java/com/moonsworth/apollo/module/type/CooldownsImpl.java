package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.common.protocol.CooldownClearMessage;
import com.moonsworth.apollo.common.protocol.CooldownMessage;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Cooldown;

import static java.util.Objects.requireNonNull;

/**
 * Provides the cooldown module.
 *
 * @since 1.0.0
 */
public final class CooldownsImpl extends Cooldowns {

    public CooldownsImpl() {
        super();
    }

    @Override
    public void sendCooldown(final ApolloPlayer player, final Cooldown cooldown) {
        requireNonNull(player, "player");
        requireNonNull(cooldown, "cooldown");
        ((AbstractApolloPlayer) player).sendPacket(CooldownMessage.newBuilder()
                .setName(cooldown.getName())
                .setDurationMs(cooldown.getDuration().toMillis())
                .build()
        );
    }

    @Override
    public void clearCooldown(final ApolloPlayer player, final Cooldown cooldown) {
        requireNonNull(player, "player");
        requireNonNull(cooldown, "cooldown");
        ((AbstractApolloPlayer) player).sendPacket(CooldownClearMessage.newBuilder()
                .setName(cooldown.getName())
                .build()
        );
    }

    @Override
    public void clearCooldowns(final ApolloPlayer player) {
        requireNonNull(player, "player");
        ((AbstractApolloPlayer) player).sendPacket(CooldownClearMessage.getDefaultInstance());
    }

}
