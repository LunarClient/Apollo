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
package com.lunarclient.apollo.example.proto.module.chatbuttons;

import com.lunarclient.apollo.button.v1.ButtonContentPart;
import com.lunarclient.apollo.common.v1.Icon;
import com.lunarclient.apollo.example.proto.util.AdventureUtil;
import java.awt.Color;
import net.kyori.adventure.text.Component;

public final class ChatButtonParts {

    public static final Color BACKGROUND = new Color(0, 0, 0, 128);
    public static final Color BORDER = new Color(0, 0, 0, 128);

    public static ButtonContentPart textPart(Component component) {
        return ButtonContentPart.newBuilder()
            .setAdventureJsonText(AdventureUtil.toJson(component))
            .build();
    }

    public static ButtonContentPart iconPart(Icon icon) {
        return ButtonContentPart.newBuilder()
            .setIcon(icon)
            .build();
    }

    private ChatButtonParts() {
    }

}
