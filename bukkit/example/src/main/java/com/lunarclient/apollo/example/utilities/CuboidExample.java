package com.lunarclient.apollo.example.utilities;

import com.lunarclient.apollo.common.cuboid.Cuboid2D;
import com.lunarclient.apollo.common.cuboid.Cuboid3D;

public class CuboidExample {

    public static Cuboid2D cuboid2DExample() {
        return Cuboid2D.builder()
            .minX(-50)
            .minZ(-50)
            .maxX(50)
            .maxZ(50)
            .build();
    }

    public static Cuboid3D cuboid3DExample() {
        return Cuboid3D.builder()
            .minX(-50)
            .minY(0)
            .minZ(-50)
            .maxX(50)
            .maxY(256)
            .maxZ(50)
            .build();
    }

}
