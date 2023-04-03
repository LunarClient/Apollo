package com.moonsworth.apollo.module.type;

import com.google.protobuf.Any;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Cooldown;
import com.moonsworth.apollo.util.ProtoUtils;
import lunarclient.apollo.common.MessageOperation;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.CooldownMessage;

import java.time.Duration;

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

        ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                .setModule(this.getName())
                .setOperation(OptionOperation.SET)
                .setValue(Any.pack(this.to(cooldown)))
                .build());
    }

    @Override
    public void clearCooldown(final ApolloPlayer player, final Cooldown cooldown) {
        requireNonNull(player, "player");
        requireNonNull(cooldown, "cooldown");

        ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                .setModule(this.getName())
                .setOperation(OptionOperation.REMOVE)
                .setValue(Any.pack(this.to(cooldown)))
                .build());
    }

    @Override
    public void clearCooldowns(final ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(MessageOperation.newBuilder()
                .setModule(this.getName())
                .setOperation(OptionOperation.CLEAR)
                .build());
    }

    private CooldownMessage to(final Cooldown cooldown) {
        return CooldownMessage.newBuilder()
                .setName(cooldown.getName())
                .setDuration(cooldown.getDuration().toMillis())
                .setItemId(cooldown.getItemId())
                .setRenderableIcon(ProtoUtils.toProtoRenderableIcon(cooldown.getIcon()))
                .build();
    }

    private Cooldown from(final CooldownMessage message) {
        return Cooldown.of(
                message.getName(),
                Duration.ofMillis(message.getDuration()),
                message.getItemId(),
                ProtoUtils.fromProtoRenderableIcon(message.getRenderableIcon())
        );
    }
}
