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
package com.lunarclient.apollo.example.api.general;

import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.Event;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.EventCancellable;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.entity.Player;

public class ApolloEventListenerExample {

    // Method 1
    public class GeneralExample1 implements ApolloListener {

        public GeneralExample1() {
            EventBus.getBus().register(this);
        }

        @Listen
        public void onApolloRegister(ApolloRegisterPlayerEvent event) {
            ((Player) event.getPlayer().getPlayer()).sendMessage("You have joined using LunarClient!");
        }
    }

    // Method 2
    public class GeneralExample2 implements ApolloListener {

        public GeneralExample2() {
            this.handle(ApolloRegisterPlayerEvent.class, this::onApolloRegister);
        }

        public void onApolloRegister(ApolloRegisterPlayerEvent event) {
            ((Player) event.getPlayer().getPlayer()).sendMessage("You have joined using LunarClient!");
        }
    }

    // Normal event
    public class CoolApolloEvent implements Event {

        private final ApolloPlayer player;

        public CoolApolloEvent(ApolloPlayer player) {
            this.player = player;
        }

        public ApolloPlayer getPlayer() {
            return this.player;
        }
    }

    // Cancellable event
    public class CoolApolloCancellableEvent implements EventCancellable {

        private final ApolloPlayer player;

        private boolean cancelled;

        public CoolApolloCancellableEvent(ApolloPlayer player) {
            this.player = player;
        }

        public ApolloPlayer getPlayer() {
            return this.player;
        }

        @Override
        public boolean isCancelled() {
            return this.cancelled;
        }

        @Override
        public void setCancelled(boolean cancel) {
            this.cancelled = cancel;
        }
    }

    public class ApolloExampleHandler {

        // Calling a normal event
        public void callCoolApolloEvent(ApolloPlayer player) {
            CoolApolloEvent event = new CoolApolloEvent(player);
            EventBus.EventResult<CoolApolloEvent> result = EventBus.getBus().post(event);

            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        }

        // Calling a cancellable event
        public void callCoolApolloCancellableEvent(ApolloPlayer player) {
            CoolApolloCancellableEvent event = new CoolApolloCancellableEvent(player);
            EventBus.EventResult<CoolApolloCancellableEvent> result = EventBus.getBus().post(event);

            if (!result.getEvent().isCancelled()) {
                // Do some action if the event is not cancelled
            }

            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        }

    }
}
