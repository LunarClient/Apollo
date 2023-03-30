package com.moonsworth.apollo.module.type;

import com.google.common.collect.Lists;
import com.moonsworth.apollo.option.NetworkOptions;
import com.moonsworth.apollo.option.OptionConverter;
import com.moonsworth.apollo.option.type.RenderableIcon;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.Cooldown;
import com.moonsworth.apollo.protocol.CooldownMessage;
import com.moonsworth.apollo.protocol.RenderableIconMessage;

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

        NetworkOptions.register(Cooldown.class, CooldownMessage.getDefaultInstance(), new OptionConverter<Cooldown, CooldownMessage>() {
            @Override
            public CooldownMessage to(final Cooldown object) throws IllegalArgumentException {
                final OptionConverter<RenderableIcon, RenderableIconMessage> iconConverter = NetworkOptions.get(RenderableIcon.class);

                return CooldownMessage.newBuilder()
                        .setName(object.getName())
                        .setDuration(object.getDuration().toMillis())
                        .setItemId(object.getItemId())
                        .setRenderableIcon(iconConverter.to(object.getIcon()))
                        .build();
            }

            @Override
            public Cooldown from(final CooldownMessage message) throws IllegalArgumentException {
                final OptionConverter<RenderableIcon, RenderableIconMessage> iconConverter = NetworkOptions.get(RenderableIcon.class);

                return Cooldown.of(
                    message.getName(),
                    Duration.ofMillis(message.getDuration()),
                    message.getItemId(),
                    iconConverter.from(message.getRenderableIcon())
                );
            }
        });
    }

    @Override
    public void sendCooldown(final ApolloPlayer player, final Cooldown cooldown) {
        requireNonNull(player, "player");
        requireNonNull(cooldown, "cooldown");
        this.getOptions().set(player, Cooldowns.COOLDOWNS, Lists.newArrayList(cooldown));
    }

    @Override
    public void clearCooldown(final ApolloPlayer player, final Cooldown cooldown) {
        requireNonNull(player, "player");
        requireNonNull(cooldown, "cooldown");
        this.getOptions().remove(player, Cooldowns.COOLDOWNS, Lists.newArrayList(cooldown));
    }

    @Override
    public void clearCooldowns(final ApolloPlayer player) {
        requireNonNull(player, "player");
        this.getOptions().set(player, Cooldowns.COOLDOWNS, Lists.newArrayList());
    }
}
