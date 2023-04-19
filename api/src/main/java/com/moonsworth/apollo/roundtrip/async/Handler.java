package com.moonsworth.apollo.roundtrip.async;

@FunctionalInterface
public interface Handler<E> {

    void handle(E response);
}
