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
package com.lunarclient.apollo.module.inventory;

import com.google.protobuf.Message;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.button.v1.ButtonUpdate;
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.event.packetenrichment.inventory.ApolloPlayerInventoryCloseEvent;
import com.lunarclient.apollo.event.packetenrichment.inventory.ApolloPlayerInventoryOpenEvent;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.event.player.ApolloUnregisterPlayerEvent;
import com.lunarclient.apollo.inventory.v1.DisplayInventoryButtonsMessage;
import com.lunarclient.apollo.inventory.v1.RemoveInventoryButtonMessage;
import com.lunarclient.apollo.inventory.v1.ResetInventoryButtonsMessage;
import com.lunarclient.apollo.inventory.v1.UpdateInventoryButtonMessage;
import com.lunarclient.apollo.module.button.ButtonModuleSupport;
import com.lunarclient.apollo.module.button.ButtonSurface;
import com.lunarclient.apollo.module.packetenrichment.PacketEnrichmentModule;
import com.lunarclient.apollo.network.ButtonNetworkTypes;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.option.config.Serializer;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

/**
 * Provides the inventory module.
 *
 * @since 1.2.9
 */
public final class InventoryModuleImpl extends InventoryModule implements ButtonSurface<InventoryButton, com.lunarclient.apollo.inventory.v1.InventoryButton>, Serializer {

    private final ButtonModuleSupport<InventoryButton, com.lunarclient.apollo.inventory.v1.InventoryButton> support =
        new ButtonModuleSupport<>(this, this, InventoryModule.BROADCAST_LIVE_BUTTONS);

    /**
     * Creates a new instance of {@link InventoryModuleImpl}.
     *
     * @since 1.2.9
     */
    public InventoryModuleImpl() {
        super();
        this.serializer(InventoryButton.class, new InventoryButtonSerializer());
        this.handle(ApolloRegisterPlayerEvent.class, this::onPlayerRegister);
        this.handle(ApolloPlayerInventoryOpenEvent.class, event -> this.support.handleOpen(event.getPlayer()));
        this.handle(ApolloPlayerInventoryCloseEvent.class, event -> this.support.handleClose(event.getPlayer().getUniqueId()));
        this.handle(ApolloUnregisterPlayerEvent.class, event -> this.support.handleUnregister(event.getPlayer().getUniqueId()));
    }

    @Override
    protected void onEnable() {
        this.support.startBroadcast();
    }

    @Override
    public void displayInventoryButtons(@NonNull Recipients recipients, @NonNull Collection<InventoryButton> buttons) {
        int leftButtons = 0;
        int rightButtons = 0;
        for (InventoryButton button : buttons) {
            if (button.getBox() == InventoryButtonBox.LEFT) {
                leftButtons++;
            } else if (button.getBox() == InventoryButtonBox.RIGHT) {
                rightButtons++;
            }
        }

        if (leftButtons > InventoryButton.MAX_BUTTONS_PER_BOX || rightButtons > InventoryButton.MAX_BUTTONS_PER_BOX) {
            throw new IllegalArgumentException("InventoryButton batches support at most " + InventoryButton.MAX_BUTTONS_PER_BOX + " buttons per box");
        }

        this.support.displayButtons(recipients, buttons);
    }

    @Override
    public void displayInventoryButton(@NonNull Recipients recipients, @NonNull InventoryButton button) {
        this.support.displayButtons(recipients, Collections.singleton(button));
    }

    @Override
    public void removeInventoryButton(@NonNull Recipients recipients, @NonNull String buttonId) {
        this.support.removeButton(recipients, buttonId);
    }

    @Override
    public void removeInventoryButton(@NonNull Recipients recipients, @NonNull InventoryButton button) {
        this.support.removeButton(recipients, button.getId());
    }

    @Override
    public void resetInventoryButtons(@NonNull Recipients recipients) {
        this.support.resetButtons(recipients);
    }

