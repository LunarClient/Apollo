package com.lunarclient.apollo.roundtrip.async;

import com.lunarclient.apollo.roundtrip.ApolloResponse;

public interface Future<T extends ApolloResponse> {

    void onSuccess(Handler<T> handler);

    void onFailure(Handler<Throwable> throwable);
}
