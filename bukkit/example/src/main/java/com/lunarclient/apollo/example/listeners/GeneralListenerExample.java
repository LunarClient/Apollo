package com.lunarclient.apollo.example.listeners;

import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.Listener;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class GeneralListenerExample {

    // Method 1
    public class GeneralExample1 implements Listener {

        public GeneralExample1() {
            EventBus.getBus().register(this);
        }

        @Listen
        public void onApolloRegister(ApolloRegisterPlayerEvent event) {
            ((Player) event.getPlayer()).sendMessage(Component.text("You have joined using LunarClient!", NamedTextColor.GREEN));
        }
    }

    // Method 2
    public class GeneralExample2 implements Listener {

        public GeneralExample2() {
            this.handle(ApolloRegisterPlayerEvent.class, this::onApolloRegister);
        }

        public void onApolloRegister(ApolloRegisterPlayerEvent event) {
            ((Player) event.getPlayer()).sendMessage(Component.text("You have joined using LunarClient!", NamedTextColor.GREEN));
        }
    }
}
