package com.moonsworth.apollo.roundtrip.async;

import com.moonsworth.apollo.roundtrip.ApolloResponse;

public interface Future<T extends ApolloResponse> {

    void onSuccess(Handler<T> handler);

    void onFailure(Handler<Throwable> throwable);
}
