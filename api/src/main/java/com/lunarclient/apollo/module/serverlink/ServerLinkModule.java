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
package com.lunarclient.apollo.module.serverlink;

import com.lunarclient.apollo.common.icon.ResourceLocationIcon;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.module.serverlink.pausemenu.LegacyServerLinkPlacement;
import com.lunarclient.apollo.module.serverlink.pausemenu.ModernServerLinkPlacement;
import com.lunarclient.apollo.option.EnumOption;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.recipients.Recipients;
import io.leangen.geantyref.TypeToken;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the server links module.
 *
 * <p>This module provides support for the Server Links feature introduced in
 * Minecraft 1.21.0, with compatibility extending down to version 1.7.</p>
 *
 * @since 1.2.5
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "server_link", name = "Server Link")
public abstract class ServerLinkModule extends ApolloModule {

    /**
     * Controls where the server links button is placed in the pause menu
     * (1.7-1.12), where it is added on a new line by default.
     *
     * @since 1.2.8
     */
    public static final EnumOption<LegacyServerLinkPlacement> LEGACY_BUTTON_PLACEMENT = Option.<LegacyServerLinkPlacement>enumerator()
        .comment(
            "Where the server links button appears in the pause menu (1.7-1.12), added on a new line by default.",
            " 'NEW_ROW' adds a dedicated row.",
            " 'REPLACE_ACHIEVEMENTS' reuses the achievements button.",
            " 'REPLACE_STATISTICS' reuses the statistics button."
        )
        .node("legacy-button-placement").type(TypeToken.get(LegacyServerLinkPlacement.class))
        .defaultValue(LegacyServerLinkPlacement.NEW_ROW).notifyClient().build();

    /**
     * Controls where the server links button is placed in the pause menu
     * (1.16.1+), where it replaces the existing report bugs button by default.
     *
     * <p>On 1.21 and above the server links button is handled natively and this
     * option has no effect.</p>
     *
     * @since 1.2.8
     */
    public static final EnumOption<ModernServerLinkPlacement> MODERN_BUTTON_PLACEMENT = Option.<ModernServerLinkPlacement>enumerator()
        .comment(
            "Where the server links button appears in the pause menu (1.16.1+), replaces the existing report bugs button by default.",
            " 'REPLACE_REPORT_BUGS' reuses the report bugs button.",
            " 'REPLACE_ACHIEVEMENTS' reuses the advancements button.",
            " 'REPLACE_STATISTICS' reuses the statistics button.",
            "Has no effect on 1.21+ (handled natively)."
        )
        .node("modern-button-placement").type(TypeToken.get(ModernServerLinkPlacement.class))
        .defaultValue(ModernServerLinkPlacement.REPLACE_REPORT_BUGS).notifyClient().build();

    ServerLinkModule() {
        this.registerOptions(
            ServerLinkModule.LEGACY_BUTTON_PLACEMENT,
            ServerLinkModule.MODERN_BUTTON_PLACEMENT
        );
    }

    /**
     * Overrides the server link menu title image for the {@link Recipients}.
     *
     * <p>The provided {@link ResourceLocationIcon} will be displayed in place of the default
     * menu title.</p>
     *
     * <p>The resource location must reference a valid client-side asset using the standard
     * namespace format:</p>
     *
     * <pre>
     * minecraft:textures/item/apple.png
     * lunar:logo/logo-100x100.png
     * </pre>
     *
     * <p>For optimal results, a square image (e.g. 128x128) is recommended.</p>
     *
     * @param recipients the recipients that are receiving the packet
     * @param icon       the resource location icon
     * @since 1.2.5
     */
    public abstract void overrideServerLinkResource(Recipients recipients, ResourceLocationIcon icon);

    /**
     * Resets the server link menu title image for the {@link Recipients}.
     *
     * <p>Reverts back to displaying the default menu title.</p>
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.2.5
     */
    public abstract void resetServerLinkResource(Recipients recipients);

    /**
     * Adds or updates the {@link ServerLink} for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param serverLink the server link
     * @since 1.2.5
     */
    public abstract void addServerLink(Recipients recipients, ServerLink serverLink);

    /**
     * Adds or updates the {@link ServerLink}s for the {@link Recipients}.
     *
     * @param recipients  the recipients that are receiving the packet
     * @param serverLinks the server links
     * @since 1.2.5
     */
    public abstract void addServerLink(Recipients recipients, List<ServerLink> serverLinks);

    /**
     * Removes the {@link ServerLink} from the {@link Recipients}.
     *
     * @param recipients   the recipients that are receiving the packet
     * @param serverLinkId the server link id
     * @since 1.2.5
     */
    public abstract void removeServerLink(Recipients recipients, String serverLinkId);

    /**
     * Removes the {@link ServerLink} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param serverLink the server link
     * @since 1.2.5
     */
    public abstract void removeServerLink(Recipients recipients, ServerLink serverLink);

    /**
     * Removes the {@link ServerLink}s from the {@link Recipients}.
     *
     * @param recipients    the recipients that are receiving the packet
     * @param serverLinkIds the server link ids
     * @since 1.2.5
     */
    public abstract void removeServerLink(Recipients recipients, List<String> serverLinkIds);

    /**
     * Resets all {@link ServerLink}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.2.5
     */
    public abstract void resetServerLinks(Recipients recipients);

}
