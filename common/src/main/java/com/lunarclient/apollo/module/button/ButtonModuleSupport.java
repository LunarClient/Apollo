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
package com.lunarclient.apollo.module.button;

import com.google.protobuf.Message;
import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.ApolloManager;
import com.lunarclient.apollo.button.v1.ButtonUpdate;
import com.lunarclient.apollo.common.button.ApolloButton;
import com.lunarclient.apollo.common.button.ApolloButtonSize;
import com.lunarclient.apollo.common.button.ApolloButtonTooltip;
import com.lunarclient.apollo.common.button.action.ApolloButtonAction;
import com.lunarclient.apollo.common.button.action.RunCommandAction;
import com.lunarclient.apollo.common.button.content.ApolloButtonContent;
import com.lunarclient.apollo.common.button.content.ApolloButtonContentPart;
import com.lunarclient.apollo.common.button.content.LiveComponentPart;
import com.lunarclient.apollo.common.location.HudPosition;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.network.ButtonNetworkTypes;
import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

/**
 * The shared engine behind every button module.
 *
 * @param <B> the button type
 * @param <P> the protobuf message
 * @since 1.2.9
 */
@RequiredArgsConstructor
public final class ButtonModuleSupport<B extends ApolloButton, P extends Message> {

    private static final long TICK_MILLIS = 50L;

    private final Set<UUID> openViewers = ConcurrentHashMap.newKeySet();
    private final Map<UUID, Map<String, LiveButton>> liveButtons = new ConcurrentHashMap<>();
    private final AtomicBoolean broadcastStarted = new AtomicBoolean(false);
    private final AtomicBoolean ticking = new AtomicBoolean(false);

    private final ApolloModule owner;
    private final ButtonSurface<B, P> surface;
    private final SimpleOption<Boolean> broadcastOption;

    /**
     * Starts the repeating live-button broadcast task.
     *
     * @since 1.2.9
     */
    public void startBroadcast() {
        if (!this.broadcastStarted.compareAndSet(false, true)) {
            return;
        }

        try {
            Apollo.getPlatform().getScheduler()
                .scheduleAsyncRepeating(this::broadcastTick, TICK_MILLIS, TICK_MILLIS, TimeUnit.MILLISECONDS);
        } catch (Throwable throwable) {
            this.broadcastStarted.set(false);
        }
    }

    /**
     * Validates and displays the buttons, resolving live values per viewer
     * and registering live buttons for the periodic broadcast.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttons    the buttons to display
     * @since 1.2.9
     */
    public void displayButtons(Recipients recipients, Collection<B> buttons) {
        if (buttons.isEmpty()) {
            throw new IllegalArgumentException("Button collection must not be empty");
        }

        Set<String> ids = new HashSet<>(buttons.size());
        List<B> live = new ArrayList<>();
        for (B button : buttons) {
            this.surface.validate(button);

            if (!ids.add(button.getId())) {
                throw new IllegalArgumentException("Duplicate button id '" + button.getId() + "' in display batch");
            }

            if (this.isLiveButton(button)) {
                live.add(button);
            }
        }

        if (live.isEmpty()) {
            List<P> elements = new ArrayList<>();
            for (B button : buttons) {
                elements.add(this.surface.toDisplayElement(button, null));
            }

            ApolloManager.getNetworkManager().sendPacket(recipients, this.surface.createDisplay(elements));
            this.untrackButtons(recipients, buttons);
            return;
        }

        recipients.forEach(recipient -> {
            ApolloPlayer player = (ApolloPlayer) recipient;

            List<P> elements = new ArrayList<>();
            for (B button : buttons) {
                elements.add(this.surface.toDisplayElement(button, player));
            }

            ApolloManager.getNetworkManager().sendPacket(player, this.surface.createDisplay(elements));

            long now = System.currentTimeMillis();
            Map<String, LiveButton> tracked = this.liveButtons.computeIfAbsent(player.getUniqueId(), uuid -> new ConcurrentHashMap<>());

            for (B button : buttons) {
                if (this.isLiveButton(button)) {
                    tracked.put(button.getId(), this.createLiveButton(button.getContent(), button.getTooltip(), now));
                } else {
                    tracked.remove(button.getId());
                }
            }
        });
    }

