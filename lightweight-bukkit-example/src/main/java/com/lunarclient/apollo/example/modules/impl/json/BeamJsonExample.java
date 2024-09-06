package com.lunarclient.apollo.example.modules.impl.json;

import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.modules.ApolloExample;
import com.lunarclient.apollo.example.modules.impl.BeamExample;
import com.lunarclient.apollo.example.utilities.JsonPacketUtil;
import com.lunarclient.apollo.example.utilities.JsonUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.awt.*;

public class BeamJsonExample extends BeamExample {

    @Override
    public void displayBeamExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.beam.v1.DisplayBeaconBeamMessage");
        message.addProperty("id", "spawn-beacon");
        message.add("location", JsonUtil.createBlockLocationObject(
            new Location(Bukkit.getWorld("world"), 0, 60, 0)
        ));
        message.add("color", JsonUtil.createColorObject(Color.CYAN));

        JsonPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void removeBeamExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.beam.v1.RemoveBeaconBeamMessage");
        message.addProperty("id", "spawn-beacon");

        JsonPacketUtil.sendPacket(viewer, message);    }

    @Override
    public void resetBeamsExample(Player viewer) {
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.beam.v1.ResetBeaconBeamsMessage");

        JsonPacketUtil.sendPacket(viewer, message);
    }

}
