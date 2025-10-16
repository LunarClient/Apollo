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
package com.lunarclient.apollo.mods;

import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.option.OptionsImpl;
import com.lunarclient.apollo.option.StatusOptionsImpl;
import java.util.Map;
import lombok.Getter;

/**
 * Manages Apollo mods for players.
 *
 * @since 1.2.1
 */
public final class ApolloModsManager implements ApolloListener {

    @Getter private final Options playerOptions;

    /**
     * Constructs the {@link ApolloModsManager}.
     *
     * @since 1.2.1
     */
    public ApolloModsManager() {
        EventBus.getBus().register(this);

        this.playerOptions = new StatusOptionsImpl();

        for (Option<?, ?, ?> option : Mods.getOptions().values()) {
            this.playerOptions.set(option, option.getDefaultValue());
        }

        OptionsImpl impl = ((OptionsImpl) this.playerOptions);
        for (Map.Entry<Option<?, ?, ?>, Object> entry : impl.getOptions().entrySet()) {
            System.out.println(entry.getKey().getKey() + ": " + entry.getValue());
        }
    }

    @Listen
    private void onApolloReceivePacket(ApolloReceivePacketEvent event) {

    }

}
