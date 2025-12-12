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
package com.lunarclient.apollo.player;

import com.google.protobuf.Value;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.client.mod.LunarClientMod;
import com.lunarclient.apollo.client.mod.LunarClientModType;
import com.lunarclient.apollo.client.version.LunarClientVersion;
import com.lunarclient.apollo.client.version.MinecraftVersion;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.player.ApolloPlayerHandshakeEvent;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.event.player.ApolloUnregisterPlayerEvent;
import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.lunarclient.apollo.module.modsettings.ModSettingModuleImpl;
import com.lunarclient.apollo.module.paynow.PayNowEmbeddedCheckoutSupport;
import com.lunarclient.apollo.module.tebex.TebexEmbeddedCheckoutSupport;
import com.lunarclient.apollo.network.NetworkOptions;
import com.lunarclient.apollo.player.v1.PlayerHandshakeMessage;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

/**
 * Provides the implementation for the {@link ApolloPlayerManager}.
 *
 * @since 1.0.0
 */
@NoArgsConstructor
public final class ApolloPlayerManagerImpl implements ApolloPlayerManager {

    private final Map<UUID, ApolloPlayer> players = new HashMap<>();

    @Override
    public Optional<ApolloPlayer> getPlayer(@NonNull UUID playerIdentifier) {
        return Optional.ofNullable(this.players.get(playerIdentifier));
    }

    @Override
    public Collection<ApolloPlayer> getPlayers() {
        return Collections.unmodifiableCollection(this.players.values());
    }

    /**
     * Adds a player to the player manager.
     *
     * @param player the player to add
     * @since 1.0.0
     */
    public void addPlayer(@NonNull ApolloPlayer player) {
        if (this.players.putIfAbsent(player.getUniqueId(), player) == null) {
            NetworkOptions.sendOptions(
                Apollo.getModuleManager().getModules(),
                true,
                player
            );

            EventBus.EventResult<ApolloRegisterPlayerEvent> result = EventBus.getBus()
                .post(new ApolloRegisterPlayerEvent(player));
            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Removes a player from the player manager.
     *
     * @param player the player to remove
     * @since 1.0.0
     */
    public void removePlayer(@NonNull UUID player) {
        ApolloPlayer apolloPlayer = this.players.remove(player);
        if (apolloPlayer != null) {
            EventBus.EventResult<ApolloUnregisterPlayerEvent> result = EventBus.getBus()
                .post(new ApolloUnregisterPlayerEvent(apolloPlayer));
            for (Throwable throwable : result.getThrowing()) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Handles an {@link PlayerHandshakeMessage} message packet from the provided player.
     *
     * @param player the player that received the packet
     * @param message the handshake message to handle
     * @since 1.0.6
     */
    public void handlePlayerHandshake(@NotNull ApolloPlayer player, @NotNull PlayerHandshakeMessage message) {
        MinecraftVersion minecraftVersion;
        try {
            minecraftVersion = MinecraftVersion.valueOf(message.getMinecraftVersion().getEnum().toUpperCase());
        } catch (IllegalArgumentException e) {
            minecraftVersion = MinecraftVersion.UNKNOWN;
        }

        LunarClientVersion lunarClientVersion = LunarClientVersion.builder()
            .gitBranch(message.getLunarClientVersion().getGitBranch())
            .gitCommit(message.getLunarClientVersion().getGitCommit())
            .semVer(message.getLunarClientVersion().getSemver())
            .build();

        LunarClientModType[] modTypes = LunarClientModType.values();
        List<LunarClientMod> mods = message.getInstalledModsList().stream().map(mod ->
            LunarClientMod.builder()
                .id(mod.getId())
                .displayName(mod.getName())
                .version(mod.getVersion())
                .type(modTypes[mod.getTypeValue() - 1])
                .build()
        ).collect(Collectors.toList());

        int embeddedCheckoutSupport = message.getEmbeddedCheckoutSupportValue();
        TebexEmbeddedCheckoutSupport tebexEmbeddedCheckoutSupport;
        try {
            tebexEmbeddedCheckoutSupport = TebexEmbeddedCheckoutSupport.values()[embeddedCheckoutSupport - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            tebexEmbeddedCheckoutSupport = TebexEmbeddedCheckoutSupport.UNSUPPORTED;
        }

        PayNowEmbeddedCheckoutSupport payNowEmbeddedCheckoutSupport;
        try {
            payNowEmbeddedCheckoutSupport = PayNowEmbeddedCheckoutSupport.values()[embeddedCheckoutSupport - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            payNowEmbeddedCheckoutSupport = PayNowEmbeddedCheckoutSupport.UNSUPPORTED;
        }

        AbstractApolloPlayer apolloPlayer = ((AbstractApolloPlayer) player);
        apolloPlayer.setMinecraftVersion(minecraftVersion);
        apolloPlayer.setLunarClientVersion(lunarClientVersion);
        apolloPlayer.setInstalledMods(mods);
        apolloPlayer.setTebexEmbeddedCheckoutSupport(tebexEmbeddedCheckoutSupport);
        apolloPlayer.setPayNowEmbeddedCheckoutSupport(payNowEmbeddedCheckoutSupport);

        Map<String, Value> modStatus = message.getModStatusMap();
        if (!modStatus.isEmpty()) {
            ModSettingModuleImpl modSettingModule = (ModSettingModuleImpl) Apollo.getModuleManager().getModule(ModSettingModule.class);

            if (modSettingModule.isEnabled()) {
                modSettingModule.updateOptions(apolloPlayer, modStatus, false);
            }
        }

        ApolloPlayerHandshakeEvent event = new ApolloPlayerHandshakeEvent(
            player, minecraftVersion, lunarClientVersion, mods,
            tebexEmbeddedCheckoutSupport, payNowEmbeddedCheckoutSupport
        );

        EventBus.EventResult<ApolloPlayerHandshakeEvent> result = EventBus.getBus().post(event);

        for (Throwable throwable : result.getThrowing()) {
            throwable.printStackTrace();
        }
    }

}
