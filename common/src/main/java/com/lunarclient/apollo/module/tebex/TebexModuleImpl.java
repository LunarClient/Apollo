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
package com.lunarclient.apollo.module.tebex;

import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.recipients.Recipients;
import com.lunarclient.apollo.tebex.v1.OpenTebexEmbeddedCheckoutMessage;
import lombok.NonNull;

/**
 * Provides the tebex module.
 *
 * @since 1.1.6
 */
public final class TebexModuleImpl extends TebexModule {

    @Override
    public void displayTebexEmbeddedCheckout(@NonNull Recipients recipients, @NonNull String basketIdent) {
        this.displayTebexEmbeddedCheckout(recipients, basketIdent, null);
    }

    @Override
    public void displayTebexEmbeddedCheckout(@NonNull Recipients recipients, @NonNull String basketIdent, String locale) {
        OpenTebexEmbeddedCheckoutMessage.Builder builder = OpenTebexEmbeddedCheckoutMessage.newBuilder()
            .setBasketIdent(basketIdent);

        if (locale != null) {
            builder.setLocale(locale);
        }

        OpenTebexEmbeddedCheckoutMessage message = builder.build();
        recipients.forEach(player -> ((AbstractApolloPlayer) player).sendPacket(message));
    }

}
