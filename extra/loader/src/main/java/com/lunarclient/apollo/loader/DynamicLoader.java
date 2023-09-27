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
package com.lunarclient.apollo.loader;

import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;
import sun.misc.Unsafe;

/**
 * Provides a loader that can load jars onto the plugin class loader
 * dynamically.
 *
 * @since 1.0.0
 */
public final class DynamicLoader {

    private static final Unsafe UNSAFE;

    static {
        Unsafe unsafe = null;

        // Search for the unsafe instance.
        for (Field field : Unsafe.class.getDeclaredFields()) {
            try {
                if (field.getType() == Unsafe.class && Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    unsafe = (Unsafe) field.get(null);
                }
            } catch (Exception ignored) {
            }
        }

        UNSAFE = unsafe;
    }

    /**
     * Extracts the jar from the specified resource location into usable
     * URL.
     *
     * @param loader the class loader
     * @param resourcePath the resource path
     * @return the url
     * @since 1.0.0
     */
    private static URL extractJar(ClassLoader loader, String resourcePath) {
        URL jarInJar = loader.getResource(resourcePath);
        if(jarInJar == null) {
            throw new RuntimeException("Unable to locate jar!");
        }

        Path path;
        try {
            path = Files.createTempFile("apollo-" + UUID.randomUUID().toString().substring(0, 8), ".jar.tmp");
        } catch(Exception exception) {
            throw new RuntimeException("Unable to create temporary jar!", exception);
        }

        path.toFile().deleteOnExit();

        try(InputStream inputStream = jarInJar.openStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch(Exception exception) {
            throw new RuntimeException("Unable to copy jar to temporary path!", exception);
        }

        try {
            return path.toUri().toURL();
        } catch(Exception exception) {
            throw new RuntimeException("Unable to get url from temporary jar path!", exception);
        }
    }

    private final URLClassLoader parent;

    private MethodHandle addURL;

    /**
     * Constructs a new {@link DynamicLoader}.
     *
     * @param parent the parent class loader
     * @since 1.0.0
     */
    public DynamicLoader(@NonNull ClassLoader parent) {
        if (!(parent instanceof URLClassLoader)) throw new IllegalArgumentException("Parent class loader must be the plugin class loader!");
        this.parent = (URLClassLoader) parent;

        try {
            Method targetMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);

            // Java 9+ requires the URLClassLoader package to be opened to the plugin class loader.
            try {
                Class<?> moduleClass = Class.forName("java.lang.Module");
                Method getModuleMethod = Class.class.getMethod("getModule");
                Method addOpensMethod = moduleClass.getMethod("addOpens", String.class, moduleClass);

                Object classLoaderModule = getModuleMethod.invoke(URLClassLoader.class);
                Object thisModule = getModuleMethod.invoke(this.getClass());

                addOpensMethod.invoke(classLoaderModule, URLClassLoader.class.getPackage().getName(), thisModule);
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException ignore) {
            }

            try {
                targetMethod.setAccessible(true);
            } catch (Exception exception) {
                // Java 9+ throws an InaccessibleObjectException
                if (exception.getClass().getName().endsWith("InaccessibleObjectException")) {
                    // Attempt to use Unsafe to bypass the access check, which may not work.
                    if (DynamicLoader.UNSAFE != null) {
                        try {
                            for (Field trustedLookup : MethodHandles.Lookup.class.getDeclaredFields()) {
                                if (trustedLookup.getType() != MethodHandles.Lookup.class
                                    || !Modifier.isStatic(trustedLookup.getModifiers())
                                    || trustedLookup.isSynthetic()) {
                                    continue;
                                }

                                try {
                                    MethodHandles.Lookup lookup = (MethodHandles.Lookup) DynamicLoader.UNSAFE.getObject(
                                        DynamicLoader.UNSAFE.staticFieldBase(trustedLookup),
                                        DynamicLoader.UNSAFE.staticFieldOffset(trustedLookup)
                                    );

                                    this.addURL = lookup.unreflect(targetMethod).bindTo(this.parent);
                                    return;
                                } catch (Exception ignored) {
                                }
                            }
                        } catch (Exception ignored) {
                        }
                    }

                    // Attempt to use a Java Agent to bypass the access check, which may not work.
                    try {
                        Class<?> agentClass = Class.forName("com.lunarclient.apollo.loader.DynamicAgent");
                        Class<?> instrumentationClass = Class.forName("java.lang.instrument.Instrumentation");
                        Object instrumentation = agentClass.getDeclaredMethod("getInstrumentation").invoke(null);
                        Method redefineModule = instrumentationClass.getDeclaredMethod("redefineModule", Class.forName("java.lang.Module"), Set.class, Map.class, Map.class, Set.class, Map.class);
                        Method getModule = Class.class.getDeclaredMethod("getModule");
                        Map<String, Set<?>> toOpen = Collections.singletonMap("java.net", Collections.singleton(getModule.invoke(getClass())));
                        redefineModule.invoke(instrumentation, getModule.invoke(URLClassLoader.class), Collections.emptySet(), Collections.emptyMap(), toOpen, Collections.emptySet(), Collections.emptyMap());
                        targetMethod.setAccessible(true);
                    } catch (Exception ignored) {
                    }
                }
            }

            this.addURL = MethodHandles.lookup().unreflect(targetMethod).bindTo(this.parent);
        } catch (NoSuchMethodException | IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Installs the specified resources onto the plugin class loader.
     *
     * @param resources the resources
     * @since 1.0.0
     */
    public void install(@NonNull String[] resources) {
        this.install(Arrays.stream(resources)
            .map(resource -> DynamicLoader.extractJar(this.parent, resource))
            .collect(Collectors.toList()));
    }

    /**
     * Installs the specified resources onto the plugin class loader.
     *
     * @param resources the resources
     * @since 1.0.0
     */
    public void install(@NonNull List<URL> resources) {
        try {
            for (URL url : resources) {
                this.loadJar(url);
            }
        } catch (Exception exception) {
            throw new RuntimeException("Unable to load library jars!", exception);
        }
    }

    /**
     * Loads the specified jar onto the plugin class loader.
     *
     * @param url the url
     * @since 1.0.0
     */
    public void loadJar(@NonNull URL url) {
        try {
            this.addURL.invokeWithArguments(url);
        } catch (Throwable throwable) {
            throw new RuntimeException("Unable to load library jar!", throwable);
        }
    }

    /**
     * Creates an instance of the provided plugin class.
     *
     * @param pluginLoaderType the plugin loader class
     * @param pluginLoaderInstance the plugin loader instance
     * @param pluginClass the plugin class
     * @param <T> the platform type
     * @return the new platform plugin
     * @since 1.0.0
     */
    public <T> PlatformPlugin createPlugin(Class<T> pluginLoaderType, T pluginLoaderInstance, String pluginClass) {
        Class<? extends PlatformPlugin> plugin;
        try {
            plugin = Class.forName(pluginClass).asSubclass(PlatformPlugin.class);
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException("Unable to load plugin class!", exception);
        }

        Constructor<? extends PlatformPlugin> constructor;
        try {
            constructor = plugin.getConstructor(pluginLoaderType);
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException("Unable to get plugin constructor!", exception);
        }

        try {
            return constructor.newInstance(pluginLoaderInstance);
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException("Unable to create plugin instance!", exception);
        }
    }

}
