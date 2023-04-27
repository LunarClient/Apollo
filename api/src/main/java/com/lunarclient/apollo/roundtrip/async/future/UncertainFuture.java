package com.lunarclient.apollo.roundtrip.async.future;

import com.lunarclient.apollo.roundtrip.ApolloResponse;
import com.lunarclient.apollo.roundtrip.async.Future;
import com.lunarclient.apollo.roundtrip.async.Handler;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a {@link Future} result of an asynchronous
 * operation that may or may not complete successfully.
 *
 * @param <T> the type of the response object that will be returned
 */
@Getter @Setter
public class UncertainFuture<T extends ApolloResponse> implements Future<T> {

    /**
     * A {@link Set} of success handlers that will be
     * invoked if the operation completes successfully.
     */
    private Set<Handler<T>> success = new HashSet<>();

    /**
     * A {@link Set} of failure handlers that will be
     * invoked if the operation does not complete successfully.
     */
    private Set<Handler<Throwable>> failure = new HashSet<>();

    /**
     * Registers a success handler to be invoked
     * when the operation completes successfully.
     *
     * @param handler the handler
     */
    @Override
    public void onSuccess(Handler<T> handler) {
        this.success.add(handler);
    }

    /**
     * Registers a failure handler to be invoked
     * when the operation does not complete successfully.
     *
     * @param throwable the throwable
     */
    @Override
    public void onFailure(Handler<Throwable> throwable) {
        this.failure.add(throwable);
    }
}
