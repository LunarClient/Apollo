package com.moonsworth.apollo.api;

import com.google.protobuf.GeneratedMessageV3;
import com.moonsworth.apollo.api.bridge.ApolloPlayer;
import com.moonsworth.apollo.api.proto.ApolloProtocol;
import com.moonsworth.apollo.api.proto.Msg;
import com.moonsworth.apollo.api.proto.SecondMsg;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

/**
 * Main API class for Apollo.
 */
public class Apollo {

    /**
     * The plugin message channel that Lunar Client and Apollo talk over.
     */
    public static final String PLUGIN_MESSAGE_CHANNEL = "lunarclient:apollo";

    /**
     * The currently loaded platform. This is set as early as possible.
     */
    @Getter
    @Setter
    private static ApolloPlatform platform = null;

    public void withPlayer(Object o, Consumer<ApolloPlayer> consumer) {
        var apolloPlayer = platform.tryWrapPlayer(o);
        if (apolloPlayer != null) {
            consumer.accept(apolloPlayer);
        }
    }

    public void test() throws com.google.protobuf.InvalidProtocolBufferException {
        Msg message = Msg.newBuilder().setFoo("test").setBlah(SecondMsg.newBuilder().setBlah(1).build()).build();
//        message.

        com.moonsworth.apollo.api.proto.Msg.parseFrom(new byte[0]);
    }

}
