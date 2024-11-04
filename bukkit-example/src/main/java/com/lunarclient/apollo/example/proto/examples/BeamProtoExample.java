/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.example.proto.examples;

import com.lunarclient.apollo.beam.v1.DisplayBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.RemoveBeaconBeamMessage;
import com.lunarclient.apollo.beam.v1.ResetBeaconBeamsMessage;
import com.lunarclient.apollo.example.common.modules.impl.BeamExample;
import com.lunarclient.apollo.example.proto.ProtobufPacketUtil;
import com.lunarclient.apollo.example.proto.ProtobufUtil;
import java.awt.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BeamProtoExample extends BeamExample {

    @Override
    public void displayBeamExample(Player viewer) {
        DisplayBeaconBeamMessage message = DisplayBeaconBeamMessage.newBuilder()
            .setId("spawn-beacon")
            .setColor(ProtobufUtil.createColorProto(Color.CYAN))
            .setLocation(ProtobufUtil.createBlockLocationProto(new Location(viewer.getWorld(), 0, 60, 0)))
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