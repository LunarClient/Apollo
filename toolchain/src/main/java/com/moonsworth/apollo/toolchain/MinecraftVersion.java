package com.moonsworth.apollo.toolchain;

public enum MinecraftVersion {
    v1_18("1.18.2"),
    v1_19("1.19")
    ;

    public final String exactVersion;

    MinecraftVersion(String exactVersion) {
        this.exactVersion = exactVersion;
    }
}
