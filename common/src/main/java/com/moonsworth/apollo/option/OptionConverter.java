package com.moonsworth.apollo.option;

import com.google.protobuf.Message;

public interface OptionConverter<I, O extends Message> {

    O to(I object) throws IllegalArgumentException;

    I from(O message) throws IllegalArgumentException;
}
