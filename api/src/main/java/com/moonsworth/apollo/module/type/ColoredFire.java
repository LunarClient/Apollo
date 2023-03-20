package com.moonsworth.apollo.module.type;

import com.moonsworth.apollo.module.ApolloModule;

public final class ColoredFire extends ApolloModule {

    ColoredFire() {
        super("ColoredFire");
    }

    @Override
    public boolean isClientNotify() {
        return true;
    }

}