    @Override
    public void updateInventoryButton(@NonNull Recipients recipients, @NonNull String buttonId,
                                      @NonNull ApolloButtonContent content, @Nullable ApolloButtonTooltip tooltip) {
        this.support.pushUpdate(recipients, buttonId, content, true, tooltip);
    }

    @Override
    public void updateInventoryButtonContent(@NonNull Recipients recipients, @NonNull String buttonId, @NonNull ApolloButtonContent content) {
        this.support.pushUpdate(recipients, buttonId, content, false, null);
    }

    @Override
    public void updateInventoryButtonTooltip(@NonNull Recipients recipients, @NonNull String buttonId, @Nullable ApolloButtonTooltip tooltip) {
        this.support.pushUpdate(recipients, buttonId, null, true, tooltip);
    }

    private void onPlayerRegister(ApolloRegisterPlayerEvent event) {
        if (!this.isEnabled() || !this.getOptions().get(InventoryModule.SEND_DEFAULT_BUTTONS)) {
            return;
        }

        ApolloPlayer player = event.getPlayer();
        List<InventoryButton> buttons = this.getOptions().get(player, InventoryModule.DEFAULT_BUTTONS);
        if (buttons == null || buttons.isEmpty()) {
            return;
        }

        try {
            this.support.displayButtons(player, buttons);
        } catch (IllegalArgumentException exception) {
            Apollo.getPlatform().getPlatformLogger()
                .warning("Skipping the default inventory buttons: " + exception.getMessage());
        }
    }

    @Override
    public void validate(InventoryButton button) {
        ButtonModuleSupport.validateCommon(button, InventoryButton.BOX_WIDTH, InventoryButton.BOX_HEIGHT);

        ButtonModuleSupport.requireSet(button.getInventoryType(), "InventoryButton#inventoryType");
        ButtonModuleSupport.requireSet(button.getBox(), "InventoryButton#box");
    }

    @Override
    public com.lunarclient.apollo.inventory.v1.InventoryButton toDisplayElement(InventoryButton button, @Nullable ApolloPlayer viewer) {
        return com.lunarclient.apollo.inventory.v1.InventoryButton.newBuilder()
            .setButton(ButtonNetworkTypes.toProtobuf(button, viewer))
            .setInventoryType(com.lunarclient.apollo.inventory.v1.InventoryType
                .forNumber(button.getInventoryType().ordinal() + 1))
            .setBox(com.lunarclient.apollo.inventory.v1.InventoryButtonBox
                .forNumber(button.getBox().ordinal() + 1))
            .build();
    }

    @Override
    public Message createDisplay(List<com.lunarclient.apollo.inventory.v1.InventoryButton> elements) {
        return DisplayInventoryButtonsMessage.newBuilder()
            .addAllInventoryButtons(elements)
            .build();
    }

    @Override
    public Message createUpdate(String buttonId, ButtonUpdate update) {
        return UpdateInventoryButtonMessage.newBuilder()
            .setId(buttonId)
            .setUpdate(update)
            .build();
    }

    @Override
    public Message createRemove(String buttonId) {
        return RemoveInventoryButtonMessage.newBuilder()
            .setId(buttonId)
            .build();
    }

    @Override
    public Message createReset() {
        return ResetInventoryButtonsMessage.getDefaultInstance();
    }

    /**
     * Returns whether open-inventory tracking is available.
     *
     * @return whether open-inventory tracking is active
     */
    @Override
    public boolean isOpenTrackingActive() {
        PacketEnrichmentModule packetEnrichment = Apollo.getModuleManager().getModule(PacketEnrichmentModule.class);
        if (packetEnrichment == null || !packetEnrichment.isEnabled()) {
            return false;
        }

        Options options = packetEnrichment.getOptions();
        return options.get(PacketEnrichmentModule.PLAYER_INVENTORY_OPEN_PACKET)
            && options.get(PacketEnrichmentModule.PLAYER_INVENTORY_CLOSE_PACKET)
            && options.get(PacketEnrichmentModule.PLAYER_INVENTORY_OPEN_EVENT)
            && options.get(PacketEnrichmentModule.PLAYER_INVENTORY_CLOSE_EVENT);
    }

}