    private void untrackButtons(Recipients recipients, Collection<B> buttons) {
        recipients.forEach(recipient -> {
            Map<String, LiveButton> tracked = this.liveButtons.get(((ApolloPlayer) recipient).getUniqueId());
            if (tracked == null) {
                return;
            }

            for (B button : buttons) {
                tracked.remove(button.getId());
            }
        });
    }

    /**
     * Removes a button by id and drops it from live tracking.
     *
     * @param recipients the recipients that are receiving the packet
     * @param buttonId   the button id
     * @since 1.2.9
     */
    public void removeButton(Recipients recipients, String buttonId) {
        ApolloManager.getNetworkManager().sendPacket(recipients, this.surface.createRemove(buttonId));

        recipients.forEach(recipient -> {
            Map<String, LiveButton> tracked = this.liveButtons.get(((ApolloPlayer) recipient).getUniqueId());
            if (tracked != null) {
                tracked.remove(buttonId);
            }
        });
    }

    /**
     * Resets all buttons and drops the recipients live tracking.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.2.9
     */
    public void resetButtons(Recipients recipients) {
        ApolloManager.getNetworkManager().sendPacket(recipients, this.surface.createReset());
        recipients.forEach(recipient -> this.liveButtons.remove(((ApolloPlayer) recipient).getUniqueId()));
    }

    /**
     * Pushes an update for a displayed button.
     *
     * @param recipients    the recipients that are receiving the packet
     * @param buttonId      the button id
     * @param content       the new content, or {@code null} to keep the current one
     * @param updateTooltip whether the tooltip should be replaced at all
     * @param tooltip       the new tooltip, or {@code null} to clear it (when
     *                      {@code updateTooltip} is {@code true})
     * @since 1.2.9
     */
    public void pushUpdate(Recipients recipients, String buttonId,
                           @Nullable ApolloButtonContent content, boolean updateTooltip,
                           @Nullable ApolloButtonTooltip tooltip) {
        if (buttonId.isEmpty()) {
            throw new IllegalArgumentException("ApolloButton#id must not be empty");
        }

        boolean live = (content != null && content.isLive()) || (updateTooltip && tooltip != null && tooltip.isLive());

        if (!live) {
            ButtonUpdate update = ButtonNetworkTypes.toUpdateProtobuf(content, tooltip, updateTooltip, null);
            ApolloManager.getNetworkManager().sendPacket(recipients, this.surface.createUpdate(buttonId, update));
        } else {
            recipients.forEach(recipient -> {
                ApolloPlayer player = (ApolloPlayer) recipient;

                ButtonUpdate update = ButtonNetworkTypes.toUpdateProtobuf(content, tooltip, updateTooltip, player);
                ApolloManager.getNetworkManager().sendPacket(player, this.surface.createUpdate(buttonId, update));
            });
        }

        recipients.forEach(recipient -> {
            Map<String, LiveButton> tracked = this.liveButtons
                .computeIfAbsent(((ApolloPlayer) recipient).getUniqueId(), uuid -> new ConcurrentHashMap<>());

            long now = System.currentTimeMillis();
            tracked.compute(buttonId, (id, previous) -> {
                ApolloButtonContent newContent = content != null ? content
                    : previous != null ? previous.content : null;
                ApolloButtonTooltip newTooltip = updateTooltip ? tooltip
                    : previous != null ? previous.tooltip : null;

                if (newContent == null || (!newContent.isLive() && (newTooltip == null || !newTooltip.isLive()))) {
                    return null;
                }

                return this.createLiveButton(newContent, newTooltip, now);
            });
        });
    }

