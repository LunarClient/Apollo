package com.lunarclient.apollo.roundtrip.async;

/**
 * Represents a callback that can be registered with a
 * {@link com.lunarclient.apollo.roundtrip.async.Future}
 * object to be invoked when the associated operation completes.
 *
 * @param <E> the type of the response object that will be handled by the callback
 */
@FunctionalInterface
public interface Handler<E> {

    /**
     * Handles the response object passed to the callback.
     *
     * @param response the object to handle
     */
    void handle(E response);
}
