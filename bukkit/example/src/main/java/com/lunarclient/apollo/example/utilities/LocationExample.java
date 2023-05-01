package com.lunarclient.apollo.example.utilities;

import com.lunarclient.apollo.common.location.ApolloBlockLocation;
import com.lunarclient.apollo.common.location.ApolloLocation;

public class LocationExample {

    public static ApolloBlockLocation blockLocationExample() {
        return ApolloBlockLocation.builder()
            .world("world")
            .x(0)
            .y(100)
            .z(0)
            .build();
    }

    public static ApolloLocation locationExample() {
        return ApolloLocation.builder()
            .world("world")
            .x(50.5D)
            .y(100)
            .z(50.0D)
            .build();
    }

}