    /**
     * Marks a player's surface as open and pushes an immediate live refresh
     * for their tracked buttons; called from the surface's open event.
     *
     * @param player the player whose surface opened
     * @since 1.2.9
     */
    public void handleOpen(ApolloPlayer player) {
        if (!this.owner.isEnabled()) {
            return;
        }

        this.openViewers.add(player.getUniqueId());

        if (!this.owner.getOptions().get(this.broadcastOption)) {
            return;
        }

        Map<String, LiveButton> tracked = this.liveButtons.get(player.getUniqueId());
        if (tracked != null && !tracked.isEmpty()) {
            this.sendLiveUpdates(player, tracked, System.currentTimeMillis(), true);
        }
    }

    /**
     * Marks a player's surface as closed; called from the surface's close
     * event.
     *
     * @param playerIdentifier the player whose surface closed
     * @since 1.2.9
     */
    public void handleClose(UUID playerIdentifier) {
        this.openViewers.remove(playerIdentifier);
    }

    /**
     * Drops all state for a disconnecting player.
     *
     * @param playerIdentifier the unregistering player
     * @since 1.2.9
     */
    public void handleUnregister(UUID playerIdentifier) {
        this.openViewers.remove(playerIdentifier);
        this.liveButtons.remove(playerIdentifier);
    }

