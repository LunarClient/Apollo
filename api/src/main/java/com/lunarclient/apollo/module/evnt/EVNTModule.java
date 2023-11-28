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
package com.lunarclient.apollo.module.evnt;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.recipients.Recipients;
import org.jetbrains.annotations.ApiStatus;
import java.util.List;

/**
 * Represents the EVNT module.
 *
 * @since 1.0.0 // TODO
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "evnt", name = "EVNT")
public abstract class EVNTModule extends ApolloModule {

    public abstract void overrideHeartTexture(Recipients recipients, int x, boolean hardcore);

    public abstract void resetHeartTexture(Recipients recipients);

    public abstract void openGui(Recipients recipients, GuiType type);

    public abstract void closeGui(Recipients recipients);

    public abstract void overrideCosmeticResources(Recipients recipients, CharacterResource resource);

    public abstract void updateCosmetics(Recipients recipients, List<String> models, List<String> animations);

    public abstract void overrideCharacter(Recipients recipients, Character character);

}
