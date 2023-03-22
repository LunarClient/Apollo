package com.moonsworth.apollo.option;

import com.google.protobuf.Message;

public interface OptionConverter<I, O extends Message> {

    O to(final I object) throws IllegalArgumentException;

    I from(final O message) throws IllegalArgumentException;
}
