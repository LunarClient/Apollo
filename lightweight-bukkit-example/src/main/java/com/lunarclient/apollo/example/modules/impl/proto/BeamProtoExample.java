package com.lunarclient.apollo.example.modules.impl.proto;

import com.lunarclient.apollo.beam.v1.DisplayBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.RemoveBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.ResetBeaconBeamsMessage;
import com.lunarclient.apollo.example.modules.impl.BeamExample;
import com.lunarclient.apollo.example.utilities.ProtobufPacketUtil;
import com.lunarclient.apollo.example.utilities.ProtobufUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.awt.*;

public class BeamProtoExample extends BeamExample {

    @Override
    public void displayBeamExample(Player viewer) {
        DisplayBeaconBeamMessage message = DisplayBeaconBeamMessage.newBuilder()
            .setId("spawn-beacon")
            .setColor(ProtobufUtil.toProtobuf(Color.CYAN))
            .setLocation(ProtobufUtil.toBlockLocationProtobuf(new Location(viewer.getWorld(), 0, 60, 0)))
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeBeamExample(Player viewer) {
        RemoveBeaconBeamMessage message = RemoveBeaconBeamMessage.newBuilder()
            .setId("spawn-beacon")
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetBeamsExample(Player viewer) {
        ResetBeaconBeamsMessage message = ResetBeaconBeamsMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}
