package com.moonsworth.apollo.api.utils;

import com.moonsworth.apollo.api.protocol.Vector3DMessage;

public class Vector3DUtils {
    public static Vector3DMessage fromVector(double x, double y, double z) {
        return Vector3DMessage.newBuilder().setX(x).setY(y).setZ(z).build();
    }

}
