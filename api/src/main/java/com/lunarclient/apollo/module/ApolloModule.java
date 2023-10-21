/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
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
package com.lunarclient.apollo.module;

import com.lunarclient.apollo.ApolloPlatform;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.lunarclient.apollo.option.SimpleOption;
import com.lunarclient.apollo.util.ConfigTarget;
import io.leangen.geantyref.TypeToken;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
     * Returns an array of {@link Option}s in this module.
     *
     * @return an array of module options
     * @since 1.0.0
     */
    @Getter(AccessLevel.PACKAGE)
    private final List<Option<?, ?, ?>> optionKeys = new LinkedList<>();

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

    private String id;
    private String name;
    private ConfigTarget configTarget;

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
        if (this.id != null) {
            return this.id;
        }

        return this.id = this.definition().id();
    }

    /**
     * Returns the module {@link String} name.
     *
     * @return the module name
     * @since 1.0.0
     */
    public String getName() {
        if (this.name != null) {
            return this.name;
        }

        return this.name = this.definition().name();
    }

    /**
     * Returns the {@link ConfigTarget} for this module.
     *
     * @return the module config target
     * @since 1.0.0
     */
    public ConfigTarget getConfigTarget() {
        if (this.configTarget != null) {
            return this.configTarget;
        }

        return this.configTarget = this.definition().configTarget();
    }

    /**
     * Returns a {@link Collection} of supported {@link com.lunarclient.apollo.ApolloPlatform.Kind}.
     *
     * @return a collection of supported kinds of platforms
     * @since 1.0.0
     */
    public Collection<ApolloPlatform.Kind> getSupportedPlatforms() {
        return Collections.singletonList(ApolloPlatform.Kind.SERVER);
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
        if (this.enabled) {
            return;
        }

        this.enabled = true;
        this.onEnable();
    }

    /**
     * Disables this module, if it is not already.
     *
     * @since 1.0.0
     */
    public void disable() {
        if (!this.enabled) {
            return;
        }

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

    private ModuleDefinition definition() {
        Class<?> moduleClass = this.getClass();
        ModuleDefinition definition = moduleClass.getAnnotation(ModuleDefinition.class);

        if (definition == null) {
            moduleClass = moduleClass.getSuperclass();
            definition = moduleClass.getAnnotation(ModuleDefinition.class);
        }

        if (definition == null) {
            throw new RuntimeException("Apollo module class " + moduleClass.getSimpleName() + " must be decorated with a ModuleDefinition annotation");
        }

        return definition;
    }

}
