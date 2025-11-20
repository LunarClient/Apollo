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
package com.lunarclient.apollo.example.api.module;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.example.module.impl.PayNowExample;
import com.lunarclient.apollo.module.paynow.PayNowEmbeddedCheckoutSupport;
import com.lunarclient.apollo.module.paynow.PayNowModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.Optional;
import org.bukkit.entity.Player;

public class PayNowApiExample extends PayNowExample {

    private final PayNowModule payNowModule = Apollo.getModuleManager().getModule(PayNowModule.class);

    @Override
    public void displayPayNowEmbeddedCheckoutExample(Player viewer, String checkoutToken) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        if (!apolloPlayerOpt.isPresent()) {
            viewer.sendMessage("Complete your purchase at " + checkoutToken); // TODO
            return;
        }

        ApolloPlayer apolloPlayer = apolloPlayerOpt.get();
        PayNowEmbeddedCheckoutSupport embeddedCheckoutSupport = apolloPlayer.getPayNowEmbeddedCheckoutSupport();

        if (embeddedCheckoutSupport == PayNowEmbeddedCheckoutSupport.UNSUPPORTED) {
            viewer.sendMessage("Complete your purchase at " + checkoutToken); // TODO
            return;
        }

        this.payNowModule.displayPayNowEmbeddedCheckout(apolloPlayer, checkoutToken);

        if (embeddedCheckoutSupport == PayNowEmbeddedCheckoutSupport.OVERLAY) {
            viewer.sendMessage("Opening checkout as game overlay!");
        } else {
            viewer.sendMessage("Opening checkout in an external window!");
        }
    }

}
