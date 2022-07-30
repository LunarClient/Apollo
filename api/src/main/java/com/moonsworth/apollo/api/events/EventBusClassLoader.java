package com.moonsworth.apollo.api.events;

import java.net.URLClassLoader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventBusClassLoader extends URLClassLoader {
    private Map<String, Class<?>> classCache = new ConcurrentHashMap<>();

    public EventBusClassLoader(ClassLoader parent) {
        super(((URLClassLoader) parent).getURLs(), parent);
    }

    public Class<?> createClass(String name, byte[] data) {
        if (classCache.containsKey(name)) {
            return classCache.get(name);
        }
        Class<?> x = defineClass(name, data, 0, data.length);
        classCache.put(name, x);
        return x;
    }
}
