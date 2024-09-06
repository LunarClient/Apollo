package com.lunarclient.apollo.example.listeners.proto;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lunarclient.apollo.common.v1.LunarClientVersion;
import com.lunarclient.apollo.common.v1.MinecraftVersion;
import com.lunarclient.apollo.example.utilities.ProtobufUtil;
import com.lunarclient.apollo.packetenrichment.v1.PlayerAttackMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerChatCloseMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerChatOpenMessage;
import com.lunarclient.apollo.packetenrichment.v1.PlayerInfo;
import com.lunarclient.apollo.packetenrichment.v1.PlayerUseItemMessage;
import com.lunarclient.apollo.player.v1.ModMessage;
import com.lunarclient.apollo.player.v1.PlayerHandshakeMessage;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.List;
import java.util.UUID;

public class ApolloPacketReceiveProtoListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        try {
            Any any = Any.parseFrom(bytes);

            if (any.is(PlayerHandshakeMessage.class)) {
                this.onPlayerHandshake(any.unpack(PlayerHandshakeMessage.class));
            }


            // Packet Enrichment Module
            if (any.is(PlayerAttackMessage.class)) {
                this.onPlayerAttack(any.unpack(PlayerAttackMessage.class));
            } else if (any.is(PlayerChatOpenMessage.class)) {
                this.onPlayerChatOpen(any.unpack(PlayerChatOpenMessage.class));
            } else if (any.is(PlayerChatCloseMessage.class)) {
                this.onPlayerChatClose(any.unpack(PlayerChatCloseMessage.class));
            } else if (any.is(PlayerUseItemMessage.class)) {
                this.onPlayerUseItem(any.unpack(PlayerUseItemMessage.class));
            }
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    private void onPlayerHandshake(PlayerHandshakeMessage message) {
        MinecraftVersion minecraftVersion = message.getMinecraftVersion();

        LunarClientVersion lunarClientVersion = message.getLunarClientVersion();
        String gitBranch = lunarClientVersion.getGitBranch();
        String gitCommit = lunarClientVersion.getGitCommit();
        String semVer = lunarClientVersion.getSemver();

        List<ModMessage> installedMods = message.getInstalledModsList();
        for (ModMessage mod : installedMods) {
            String modId = mod.getId();
            String displayName = mod.getName();
            String version = mod.getVersion();
            ModMessage.Type type = mod.getType();
        }
    }

    private void onPlayerAttack(PlayerAttackMessage message) {
         long instantiationTimeMs = ProtobufUtil.fromProtobuf(message.getPacketInfo().getInstantiationTime());

        PlayerInfo targetInfo = message.getTargetInfo();
        PlayerInfo attackerInfo = message.getAttackerInfo();

        this.onPlayerInfo(targetInfo);
        this.onPlayerInfo(attackerInfo);
    }

    private void onPlayerChatOpen(PlayerChatOpenMessage message) {
        long instantiationTimeMs = ProtobufUtil.fromProtobuf(message.getPacketInfo().getInstantiationTime());

        PlayerInfo playerInfo = message.getPlayerInfo();
        this.onPlayerInfo(playerInfo);
    }

    private void onPlayerChatClose(PlayerChatCloseMessage message) {
        long instantiationTimeMs = ProtobufUtil.fromProtobuf(message.getPacketInfo().getInstantiationTime());

        PlayerInfo playerInfo = message.getPlayerInfo();
        this.onPlayerInfo(playerInfo);
    }

    private void onPlayerUseItem(PlayerUseItemMessage message) {
        long instantiationTimeMs = ProtobufUtil.fromProtobuf(message.getPacketInfo().getInstantiationTime());

        PlayerInfo playerInfo = message.getPlayerInfo();
        this.onPlayerInfo(playerInfo);

        boolean mainHand = message.getMainHand();
    }

    private void onPlayerInfo(PlayerInfo info) {
        UUID uuid = ProtobufUtil.fromProtobuf(info.getPlayerUuid());
        Location location = ProtobufUtil.fromProtobuf(info.getLocation());
        boolean sneaking = info.getSneaking();
        boolean sprinting = info.getSprinting();
        boolean jumping = info.getJumping();
        float forwardSpeed = info.getForwardSpeed();
        float strafeSpeed = info.getStrafeSpeed();
    }

}
