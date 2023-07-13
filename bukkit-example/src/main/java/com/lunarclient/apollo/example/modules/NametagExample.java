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

import com.google.common.collect.Lists;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.audience.Audience;
import com.lunarclient.apollo.common.Component;
import com.lunarclient.apollo.module.nametag.Nametag;
import com.lunarclient.apollo.module.nametag.NametagModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.awt.Color;
import java.util.Collections;
import java.util.Optional;
import org.bukkit.entity.Player;

public class NametagExample {

    private final NametagModule nametagModule = Apollo.getModuleManager().getModule(NametagModule.class);

    public void overrideNametagExample(Player target) {
        this.nametagModule.overrideNametag(Audience.ofEveryone(), target.getUniqueId(), Nametag.builder()
            .lines(Lists.newArrayList(
                Component.builder()
                    .content("[StaffMode]")
                    .decorators(Collections.singletonList(Component.TextDecorators.ITALIC))
                    .color(Color.GRAY)
                    .build(),
                Component.builder()
                    .content(target.getName())
                    .color(Color.RED)
                    .build()
            ))
            .build()
        );
    }

    public void resetNametagExample(Player target) {
        this.nametagModule.resetNametag(Audience.ofEveryone(), target.getUniqueId());
    }

    public void resetNametagsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());
        apolloPlayerOpt.ifPresent(this.nametagModule::resetNametags);
    }

}
