package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;

public final class HeartTexture extends ApolloModule {

    HeartTexture() {
        super("HeartTexture");
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

}
