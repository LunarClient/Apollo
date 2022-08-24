package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.google.protobuf.ByteString;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.protocol.NametagMessage;
import com.moonsworth.apollo.api.protocol.RenderableString;

import java.util.ArrayList;
import java.util.List;

public class NametagModule extends ApolloModule {

    public NametagModule() {
        super("NameTagModule");
    }

    @Override
    public List<ApolloOption> options() {
        return new ArrayList<>();
    }

    @Override
    public boolean notifyPlayers() {
        return false;
    }

    @Override
    public List<ApolloPlatform.Kind> runsOn() {
        return ImmutableList.of(ApolloPlatform.Kind.SERVER);
    }

    public void sendNametag(NametagMessage nametag, ApolloPlayer... receiver) {
        for (ApolloPlayer player : receiver) {
            player.sendPacket(nametag);
        }
    }


    /**
     * Override the normal (bukkit) nametag with lunar client nametags.
     * This supports multiple lines, so index 0 will be bottom of the nametags.
     *
     * @param target              The player whos nametag will be affected for the viewer.
     * @param lines               The list of nametags that will be sent to the viewer. Supports color codes.
     * @param indexContainingName The index the client will use to attach the luanr logo to.
     *                            If one is not provided, the client will use whatever line contains the player's name
     * @param viewer              The observer who will see the targets nametag as a lunar client nametag.
     */
    public void overrideNametag(ApolloPlayer target, List<RenderableString> lines, int indexContainingName, ApolloPlayer... viewer) {
        var message = NametagMessage.newBuilder().setPlayerAffected(ByteString.copyFromUtf8(target.getUniqueId().toString())).addAllNametagOverride(lines).setIndexContainingPlayerName(indexContainingName).build();
        for (ApolloPlayer player : viewer) {
            player.sendPacket(message);
        }
    }

    /**
     * Reset anything done to the nametag.
     * This will reset hideNametag or any
     * other action done to the nametag.
     *
     * @param target The player's nametag that will be reset for the viewer.
     * @param viewer The observer who will see the targets nametag as normal (bukkit).
     */
    public void resetNametag(ApolloPlayer target, ApolloPlayer... viewer) {
        for (ApolloPlayer player : viewer) {
            player.sendPacket(NametagMessage.newBuilder().setPlayerAffected(ByteString.copyFromUtf8(target.getUniqueId().toString())).build());
        }
    }

    /**
     * Hide the target's username from the viewer.
     *
     * @param target This player's nametag will be hidden from the viewer.
     * @param viewer The observer who will hide the targets nametag.
     */
    public void hideNametag(ApolloPlayer target, ApolloPlayer... viewer) {
        for (ApolloPlayer player : viewer) {
            player.sendPacket(NametagMessage.newBuilder().setPlayerAffected(ByteString.copyFromUtf8(target.getUniqueId().toString())).setHideNametag(true).build());
        }
    }

}
