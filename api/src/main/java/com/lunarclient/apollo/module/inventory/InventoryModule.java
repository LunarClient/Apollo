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

import com.lunarclient.apollo.common.button.ApolloButtonShape;
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.action.ApolloButtonAction;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.location.HudPosition;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.ListOption;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.recipients.Recipients;
import io.leangen.geantyref.TypeToken;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
        .comment(
            "Set to 'true' to automatically re-send resolved live inventory button content, otherwise 'false'.",
            "When enabled, updates are always sent, even to players whose inventory is closed; also enable",
            "the packet enrichment module and its player inventory open/close packets and events to only",
            "send updates to players who currently have their inventory open."
        )
        .node("buttons", "live-broadcast").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    /**
     * Controls whether the {@link #DEFAULT_BUTTONS} are displayed to
     * players when they join.
     *
     * @since 1.2.9
     */
    public static final SimpleOption<Boolean> SEND_DEFAULT_BUTTONS = Option.<Boolean>builder()
        .comment("Set to 'true' to display the default buttons to players when they join, otherwise 'false'.")
        .node("buttons", "send-defaults").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    /**
     * The default {@link InventoryButton}s displayed to joining players
     * while {@link #SEND_DEFAULT_BUTTONS} is enabled.
     *
     * @since 1.2.9
     */
    public static final ListOption<InventoryButton> DEFAULT_BUTTONS = Option.<InventoryButton>list()
        .comment(
            "Sets the default buttons to display to players when they join, while send-defaults is enabled.",
            "Text is read as legacy strings ('&'-color codes) and icons as icon definitions; live values",
            "are only available through the API."
        )
        .node("buttons", "defaults").type(new TypeToken<List<InventoryButton>>() {})
        .defaultValue(InventoryModule.createDefaultButtons())
        .build();

    protected InventoryModule() {
        this.registerOptions(
            ApolloModule.ENABLE_OPTION_OFF,
            InventoryModule.BROADCAST_LIVE_BUTTONS,
            InventoryModule.SEND_DEFAULT_BUTTONS,
            InventoryModule.DEFAULT_BUTTONS
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

    private static List<InventoryButton> createDefaultButtons() {
        InventoryButton shop = InventoryButton.builder()
            .id("shop")
            .inventoryType(InventoryType.PLAYER)
            .box(InventoryButtonBox.LEFT)
            .position(HudPosition.of(4, 4))
            .size(InventoryButton.SIZE_MEDIUM)
            .shape(ApolloButtonShape.ROUNDED_SQUARE)
            .content(ApolloButtonContent.builder()
                .append(ItemStackIcon.builder().itemName("EMERALD").build())
                .build())
            .tooltip(ApolloButtonTooltip.of(
                Component.text("Shop", NamedTextColor.GREEN),
                Component.text("Browse categories and buy items", NamedTextColor.GRAY),
                Component.text("Click to open", NamedTextColor.YELLOW)))
            .onClick(ApolloButtonAction.runCommand("/shop"))
            .build();

        InventoryButton spawn = InventoryButton.builder()
            .id("spawn")
            .inventoryType(InventoryType.PLAYER)
            .box(InventoryButtonBox.LEFT)
            .position(HudPosition.of(48, 4))
            .size(InventoryButton.SIZE_MEDIUM)
            .shape(ApolloButtonShape.ROUNDED_SQUARE)
            .content(ApolloButtonContent.builder()
                .append(ItemStackIcon.builder().itemName("RED_BED").build())
                .build())
            .tooltip(ApolloButtonTooltip.of(
                Component.text("Spawn", NamedTextColor.AQUA),
                Component.text("Teleport back to spawn", NamedTextColor.GRAY),
                Component.text("Click to teleport", NamedTextColor.YELLOW)))
            .onClick(ApolloButtonAction.runCommand("/spawn"))
            .build();

        InventoryButton warps = InventoryButton.builder()
            .id("warps")
            .inventoryType(InventoryType.PLAYER)
            .box(InventoryButtonBox.LEFT)
            .position(HudPosition.of(4, 48))
            .size(InventoryButton.SIZE_MEDIUM)
            .shape(ApolloButtonShape.ROUNDED_SQUARE)
            .content(ApolloButtonContent.builder()
                .append(ItemStackIcon.builder().itemName("COMPASS").build())
                .build())
            .tooltip(ApolloButtonTooltip.of(
                Component.text("Warps", NamedTextColor.AQUA),
                Component.text("Browse public warps", NamedTextColor.GRAY),
                Component.text("Click to teleport", NamedTextColor.YELLOW)))
            .onClick(ApolloButtonAction.runCommand("/warps"))
            .build();

        InventoryButton enderChest = InventoryButton.builder()
            .id("enderchest")
            .inventoryType(InventoryType.PLAYER)
            .box(InventoryButtonBox.LEFT)
            .position(HudPosition.of(48, 48))
            .size(InventoryButton.SIZE_MEDIUM)
            .shape(ApolloButtonShape.ROUNDED_SQUARE)
            .content(ApolloButtonContent.builder()
                .append(ItemStackIcon.builder().itemName("ENDER_CHEST").build())
                .build())
            .tooltip(ApolloButtonTooltip.of(
                Component.text("Ender Chest", NamedTextColor.LIGHT_PURPLE),
                Component.text("Open your personal storage", NamedTextColor.GRAY),
                Component.text("Click to open", NamedTextColor.YELLOW)))
            .onClick(ApolloButtonAction.runCommand("/enderchest"))
            .build();

        InventoryButton profile = InventoryButton.builder()
            .id("profile")
            .inventoryType(InventoryType.PLAYER)
            .box(InventoryButtonBox.RIGHT)
            .position(HudPosition.of(4, 4))
            .size(InventoryButton.SIZE_MEDIUM)
            .shape(ApolloButtonShape.CIRCLE)
            .content(ApolloButtonContent.builder()
                .append(ItemStackIcon.builder().itemName("PLAYER_HEAD").build())
                .build())
            .tooltip(ApolloButtonTooltip.of(
                Component.text("Your Profile", NamedTextColor.GOLD),
                Component.text("View your stats", NamedTextColor.GRAY)))
            .onClick(ApolloButtonAction.runCommand("/profile"))
            .build();

        InventoryButton settings = InventoryButton.builder()
            .id("settings")
            .inventoryType(InventoryType.PLAYER)
            .box(InventoryButtonBox.RIGHT)
            .position(HudPosition.of(48, 4))
            .size(InventoryButton.SIZE_MEDIUM)
            .shape(ApolloButtonShape.CIRCLE)
            .backgroundColor(new Color(222, 160, 60, 85))
            .borderColor(new Color(255, 218, 150, 140))
            .content(ApolloButtonContent.builder()
                .append(ItemStackIcon.builder().itemName("COMPARATOR").build())
                .build())
            .tooltip(ApolloButtonTooltip.of(
                Component.text("Settings", NamedTextColor.WHITE),
                Component.text("Server preferences", NamedTextColor.GRAY)))
            .onClick(ApolloButtonAction.runCommand("/settings"))
            .build();

        InventoryButton vote = InventoryButton.builder()
            .id("vote")
            .inventoryType(InventoryType.PLAYER)
            .box(InventoryButtonBox.RIGHT)
            .position(HudPosition.of(6, 52))
            .size(InventoryButton.SIZE_WIDE)
            .shape(ApolloButtonShape.ROUNDED_SQUARE)
            .backgroundColor(new Color(34, 204, 68, 64))
            .borderColor(new Color(190, 255, 205, 110))
            .hoveredBackgroundColor(new Color(34, 204, 68, 130))
            .hoveredBorderColor(new Color(190, 255, 205, 210))
            .content(ApolloButtonContent.builder()
                .append(Component.text("Vote"))
                .build())
            .tooltip(ApolloButtonTooltip.of(
                Component.text("Vote", NamedTextColor.GREEN),
                Component.text("Vote daily for rewards", NamedTextColor.GRAY)))
            .onClick(ApolloButtonAction.openUrl("https://example.com/vote"))
            .build();

        InventoryButton discord = InventoryButton.builder()
            .id("discord")
            .inventoryType(InventoryType.PLAYER)
            .box(InventoryButtonBox.RIGHT)
            .position(HudPosition.of(6, 84))
            .size(InventoryButton.SIZE_WIDE)
            .shape(ApolloButtonShape.ROUNDED_SQUARE)
            .backgroundColor(new Color(88, 101, 242, 90))
            .borderColor(new Color(150, 160, 250, 140))
            .content(ApolloButtonContent.builder()
                .append(Component.text("Discord"))
                .build())
            .tooltip(ApolloButtonTooltip.of(
                Component.text("Discord", NamedTextColor.BLUE),
                Component.text("Join our community", NamedTextColor.GRAY)))
            .onClick(ApolloButtonAction.openUrl("https://lunarclient.dev/discord"))
            .build();

        InventoryButton lobby = InventoryButton.builder()
            .id("lobby")
            .inventoryType(InventoryType.PLAYER)
            .box(InventoryButtonBox.RIGHT)
            .position(HudPosition.of(6, 136))
            .size(InventoryButton.SIZE_WIDE)
            .shape(ApolloButtonShape.ROUNDED_SQUARE)
            .backgroundColor(new Color(224, 64, 64, 128))
            .borderColor(new Color(255, 200, 200, 140))
            .content(ApolloButtonContent.builder()
                .append(Component.text("Back to Lobby"))
                .build())
            .tooltip(ApolloButtonTooltip.of(
                Component.text("Back to Lobby", NamedTextColor.RED),
                Component.text("Return to the main lobby", NamedTextColor.GRAY)))
            .onClick(ApolloButtonAction.runCommand("/lobby"))
            .build();

        return new ArrayList<>(Arrays.asList(shop, spawn, warps, enderChest,
            profile, settings, vote, discord, lobby));
    }

}
