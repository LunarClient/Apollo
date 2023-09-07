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
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

/**
 * Provides a class loader that allows dependencies to be dynamically loaded.
 *
 * @since 1.0.0
 */
public final class DynamicClassLoader extends URLClassLoader {

    static {
        ClassLoader.registerAsParallelCapable();
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

    /**
     * Constructs a new {@link DynamicClassLoader}.
     *
     * @param resources the jars to load
     * @param parent the parent class loader
     * @since 1.0.0
     */
    public DynamicClassLoader(String[] resources, ClassLoader parent) {
        super(
            Arrays.stream(resources)
                .map(resource -> DynamicClassLoader.extractJar(parent, resource))
                .toArray(URL[]::new),
            parent
        );
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
            plugin = this.loadClass(pluginClass).asSubclass(PlatformPlugin.class);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Unable to load plugin class!", e);
        }

        Constructor<? extends PlatformPlugin> constructor;
        try {
            constructor = plugin.getConstructor(pluginLoaderType);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Unable to get plugin constructor!", e);
        }

        try {
            return constructor.newInstance(pluginLoaderInstance);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Unable to create plugin instance!", e);
        }
    }

}
