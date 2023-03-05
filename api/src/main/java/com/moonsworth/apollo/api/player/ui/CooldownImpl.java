package com.moonsworth.apollo.api.player.ui;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.requireNonNull;

final class CooldownImpl implements Cooldown {

    private final String name;
    private final Duration duration;

    CooldownImpl(final String name, final Duration duration) {
        this.name = requireNonNull(name, "name");
        this.duration = requireNonNull(duration, "duration");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Duration getDuration() {
        return this.duration;
    }

    static final class BuilderImpl implements Builder {

        private String name;
        private Duration duration;

        BuilderImpl() {}

        BuilderImpl(final Cooldown existing) {
            this.name(existing.getName()).duration(existing.getDuration());
        }

        @Override
        public Builder name(final String name) {
            this.name = requireNonNull(name, "name");
            return this;
        }

        @Override
        public Builder duration(final long time, final TimeUnit timeUnit) {
            return this.duration(Duration.ofMillis(timeUnit.toMillis(time)));
        }

        @Override
        public Builder duration(final Duration duration) {
            this.duration = requireNonNull(duration, "duration");
            return this;
        }

        @Override
        public Cooldown build() {
            return new CooldownImpl(this.name, this.duration);
        }

    }

}
