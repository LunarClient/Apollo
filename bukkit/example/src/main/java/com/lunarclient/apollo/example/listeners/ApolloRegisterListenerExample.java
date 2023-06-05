package com.lunarclient.apollo.example.listeners;

import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ApolloRegisterListenerExample implements ApolloListener {

    public ApolloRegisterListenerExample() {
        EventBus.getBus().register(this);
    }

    @Listen
    private void onApolloRegisterPlayer(ApolloRegisterPlayerEvent event) {
        Player player = ((Player) event.getPlayer());

        player.getInventory().addItem(new ItemStack(Material.COOKIE));
        player.sendMessage(Component.text("You are using Lunar Client! Here's a cookie.", NamedTextColor.GREEN));
    }
}
