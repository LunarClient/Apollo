package com.lunarclient.apollo.module;

import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.player.ApolloPlayerVersion;
import io.leangen.geantyref.TypeToken;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents a module for Apollo.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
public abstract class ApolloModule implements ApolloListener {

    /**
     * Whether to enable this module.
     *
     * @since 1.0.0
     */
    public static final SimpleOption<Boolean> ENABLE = Option.<Boolean>builder()
        .comment("Set to 'true' to enable this module, otherwise set 'false'.")
        .node("enable").type(TypeToken.get(Boolean.class))
        .defaultValue(true).build();

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
     * Returns the {@link Options} of this module.
     *
     * @return the module options container
     * @since 1.0.0
     */
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private Options options = Options.empty();

    /**
     * Returns an array of {@link Option}s in this module.
     *
     * @return an array of module options
     * @since 1.0.0
     */
    @Getter(AccessLevel.PACKAGE)
    private List<Option<?, ?, ?>> optionKeys = new LinkedList<>();

    private String id;

    /**
     * Constructs a new {@link ApolloModule}.
     *
     * @since 1.0.0
     */
    protected ApolloModule() {
        this.registerOptions(ApolloModule.ENABLE);
    }

    /**
     * Registers {@link Option}s for this module.
     *
     * @param options the option keys
     * @since 1.0.0
     */
    protected void registerOptions(Option<?, ?, ?>... options) {
        this.optionKeys.addAll(Arrays.asList(options));
    }

    /**
     * Returns the module {@link String} name.
     *
     * @return the module name
     * @since 1.0.0
     */
    public String getId() {
        if(this.id != null) return this.id;

        Class<?> moduleClass = this.getClass();
        ModuleDefinition definition = moduleClass.getAnnotation(ModuleDefinition.class);

        if(definition == null) {
            moduleClass = moduleClass.getSuperclass();
            definition = moduleClass.getAnnotation(ModuleDefinition.class);
        }

        if(definition == null) {
            throw new RuntimeException("Apollo module class " + moduleClass.getSimpleName() + " must be decorated with a ModuleDefinition annotation");
        }

        return this.id = definition.id();
    }

    /**
     * Returns a {@link Collection} of supported {@link ApolloPlatform.Kind}.
     *
     * @return a collection of supported kinds of platforms
     * @since 1.0.0
     */
    public Collection<ApolloPlatform.Kind> getSupportedPlatforms() {
        return Collections.singletonList(ApolloPlatform.Kind.SERVER);
    }

    /**
     * Returns a {@link Set} of supported {@link ApolloPlayerVersion}.
     *
     * @return a collection of supported client versions
     * @since 1.0.0
     */
    public Set<ApolloPlayerVersion> getSupportedVersions() {
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
     * Disables this module, if it is not already.
     *
     * @since 1.0.0
     */
    public void disable() {
        if(!this.enabled) return;
        this.enabled = false;
        this.onDisable();
    }

    /**
     * Called when this module is enabled.
     *
     * @since 1.0.0
     */
    protected void onEnable() {
    }

    /**
     * Called when this module is disabled.
     *
     * @since 1.0.0
     */
    private void onDisable() {
    }

}
