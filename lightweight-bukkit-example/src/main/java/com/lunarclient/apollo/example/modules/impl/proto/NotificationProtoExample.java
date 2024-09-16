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

import com.lunarclient.apollo.example.modules.impl.NotificationExample;
import com.lunarclient.apollo.example.utilities.AdventureUtil;
import com.lunarclient.apollo.example.utilities.ProtobufPacketUtil;
import com.lunarclient.apollo.example.utilities.ProtobufUtil;
import com.lunarclient.apollo.notification.v1.DisplayNotificationMessage;
import com.lunarclient.apollo.notification.v1.ResetNotificationsMessage;
import java.time.Duration;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class NotificationProtoExample extends NotificationExample {

    @Override
    public void displayNotificationExample(Player viewer) {
        DisplayNotificationMessage message = DisplayNotificationMessage.newBuilder()
            .setTitleAdventureJsonLines(AdventureUtil.toJson(
                Component.text("UHC Announcement", NamedTextColor.GREEN)
            ))
            .setDescriptionAdventureJsonLines(AdventureUtil.toJson(
                Component.text("UHC starts in 5 minutes...", NamedTextColor.RED)
                    .appendNewline()
                    .append(Component.text("Get ready!", NamedTextColor.WHITE))
                    .appendNewline()
                    .append(Component.text("Good luck!", NamedTextColor.GOLD))
                )
            )
            .setResourceLocation("icons/golden_apple.png") // This field is optional
            .setDisplayTime(ProtobufUtil.createDurationProto(Duration.ofSeconds(5)))
            .build();

        ProtobufPacketUtil.sendPacket(viewer, message);
    }

    @Override
    public void resetNotificationsExample(Player viewer) {
        ResetNotificationsMessage message = ResetNotificationsMessage.getDefaultInstance();
        ProtobufPacketUtil.sendPacket(viewer, message);
    }

}
