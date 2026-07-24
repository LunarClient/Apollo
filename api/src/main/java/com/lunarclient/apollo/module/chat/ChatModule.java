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
package com.lunarclient.apollo.module.chat;

import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.recipients.Recipients;
import io.leangen.geantyref.TypeToken;
import java.util.Collection;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the chat module.
 *
 * @since 1.0.2
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "chat", name = "Chat")
public abstract class ChatModule extends ApolloModule {

    /**
     * Controls whether live {@link ChatButton} content is automatically
     * re-resolved and re-sent to viewers.
     *
     * <p>When enabled without further setup, updates are always sent to
     * every player holding live buttons, even while their chat is closed.
     * Also enable the packet enrichment module and its player chat
     * open/close packets and events to only send updates to players who
     * currently have their chat open.</p>
     *
     * @since 1.2.9
     */
    public static final SimpleOption<Boolean> BROADCAST_LIVE_BUTTONS = Option.<Boolean>builder()
        .comment("Set to 'true' to automatically re-send resolved live chat button content, otherwise 'false'. "
            + "When enabled, updates are always sent, even to players whose chat is closed; also enable the "
            + "packet enrichment module and its player chat open/close packets and events to only send "
            + "updates to players who currently have their chat open.")
        .node("live-buttons", "broadcast").type(TypeToken.get(Boolean.class))
        .defaultValue(false).build();

    protected ChatModule() {
        this.registerOptions(
            ChatModule.BROADCAST_LIVE_BUTTONS
        );
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

    /**
     * Displays the message to the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param text       the text to display
     * @param messageId  the message id to update
     * @since 1.0.2
     */
    public abstract void displayLiveChatMessage(Recipients recipients, Component text, int messageId);

    /**
     * Removes the message from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param messageId  the message id to update
     * @since 1.0.2
     */
    public abstract void removeLiveChatMessage(Recipients recipients, int messageId);

    /**
     * Displays the {@link ChatButton}s to the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttons    the chat buttons
     * @since 1.2.9
     */
    public abstract void displayChatButtons(Recipients recipients, Collection<ChatButton> buttons);

    /**
     * Displays the {@link ChatButton} to the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param button     the chat button
     * @since 1.2.9
     */
    public abstract void displayChatButton(Recipients recipients, ChatButton button);

    /**
     * Removes the {@link ChatButton} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttonId   the chat button id
     * @since 1.2.9
     */
    public abstract void removeChatButton(Recipients recipients, String buttonId);

    /**
     * Removes the {@link ChatButton} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param button     the chat button
     * @since 1.2.9
     */
    public abstract void removeChatButton(Recipients recipients, ChatButton button);

    /**
     * Resets all {@link ChatButton}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.2.9
     */
    public abstract void resetChatButtons(Recipients recipients);

    /**
     * Updates the content and tooltip of a previously displayed
     * {@link ChatButton} for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttonId   the chat button id
     * @param content    the new button content
     * @param tooltip    the new tooltip, or {@code null} to clear the tooltip
     * @since 1.2.9
     */
    public abstract void updateChatButton(Recipients recipients, String buttonId,
                                          ApolloButtonContent content, @Nullable ApolloButtonTooltip tooltip);

    /**
     * Updates only the content of a previously displayed
     * {@link ChatButton} for the {@link Recipients}, keeping the
     * previous tooltip.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttonId   the chat button id
     * @param content    the new button content
     * @since 1.2.9
     */
    public abstract void updateChatButtonContent(Recipients recipients, String buttonId,
                                                 ApolloButtonContent content);

    /**
     * Updates only the tooltip of a previously displayed
     * {@link ChatButton} for the {@link Recipients}, keeping the
     * previous content.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttonId   the chat button id
     * @param tooltip    the new tooltip, or {@code null} to clear the tooltip
     * @since 1.2.9
     */
    public abstract void updateChatButtonTooltip(Recipients recipients, String buttonId,
                                                 @Nullable ApolloButtonTooltip tooltip);

}
