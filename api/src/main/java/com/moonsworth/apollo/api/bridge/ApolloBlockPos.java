package com.moonsworth.apollo.api.bridge;

import com.moonsworth.apollo.api.protocol.BlockPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApolloBlockPos {
    private int x;
    private int y;
    private int z;

    public BlockPosition toProto() {
        return BlockPosition.newBuilder().setX(x).setY(y).setZ(z).build();
    }
}
