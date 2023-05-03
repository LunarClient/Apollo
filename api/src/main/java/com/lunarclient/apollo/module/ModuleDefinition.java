package com.lunarclient.apollo.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to define a module.
 *
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleDefinition {

    /**
     * The identifier of the module.
     *
     * @return the identifier
     * @since 1.0.0
     */
    String id();

    /**
     * The friendly name of the module.
     *
     * @return the name
     * @since 1.0.0
     */
    String name();

}
