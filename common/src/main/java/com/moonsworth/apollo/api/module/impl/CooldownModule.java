package com.moonsworth.apollo.api.module.impl;

import com.google.common.collect.ImmutableList;
import com.google.protobuf.ByteString;
import com.moonsworth.apollo.api.ApolloPlatform;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.module.ApolloModule;
import com.moonsworth.apollo.api.options.ApolloOption;
import com.moonsworth.apollo.api.protocol.ClearCooldownMessage;
import com.moonsworth.apollo.api.protocol.CooldownMessage;
import com.moonsworth.apollo.api.protocol.RenderableIcon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CooldownModule extends ApolloModule {

    private final Map<String, CooldownMessage> registeredCooldowns = new HashMap<>();

    public CooldownModule() {
        super("CooldownModule");
    }

    @Override
    public List<ApolloOption<?>> options() {
        return new ArrayList<>();
    }

    @Override
    public boolean notifyPlayers() {
        return false;
    }

    @Override
    public List<ApolloPlatform.Kind> runsOn() {
        return ImmutableList.of(ApolloPlatform.Kind.SERVER);
    }

    /**
     * Creates a cooldown without using Protobuf builder for ease of use.
     *
     * @param name The unique name of the cool down
     * @param millis The amount of time to send to cool down for.
     * @param materialId The icon for the cool down
     * @return The protobuf representation of a Cooldown
     */
    public CooldownMessage createCooldown(String name, long millis, int materialId) {
        return CooldownMessage.newBuilder().setName(ByteString.copyFromUtf8(name)).setDurationMs(millis).setItemId(materialId).build();
    }

    /**
     * A helper method for cooldown buidlers.
     * @see this.registerCooldown
     * @param cooldown The cooldown to register
     */
    public void registerCooldown(CooldownMessage.Builder cooldown) {
        registerCooldown(cooldown.build());
    }

    /**
     * Used to register a persisting cooldown.
     * Ideally 1 {@link CooldownMessage} will be created for each task.
     * Create the {@link CooldownMessage} once, register it and then send it to each player.
     *
     * @param cooldown The cooldown to register
     */
    public void registerCooldown(CooldownMessage cooldown) {
        registeredCooldowns.put(cooldown.getName().toString().toLowerCase(), cooldown);
    }

    /**
     * Used to unregister a persisting cooldown.
     * If a cooldown will no longer be used or in shutdown, unregistering is a good idea.
     *
     * @param cooldown The cooldown to register
     */
    public void unregisterCooldown(CooldownMessage cooldown) {
        registeredCooldowns.remove(cooldown.getName().toString().toLowerCase());
    }

    /**
     * Sends a cooldown to a Lunar Client player that has previously been registered.
     * This could be used instead of passing around a {@link CooldownMessage} instance.
     *
     * @param player The player to send a cooldown to
     * @param cooldownName The name of the {@link CooldownMessage} that is sent.
     * @throws IllegalStateException If the cooldown to send doesn't exist.
     */
    public void sendCooldown(ApolloPlayer player, String cooldownName) throws IllegalStateException {
        CooldownMessage cooldownMessage = this.getCooldown(cooldownName);
        player.sendPacket(cooldownMessage);
    }

    /**
     * Sends a cooldown to a Lunar Client player that has previously been registered.
     * This could be used instead of passing around a {@link CooldownMessage} instance.
     *
     * @param player The player to send a cooldown to
     * @param cooldownName The name of the {@link CooldownMessage} that is sent.
     * @param millis The amount of time to send to cool down for.
     * @throws IllegalStateException If the cooldown to send doesn't exist.
     */
    public void sendCooldown(ApolloPlayer player, String cooldownName, long millis) throws IllegalStateException {
        CooldownMessage cooldownMessage = this.getCooldown(cooldownName);
        player.sendPacket(cooldownMessage.toBuilder().setDurationMs(millis).build());
    }

    /**
     * Clears a cooldown for an apollo player
     *
     * @param player The player to remove the cooldown for.
     * @param cooldownName The name of the cooldown
     * @throws IllegalStateException If the static cool down doesn't exist.
     */
    public void clearCooldown(ApolloPlayer player, String cooldownName) throws IllegalStateException {
        String cooldownId = cooldownName.toLowerCase();

        if (!registeredCooldowns.containsKey(cooldownId)) {
            throw new IllegalStateException("Attempted to send a cooldown that isn't registered [" + cooldownName + "]");
        }

        player.sendPacket(CooldownMessage.newBuilder().setName(ByteString.copyFromUtf8(cooldownId)).setDurationMs(0).build());
    }

    /**
     * Clears all cooldowns for a player
     * @param player The player to remove the cooldowns for.
     */
    public void clearCooldowns(ApolloPlayer player) {
        player.sendPacket(ClearCooldownMessage.newBuilder().build());
    }

    /**
     * Gets the cooldown message by cooldown name
     *
     * @param cooldownName The name of the cooldown
     * @return The protobuf representation of a Cooldown
     */
    private CooldownMessage getCooldown(String cooldownName) {
        String cooldownId = cooldownName.toLowerCase();
        CooldownMessage cooldownMessage = registeredCooldowns.get(cooldownId);

        if(cooldownMessage == null) {
            throw new IllegalStateException("Attempted to send a cooldown that isn't registered [" + cooldownName + "]");
        }

        return cooldownMessage;
    }
}
