package com.moonsworth.apollo.roundtrip.async.future;

import com.moonsworth.apollo.roundtrip.ApolloResponse;
import com.moonsworth.apollo.roundtrip.async.Future;
import com.moonsworth.apollo.roundtrip.async.Handler;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public class UncertainFuture<T extends ApolloResponse> implements Future<T> {

    private Set<Handler<T>> success = new HashSet<>();
    private Set<Handler<Throwable>> fail = new HashSet<>();

    @Override
    public void onSuccess(Handler<T> handler) {
        this.success.add(handler);
    }

    @Override
    public void onFailure(Handler<Throwable> throwable) {
        this.fail.add(throwable);
    }
}
