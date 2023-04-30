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
 * @since 1.0.0
 */
@Getter @Setter
public class UncertainFuture<T extends ApolloResponse> implements Future<T> {

    /**
     * A {@link Set} of success handlers that will be
     * invoked if the operation completes successfully.
     *
     * @since 1.0.0
     */
    private Set<Handler<T>> success = new HashSet<>();

    /**
     * A {@link Set} of failure handlers that will be
     * invoked if the operation does not complete successfully.
     *
     * @since 1.0.0
     */
    private Set<Handler<Throwable>> failure = new HashSet<>();

    /**
     * Registers a success handler to be invoked
     * when the operation completes successfully.
     *
     * @param handler the handler
     * @since 1.0.0
     */
    @Override
    public UncertainFuture<T> onSuccess(Handler<T> handler) {
        this.success.add(handler);
        return this;
    }

    /**
     * Registers a failure handler to be invoked
     * when the operation does not complete successfully.
     *
     * @param throwable the throwable
     * @since 1.0.0
     */
    @Override
    public UncertainFuture<T> onFailure(Handler<Throwable> throwable) {
        this.failure.add(throwable);
        return this;
    }

}
