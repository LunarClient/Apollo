package com.lunarclient.apollo.roundtrip.async;

import com.lunarclient.apollo.roundtrip.ApolloResponse;

/**
 * Represents a future result of an asynchronous
 * operation invoked when the operation is completed.
 *
 * @param <T> the type of the response object that will be returned
 */
public interface Future<T extends ApolloResponse> {

    /**
     * Registers a success handler to be invoked
     * when the operation completes successfully.
     *
     * @param handler the handler
     */
    void onSuccess(Handler<T> handler);

    /**
     * Registers a failure handler to be invoked
     * when the operation does not complete successfully.
     *
     * @param throwable the throwable
     */
    void onFailure(Handler<Throwable> throwable);
}
