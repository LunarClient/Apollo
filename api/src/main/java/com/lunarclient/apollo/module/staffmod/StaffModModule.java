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
package com.lunarclient.apollo.module.staffmod;

import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the staff mod module.
 *
 * @since 1.0.0
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "staff_mod", name = "Staff Mod")
public abstract class StaffModModule extends ApolloModule {

    /**
     * Enables the {@link StaffMod}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param mods       the staff mods
     * @since 1.0.0
     */
    public abstract void enableStaffMods(Recipients recipients, List<StaffMod> mods);

    /**
     * Disables the {@link StaffMod}s from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @param mods       the staff mods
     * @since 1.0.0
     */
    public abstract void disableStaffMods(Recipients recipients, List<StaffMod> mods);

    /**
     * Enables all {@link StaffMod}s for the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.0.0
     */
    public abstract void enableAllStaffMods(Recipients recipients);

    /**
     * Disables all {@link StaffMod}s from the {@link Recipients}.
     *
     * @param recipients the recipients that are receiving the packet
     * @since 1.0.0
     */
    public abstract void disableAllStaffMods(Recipients recipients);

}
