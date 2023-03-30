package com.moonsworth.apollo.option;

import com.moonsworth.apollo.option.type.*;

public class OptionsRegistry {

    public OptionsRegistry() {
        new BlockLocationImpl();
        new LocationImpl();
        new IconSpecificationsImpl();
        new RenderableIconImpl();
        new RenderableStringImpl();
    }
}
