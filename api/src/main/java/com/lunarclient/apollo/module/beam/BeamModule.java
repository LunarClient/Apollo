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
package com.lunarclient.apollo.module.beam;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.recipients.Recipients;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the beam module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "beam", name = "Beam")
public abstract class BeamModule extends ApolloModule {

    /**
     * Displays the {@link Beam} to the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param beam       the beam
     * @since 1.0.0
     */
    public abstract void displayBeam(Recipients recipients, Beam beam);

    /**
     * Removes the {@link Beam} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param beamId     the beam id
     * @since 1.0.0
     */
    public abstract void removeBeam(Recipients recipients, String beamId);

    /**
     * Removes the {@link Beam} from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param beam       the beam
     * @since 1.0.0
     */
    public abstract void removeBeam(Recipients recipients, Beam beam);

    /**
     * Resets all {@link Beam}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.0.0
     */
    public abstract void resetBeams(Recipients recipients);

}
