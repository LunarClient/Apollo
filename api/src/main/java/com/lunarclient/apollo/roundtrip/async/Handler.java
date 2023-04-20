package com.lunarclient.apollo.roundtrip.async;

@FunctionalInterface
public interface Handler<E> {

    void handle(E response);
}
