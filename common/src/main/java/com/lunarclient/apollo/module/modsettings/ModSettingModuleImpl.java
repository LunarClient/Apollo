/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2026 Moonsworth
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
package com.lunarclient.apollo.module.modsettings;

import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Value;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.async.Future;
import com.lunarclient.apollo.client.mod.LunarClientMod;
import com.lunarclient.apollo.client.mod.LunarClientModType;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.lunarclient.apollo.event.ApolloReceivePacketEvent;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.modsetting.ApolloUpdateModOptionEvent;
import com.lunarclient.apollo.modsetting.v1.Mod;
import com.lunarclient.apollo.module.modsetting.InstalledModsRequest;
import com.lunarclient.apollo.module.modsetting.InstalledModsResponse;
import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.lunarclient.apollo.network.NetworkOptions;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.StatusOptionsImpl;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

/**
 * Provides the mod settings module.
 *
 * @since 1.2.1
 */
public final class ModSettingModuleImpl extends ModSettingModule {

    private static final LunarClientModType[] MOD_TYPES = LunarClientModType.values();

    /**
     * Creates a new instance of {@link ModSettingModuleImpl}.
     *
     * @since 1.2.1
     */
    public ModSettingModuleImpl() {
        super();
        this.registerOptions(ApolloManager.getModsManager().getContainer().getModSettingsOptions());
        this.handle(ApolloReceivePacketEvent.class, this::onReceivePacket);
    }

    @Override
    public <T, C extends Option<T, ?, ?>> T getStatus(@NotNull ApolloPlayer player, @NonNull C option) {
        return ApolloManager.getModsManager().getPlayerOptions().get(player, option);
    }

    @Override
    public Future<InstalledModsResponse> requestInstalledMods(@NotNull ApolloPlayer player) {
        InstalledModsRequest request = InstalledModsRequest.builder().build();

        com.lunarclient.apollo.modsetting.v1.InstalledModsRequest requestProto = com.lunarclient.apollo.modsetting.v1.InstalledModsRequest.newBuilder()
            .setRequestId(ByteString.copyFromUtf8(request.getRequestId().toString()))
            .build();

        return ((AbstractApolloPlayer) player).sendRoundTripPacket(request, requestProto);
    }

    private void onReceivePacket(ApolloReceivePacketEvent event) {
        ApolloPlayer player = event.getPlayer();
        Any any = event.getPacket();

        if (any.is(OverrideConfigurableSettingsMessage.class) || any.is(ConfigurableSettings.class)) {
            this.handleConfiguration(player, any);
        }

        event.unpack(com.lunarclient.apollo.modsetting.v1.InstalledModsResponse.class).ifPresent(packet -> {
            List<LunarClientMod> mods = packet.getModGroupsList().stream()
                .flatMap(group -> group.getModsList().stream()
                    .map(mod -> this.fromProtobuf(MOD_TYPES[group.getTypeValue() - 1], mod)))
                .collect(Collectors.toList());

            InstalledModsResponse response = InstalledModsResponse.builder()
                .packetId(UUID.fromString(packet.getRequestId().toStringUtf8()))
                .page(packet.getPage())
                .totalPages(packet.getTotalPages())
                .elements(mods)
                .build();

            ApolloManager.getRoundtripManager().handleResponse(response);
        });
    }

    private LunarClientMod fromProtobuf(LunarClientModType type, Mod mod) {
        return LunarClientMod.builder()
            .id(mod.getId())
            .displayName(mod.getId())
            .version(mod.getVersion())
            .type(type)
            .build();
    }

    private void handleConfiguration(ApolloPlayer player, Any any) {
        // Unpack the settings first.
        List<ConfigurableSettings> settings;
        try {
            if (any.is(OverrideConfigurableSettingsMessage.class)) {
                OverrideConfigurableSettingsMessage message = any.unpack(OverrideConfigurableSettingsMessage.class);
                settings = message.getConfigurableSettingsList();
            } else {
                settings = Collections.singletonList(any.unpack(ConfigurableSettings.class));
            }
        } catch (InvalidProtocolBufferException exception) {
            throw new RuntimeException(exception);
        }

        for (ConfigurableSettings setting : settings) {
            if (!setting.hasApolloModule()) {
                continue;
            }

            if (!setting.getApolloModule().equals(this.getId())) {
                continue;
            }

            this.updateOptions(player, setting.getPropertiesMap(), true);
        }
    }

    /**
     * Updates the {@link Option}s for a specific {@link ApolloPlayer} using the properties
     * received from the client.
     *
     * @param player     the apollo player
     * @param properties a map of option keys and values
     * @param callEvent  whether to call the update mod option event
     * @since 1.2.1
     */
    public void updateOptions(ApolloPlayer player, Map<String, Value> properties, boolean callEvent) {
        StatusOptionsImpl statusOptions = ApolloManager.getModsManager().getPlayerOptions();

        for (Map.Entry<String, Value> entry : properties.entrySet()) {
            Option<?, ?, ?> option = statusOptions.getRegistry().get(entry.getKey());

            // Option exists on the client but doesn't exist in the API yet.
            if (option == null) {
                continue;
            }

            Object unwrappedValue = NetworkOptions.unwrapValue(
                entry.getValue(),
                option.getTypeToken().getType()
            );

            statusOptions.set(player, option, unwrappedValue);

            if (callEvent) {
                EventBus.EventResult<ApolloUpdateModOptionEvent> eventResult = EventBus.getBus()
                    .post(new ApolloUpdateModOptionEvent(player, option, unwrappedValue));

                for (Throwable throwable : eventResult.getThrowing()) {
                    throwable.printStackTrace();
                }
            }
        }
    }

}
