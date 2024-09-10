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
package com.lunarclient.apollo.example.modules.impl.proto;

import com.lunarclient.apollo.example.modules.impl.GlowExample;
import com.lunarclient.apollo.example.utilities.ProtobufPacketUtil;
import com.lunarclient.apollo.example.utilities.ProtobufUtil;
import com.lunarclient.apollo.glow.v1.OverrideGlowEffectMessage;
import com.lunarclient.apollo.glow.v1.ResetGlowEffectMessage;
import com.lunarclient.apollo.glow.v1.ResetGlowEffectsMessage;
import java.awt.Color;
import java.util.UUID;
import org.bukkit.entity.Player;

// DONE
public class GlowProtoExample extends GlowExample {

    @Override
    public void overrideGlowEffectExample(UUID glowingPlayer) {
        OverrideGlowEffectMessage message = OverrideGlowEffectMessage.newBuilder()
            .setPlayerUuid(ProtobufUtil.createUuidProto(glowingPlayer))
            .setColor(ProtobufUtil.createColorProto(Color.RED))
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetGlowEffectExample(UUID glowingPlayer) {
        ResetGlowEffectMessage message = ResetGlowEffectMessage.newBuilder()
            .setPlayerUuid(ProtobufUtil.createUuidProto(glowingPlayer))
            .build();

        ProtobufPacketUtil.broadcastPacket(message);
    }

    @Override
    public void resetGlowEffectsExample(Player viewer) {
        ResetGlowEffectsMessage message = ResetGlowEffectsMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}
