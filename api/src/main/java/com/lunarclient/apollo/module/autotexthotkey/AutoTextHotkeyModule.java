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
package com.lunarclient.apollo.module.autotexthotkey;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.option.ListOption;
import com.lunarclient.apollo.option.Option;
import io.leangen.geantyref.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the auto text hotkey module.
 *
 * @since 1.1.8
 */
@ModuleDefinition(id = "auto_text_hotkey", name = "Auto Text Hotkey")
public final class AutoTextHotkeyModule extends ApolloModule {

    /**
     * A list of text inputs that are blocked and cannot be used by the user.
     *
     * @since 1.1.8
     */
    public static final ListOption<String> BLOCKED_TEXT_INPUTS = Option.<String>list()
        .comment("A list of text inputs that are blocked and cannot be used by the user.")
        .node("blocked-text-inputs").type(new TypeToken<List<String>>() {})
        .defaultValue(new ArrayList<>(Arrays.asList("/sellall", "/msg", "/pay")))
        .notifyClient().build();

    AutoTextHotkeyModule() {
        this.registerOptions(
            AutoTextHotkeyModule.BLOCKED_TEXT_INPUTS
        );
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

}
