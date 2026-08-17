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
package com.lunarclient.apollo.example.json.module.inventorybuttons;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.apollo.example.json.util.AdventureUtil;
import com.lunarclient.apollo.example.json.util.JsonUtil;
import java.awt.Color;
import net.kyori.adventure.text.Component;

public final class InventoryButtonParts {

    public static final Color BACKGROUND = new Color(255, 255, 255, 40);
    public static final Color BORDER = new Color(37, 37, 37, 128);

    public static JsonObject createDisplayMessage(JsonObject... buttons) {
        JsonArray buttonsArray = new JsonArray();
        for (JsonObject button : buttons) {
            buttonsArray.add(button);
        }

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.inventory.v1.DisplayInventoryButtonsMessage");
        message.add("inventory_buttons", buttonsArray);

        return message;
    }

    public static JsonObject createUpdateMessage(String id, JsonObject content, JsonObject tooltip) {
        JsonObject update = new JsonObject();
        update.add("content", content);
        if (tooltip != null) {
            update.add("tooltip", tooltip);
        }

        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.inventory.v1.UpdateInventoryButtonMessage");
        message.addProperty("id", id);
        message.add("update", update);

        return message;
    }

    public static JsonObject createButtonObject(String id, String box, float x, float y, float width, float height,
                                         String shape, Color backgroundColor, Color borderColor) {
        JsonObject position = new JsonObject();
        position.addProperty("x", x);
        position.addProperty("y", y);

        JsonObject size = new JsonObject();
        size.addProperty("width", width);
        size.addProperty("height", height);

        JsonObject button = new JsonObject();
        button.addProperty("id", id);
        button.add("position", position);
        button.add("size", size);
        button.addProperty("shape", shape);
        button.add("background_color", JsonUtil.createColorObject(backgroundColor));
        button.add("border_color", JsonUtil.createColorObject(borderColor));

        JsonObject wrapper = new JsonObject();
        wrapper.add("button", button);
        wrapper.addProperty("box", box);

        return wrapper;
    }

    public static JsonObject button(JsonObject wrapper) {
        return wrapper.getAsJsonObject("button");
    }

    public static JsonObject createContentObject(float scale, JsonObject... parts) {
        JsonArray partsArray = new JsonArray();
        for (JsonObject part : parts) {
            partsArray.add(part);
        }

        JsonObject content = new JsonObject();
        content.add("parts", partsArray);
        content.addProperty("scale", scale);

        return content;
    }

    public static JsonObject createTooltipObject(Component... lines) {
        JsonArray linesArray = new JsonArray();
        for (Component line : lines) {
            linesArray.add(AdventureUtil.toJson(line));
        }

        JsonObject tooltip = new JsonObject();
        tooltip.add("adventure_json_lines", linesArray);

        return tooltip;
    }

    public static JsonObject textPart(Component component) {
        JsonObject part = new JsonObject();
        part.addProperty("adventure_json_text", AdventureUtil.toJson(component));

        return part;
    }

    public static JsonObject iconPart(JsonObject icon) {
        JsonObject part = new JsonObject();
        part.add("icon", icon);

        return part;
    }

    private InventoryButtonParts() {
    }

}
