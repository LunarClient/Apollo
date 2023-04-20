package com.lunarclient.apollo.module.type;

import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.player.ui.Cooldown;
import java.time.Duration;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.CooldownMessage;

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
    public void sendCooldown(ApolloPlayer player, Cooldown cooldown) {
        requireNonNull(player, "player");
        requireNonNull(cooldown, "cooldown");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, this.to(cooldown));
    }

    @Override
    public void clearCooldown(ApolloPlayer player, Cooldown cooldown) {
        requireNonNull(player, "player");
        requireNonNull(cooldown, "cooldown");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.REMOVE, this.to(cooldown));
    }

    @Override
    public void clearCooldowns(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.CLEAR);
    }

    private CooldownMessage to(Cooldown cooldown) {
        return CooldownMessage.newBuilder()
                .setName(cooldown.getName())
                .setDuration(cooldown.getDuration().toMillis())
                .setItemId(cooldown.getItemId())
                .setRenderableIcon(NetworkTypes.toRenderableIcon(cooldown.getIcon()))
                .build();
    }

    private Cooldown from(CooldownMessage message) {
        return Cooldown.of(
                message.getName(),
                Duration.ofMillis(message.getDuration()),
                message.getItemId(),
                NetworkTypes.fromRenderableIcon(message.getRenderableIcon())
        );
    }
}
