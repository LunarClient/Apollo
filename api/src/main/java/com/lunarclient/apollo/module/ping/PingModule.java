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
package com.lunarclient.apollo.module.ping;

import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.module.ApolloModule;
import com.lunarclient.apollo.module.ModuleDefinition;
import com.lunarclient.apollo.recipients.Recipients;
import java.util.UUID;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents the Player Ping module.
 *
 * @since 1.1.1
 */
@ApiStatus.NonExtendable
@ModuleDefinition(id = "ping", name = "Ping")
public abstract class PingModule extends ApolloModule {

    /**
     * Send a team ping to all the given Recipients.
     *
     * @param recipients the recipients to send the packet to
     * @param playerUuid the player UUID who has originally sent the ping
     * @param location  the in-world location of the ping
     * @param isDoublePing true if the ping is a double-click ping, false otherwise
     * @since 1.1.1
     */
    public abstract void pingTeamMembers(Recipients recipients, UUID playerUuid, ApolloLocation location, boolean isDoublePing);

}
