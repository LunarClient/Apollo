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
package com.lunarclient.apollo.example.nms;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public final class CosmeticOptionsAdapter implements JsonSerializer<CommandCosmetic.Options>, JsonDeserializer<CommandCosmetic.Options> {

    @Override
    public JsonElement serialize(CommandCosmetic.Options options, Type cosmeticType, JsonSerializationContext context) {
        if (options == null) {
            return JsonNull.INSTANCE;
        }

        JsonObject object;
        String type;
        if (options instanceof CommandCosmetic.Hat) {
            object = context.serialize(options, CommandCosmetic.Hat.class).getAsJsonObject();
            type = "hat";
        } else if (options instanceof CommandCosmetic.Cloak) {
            object = context.serialize(options, CommandCosmetic.Cloak.class).getAsJsonObject();
            type = "cloak";
        } else if (options instanceof CommandCosmetic.Pet) {
            object = context.serialize(options, CommandCosmetic.Pet.class).getAsJsonObject();
            type = "pet";
        } else if (options instanceof CommandCosmetic.Body) {
            object = context.serialize(options, CommandCosmetic.Body.class).getAsJsonObject();
            type = "body";
        } else {
            return JsonNull.INSTANCE;
        }

        object.addProperty("type", type);
        return object;
    }

    @Override
    public CommandCosmetic.Options deserialize(JsonElement json, Type cosmeticType, JsonDeserializationContext context) {
        if (json == null || json.isJsonNull() || !json.isJsonObject()) {
            return null;
        }

        JsonObject object = json.getAsJsonObject();
        JsonElement element = object.get("type");
        if (element == null || element.isJsonNull()) {
            return null;
        }

        switch (element.getAsString()) {
            case "hat": return context.deserialize(object, CommandCosmetic.Hat.class);
            case "cloak": return context.deserialize(object, CommandCosmetic.Cloak.class);
            case "pet": return context.deserialize(object, CommandCosmetic.Pet.class);
            case "body": return context.deserialize(object, CommandCosmetic.Body.class);
            default: return null;
        }
    }
}
