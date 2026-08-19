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
package com.lunarclient.apollo.common.button.content;

import com.lunarclient.apollo.common.button.ApolloButton;
import com.lunarclient.apollo.common.icon.Icon;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import lombok.Getter;
import lombok.NonNull;
import net.kyori.adventure.text.Component;

/**
 * Represents the content of an {@link ApolloButton}.
 *
 * @since 1.2.9
 */
@Getter
public final class ApolloButtonContent {

    /**
     * The maximum number of content parts per button.
     *
     * @since 1.2.9
     */
    public static final int MAX_PARTS = 30;

    /**
     * The minimum content {@link #scale}.
     *
     * @since 1.2.9
     */
    public static final float MIN_SCALE = 0.25F;

    /**
     * The maximum content {@link #scale}.
     *
     * @since 1.2.9
     */
    public static final float MAX_SCALE = 4.0F;

    /**
     * Creates a new {@link Builder} for building button content.
     *
     * @return the content builder
     * @since 1.2.9
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns an unmodifiable {@link List} of the content
     * {@link ApolloButtonContentPart}s, in render order.
     *
     * @return the content parts
     * @since 1.2.9
     */
    private final List<ApolloButtonContentPart> parts;

    /**
     * Returns the {@code float} scale factor applied to the content row.
     *
     * @return the content scale
     * @since 1.2.9
     */
    private final float scale;

    /**
     * Returns whether any part of this content is live.
     *
     * @return whether this content has live parts
     * @since 1.2.9
     */
    private final boolean live;

    private ApolloButtonContent(List<ApolloButtonContentPart> parts, float scale, boolean live) {
        this.parts = Collections.unmodifiableList(parts);
        this.scale = scale;
        this.live = live;
    }

    /**
     * Represents a builder for {@link ApolloButtonContent}.
     *
     * @since 1.2.9
     */
    public static final class Builder {

        private final List<ApolloButtonContentPart> parts = new ArrayList<>();
        private float scale = 1.0F;
        private boolean live;

        private Builder() {
        }

        /**
         * Appends an adventure {@link Component} to the content.
         *
         * @param component the component to append
         * @return this builder
         * @since 1.2.9
         */
        public Builder append(@NonNull Component component) {
            return this.append(ApolloButtonContentPart.component(component));
        }

        /**
         * Appends an {@link Icon} to the content; see {@link IconPart}.
         *
         * @param icon the icon to append
         * @return this builder
         * @since 1.2.9
         */
        public Builder append(@NonNull Icon icon) {
            return this.append(ApolloButtonContentPart.icon(icon));
        }

        /**
         * Appends a live part, resolved into an adventure {@link Component}
         * for each viewing {@link ApolloPlayer} and refreshed at the given
         * interval.
         *
         * @param liveComponent  the per-player component resolver to append
         * @param updateInterval the refresh interval
         * @return this builder
         * @since 1.2.9
         */
        public Builder append(@NonNull Function<ApolloPlayer, Component> liveComponent,
                              @NonNull Duration updateInterval) {
            return this.append(ApolloButtonContentPart.live(liveComponent, updateInterval));
        }

        /**
         * Appends a pre-built content part; see the
         * {@link ApolloButtonContentPart} factories.
         *
         * @param part the content part to append
         * @return this builder
         * @since 1.2.9
         */
        public Builder append(@NonNull ApolloButtonContentPart part) {
            this.parts.add(part);
            this.live |= part.isLive();
            return this;
        }

        /**
         * Sets the {@code float} scale factor applied to the content row.
         * Defaults to {@code 1.0}.
         *
         * @param scale the content scale, between {@link #MIN_SCALE} and {@link #MAX_SCALE}
         * @return this builder
         * @since 1.2.9
         */
        public Builder scale(float scale) {
            if (!(scale >= MIN_SCALE && scale <= MAX_SCALE)) {
                throw new IllegalArgumentException("ApolloButtonContent#scale must be between " + MIN_SCALE + " and " + MAX_SCALE);
            }

            this.scale = scale;
            return this;
        }

        /**
         * Builds the {@link ApolloButtonContent}.
         *
         * @return the built content
         * @since 1.2.9
         */
        public ApolloButtonContent build() {
            if (this.parts.isEmpty()) {
                throw new IllegalArgumentException("ApolloButtonContent requires at least one part");
            }

            if (this.parts.size() > MAX_PARTS) {
                throw new IllegalArgumentException("ApolloButtonContent supports at most " + MAX_PARTS + " parts");
            }

            return new ApolloButtonContent(new ArrayList<>(this.parts), this.scale, this.live);
        }

    }

}
