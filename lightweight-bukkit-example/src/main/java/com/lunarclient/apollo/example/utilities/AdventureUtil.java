package com.lunarclient.apollo.example.utilities;

import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

// DONE
public final class AdventureUtil {

    public static String toJson(@NonNull Component component) {
        return GsonComponentSerializer.gson().serialize(component);
    }

    private AdventureUtil() {
    }

}
