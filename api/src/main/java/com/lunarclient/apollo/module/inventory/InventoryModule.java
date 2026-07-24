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

import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.recipients.Recipients;
import io.leangen.geantyref.TypeToken;
import java.util.Collection;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the inventory module.
 *
 * @since 1.1.7
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "inventory", name = "Inventory")
public abstract class InventoryModule extends ApolloModule {

    /**
     * Controls whether live {@link InventoryButton} content is automatically
     * re-resolved and re-sent to viewers.
     *
     * <p>When enabled without further setup, updates are always sent to
     * every player holding live buttons, even while their inventory is
     * closed. Also enable the packet enrichment module and its player
     * inventory open/close packets and events to only send updates to
     * players who currently have their inventory open.</p>
     *
     * @since 1.2.9
     */
    public static final SimpleOption<Boolean> BROADCAST_LIVE_BUTTONS = Option.<Boolean>builder()
        .comment("Set to 'true' to automatically re-send resolved live inventory button content, otherwise 'false'. "
            + "When enabled, updates are always sent, even to players whose inventory is closed; also enable the "
            + "packet enrichment module and its player inventory open/close packets and events to only send "
            + "updates to players who currently have their inventory open.")
        .node("live-buttons", "broadcast").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    protected InventoryModule() {
        this.registerOptions(
            ApolloModule.ENABLE_OPTION_OFF,
            InventoryModule.BROADCAST_LIVE_BUTTONS
        );
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Displays the {@link InventoryButton}s to the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttons    the inventory buttons
     * @since 1.2.9
     */
    public abstract void displayInventoryButtons(Recipients recipients, Collection<InventoryButton> buttons);

    /**
     * Displays the {@link InventoryButton} to the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param button     the inventory button
     * @since 1.2.9
     */
    public abstract void displayInventoryButton(Recipients recipients, InventoryButton button);

    /**
     * Removes the {@link InventoryButton} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttonId   the inventory button id
     * @since 1.2.9
     */
    public abstract void removeInventoryButton(Recipients recipients, String buttonId);

    /**
     * Removes the {@link InventoryButton} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param button     the inventory button
     * @since 1.2.9
     */
    public abstract void removeInventoryButton(Recipients recipients, InventoryButton button);

    /**
     * Resets all {@link InventoryButton}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.2.9
     */
    public abstract void resetInventoryButtons(Recipients recipients);

    /**
     * Updates the content and tooltip of a previously displayed
     * {@link InventoryButton} for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttonId   the inventory button id
     * @param content    the new button content
     * @param tooltip    the new tooltip, or {@code null} to clear the tooltip
     * @since 1.2.9
     */
    public abstract void updateInventoryButton(Recipients recipients, String buttonId,
                                               ApolloButtonContent content, @Nullable ApolloButtonTooltip tooltip);

    /**
     * Updates only the content of a previously displayed
     * {@link InventoryButton} for the {@link Recipients}, keeping the
     * previous tooltip.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttonId   the inventory button id
     * @param content    the new button content
     * @since 1.2.9
     */
    public abstract void updateInventoryButtonContent(Recipients recipients, String buttonId,
                                                      ApolloButtonContent content);

    /**
     * Updates only the tooltip of a previously displayed
     * {@link InventoryButton} for the {@link Recipients}, keeping the
     * previous content.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttonId   the inventory button id
     * @param tooltip    the new tooltip, or {@code null} to clear the tooltip
     * @since 1.2.9
     */
    public abstract void updateInventoryButtonTooltip(Recipients recipients, String buttonId,
                                                      @Nullable ApolloButtonTooltip tooltip);

}