    private void broadcastTick() {
        if (!this.ticking.compareAndSet(false, true)) {
            return;
        }

        try {
            if (!this.owner.isEnabled()) {
                this.liveButtons.clear();
                this.openViewers.clear();
                return;
            }

            if (!this.owner.getOptions().get(this.broadcastOption) || this.liveButtons.isEmpty()) {
                return;
            }

            long now = System.currentTimeMillis();
            boolean trackingActive = this.surface.isOpenTrackingActive();

            Iterator<Map.Entry<UUID, Map<String, LiveButton>>> iterator = this.liveButtons.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<UUID, Map<String, LiveButton>> entry = iterator.next();
                if (entry.getValue().isEmpty()) {
                    iterator.remove();
                    continue;
                }

                if (trackingActive && !this.openViewers.contains(entry.getKey())) {
                    continue;
                }

                if (!this.anyUpdateDue(entry.getValue(), now)) {
                    continue;
                }

                ApolloPlayer player = Apollo.getPlayerManager().getPlayer(entry.getKey()).orElse(null);
                if (player == null) {
                    iterator.remove();
                    continue;
                }

                try {
                    this.sendLiveUpdates(player, entry.getValue(), now, false);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            this.ticking.set(false);
        }
    }

    private long millisToTicks(Duration interval) {
        return Math.max(1L, interval.toMillis() / TICK_MILLIS) * TICK_MILLIS;
    }

    private LiveButton createLiveButton(ApolloButtonContent content, @Nullable ApolloButtonTooltip tooltip, long now) {
        long contentInterval = 0L;
        for (ApolloButtonContentPart part : content.getParts()) {
            if (!(part instanceof LiveComponentPart)) {
                continue;
            }

            LiveComponentPart livePart = (LiveComponentPart) part;
            long interval = this.millisToTicks(livePart.getUpdateInterval());

            if (contentInterval == 0L || interval < contentInterval) {
                contentInterval = interval;
            }
        }

        Duration tooltipUpdateInterval = tooltip != null ? tooltip.getUpdateInterval() : null;
        long tooltipInterval = tooltipUpdateInterval != null ? this.millisToTicks(tooltipUpdateInterval) : 0L;

        return new LiveButton(content, tooltip, contentInterval, tooltipInterval, now);
    }

    private boolean anyUpdateDue(Map<String, LiveButton> buttons, long now) {
        for (LiveButton button : buttons.values()) {
            if (button.isContentDue(now) || button.isTooltipDue(now)) {
                return true;
            }
        }

        return false;
    }

    private void sendLiveUpdates(ApolloPlayer player, Map<String, LiveButton> buttons, long now, boolean force) {
        for (Map.Entry<String, LiveButton> entry : buttons.entrySet()) {
            LiveButton button = entry.getValue();

            boolean contentDue = force ? button.contentIntervalMillis > 0L : button.isContentDue(now);
            boolean tooltipDue = force ? button.tooltipIntervalMillis > 0L : button.isTooltipDue(now);
            if (!contentDue && !tooltipDue) {
                continue;
            }

            ButtonUpdate update = ButtonNetworkTypes.toUpdateProtobuf(contentDue
                ? button.content : null, button.tooltip, tooltipDue, player);

            ApolloManager.getNetworkManager().sendPacket(player, this.surface.createUpdate(entry.getKey(), update));

            if (contentDue) {
                button.nextContentUpdate = now + button.contentIntervalMillis;
            }

            if (tooltipDue) {
                button.nextTooltipUpdate = now + button.tooltipIntervalMillis;
            }
        }
    }

    private boolean isLiveButton(ApolloButton button) {
        ApolloButtonTooltip tooltip = button.getTooltip();
        return button.getContent().isLive() || (tooltip != null && tooltip.isLive());
    }

    private static final class LiveButton {

        private final ApolloButtonContent content;
        private final @Nullable ApolloButtonTooltip tooltip;
        private final long contentIntervalMillis;
        private final long tooltipIntervalMillis;
        private volatile long nextContentUpdate;
        private volatile long nextTooltipUpdate;

        private LiveButton(ApolloButtonContent content, @Nullable ApolloButtonTooltip tooltip,
                           long contentIntervalMillis, long tooltipIntervalMillis, long now) {
            this.content = content;
            this.tooltip = tooltip;
            this.contentIntervalMillis = contentIntervalMillis;
            this.tooltipIntervalMillis = tooltipIntervalMillis;
            this.nextContentUpdate = now + contentIntervalMillis;
            this.nextTooltipUpdate = now + tooltipIntervalMillis;
        }

        private boolean isContentDue(long now) {
            return this.contentIntervalMillis > 0L && now >= this.nextContentUpdate;
        }

        private boolean isTooltipDue(long now) {
            return this.tooltipIntervalMillis > 0L && now >= this.nextTooltipUpdate;
        }
    }

    /**
     * Validates the button properties.
     *
     * @param button    the button to validate
     * @param boxWidth  the surface container width
     * @param boxHeight the surface container height
     * @since 1.2.9
     */
    public static void validateCommon(ApolloButton button, float boxWidth, float boxHeight) {
        requireSet(button.getId(), "ApolloButton#id");
        requireSet(button.getPosition(), "ApolloButton#position");
        requireSet(button.getSize(), "ApolloButton#size");
        requireSet(button.getShape(), "ApolloButton#shape");
        requireSet(button.getBackgroundColor(), "ApolloButton#backgroundColor");
        requireSet(button.getBorderColor(), "ApolloButton#borderColor");
        requireSet(button.getContent(), "ApolloButton#content");

        if (button.getId().isEmpty()) {
            throw new IllegalArgumentException("ApolloButton#id must not be empty");
        }

        HudPosition position = button.getPosition();
        ApolloButtonSize size = button.getSize();

        if (!Float.isFinite(position.getX()) || !Float.isFinite(position.getY())
            || !Float.isFinite(size.getWidth()) || !Float.isFinite(size.getHeight())) {
            throw new IllegalArgumentException("ApolloButton position and size must be finite");
        }

        if (size.getWidth() <= 0.0F || size.getHeight() <= 0.0F) {
            throw new IllegalArgumentException("ApolloButton#size width and height must be greater than 0");
        }

        if (position.getX() < 0.0F || position.getY() < 0.0F
            || position.getX() + size.getWidth() > boxWidth
            || position.getY() + size.getHeight() > boxHeight) {
            throw new IllegalArgumentException("ApolloButton must fit within the " + boxWidth + "x" + boxHeight + " button box");
        }

        ApolloButtonAction onClick = button.getOnClick();
        if (onClick instanceof RunCommandAction && !((RunCommandAction) onClick).getCommand().startsWith("/")) {
            throw new IllegalArgumentException("RunCommandAction#command must start with '/'");
        }
    }

    /**
     * Throws a {@link IllegalArgumentException} when the value is {@code null}.
     *
     * @param value the value to check
     * @param name  the field name
     * @since 1.2.9
     */
    public static void requireSet(@Nullable Object value, String name) {
        if (value == null) {
            throw new IllegalArgumentException(name + " must not be null");
        }
    }

}
