package com.moonsworth.apollo.module;

import com.moonsworth.apollo.ApolloPlatform;
import com.moonsworth.apollo.event.Listener;
import com.moonsworth.apollo.option.Option;
import com.moonsworth.apollo.option.Options;
import com.moonsworth.apollo.player.ApolloPlayerVersion;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.requireNonNull;

/**
 * Represents a module for Apollo.
 *
 * @since 1.0.0
 */
public abstract class ApolloModule implements Listener {

    /**
     * Returns the module {@link String} name.
     *
     * @return the module name
     * @since 1.0.0
     */
    @Getter
    private final String name;

    /**
     * Returns {@code true} if the module is enabled, otherwise returns
     * {@code false}.
     *
     * @return true if the module is enabled, otherwise false
     * @since 1.0.0
     */
    @Getter
    private boolean enabled;

    /**
     * Returns the {@link Options.Container} of this module.
     *
     * @return the module options container
     * @since 1.0.0
     */
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private Options.Container options = Options.empty();

    /**
     * Returns an array of {@link Option}s in this module.
     *
     * @return an array of module options
     * @since 1.0.0
     */
    @Getter(AccessLevel.PACKAGE)
    private Option<?, ?, ?>[] optionKeys = {};

    public ApolloModule(final String name) {
        this.name = requireNonNull(name, "name");
    }

    /**
     * Registers {@link Option}s for this module.
     *
     * @param options the option keys
     * @since 1.0.0
     */
    protected void registerOptions(final Option<?, ?, ?>... options) {
        this.optionKeys = options;
    }

    /**
     * Returns a {@link Collection} of supported {@link ApolloPlatform.Kind}.
     *
     * @return a collection of supported kinds of platforms
     * @since 1.0.0
     */
    public Collection<ApolloPlatform.Kind> getSupport() {
        return Collections.singletonList(ApolloPlatform.Kind.SERVER);
    }

    /**
     * Returns a {@link Set} of supported {@link ApolloPlayerVersion}.
     *
     * @return a collection of supported client versions
     * @since 1.0.0
     */
    public Set<ApolloPlayerVersion> getSupportedClientVersions() {
        return ApolloPlayerVersion.getVersions();
    }

    /**
     * Returns {@code true} if the client will be notified of this module,
     * otherwise returns {@code false}.
     *
     * <p>Setting this to true will also send any configuration options
     * that can be sent to the client.</p>
     *
     * @return true if the client should be notified, otherwise false
     * @since 1.0.0
     */
    public boolean isClientNotify() {
        return false;
    }

    /**
     * Enables this module, if it is not already.
     *
     * @since 1.0.0
     */
    public void enable() {
        if(this.enabled) return;
        this.enabled = true;
        this.onEnable();
    }

    /**
     * Called when this module is enabled.
     *
     * @since 1.0.0
     */
    protected void onEnable() {
    }

}
