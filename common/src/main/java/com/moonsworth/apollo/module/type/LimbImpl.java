package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.network.NetworkTypes;
import com.moonsworth.apollo.player.AbstractApolloPlayer;
import com.moonsworth.apollo.player.ApolloPlayer;
import com.moonsworth.apollo.player.ui.limb.Armor;
import com.moonsworth.apollo.player.ui.limb.Body;
import lunarclient.apollo.common.OptionOperation;
import lunarclient.apollo.modules.ToggleArmorPartMessage;
import lunarclient.apollo.modules.ToggleBodyPartMessage;

import static java.util.Objects.requireNonNull;

/**
 * Provides the limb module.
 *
 * @since 1.0.0
 */
public class LimbImpl extends Limb {

    public LimbImpl() {
        super();
    }

    @Override
    public void toggleArmorPart(ApolloPlayer player, Armor armor) {
        requireNonNull(player, "player");
        requireNonNull(armor, "armor");

        ToggleArmorPartMessage message = ToggleArmorPartMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toUuid(armor.getPlayer()))
            .setPart(ToggleArmorPartMessage.Part.valueOf(armor.getArmorPart().name()))
            .setHidden(armor.isHidden())
            .build();

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, message);
    }

    @Override
    public void toggleBodyPart(ApolloPlayer player, Body body) {
        requireNonNull(player, "player");
        requireNonNull(body, "body");

        ToggleBodyPartMessage message = ToggleBodyPartMessage.newBuilder()
            .setPlayerUuid(NetworkTypes.toUuid(body.getPlayer()))
            .setPart(ToggleBodyPartMessage.Part.valueOf(body.getBodyPart().name()))
            .setHidden(body.isHidden())
            .build();

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.SET, message);
    }

    @Override
    public void clearModifications(ApolloPlayer player) {
        requireNonNull(player, "player");

        ((AbstractApolloPlayer) player).sendPacket(this, OptionOperation.CLEAR);
    }
}
