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
package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.module.pingmarker.PingMarker;
import com.lunarclient.apollo.module.pingmarker.PingMarkerModule;
import com.lunarclient.apollo.module.pingmarker.PingMarkerType;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.awt.Color;
import java.time.Duration;
import java.util.Collection;
import java.util.UUID;

public class PingMarkerExample {

    private final PingMarkerModule pingMarkerModule = Apollo.getModuleManager().getModule(PingMarkerModule.class);

    public void displayMarkerExample(PingMarkerType type, ApolloLocation location, Collection<ApolloPlayer> players) {
        this.pingMarkerModule.displayMarker(Recipients.of(players), PingMarker.builder()
            .id(UUID.randomUUID())
            .type(type)
            .location(location)
            .color(Color.WHITE)
            .duration(Duration.ofSeconds(3))
            .focus(true)
            .build());
    }

}
