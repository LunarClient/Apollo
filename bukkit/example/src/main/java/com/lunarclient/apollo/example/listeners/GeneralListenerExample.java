package com.lunarclient.apollo.example.listeners;

import com.lunarclient.apollo.event.Event;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.EventCancellable;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.player.ApolloPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class GeneralListenerExample {

    // Method 1
    public class GeneralExample1 implements ApolloListener {

        public GeneralExample1() {
            EventBus.getBus().register(this);
        }

        @Listen
        public void onApolloRegister(ApolloRegisterPlayerEvent event) {
            ((Player) event.getPlayer()).sendMessage("You have joined using LunarClient!");
        }
    }

    // Method 2
    public class GeneralExample2 implements ApolloListener {

        public GeneralExample2() {
            this.handle(ApolloRegisterPlayerEvent.class, this::onApolloRegister);
        }

        public void onApolloRegister(ApolloRegisterPlayerEvent event) {
            ((Player) event.getPlayer()).sendMessage("You have joined using LunarClient!");
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
